package com.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.entity.Admin;
import com.game.repository.AdminRepository;

import java.util.NoSuchElementException;

@Service
public class LoginService {
	@Autowired
	private static AdminRepository adminRepository;

	public boolean authenticate(Admin testAdmin) {
		Admin repoAdmin = adminRepository.findByUsername(testAdmin.getUsername());
		if (repoAdmin != null) {
			if (repoAdmin.getPassword().equals(testAdmin.getPassword())) {
				return true;
			}
		}
		return false;
	}

	public Admin login(Admin admin) {
		Admin loggedInAdmin = adminRepository.findByUsername(admin.getUsername());
		//if (authenticate(loggedInAdmin)) {
		return loggedInAdmin;
		//}
		//return null;
	}

	public static Admin findAdminById(Long id) {
		return adminRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Admin with ID " + id + " not found"));
	}
}
