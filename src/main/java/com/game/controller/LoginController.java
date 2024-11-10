package com.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.game.service.LoginService;

import jakarta.servlet.http.HttpSession;

import com.game.entity.Admin;
import com.game.entity.LoginRequest;

//@RestController
@Controller
// @RequestMapping("/auth")
public class LoginController {
	@Autowired
	private LoginService loginService;

	@GetMapping("/")
	public String loginGet(Admin admin) {
		return "login";
	}

	@PostMapping("/")
	public String loginPost(Admin admin, Model model, HttpSession session) {
		boolean isAuthenticAdmin = loginService.authenticate(admin);
		if (isAuthenticAdmin) {
			Admin loggedInAdmin = loginService.login(admin);
			model.addAttribute("LoggedInUser", loggedInAdmin);
			session.setAttribute("LoggedInUser", loggedInAdmin);
			// return "LoggedInUser";
			return "redirect:/letters";
		}
		model.addAttribute("error", "Incorrect credentials");
		return "redirect:/";
	}

	public String ifSupervisor(Admin admin) {
		if (admin.isSupervisor()) {
			return "redirect:/supervisor";
		}
		return "redirect:/supervisor";
	}

}
