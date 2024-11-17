package com.game.controller;

import com.game.entity.Question;
import com.game.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {
    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private QuestionService questionService;

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

    @GetMapping("/getImage")
    public ResponseEntity<byte[]> getImage() {
        try {
            Question question = questionService.getQuestionById(1L);
            byte[] imageData = question.getCorrectAnswer().getImageData();

            // Set the headers to indicate an image response
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG); // Change to IMAGE_PNG or other format as needed

            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("An error occurred while fetching the image", e);

            // Return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/playQuestion")
    public ResponseEntity<byte[]> playQuestion() {
        try {
            Question question = questionService.getQuestionById(1L);
            byte[] audioData = question.getAudioQuestion();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); // Fallback if audio type is unknown
            headers.setContentType(MediaType.valueOf("audio/mpeg")); // Use "audio/wav" or other format if needed
            headers.setContentDisposition(ContentDisposition.inline().filename("audioQuestion.wav").build()); // Adjust filename and extension

            return new ResponseEntity<>(audioData, headers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("An error occurred while fetching the audio question", e);

            // Return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}
