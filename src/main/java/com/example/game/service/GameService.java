package com.example.game.service;

import com.example.game.model.Game;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private Game game;

    public Game startGame() {
        game = new Game();
        game.setScore(0);
        game.setOptions(new String[]{"A", "B", "C"});
        game.setCorrectAnswer("B");  // Example, this could be randomized
        return game;
    }

    public boolean checkAnswer(String answer) {
        boolean isCorrect = game.getCorrectAnswer().equals(answer);
        if (isCorrect) {
            game.setScore(game.getScore() + 1);
        }
        return isCorrect;
    }

    public Game getGame() {
        return game;
    }
}
