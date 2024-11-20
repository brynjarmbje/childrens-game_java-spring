package com.game.controller;

import com.game.entity.Admin;
import com.game.entity.Game;
import com.game.entity.Image;
import com.game.entity.Question;
import com.game.model.Card;
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
	private static final int LETTERS_MAX_ID = 72;

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
		memoryGameService.initializeGame();
		model.addAttribute("cards", memoryGameService.getCards());
		return "memory-game"; // Loads memory-game.html
	}

	@PostMapping("/memory-game/flip")
	@ResponseBody
	public Map<String, Object> flipCard(@RequestParam int id) {
		memoryGameService.flipCard(id);

		// Always return the updated state of the game
		Map<String, Object> response = new HashMap<>();
		response.put("cards", memoryGameService.getCards());
		response.put("gameComplete", memoryGameService.isGameComplete());
		return response;
	}


	@PostMapping("/memory-game/reset")
	public String resetGame() {
		memoryGameService.resetGame();
		return "redirect:/memory-game";
	}

	@GetMapping("/matching-game")
	public String matchingGame(Model model) {
		matchingGameService.initializeGame();
		model.addAttribute("imageCards", matchingGameService.getImageCards());
		model.addAttribute("letterCards", matchingGameService.getLetterCards());
		return "matching-game"; // Load matching-game.html
	}

}

