package com.game.controller;

import com.game.entity.Child;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.game.service.LoginService;

import jakarta.servlet.http.HttpSession;

import com.game.entity.Admin;

import java.util.List;

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

	@GetMapping("/index")
	public String showIndexPage() {
		return "index"; // This should correspond to an `index.html` template
	}

	@PostMapping("/login")
	public String loginPost(Admin admin, Model model, HttpSession session) {
		boolean isAuthenticAdmin = loginService.authenticate(admin);
		if (isAuthenticAdmin) {
			Admin loggedInAdmin = loginService.login(admin);
			model.addAttribute("username", loggedInAdmin.getUsername());
			session.setAttribute("username", loggedInAdmin.getUsername()); // Save username instead of whole object
			return ifSupervisor(loggedInAdmin, session); // Return redirect URL based on supervisor status
		}
		System.out.println("Admin login failed");
		model.addAttribute("error", "Incorrect credentials");
		return "redirect:/login";
	}

	public String ifSupervisor(Admin admin, HttpSession session) {
		if (admin.isSupervisor()) { // Corrected method call
			session.setAttribute("isSupervisor", "true");
			System.out.println("Supervisor detected, redirecting to /supervisor");
			return "redirect:/supervisor";
		}
		System.out.println("Regular admin detected, redirecting to /index");
		return "redirect:/index";
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
	@GetMapping("/testAdmin")
	public String testAdmin() {
		// Fetch the Admin entity with ID 1
		Admin admin = LoginService.findAdminById(1L);

		if (admin == null) {
			throw new RuntimeException("Admin with ID 1 not found");
		}

		// Print admin details
		System.out.println("Admin Details:");
		System.out.println("ID: " + admin.getId());
		System.out.println("Username: " + admin.getUsername());
		System.out.println("Password: " + admin.getPassword());
		System.out.println("Is Supervisor: " + admin.isSupervisor());
		System.out.println("School: " + admin.getSchool());

		// Return a view name
		return "testAdmin";
	}

}
