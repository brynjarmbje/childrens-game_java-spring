package com.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.entity.Admin;
import com.game.repository.AdminRepository;

@Service
public class LoginService {
    @Autowired
    private AdminRepository adminRepository;

    public Admin authenticate(String username, String password) {
        Admin admin = adminRepository.findByUsername(username);
        if (admin != null) {
            if (admin.getPassword().equals(password)) {
                return admin;
            }
        }
        return null;
    }
}
