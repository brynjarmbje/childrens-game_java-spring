package com.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.entity.Admin;
import com.game.repository.AdminRepository;

@Service
public class LoginService {
	@Autowired
	private AdminRepository adminRepository;

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
}
