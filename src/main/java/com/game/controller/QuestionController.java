package com.game.controller;

import com.game.entity.Question;
import com.game.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.stream.Collectors;

@RestController
public class QuestionController {
    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private QuestionService questionService;

    @GetMapping("/getImage")
    public ResponseEntity<byte[]> getImage(@RequestParam long id) {
        Question question = questionService.getQuestionById(id);
        byte[] imageData = question.getCorrectAnswer().getImageData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }

    @GetMapping("/playAudio")
    public ResponseEntity<byte[]> playAudio(@RequestParam long id) {
        Question question = questionService.getQuestionById(id);
        byte[] audioData = question.getAudioQuestion();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("audio/mpeg"));
        return new ResponseEntity<>(audioData, headers, HttpStatus.OK);
    }

    @GetMapping("/getAllQuestions")
    @ResponseBody
    public ResponseEntity<?> showQuestionById() {
        try {
            Question question = questionService.getQuestionById(1L);
            return ResponseEntity.ok(question.getCorrectAnswer().getImageData());
        } catch (Exception e) {
            // Log the exception
            logger.error("An error occurred while fetching all questions", e);

            // Return an informative response to the client
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing your request: " + e.getMessage());
        }
    }
}
