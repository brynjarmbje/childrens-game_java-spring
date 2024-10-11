package com.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.service.LoginService;
import com.login.entity.LoginRequests;

@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequests loginRequests) {
        boolean isAuthenticated = loginService.authenticate(loginRequests.getUsername(),loginRequests.getpassword());//TODO:Nota authenticate frá service

        if (isAuthenticated) {
            return ResponseEntity.ok("Login successfull");
        } else { 
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"); }
    }

}
