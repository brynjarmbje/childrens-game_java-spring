package com.game.service;

import com.game.model.Game;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GameService {

    private final Random random = new Random();

    public boolean checkAnswer(String answer, Game game) {
        boolean isCorrect = game.getCorrectAnswer().equals(answer);
        if (isCorrect) {
            game.setScore(game.getScore() + 10);  // Add 10 points for each correct answer
        }
        return isCorrect;
    }

    public String[] generateRandomOptions() {
        char[] letters = "AÁBDÐEÉFGHIÍJKLMNOÓPRSTUÚVXYÝÞÆÖ".toCharArray();  // Include Icelandic letters
        String[] options = new String[3];
        for (int i = 0; i < 3; i++) {
            options[i] = String.valueOf(letters[random.nextInt(letters.length)]);
        }
        return options;
    }

    public String[] generateRandomNumbers() {
        String[] options = new String[3];
        for (int i = 0; i < 3; i++) {
            options[i] = String.valueOf(random.nextInt(100)); // Generate random numbers between 0-99
        }
        return options;
    }
}



