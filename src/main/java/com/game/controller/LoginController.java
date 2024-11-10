package com.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.game.service.LoginService;

import jakarta.servlet.http.HttpSession;

import com.game.entity.Admin;

//@RestController
@Controller
// @RequestMapping("/auth")
@SessionAttributes({ "username"})
public class LoginController {
	@Autowired
	private LoginService loginService;

	@GetMapping("/")
	public String loginGet(@SessionAttribute(value = "username", required = false) String username) {
		if (username == null || username.isEmpty()){
			return "redirect:/login";
		}
		return "index";
	}
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("admin", new Admin());
		model.addAttribute("username", "Guest");
		return "login";
	}


	@PostMapping("/login")
	public String loginPost(@ModelAttribute("admin") Admin admin, Model model, HttpSession session) {
		boolean isAuthenticAdmin = loginService.authenticate(admin);
		if (isAuthenticAdmin) {
			Admin loggedInAdmin = loginService.login(admin);
			model.addAttribute("username", loggedInAdmin.getUsername()); // Use appropriate attribute
			session.setAttribute("username", loggedInAdmin.getUsername()); // Store only the username in session
			return ifSupervisor(loggedInAdmin, session); // Redirect based on supervisor status
		}
		model.addAttribute("error", "Incorrect credentials");
		return "login";
	}

	@PostMapping("/guest-login")
	public String handleGuestLogin(@RequestParam("guestUsername") String guestUsername, Model model, HttpSession session) {
		// Set a default username for guest users
		if (guestUsername == null || guestUsername.trim().isEmpty()) {
			guestUsername = "Guest";
		}

		model.addAttribute("username", guestUsername);
		session.setAttribute("username", guestUsername);

		// Redirect to index or any other page as needed
		return "index"; // Or "redirect:/" based on your application structure
	}

	public String ifSupervisor(Admin admin,HttpSession session) {
		if (admin.isSupervisor()) {
			session.setAttribute("isSupervisor", "true");
			return "redirect:/supervisor";
		}
		return "redirect:/index";
	}

}
