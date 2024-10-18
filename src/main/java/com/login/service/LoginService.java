package com.login.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.persistance.repositories.User;
import com.login.persistance.repositories.UserRepository;

@Service
public class LoginService {
	public boolean authenticate(String username, String password) {
		Optional<User> userByUsername = UserRepository.findByUsername(username); // get userdata from database
		if (userByUsername.isPresent()) {
			User user = userByUsername.get();
			return user.getPassword().equals(password);
		} else {
			return false;
		}
	}
}
