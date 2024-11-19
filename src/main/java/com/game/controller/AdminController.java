package com.game.controller;

import com.game.entity.Child;
import com.game.service.AdminService;
import com.game.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * Handles GET requests to the /admin/{adminId} endpoint.
     * Loads the admin page with a list of managed and available children.
     *
     * @param adminId The ID of the admin.
     * @param model   The model to populate with data for the view.
     * @return The admin page template.
     */
    @GetMapping("/{adminId}")
    public String adminPage(@PathVariable Long adminId, Model model) {
        model.addAttribute("adminId", adminId);
        try {
            // Get children managed by the admin
            List<Child> managedChildren = adminService.getChildrenManagedByAdmin(adminId);

            // Get all children and filter out those already managed
            List<Child> allChildren = adminService.getAllChildren();
            allChildren.removeAll(managedChildren);

            // Add data to the model
            model.addAttribute("managedChildren", managedChildren);
            model.addAttribute("availableChildren", allChildren);

            return "admin"; // Return the admin.html template
        } catch (Exception e) {
            // Handle errors gracefully
            e.printStackTrace();
            model.addAttribute("error", "Failed to load data for admin: " + e.getMessage());
            return "error"; // Use a valid error page
        }
    }

    /**
     * Handles POST requests to add a child to the admin's group.
     *
     * @param adminId The ID of the admin.
     * @param childId The ID of the child to add.
     * @param model   The model to populate with feedback messages.
     * @return A redirect to the admin page.
     */
    @PostMapping("/{adminId}/add-child")
    public String addChildToGroup(@PathVariable Long adminId, @RequestParam Long childId, Model model) {
        try {
            adminService.addChildToAdmin(adminId, childId);
            model.addAttribute("success", "Child added to the group successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "Failed to add child to the group: " + e.getMessage());
        }
        return "redirect:/admin/" + adminId;
    }

    /**
     * Handles POST requests to remove a specific child from the admin's group.
     *
     * @param adminId The ID of the admin.
     * @param childId The ID of the child to remove.
     * @param model   The model to populate with feedback messages.
     * @return A redirect to the admin page.
     */
    @PostMapping("/{adminId}/remove-child")
    public String removeChildFromGroup(@PathVariable Long adminId, @RequestParam Long childId, Model model) {
        try {
            adminService.removeChildFromAdmin(adminId, childId);
            model.addAttribute("success", "Child removed from the group successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "Failed to remove child from the group: " + e.getMessage());
        }
        return "redirect:/admin/" + adminId;
    }

    /**
     * Handles POST requests to clear all children from the admin's group.
     *
     * @param adminId The ID of the admin.
     * @param model   The model to populate with feedback messages.
     * @return A redirect to the admin page.
     */
    @PostMapping("/{adminId}/clear-group")
    public String clearGroup(@PathVariable Long adminId, Model model) {
        try {
            adminService.clearChildrenFromAdmin(adminId);
            model.addAttribute("success", "Group cleared successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "Failed to clear the group: " + e.getMessage());
        }
        return "redirect:/admin/" + adminId;
    }

    /**
     * Redirects to the login page if the admin ID is missing.
     *
     * @return Redirect to the login page.
     */
    @GetMapping
    public String missingAdminIdRedirect() {
        return "redirect:/login";
    }
}