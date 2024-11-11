package com.game.controller;

import com.game.entity.Child;
import com.game.entity.Admin;
import com.game.service.SupervisorService;
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

    // List all Children
    @GetMapping("/children")
    public ResponseEntity<List<Child>> getAllChildren() {
        List<Child> children = supervisorService.getAllChildren();
        return new ResponseEntity<>(children, HttpStatus.OK);
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

    // List all Admins
    @GetMapping("/admins")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = supervisorService.getAllAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }
}


