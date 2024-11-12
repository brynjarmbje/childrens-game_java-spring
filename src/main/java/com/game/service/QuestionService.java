package com.game.service;

import com.game.entity.Audio;
import com.game.entity.Image;
import com.game.entity.Question;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

public class QuestionService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void createQuestionWithAssets(String questionId, int type, int level, byte[] audioData, List<byte[]> imageDataList) {
        // Step 1: Create and persist the Audio entity
        Audio audio = new Audio("Question Audio", 1, audioData);
        entityManager.persist(audio);

        // Step 2: Create and persist the Image entities
        List<Image> images = imageDataList.stream()
                .map(data -> new Image("Image for Question", 1, data))
                .toList();

        images.forEach(entityManager::persist);

        // Step 3: Create and persist the Question entity
        Question question = new Question(questionId, type, level);
        question.setAudioQuestion(audio);
        question.setGroup(images);
        entityManager.persist(question);
    }
}