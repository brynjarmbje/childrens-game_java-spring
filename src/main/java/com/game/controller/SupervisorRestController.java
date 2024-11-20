package com.game.controller;

import com.game.entity.Child;
import com.game.entity.Admin;
import com.game.service.SupervisorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supervisor")
public class SupervisorRestController {

    @Autowired
    private SupervisorService supervisorService;

    @Autowired
    private HttpSession session;

    private boolean validateSupervisorSession() {
        Boolean isSupervisor = (Boolean) session.getAttribute("isSupervisor");
        return isSupervisor == null || !isSupervisor;
    }

    @PostMapping("/update-session")
    public ResponseEntity<String> updateSessionAttributes(@RequestParam String username) {
        session.setAttribute("username", username);
        session.setAttribute("isSupervisor", true); // Example update
        return new ResponseEntity<>("Session updated successfully", HttpStatus.OK);
    }

    // Create a new Child
    @PostMapping("/child/create")
    public ResponseEntity<Child> createChild(@RequestBody Child child, @RequestParam Long supervisorId) {
        if (validateSupervisorSession()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if (child == null || supervisorId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Child newChild = supervisorService.createChild(child, supervisorId);
        return new ResponseEntity<>(newChild, HttpStatus.CREATED);
    }

    // Delete a Child by ID
    @DeleteMapping("/child/delete/{id}")
    public ResponseEntity<Void> deleteChild(@PathVariable Long id) {
        if (!validateSupervisorSession()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        supervisorService.deleteChild(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // List all Children
    @GetMapping("/children")
    public ResponseEntity<List<Child>> getAllChildren() {
        if (validateSupervisorSession()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN); // Reject if not supervisor
        }
        List<Child> children = supervisorService.getAllChildren();
        return new ResponseEntity<>(children, HttpStatus.OK);
    }

    // Create a new Admin
    @PostMapping("/admin/create")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin, @RequestParam Long supervisorId) {
        if (validateSupervisorSession()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN); // Ensure only supervisors can create admins
        }
        if (admin == null || supervisorId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Admin newAdmin = supervisorService.createAdmin(admin, supervisorId);
        return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
    }

    // Delete an Admin by ID
    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        if (!validateSupervisorSession()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        supervisorService.deleteAdmin(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // List all Admins
    @GetMapping("/admins")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = supervisorService.getAllAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }
}


