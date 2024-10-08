package com.example.game.controller;

import com.example.game.model.Game;
import com.example.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "redirect:/letters";
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
        return "index";
    }

    @GetMapping("/numbers")
    public String numbersGame(Model model, @ModelAttribute("game") Game game,
                              @SessionAttribute("username") String username) {
        game.setOptions(gameService.generateRandomNumbers());
        game.setCorrectAnswer(game.getOptions()[new Random().nextInt(3)]);
        model.addAttribute("game", game);
        model.addAttribute("username", username);
        return "index";
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
        return "redirect:/letters";
    }
}


