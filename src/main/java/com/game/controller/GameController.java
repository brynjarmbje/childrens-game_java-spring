package com.game.controller;

import com.game.entity.Child;
import com.game.entity.Game;
import com.game.entity.Question;
import com.game.entity.Session;
import com.game.service.*;
import jakarta.servlet.http.HttpSession;
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

	@Autowired
	private SessionService sessionService;

	private static final int LETTERS_MIN_ID = 41;
	private static final int LETTERS_MAX_ID = 72;

	@GetMapping("/letters")
	public String startLettersGame(HttpSession httpSession, Model model) {
		// Retrieve the selected child's session
		Child selectedChild = (Child) httpSession.getAttribute("selectedChild");
		if (selectedChild == null) {
			return "redirect:/"; // Redirect to home if no child is selected
		}

		Session session = sessionService.findOrCreateSessionForChild(selectedChild);

		// Fetch all IDs for the letters game
		List<Long> allQuestionIds = questionService.getQuestionIdsInRange(LETTERS_MIN_ID, LETTERS_MAX_ID);

		// Generate a random question for the current level
		Question question = gameService.generateRandomQuestion(session, allQuestionIds);

		// Generate IDs for wrong options
		List<Long> wrongOptionIds = gameService.generateWrongOptions(question.getId(), session, allQuestionIds);

		// Combine correct and wrong option IDs into one list
		List<Long> optionIds = new ArrayList<>(wrongOptionIds);
		optionIds.add(question.getId()); // Add correct option
		Collections.shuffle(optionIds); // Randomize the order

		// Pass data to the model
		model.addAttribute("correctId", question.getId());
		model.addAttribute("optionIds", optionIds);
		model.addAttribute("level", session.getLevel());

		return "letters"; // This loads letters.html
	}


//	private static final Session NUMBERS_MIN_ID = 1;
//	private static final int NUMBERS_MAX_ID = 40;
//
//	@GetMapping("/numbers")
//	public String startNumbersGame(Model model) {
//		// Generate a random question for the numbers game
//		Question question = gameService.generateRandomQuestion(NUMBERS_MIN_ID, NUMBERS_MAX_ID);
//
//		// Generate IDs for wrong options
//		List<Long> wrongOptionIds = gameService.generateWrongOptions(question.getId(), NUMBERS_MIN_ID, NUMBERS_MAX_ID);
//
//		// Combine correct and wrong option IDs into one list
//		List<Long> optionIds = new ArrayList<>(wrongOptionIds);
//		optionIds.add(question.getId()); // Add correct option
//		Collections.shuffle(optionIds); // Randomize the order
//
//		// Pass data to the model
//		model.addAttribute("correctId", question.getId());
//		model.addAttribute("optionIds", optionIds);
//
//		return "numbers"; // This loads numbers.html
//	}
//
//	private static final int LOCATE_MIN_ID = 100;
//	private static final int LOCATE_MAX_ID = 135;
//
//	@GetMapping("/locate-game")
//	public String startLocateGame(Model model) {
//		// Generate a random question for the numbers game
//		Question question = gameService.generateRandomQuestion(LOCATE_MIN_ID, LOCATE_MAX_ID);
//
//		// Generate IDs for wrong options
//		List<Long> wrongOptionIds = gameService.generateWrongOptions(question.getId(), LOCATE_MIN_ID, LOCATE_MAX_ID);
//
//		// Combine correct and wrong option IDs into one list
//		List<Long> optionIds = new ArrayList<>(wrongOptionIds);
//		optionIds.add(question.getId()); // Add correct option
//		Collections.shuffle(optionIds); // Randomize the order
//
//		// Pass data to the model
//		model.addAttribute("correctId", question.getId());
//		model.addAttribute("optionIds", optionIds);
//
//		return "locate-game"; // This loads numbers.html
//	}

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

