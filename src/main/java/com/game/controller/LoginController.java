package com.game.controller;

import com.game.entity.Admin;
import com.game.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@Controller
// @RequestMapping("/auth")
@SessionAttributes({ "username"})
public class LoginController {
	@Autowired
	private LoginService loginService;

	@GetMapping("/")
	public String loginGet(HttpSession session) {
		String username = (String) session.getAttribute("username");
		Boolean isSupervisor = (Boolean) session.getAttribute("isSupervisor");
		Long adminId = (Long) session.getAttribute("adminId");

		// Redirect non-authenticated users to login
		if (username == null || username.isEmpty()) {
			return "redirect:/login";
		}

		// Redirect supervisors to /supervisor
		if (Boolean.TRUE.equals(isSupervisor)) {
			return "redirect:/supervisor" + adminId;
		}

		// Redirect regular admins to /admin/{adminId}
		if (adminId != null) {
			return "redirect:/admin/" + adminId;
		}

		// Default redirect to index for unknown cases
		return "redirect:/index";
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
		if (admin.getId() == null) {
			throw new IllegalStateException("Admin ID is null. Cannot redirect.");
		}
		session.setAttribute("adminId", admin.getId());
		session.setAttribute("username", admin.getUsername());

		if (admin.isSupervisor()) {
			session.setAttribute("isSupervisor", true);
			System.out.println("Supervisor detected, redirecting to /supervisor/" + admin.getId());

			// Correct concatenation
			return "redirect:/supervisor/" + admin.getId();
		} else {
			session.setAttribute("isSupervisor", false);
			System.out.println("Regular admin detected, redirecting to /admin/" + admin.getId());

			return "redirect:/admin/" + admin.getId();
		}
	}

	@PostMapping("/guest-login")
	public String handleGuestLogin(@RequestParam("guestUsername") String guestUsername, Model model, HttpSession session) {
		if (guestUsername == null || guestUsername.trim().isEmpty()) {
			guestUsername = "Guest"; // Default username for guests
		}

		session.setAttribute("username", guestUsername);
		session.setAttribute("isSupervisor", false); // Guests are not supervisors
		session.setAttribute("adminId", null); // No admin ID for guests
		System.out.println("Guest login successful, redirecting to index");

		return "redirect:/index"; // Redirect to the guest dashboard or index
	}

	@GetMapping("/getAllAdmins")
	@ResponseBody // Ensures JSON response for REST-style interaction
	public List<Admin> getAllAdmins() {
		return loginService.getAllAdmins();
	}

	public String validateSupervisorAccess(HttpSession session) {
		String username = (String) session.getAttribute("username");
		Boolean isSupervisor = (Boolean) session.getAttribute("isSupervisor");

		// Redirect non-authenticated users to login
		if (username == null || username.isEmpty()) {
			return "redirect:/login";
		}

		// Redirect regular admins to /admin
		if (Boolean.FALSE.equals(isSupervisor)) {
			Long adminId = (Long) session.getAttribute("adminId");
			return "redirect:/admin/" + adminId;
		}

		// Allow access for supervisors
		return null;
	}
}
