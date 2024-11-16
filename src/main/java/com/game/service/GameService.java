package com.game.service;

import com.game.data.AudioHandler;
import com.game.data.ImageHandler;
import com.game.entity.Game;
import com.game.entity.Image;
import com.game.entity.Question;
import com.game.errors.QuestionNotFoundException;
import com.game.repository.ImageRepository;
import com.game.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.sql.DataSource;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

@Service
public class GameService {

    @Autowired
    private QuestionService questionService;

    @Autowired
    QuestionRepository questionRepository;

    private final Random random = new Random();

    // Method to fetch all letter questions (type = 1)
    public List<Question> getLetterQuestions() {
        List<Question> questions = questionRepository.findByType(1); // Type 1 for letters
        if (questions.isEmpty()) {
            throw new RuntimeException("No questions found for letters.");
        }
        return questions;
    }

    public Question selectRandomQuestion(List<Question> questions) {
        return questions.get(random.nextInt(questions.size()));
    }

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ImageRepository imageRepository;

    // Directly include the method to play audio from a Blob
    @Transactional
    public void playAudioBlob(byte[] audioBytes) {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(audioBytes)) {
            // Create an AudioInputStream from the byte array
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(byteArrayInputStream);

            // Get a Clip instance and open the AudioInputStream
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            // Start playing the audio
            clip.start();
            System.out.println("Audio is playing...");

            // Keep the method alive while audio is playing
            while (clip.isRunning()) {
                Thread.sleep(50); // Polling for completion
            }

            // Close the clip after playback
            clip.close();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
            System.err.println("Failed to play audio from byte array.");
        }
    }

    public static void displayBlob(byte[] imageBytes) {
        try (InputStream inputStream = new ByteArrayInputStream(imageBytes)) {
            // Read InputStream into a BufferedImage
            BufferedImage blobImage = ImageIO.read(inputStream);

            // Display the image
            if (blobImage != null) {
                JFrame frame = new JFrame("Image Display from Bytes");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(blobImage.getWidth(), blobImage.getHeight());
                JLabel label = new JLabel(new ImageIcon(blobImage));
                frame.add(label);
                frame.pack();
                frame.setVisible(true);
            } else {
                System.out.println("Failed to decode image from byte array.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error displaying image from byte array.");
        }
    }

    // Play audio for the selected question
    public void playAudioForQuestion(Question question) {
        if (question != null && question.getAudioQuestion() != null) {
            playAudioBlob(question.getAudioQuestion());
        }
    }

    public void startGame(Game game, int type, int level) {
        game.setLevel(level);
        game.setType(type);

        // Fetch questions based on the type (1 for letters, 2 for numbers)
        List<Question> questions = (type == 1)
                ? questionService.getAllLetters()
                : questionService.getAllAnimalsAndThings();

        if (questions.isEmpty()) {
            throw new QuestionNotFoundException("No questions available for the given type");
        }

        // Select three random questions for the options
        Question[] options = new Question[3];
        for (int i = 0; i < 3; i++) {
            options[i] = questions.get(random.nextInt(questions.size()));
        }

        // Display the images for each option
        for (Question option : options) {
            if (option.getCorrectAnswer() != null && option.getCorrectAnswer().getImageData() != null) {
                displayImage(option.getCorrectAnswer().getImageData());
            }
        }
    }

    public boolean checkAnswer(String answer, Game game) {
        boolean isCorrect = game.getCorrectAnswer().equals(answer);
        if (isCorrect) {
            game.setScore(game.getScore() + 10);  // Add points for correct answer
        }
        return isCorrect;
    }

    private void displayImage(byte[] imageBytes) {
        if (imageBytes != null) {
            ImageHandler.displayBlob(imageBytes);
        } else {
            System.out.println("No image found for this question.");
        }
    }
    public String[] selectRandomQuestionNames(List<Question> questions) {
        String[] options = new String[3];
        for (int i = 0; i < 3; i++) {
            Question selectedQuestion = questions.get(random.nextInt(questions.size()));
            options[i] = selectedQuestion.getName(); // Extract name or another string representation
        }
        return options;
    }

}







