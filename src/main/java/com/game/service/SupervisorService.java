package com.game.service;

import com.game.entity.Child;
import com.game.entity.Admin;
import com.game.repository.ChildRepository;
import com.game.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupervisorService {

    @Autowired
    private ChildRepository childRepository;

    @Autowired
    private AdminRepository adminRepository;

    // Create a new Child
    public Child createChild(Child child) {
        return childRepository.save(child);
    }

    // Delete an existing Child
    public void deleteChild(Long id) {
        childRepository.deleteById(id);
    }

    // Get a Child by ID
    public Child getChildById(Long id) {
        return childRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Child not found with id: " + id));
    }

    // Get all Children
    public List<Child> getAllChildren() {
        return childRepository.findAll();
    }

    // Find Children by Name
    public List<Child> getChildrenByName(String name) {
        return childRepository.findByName(name);
    }



    // Create a new Admin
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    // Delete an existing Admin
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    // Get all Admins
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    // Get an Admin by ID
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Admin not found with id: " + id));
    }

}

