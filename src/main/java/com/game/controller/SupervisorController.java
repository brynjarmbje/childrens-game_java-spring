package com.game.controller;

import com.game.entity.Child;
import com.game.entity.Admin;
import com.game.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/supervisor")
public class SupervisorController {

    @Autowired
    private SupervisorService supervisorService;

    // Display supervisor page with children and admins
    @GetMapping
    public String supervisorPage(Model model) {
        // Retrieve all children and admins from the service layer
        List<Child> children = supervisorService.getAllChildren();
        List<Admin> admins = supervisorService.getAllAdmins();

        // Add the retrieved data to the model
        model.addAttribute("children", children);
        model.addAttribute("admins", admins);

        // Return the view name (assuming it corresponds to a `supervisor.html` template)
        return "supervisor";
    }

    // Create a new Child (Form submission)
    @PostMapping("/child/create")
    public String createChild(@ModelAttribute Child child, Model model) {
        supervisorService.createChild(child);

        // Redirect to refresh the list of children displayed on the supervisor page
        return "redirect:/supervisor";
    }

    // Delete a Child by ID (Form submission)
    @PostMapping("/child/delete")
    public String deleteChild(@RequestParam Long id, Model model) {
        if (id == null || id <= 0) {
            // Handle invalid or missing ID
            model.addAttribute("error", "Invalid child ID.");
            return "redirect:/supervisor";
        }

        supervisorService.deleteChild(id);

        // Redirect to refresh the list of children displayed on the supervisor page
        return "redirect:/supervisor";
    }

    // Create a new Admin (Form submission)
    @PostMapping("/admin/create")
    public String createAdmin(@ModelAttribute Admin admin, Model model) {
        supervisorService.createAdmin(admin);

        // Redirect to refresh the list of admins displayed on the supervisor page
        return "redirect:/supervisor";
    }

    // Delete an Admin by ID (Form submission)
    @PostMapping("/admin/delete")
    public String deleteAdmin(@RequestParam Long id, Model model) {
        if (id == null || id <= 0) {
            // Handle invalid or missing ID
            model.addAttribute("error", "Invalid admin ID.");
            return "redirect:/supervisor";
        }
        supervisorService.deleteAdmin(id);

        // Redirect to refresh the list of admins displayed on the supervisor page
        return "redirect:/supervisor";
    }
}

