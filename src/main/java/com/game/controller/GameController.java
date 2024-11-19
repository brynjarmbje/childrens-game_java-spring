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

import java.util.*;

@Controller
@SessionAttributes({ "game" })
public class GameController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private GameService gameService;

	private static final int LETTERS_MIN_ID = 41;
	private static final int LETTERS_MAX_ID = 70;

	@GetMapping("/letters")
	public String startLettersGame(Model model) {
		// Generate a random question for the letters game
		Question question = gameService.generateRandomQuestion(LETTERS_MIN_ID, LETTERS_MAX_ID);

		// Generate IDs for wrong options
		List<Long> wrongOptionIds = gameService.generateWrongOptions(question.getId(), LETTERS_MIN_ID, LETTERS_MAX_ID);

		// Combine correct and wrong option IDs into one list
		List<Long> optionIds = new ArrayList<>(wrongOptionIds);
		optionIds.add(question.getId()); // Add correct option
		Collections.shuffle(optionIds); // Randomize the order

		// Pass data to the model
		model.addAttribute("correctId", question.getId());
		model.addAttribute("optionIds", optionIds);

		return "letters"; // This loads letters.html
	}

	private static final int NUMBERS_MIN_ID = 1;
	private static final int NUMBERS_MAX_ID = 40;

	@GetMapping("/numbers")
	public String startNumbersGame(Model model) {
		// Generate a random question for the numbers game
		Question question = gameService.generateRandomQuestion(NUMBERS_MIN_ID, NUMBERS_MAX_ID);

		// Generate IDs for wrong options
		List<Long> wrongOptionIds = gameService.generateWrongOptions(question.getId(), NUMBERS_MIN_ID, NUMBERS_MAX_ID);

		// Combine correct and wrong option IDs into one list
		List<Long> optionIds = new ArrayList<>(wrongOptionIds);
		optionIds.add(question.getId()); // Add correct option
		Collections.shuffle(optionIds); // Randomize the order

		// Pass data to the model
		model.addAttribute("correctId", question.getId());
		model.addAttribute("optionIds", optionIds);

		return "numbers"; // This loads numbers.html
	}

	private static final int LOCATE_MIN_ID = 100;
	private static final int LOCATE_MAX_ID = 135;

	@GetMapping("/locate-game")
	public String startLocateGame(Model model) {
		// Generate a random question for the numbers game
		Question question = gameService.generateRandomQuestion(LOCATE_MIN_ID, LOCATE_MAX_ID);

		// Generate IDs for wrong options
		List<Long> wrongOptionIds = gameService.generateWrongOptions(question.getId(), LOCATE_MIN_ID, LOCATE_MAX_ID);

		// Combine correct and wrong option IDs into one list
		List<Long> optionIds = new ArrayList<>(wrongOptionIds);
		optionIds.add(question.getId()); // Add correct option
		Collections.shuffle(optionIds); // Randomize the order

		// Pass data to the model
		model.addAttribute("correctId", question.getId());
		model.addAttribute("optionIds", optionIds);

		return "locate-game"; // This loads numbers.html
	}

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

