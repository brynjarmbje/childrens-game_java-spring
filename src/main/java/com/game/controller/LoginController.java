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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

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
	public String loginPage(Model model){
		model.addAttribute("username","Guest");
		return "login";
	}

	@PostMapping("/login")
	public String loginPost(Admin admin, Model model, HttpSession session) {
		boolean isAuthenticAdmin = loginService.authenticate(admin);
		if (isAuthenticAdmin) {
			Admin loggedInAdmin = loginService.login(admin);
			model.addAttribute("username", loggedInAdmin);
			session.setAttribute("username", loggedInAdmin);
			ifSupervisor(admin, session);
			// return "LoggedInUser";
			//return "redirect:/letters";
			return "index";
		}
		model.addAttribute("error", "Incorrect credentials");
		return "redirect:/login";
	}

	public String ifSupervisor(Admin admin,HttpSession session) {
		if (admin.isSupervisor()) {
			session.setAttribute("isSupervisor", "true");
			return "redirect:/supervisor";
		}
		return "redirect:/supervisor";
	}

}
