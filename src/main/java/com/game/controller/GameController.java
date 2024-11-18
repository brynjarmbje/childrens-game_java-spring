package com.game.controller;

import com.game.entity.Admin;
import com.game.entity.Game;
import com.game.entity.Question;
import com.game.service.GameService;
import com.game.service.MemoryGameService;
import com.game.service.MatchingGameService;
import com.game.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
@SessionAttributes({ "game" })
public class GameController {

	@Autowired
	private GameService gameService;

	@Autowired
	private QuestionService questionService;

	@ModelAttribute("game")
	public Game getGame() {
		return new Game();
	}

	@Autowired
	private MatchingGameService matchingGameService;

	@Autowired
	private MemoryGameService memoryGameService;

	@GetMapping("/memory-game")
	public String memoryGame(Model model) {
		model.addAttribute("cards", memoryGameService.getCards());
		return "memory-game"; // Ensure this matches the name of your Thymeleaf template
	}

	@PostMapping("/memory-game/flip")
	public String flipCard(@RequestParam int id, Model model) {
		memoryGameService.flipCard(id);
		model.addAttribute("cards", memoryGameService.getCards());
		if (memoryGameService.isGameComplete()) {
			model.addAttribute("gameComplete", true);
		}
		return "memory-game"; // Return the updated state to the same template
	}

	@PostMapping("/memory-game/reset")
	public String resetGame() {
		memoryGameService.resetGame();
		return "redirect:/memory-game";
	}

	@GetMapping("/matching-game")
	public String matchingGame(Model model) {
		if (matchingGameService.getIcons() == null || matchingGameService.getIcons().isEmpty()) {
			matchingGameService.initializeGame();
		}
		model.addAttribute("icons", matchingGameService.getIcons());
		model.addAttribute("letters", matchingGameService.getLetters());
		return "matching-game";
	}

	@PostMapping("/matching-game/match")
	@ResponseBody // Ensure JSON response for AJAX
	public Map<String, Boolean> matchIcon(@RequestParam int iconId, @RequestParam char letter) {
		boolean isMatch = matchingGameService.checkMatch(iconId, letter);
		Map<String, Boolean> response = new HashMap<>();
		response.put("isMatch", isMatch);
		return response;
	}

	@PostMapping("/matching-game/reset")
	public String resetMatchingGame() {
		matchingGameService.initializeGame();
		return "redirect:/matching-game";
	}
}

