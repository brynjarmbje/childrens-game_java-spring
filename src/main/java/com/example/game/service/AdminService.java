package com.example.game.service;

import com.example.game.entity.Admin;
import com.example.game.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    // Method to create a new Admin or Supervisor
    public Admin createAdmin(String name, String password, boolean isSupervisor) {
        Admin admin = new Admin(name, password, isSupervisor);
        return adminRepository.save(admin);
    }

    // Get all admins
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    // Find an Admin by ID
    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }

    // Delete an Admin by ID
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    // Custom method to promote an Admin to Supervisor
    public Admin promoteToSupervisor(Long id) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            admin.setSupervisor(true);
            return adminRepository.save(admin);
        }
        return null; // or throw an exception if admin not found
    }
}
