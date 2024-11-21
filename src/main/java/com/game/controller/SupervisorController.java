package com.game.controller;

import com.game.entity.Child;
import com.game.entity.Admin;
import com.game.service.SupervisorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/supervisor")
@SessionAttributes({"username", "isSupervisor", "adminId"})
public class SupervisorController {

    @Autowired
    private SupervisorService supervisorService;

    private static final String DEFAULT_USERNAME = "gestur";
    private static final boolean DEFAULT_IS_SUPERVISOR = false;
    private static final long DEFAULT_ADMIN_ID = 0L;

    private void ensureSessionAttributes(HttpSession session) {
        if (session.getAttribute("username") == null) {
            session.setAttribute("username", DEFAULT_USERNAME);
        }
        if (session.getAttribute("isSupervisor") == null) {
            session.setAttribute("isSupervisor", DEFAULT_IS_SUPERVISOR);
        }
        if (session.getAttribute("adminId") == null) {
            session.setAttribute("adminId", DEFAULT_ADMIN_ID);
        }
    }

    // Display supervisor page with children and admins
    @GetMapping("/{adminId}")
    public String supervisorPage(@PathVariable Long adminId, HttpSession session, Model model) {
        ensureSessionAttributes(session);
        model.addAttribute("adminId", adminId); // Add adminId to the model

        List<Child> children = supervisorService.getAllChildrenInSameSchool(adminId);
        List<Admin> admins = supervisorService.getAllAdminsInSameSchool(adminId);
        String schoolName = supervisorService.getSchoolNameByAdminId(adminId);
        model.addAttribute("schoolName", schoolName);
        model.addAttribute("children", children);
        model.addAttribute("admins", admins);

        return "supervisor";
    }

    // Create a new Child (Form submission)
    @PostMapping("/child/create")
    public String createChild(@RequestParam Long adminId, @ModelAttribute Child child, Model model) {
        supervisorService.createChild(child, adminId);

        // Redirect to refresh the list of children displayed on the supervisor page
        return "redirect:/supervisor/" + adminId;
    }

    // Delete a Child by ID (Form submission)
    @PostMapping("/child/delete")
    public String deleteChild(@RequestParam Long adminId, @RequestParam Long id, Model model) {
        if (id == null || id <= 0) {
            // Handle invalid or missing ID
            model.addAttribute("error", "Invalid child ID.");
            return "redirect:/supervisor/" + adminId;
        }

        supervisorService.deleteChild(id);

        // Redirect to refresh the list of children displayed on the supervisor page
        return "redirect:/supervisor/" + adminId;
    }

    // Create a new Admin (Form submission)
    @PostMapping("/admin/create")
    public String createAdmin(@RequestParam Long adminId, @ModelAttribute Admin admin, Model model) {
        supervisorService.createAdmin(admin, adminId);

        // Redirect to refresh the list of admins displayed on the supervisor page
        return "redirect:/supervisor/" + adminId;
    }

    // Delete an Admin by ID (Form submission)
    @PostMapping("/admin/delete")
    public String deleteAdmin(@RequestParam Long adminId, @RequestParam Long id, Model model) {
        if (id == null || id <= 0) {
            // Handle invalid or missing ID
            model.addAttribute("error", "Invalid admin ID.");
            return "redirect:/supervisor/" + adminId;
        }
        supervisorService.deleteAdmin(id);

        // Redirect to refresh the list of admins displayed on the supervisor page
        return "redirect:/supervisor/" + adminId;
    }

}

