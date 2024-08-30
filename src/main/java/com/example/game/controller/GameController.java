package com.example.game.controller;

import com.example.game.model.Game;
import com.example.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/")
    public String home(Model model) {
        Game game = gameService.startGame();
        model.addAttribute("game", game);
        return "index";
    }

    @PostMapping("/check")
    public String checkAnswer(@RequestParam("answer") String answer, Model model) {
        boolean isCorrect = gameService.checkAnswer(answer);
        model.addAttribute("isCorrect", isCorrect);
        model.addAttribute("game", gameService.getGame());
        return "index";
    }
}
