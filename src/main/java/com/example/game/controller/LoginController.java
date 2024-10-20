package com.example.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.game.service.LoginService;
import com.example.game.entity.LoginRequest;


@RestController
@RequestMapping("/auth")
public class LoginController {
	@Autowired
	private LoginService loginService;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequests) {
		boolean isAuthenticated = loginService.authenticate(loginRequests.getUsername(), loginRequests.getpassword());
		if (isAuthenticated) {
			return ResponseEntity.ok("Login successfull");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login");
		}
	}

}
