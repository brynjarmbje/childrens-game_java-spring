package com.game.controller;

import com.game.entity.Card;
import com.game.service.MemoryGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class MemoryGameController {

    @Autowired
    private MemoryGameService gameService;

    @GetMapping("/initialize")
    public List<Card> initializeGame() {
        gameService.initializeGame();
        return gameService.getCards();
    }

    @GetMapping("/cards")
    public List<Card> getCards() {
        return gameService.getCards();
    }

    @PostMapping("/flip/{id}")
    public String flipCard(@PathVariable int id) {
        return gameService.flipCard(id);
    }
}

