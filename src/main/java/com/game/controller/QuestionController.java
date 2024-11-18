package com.game.controller;

import com.game.entity.Question;
import com.game.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Base64;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
public class QuestionController {
    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private QuestionService questionService;

    private long currentRandomId = -1; // Class-level variable to store the random ID

    @GetMapping("/getImage")
    public ResponseEntity<byte[]> getImage(@RequestParam int minId, @RequestParam int maxId) {
        try {
            // Generate a random ID within the given bounds
            currentRandomId = minId + new Random().nextInt(maxId - minId + 1);
            logger.info("Fetching image for question ID: " + currentRandomId);

            Question question = questionService.getQuestionById(currentRandomId);
            if (question == null || question.getCorrectAnswer() == null || question.getCorrectAnswer().getImageData() == null) {
                logger.warn("No image data found for question ID: " + currentRandomId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            byte[] imageData = question.getCorrectAnswer().getImageData();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);

            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("An error occurred while fetching the image", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/playAudio")
    public ResponseEntity<byte[]> playAudio() {
        try {
            if (currentRandomId == -1) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Ensure an image request precedes this call
            }

            // Fetch the question by the same random ID
            Question question = questionService.getQuestionById(currentRandomId);

            if (question == null || question.getAudioQuestion() == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            byte[] audioData = question.getAudioQuestion();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf("audio/mpeg")); // Use appropriate format for your audio
            headers.setContentDisposition(ContentDisposition.inline().filename("audioQuestion.mp3").build());

            return new ResponseEntity<>(audioData, headers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("An error occurred while fetching the number audio", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getWrongImage")
    public ResponseEntity<byte[]> getWrongImage() {
        try {
            long randomId;
            // Ensure the wrong image ID is different from the currentRandomId
            do {
                randomId = new Random().nextInt(40) + 1; // Adjust bounds as needed for the range
            } while (randomId == currentRandomId);

            Question question = questionService.getQuestionById(randomId);
            if (question == null || question.getCorrectAnswer() == null || question.getCorrectAnswer().getImageData() == null) {
                logger.warn("No image data found for wrong option ID: " + randomId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            byte[] imageData = question.getCorrectAnswer().getImageData();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);

            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("An error occurred while fetching the wrong image", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
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
