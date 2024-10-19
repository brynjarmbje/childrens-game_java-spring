package com.example.game.controller;

import com.example.game.entity.Child;
import com.example.game.entity.Admin;
import com.example.game.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supervisor")
public class SupervisorController {

    @Autowired
    private SupervisorService supervisorService;

    // Create a new Child
    @PostMapping("/child/create")
    public ResponseEntity<Child> createChild(@RequestBody Child child) {
        Child newChild = supervisorService.createChild(child);
        return new ResponseEntity<>(newChild, HttpStatus.CREATED);
    }

    // Delete a Child by ID
    @DeleteMapping("/child/delete/{id}")
    public ResponseEntity<Void> deleteChild(@PathVariable Long id) {
        supervisorService.deleteChild(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Create a new Admin
    @PostMapping("/admin/create")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        Admin newAdmin = supervisorService.createAdmin(admin);
        return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
    }

    // Delete an Admin by ID
    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        supervisorService.deleteAdmin(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
