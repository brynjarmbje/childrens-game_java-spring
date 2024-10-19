package com.example.game.controller;

import com.example.game.entity.Admin;
import com.example.game.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class SupervisorController {

    @Autowired
    private AdminService adminService;

    // Create a new Admin
    @PostMapping("/create")
    public ResponseEntity<Admin> createAdmin(@RequestParam String name, @RequestParam String password, @RequestParam boolean isSupervisor) {
        Admin admin = adminService.createAdmin(name, password, isSupervisor);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

    // Get all Admins
    @GetMapping("/all")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    // Promote an Admin to Supervisor
    @PutMapping("/promote/{id}")
    public ResponseEntity<Admin> promoteToSupervisor(@PathVariable Long id) {
        Admin updatedAdmin = adminService.promoteToSupervisor(id);
        if (updatedAdmin != null) {
            return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete an Admin by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
