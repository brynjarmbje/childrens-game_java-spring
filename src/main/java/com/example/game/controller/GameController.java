package com.example.game.controller;

import com.example.game.model.Game;
import com.example.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Random;

@Controller
@SessionAttributes("game")
public class GameController {

    @Autowired
    private GameService gameService;

    @ModelAttribute("game")
    public Game getGame() {
        return new Game(); // Default game initialization in case there's no game in the session
    }

    @GetMapping("/")
    public String home(Model model, @ModelAttribute("game") Game game) {
        if (game.getOptions() == null) { // Only generate options if they haven't been generated yet
            game.setOptions(gameService.generateRandomOptions());
            game.setCorrectAnswer(game.getOptions()[new Random().nextInt(3)]);
        }
        model.addAttribute("game", game);
        return "index";
    }

    @PostMapping("/check")
    public String checkAnswer(@RequestParam("answer") String answer, @ModelAttribute("game") Game game, Model model) {
        boolean isCorrect = gameService.checkAnswer(answer, game);
        model.addAttribute("isCorrect", isCorrect);
        model.addAttribute("game", game);
        return "index";
    }

    @GetMapping("/reset")
    public String resetGame(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
