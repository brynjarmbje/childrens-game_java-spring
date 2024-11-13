package com.game;

import com.game.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class GameApplication implements CommandLineRunner {

    @Autowired
    private QuestionService questionService;

    public static void main(String[] args) {
        SpringApplication.run(GameApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            System.out.println("Starting createQuestionWithCorrectImage...");

            questionService.createQuestionWithCorrectImage(
                    "test",
                    1,
                    1,
                    "src/main/resources/static/audio_files/test_audio/audio_test.wav",
                    "src/main/resources/static/image_files/test_image/image_test.png",
                    "png"
            );

            System.out.println("createQuestionWithCorrectImage completed successfully.");
        } catch (Exception e) {
            System.err.println("An error occurred while creating the question with the correct image.");
            e.printStackTrace();
        }
    }
}
