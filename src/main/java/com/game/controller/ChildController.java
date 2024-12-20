package com.game.controller;

import com.game.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.game.entity.Child;
import com.game.repository.AdminRepository;
import com.game.service.AdminService;
import com.game.service.GameService;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/admin/{adminId}/child/")
@SessionAttributes("username")
public class ChildController {
	@Autowired
	private AdminService adminService;

	@Autowired
	private GameService gameService;

	/**
	 * Handles GET requests to the /child/{childId} endpoint.
	 * Loads the select game page with a list of available games.
	 *
	 * @param childId The ID of the selected child.
	 * @param model   The model to populate with data for the view.
	 * @return The select game page template.
	 */
	@GetMapping("/{childId}")
	public String selectGamePage(@PathVariable Long childId,@PathVariable Long adminId  ,Model model) {
		try {
			Child child = adminService.getChildById(childId);

			String childName = child.getName();

			// Add adminId and childName to the model
			model.addAttribute("adminId", adminId);
			model.addAttribute("childName", childName);
			model.addAttribute("childId", childId);
			return "child";
		} catch (Exception e) {
			// Handle errors gracefully
			e.printStackTrace();
			model.addAttribute("error", "Failed to load data for child: " + e.getMessage());
			return "error"; // Use a valid error page
		}
	}

	/**
	 * Handles GET requests to the game endpoint
	 * Loads the selected game.
	 *
	 * @param childId  The ID of the selected child.
	 * @param gameType The ID of the selected child.
	 * @param model    The model to populate with data for the view.
	 * @return The select game page template.
	 */
	// @GetMapping("/{childId}/playgame")
	// public String playGame(@PathVariable Long childId, @RequestParam int
	// gametype, Model model) {
	// try {
	//
	// // TODO: get level and progress of child
	// // and add as model attribute
	// // model.addAttribute("gameType", gameType);
	// // model.addAttribute("gamelevel", gameLevel);
	//
	// return "child";
	// } catch (Exception e) {
	// // Handle errors gracefully
	// e.printStackTrace();
	// model.addAttribute("error", "Failed to load data for the selected game: " +
	// e.getMessage());
	// return "error"; // Use a valid error page
	// }
	// }

	@GetMapping("/letters")
	public String playGameLetters(@PathVariable Long childId,@PathVariable Long adminId, Model model) {
		try {
			int gameType = 1;
			model.addAttribute("gameType", gameType);
			// TODO: get level and progress of child
			// and add as model attribute
			// model.addAttribute("gamelevel", gameLevel);

			return "redirect:/admin/" + adminId + "/child/" + childId + "/letters";
		} catch (Exception e) {
			// Handle errors gracefully
			e.printStackTrace();
			model.addAttribute("error", "Failed to load data for the selected game: " + e.getMessage());
			return "error"; // Use a valid error page
		}
	}

	@GetMapping("/numbers")
	public String playGameNumbers(@PathVariable Long childId,@PathVariable Long adminId, Model model) {
		try {
			int gameType = 2;
			model.addAttribute("gameType", gameType);
			// TODO: get level and progress of child
			// and add as model attribute
			// model.addAttribute("gamelevel", gameLevel);

			return "redirect:/admin/" + adminId + "/child/" + childId + "/numbers";
		} catch (Exception e) {
			// Handle errors gracefully
			e.printStackTrace();
			model.addAttribute("error", "Failed to load data for the selected game: " + e.getMessage());
			return "error"; // Use a valid error page
		}
	}

	@GetMapping("/memory-game")
	public String playGameMemory(@PathVariable Long childId,@PathVariable Long adminId, Model model) {
		try {
			int gameType = 3;
			model.addAttribute("gameType", gameType);
			// TODO: get level and progress of child
			// and add as model attribute
			// model.addAttribute("gamelevel", gameLevel);

			return "redirect:/admin/" + adminId + "/child/" + childId + "/memory-game";
		} catch (Exception e) {
			// Handle errors gracefully
			e.printStackTrace();
			model.addAttribute("error", "Failed to load data for the selected game: " + e.getMessage());
			return "error"; // Use a valid error page
		}
	}

	@GetMapping("/matching-game")
	public String playGameMatching(@PathVariable Long childId,@PathVariable Long adminId, Model model) {
		try {
			int gameType = 4;
			model.addAttribute("gameType", gameType);
			// TODO: get level and progress of child
			// and add as model attribute
			// model.addAttribute("gamelevel", gameLevel);

			return "redirect:/admin/" + adminId + "/child/" + childId + "/matching-game";
		} catch (Exception e) {
			// Handle errors gracefully
			e.printStackTrace();
			model.addAttribute("error", "Failed to load data for the selected game: " + e.getMessage());
			return "error"; // Use a valid error page
		}
	}

	@GetMapping("/locate-game")
	public String playGameLocate(@PathVariable Long childId,@PathVariable Long adminId, Model model) {
		try {
			int gameType = 5;
			model.addAttribute("gameType", gameType);
			// TODO: get level and progress of child
			// and add as model attribute
			// model.addAttribute("gamelevel", gameLevel);

			return "redirect:/admin/" + adminId + "/child/" + childId + "/locate-game";
		} catch (Exception e) {
			// Handle errors gracefully
			e.printStackTrace();
			model.addAttribute("error", "Failed to load data for the selected game: " + e.getMessage());
			return "error"; // Use a valid error page
		}
	}
}
