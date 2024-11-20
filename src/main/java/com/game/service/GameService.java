package com.game.service;


import com.game.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.game.entity.Game;

import java.util.*;

@Service
public class GameService {

    private final Random random = new Random();

    @Autowired
    private QuestionService questionService;

    public Question generateRandomQuestion(int minId, int maxId) {
        long randomId = minId + new Random().nextInt(maxId - minId + 1);
        return questionService.getQuestionById(randomId);
    }

    public List<Long> generateWrongOptions(long correctId, int minId, int maxId) {
        Set<Long> usedIds = new HashSet<>(Collections.singletonList(correctId));
        List<Long> wrongOptionIds = new ArrayList<>();

        while (wrongOptionIds.size() < 2) {
            long randomId = minId + new Random().nextInt(maxId - minId + 1);
            if (!usedIds.contains(randomId)) {
                wrongOptionIds.add(randomId);
                usedIds.add(randomId);
            }
        }

        return wrongOptionIds;
    }

    public void startGame(Game game, int type, int level) {
        game.setLevel(level);
        game.setType(type);
    }

    public boolean checkAnswer(String answer, Game game) {
        boolean isCorrect = game.getCorrectAnswer().equals(answer);
        if (isCorrect) {
            game.setScore(game.getScore() + 10);  // Add points for correct answer
        }
        return isCorrect;
    }

    public String[] generateRandomOptions() {
        char[] letters = "AÁBDÐEÉFGHIÍJKLMNOÓPRSTUÚVXYÝÞÆÖ".toCharArray(); // Icelandic letters
        String[] options = new String[3];
        for (int i = 0; i < 3; i++) {
            options[i] = String.valueOf(letters[random.nextInt(letters.length)]);
        }
        return options;
    }

    public String[] generateRandomNumbers() {
        String[] options = new String[3];
        for (int i = 0; i < 3; i++) {
            options[i] = String.valueOf(random.nextInt(100)); // Random numbers 0-99
        }
        return options;
    }



}






