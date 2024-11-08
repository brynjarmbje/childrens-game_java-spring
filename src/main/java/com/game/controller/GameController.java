package com.game.controller;

import com.game.model.Game;
import com.game.service.GameService;
import com.game.service.MemoryGameService;
import com.game.service.MatchingGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@SessionAttributes({"game", "username"})
public class GameController {

    @Autowired
    private GameService gameService;

    @ModelAttribute("game")
    public Game getGame() {
        return new Game();
    }

    @GetMapping("/")
    public String index(@SessionAttribute(value = "username", required = false) String username) {
        if (username == null || username.isEmpty()) {
            return "redirect:/login";
        }
        return "index"; // No "redirect:/index", instead render "index" view
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
        game.setOptions(gameService.generateRandomOptions());
        game.setCorrectAnswer(game.getOptions()[new Random().nextInt(3)]);
        return "index";
    }

    @GetMapping("/letters")
    public String lettersGame(Model model, @ModelAttribute("game") Game game,
                              @SessionAttribute("username") String username) {
        game.setOptions(gameService.generateRandomOptions());
        game.setCorrectAnswer(game.getOptions()[new Random().nextInt(3)]);
        model.addAttribute("game", game);
        model.addAttribute("username", username);
        return "letters";
    }

    @GetMapping("/numbers")
    public String numbersGame(Model model, @ModelAttribute("game") Game game,
                              @SessionAttribute("username") String username) {
        game.setOptions(gameService.generateRandomNumbers());
        game.setCorrectAnswer(game.getOptions()[new Random().nextInt(3)]);
        model.addAttribute("game", game);
        model.addAttribute("username", username);
        return "numbers";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("username", "Guest");
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, Model model) {
        if (username == null || username.trim().isEmpty()) {
            model.addAttribute("error", "Username cannot be empty");
            return "login";
        }
        model.addAttribute("username", username);
        return "redirect:/"; // Redirects to the root URL, which triggers index() method
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


