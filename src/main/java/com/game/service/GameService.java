package com.game.service;


import com.game.entity.Question;
import com.game.entity.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.game.entity.Game;

import java.util.*;

@Service
public class GameService {

    private final Random random = new Random();

    @Autowired
    private QuestionService questionService;

    @Autowired
    private SessionService sessionService;

    /**
     * Generate a random question within the level-specific question IDs.
     */
    public Question generateRandomQuestion(Session session, List<Long> allQuestionIds) {
        List<Long> questionIdsForLevel = sessionService.getQuestionIdsForLevel(session.getLevel(), allQuestionIds);
        long randomId = questionIdsForLevel.get(random.nextInt(questionIdsForLevel.size()));
        return questionService.getQuestionById(randomId);
    }

    /**
     * Generate wrong options for the current level.
     */
    public List<Long> generateWrongOptions(long correctId, Session session, List<Long> allQuestionIds) {
        List<Long> questionIdsForLevel = sessionService.getQuestionIdsForLevel(session.getLevel(), allQuestionIds);
        Set<Long> usedIds = new HashSet<>(Collections.singletonList(correctId));
        List<Long> wrongOptionIds = new ArrayList<>();

        while (wrongOptionIds.size() < 2) {
            long randomId = questionIdsForLevel.get(random.nextInt(questionIdsForLevel.size()));
            if (!usedIds.contains(randomId)) {
                wrongOptionIds.add(randomId);
                usedIds.add(randomId);
            }
        }

        return wrongOptionIds;
    }
}






