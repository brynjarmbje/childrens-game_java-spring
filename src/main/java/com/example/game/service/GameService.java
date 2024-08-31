package com.example.game.service;

import com.example.game.model.Game;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GameService {

    private Game game;
    private final Random random = new Random();

    public Game startGame() {
        game = new Game();
        game.setScore(0);
        game.setOptions(generateRandomOptions());
        game.setCorrectAnswer(game.getOptions()[random.nextInt(3)]); // Random correct answer
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

    private String[] generateRandomOptions() {
        char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        String[] options = new String[3];
        for (int i = 0; i < 3; i++) {
            options[i] = String.valueOf(letters[random.nextInt(letters.length)]);
        }
        return options;
    }
}
