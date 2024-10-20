package com.example.game.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.game.entity.Admin;
import com.example.game.repository.AdminRepository;

@Service
public class LoginService {
	public boolean authenticate(String username, String password) {
		Optional<Admin> userByUsername = AdminRepository.findByUsername(username); // TODO: fix findByUsername
		if (userByUsername.isPresent()) {
			Admin admin = userByUsername.get();
			return admin.getPassword().equals(password);
		} else {
			return false;
		}
	}
}
