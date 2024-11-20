package com.game.service;

import com.game.entity.Question;
import com.game.model.Card;
import com.game.model.Icon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchingGameService {

    @Autowired
    private QuestionService questionService;

    private static final int ANIMALS_THINGS_MIN_ID = 73;
    private static final int ANIMALS_THINGS_MAX_ID = 99;

    private List<Card> imageCards;
    private List<Card> letterCards;

    public void initializeGame() {
        List<Question> imageQuestions = questionService.getQuestionsInRange(ANIMALS_THINGS_MIN_ID, ANIMALS_THINGS_MAX_ID);
        List<Question> letterQuestions = questionService.getQuestionsInRange(41, 72);

        if (imageQuestions.size() < 6 || letterQuestions.size() < 6) {
            throw new IllegalStateException("Not enough questions for the game!");
        }

        Map<Character, Question> letterQuestionMap = new HashMap<>();
        for (Question letterQ : letterQuestions) {
            char firstLetter = Character.toUpperCase(letterQ.getCorrectAnswer().getFirstLetter().charAt(0));
            letterQuestionMap.put(firstLetter, letterQ);
        }

        Collections.shuffle(imageQuestions);

        imageCards = new ArrayList<>();
        letterCards = new ArrayList<>();
        int cardId = 0;

        for (int i = 0; i < 6; i++) {
            Question imageQ = imageQuestions.get(i);
            char firstLetter = Character.toUpperCase(imageQ.getCorrectAnswer().getFirstLetter().charAt(0));

            // Ensure we have a matching letter
            if (!letterQuestionMap.containsKey(firstLetter)) continue;

            Question letterQ = letterQuestionMap.get(firstLetter);

            // Create cards for the image and letter
            imageCards.add(new Card(cardId++, "/getImage?id=" + imageQ.getId(), firstLetter, imageQ.getId()));
            letterCards.add(new Card(cardId++, "/getImage?id=" + letterQ.getId(), firstLetter, letterQ.getId()));
        }

        // Shuffle letter cards for random placement
        Collections.shuffle(letterCards);
    }

    public List<Card> getImageCards() {
        return imageCards;
    }

    public List<Card> getLetterCards() {
        return letterCards;
    }
}

