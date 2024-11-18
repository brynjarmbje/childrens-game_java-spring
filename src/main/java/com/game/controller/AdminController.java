package com.game.controller;

import com.game.entity.Child;
import com.game.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private SupervisorService supervisorService;

    // List to store the current group of selected children
    private List<Child> group = new ArrayList<>();

    /**
     * Handles GET requests to the /admin endpoint.
     * Loads the admin page with a list of children and the current group.
     *
     * @param model the model to populate with data for the view
     * @return the admin page template
     */
    @GetMapping
    public String adminPage(Model model) {
        // Fetch all children from the SupervisorService
        List<Child> children = supervisorService.getAllChildren();

        // Add children and group data to the model
        model.addAttribute("children", children);
        model.addAttribute("group", group.isEmpty() ? null : group);

        return "admin"; // Return the admin.html template
    }

    /**
     * Handles POST requests to add selected children to the group.
     *
     * @param childrenIds the list of IDs of selected children
     * @param model       the model to populate with feedback messages
     * @return a redirect to the admin page
     */
    @PostMapping("/add-to-group")
    public String addToGroup(@RequestParam List<Long> childrenIds, Model model) {
        try {
            // Loop through the provided children IDs
            for (Long id : childrenIds) {
                Child child = supervisorService.getChildById(id); // Fetch the child by ID
                if (!group.contains(child)) {
                    group.add(child); // Add to the group if not already present
                }
            }
            model.addAttribute("success", "Children added successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "Failed to add children to the group: " + e.getMessage());
            e.printStackTrace(); // Log the error for debugging
        }
        return "redirect:/admin"; // Redirect back to the admin page
    }

    /**
     * Handles POST requests to clear the current group of children.
     *
     * @return a redirect to the admin page
     */
    @PostMapping("/clear-group")
    public String clearGroup() {
        group.clear(); // Clear the group list
        return "redirect:/admin"; // Redirect back to the admin page
    }
}
