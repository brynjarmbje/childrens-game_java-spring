package com.game.controller;

import com.game.data.ImageHandler;
import com.game.entity.Admin;
import com.game.entity.Game;
import com.game.entity.Image;
import com.game.entity.Question;
import com.game.errors.QuestionNotFoundException;
import com.game.service.GameService;
import com.game.service.MemoryGameService;
import com.game.service.MatchingGameService;
import com.game.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Blob;
import java.sql.SQLException;
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

	@PostMapping("/check")
	public String checkAnswer(@RequestParam("answer") String answer, @ModelAttribute("game") Game game, Model model) {
		boolean isCorrect = gameService.checkAnswer(answer, game);
		model.addAttribute("isCorrect", isCorrect);
		model.addAttribute("game", game);
		return "index";
	}

	@GetMapping("/reset")
	public String resetGame(@ModelAttribute("game") Game game) {
		List<Question> questions = questionService.getAllLetters(); // Reset with letters by default
		if (questions.isEmpty()) {
			throw new QuestionNotFoundException("No questions available for letters.");
		}
		game.setOptions(gameService.selectRandomQuestionNames(questions));
		game.setCorrectAnswer(game.getOptions()[new Random().nextInt(3)]);
		return "index"; // Return the appropriate view
	}

	@GetMapping("/letters")
	public String lettersGame(Model model, @ModelAttribute("game") Game game) {
		List<Question> questions = gameService.getLetterQuestions();
		Question selectedQuestion = gameService.selectRandomQuestion(questions);

		// Set the correct answer in the game
		game.setCorrectAnswer(selectedQuestion.getName());

		// Play the audio associated with the selected question
		gameService.playAudioForQuestion(selectedQuestion);

		// Pass the question and its image data to the view
		model.addAttribute("question", selectedQuestion);
		model.addAttribute("game", game);

		return "letters"; // View template for the letters game
	}



	@GetMapping("/testLetterA")
	public String testLetterA() {
		// Fetch the Question entity for the letter "A"
		Question question = questionService.getQuestionById(41L);
		Admin admin = new Admin();


		if (question != null) {
			// Play the audio for the letter "A"
			gameService.playAudioForQuestion(question);

			// Display the image for the letter "A"
			byte[] imageBytes = question.getCorrectAnswer().getImageData();
			if (imageBytes != null) {
				try {
					GameService.displayBlob(imageBytes); // Using the updated displayBlob(byte[]) method
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Error displaying the image for letter 'A'.");
				}
			} else {
				System.out.println("Image data is null for the letter 'A'.");
			}
		} else {
			System.out.println("Question for letter 'A' not found.");
		}

		return "testLetterA"; // Optional view rendering, can be removed if not needed
	}

	@GetMapping("/playAudio")
	public String playAudio(@RequestParam Long questionId) {
		Question question = questionService.getQuestionById(questionId); // Implement this method in GameService or use an existing method
		gameService.playAudioForQuestion(question);
		return "redirect:/letters"; // Redirect back to the letters game
	}

	@GetMapping("/numbers")
	public String numbersGame(Model model, @ModelAttribute("game") Game game,
							  @SessionAttribute("username") String username) {
		List<Question> questions = questionService.getAllAnimalsAndThings(); // Assuming "AllAnimalsAndThings" is used for numbers
		if (questions.isEmpty()) {
			throw new QuestionNotFoundException("No questions available for numbers.");
		}
		game.setOptions(gameService.selectRandomQuestionNames(questions));
		game.setCorrectAnswer(game.getOptions()[new Random().nextInt(3)]);
		model.addAttribute("game", game);
		model.addAttribute("username", username);
		return "numbers"; // Return the view for numbers game
	}

	@GetMapping("/getAudioBlob")
	public ResponseEntity<byte[]> getAudioBlob(@RequestParam Long questionId) {
		try {
			// Fetch the Question entity by ID
			Question question = questionService.getQuestionById(questionId);

			// Get the audio data as byte[]
			byte[] audioBytes = question.getAudioQuestion(); // Assuming getAudioQuestion() now returns byte[]

			if (audioBytes == null || audioBytes.length == 0) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT)
						.body(null); // Return 204 if audio data is missing
			}

			// Return the audio bytes as a response
			return ResponseEntity.ok()
					.contentType(MediaType.APPLICATION_OCTET_STREAM)
					.body(audioBytes);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null); // Return 500 on error
		}
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
