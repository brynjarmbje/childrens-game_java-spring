package com.game.service;
import com.game.data.AudioHandler;
import com.game.entity.Admin;
import com.game.entity.Image;
import com.game.errors.QuestionNotFoundException;
import com.game.repository.ImageRepository;
import com.game.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.game.entity.Question;
import com.game.data.ImageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private ImageRepository imageRepository;
	private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);

	public List<Question> getAllQuestions() {
		try {
			List<Question> questions = questionRepository.findAll();
			logger.info("Successfully fetched {} questions from the database", questions.size());
			return questions;
		} catch (Exception e) {
			// Log exception details
			logger.error("Error fetching questions from the database: {}", e.getMessage(), e);

			// Re-throw the exception if you want it to propagate further
			throw new RuntimeException("Failed to fetch questions", e);
		}
	}


    // Retrieve a question by ID
	public Question getQuestionById(Long id) {
		try {
			return questionRepository.findById(id)
					.orElseThrow(() -> new QuestionNotFoundException("Question with ID " + id + " not found"));
		} catch (Exception e) {
			throw new RuntimeException("Error while retrieving question by ID: " + id, e);
		}
	}
	public byte[] getAudioQuestionById(Long id) {
		try {
			Question question = questionRepository.findById(id)
					.orElseThrow(() -> new QuestionNotFoundException("Question with ID " + id + " not found"));
			return question.getAudioQuestion();
		} catch (Exception e) {
			throw new RuntimeException("Error while retrieving audio question by ID: " + id, e);
		}
	}

	// Retrieve a question by name
	public Question getQuestionByName(String name) {
		try {
			return questionRepository.findByName(name)
					.orElseThrow(() -> new QuestionNotFoundException("Question with name '" + name + "' not found"));
		} catch (Exception e) {
			throw new RuntimeException("Error while retrieving question by name: " + name, e);
		}
	}

	// Retrieve all questions by type
	public List<Question> getQuestionsByType(int type) {
		try {
			List<Question> questions = questionRepository.findByType(type);
			if (questions.isEmpty()) {
				throw new QuestionNotFoundException("No questions found with type: " + type);
			}
			return questions;
		} catch (Exception e) {
			throw new RuntimeException("Error while retrieving questions by type: " + type, e);
		}
	}

	// Retrieve all questions by level
	public List<Question> getQuestionsByLevel(int level) {
		try {
			List<Question> questions = questionRepository.findByLevel(level);
			if (questions.isEmpty()) {
				throw new QuestionNotFoundException("No questions found with level: " + level);
			}
			return questions;
		} catch (Exception e) {
			throw new RuntimeException("Error while retrieving questions by level: " + level, e);
		}
	}

	public List<Question> getAllLetters() {
		return questionRepository.findByType(1);
	}

	public List<Question> getAllAnimalsAndThings() {
		return questionRepository.findByType(2);
	}


	public List<Question> getAllNedanOfanHaegriVinstri() {
		return questionRepository.findByType(3);
	}

	public void createQuestion(
			String questionName,
			int type,
			int level,
			String folder, // Folder name for the image file
			String path, // Path to the audio file
			String imageFormat, // Image format (e.g., "png", "jpg")
			boolean isLetter, // Whether the image is a letter
			String firstLetter // Whether the image is the first letter
	) {
		try {
			String imagePath = "src/main/resources/static/image_files/" + folder + "/" + path + ".png";
			System.out.println("Image path: " + imagePath);
			// Load the image file as a BufferedImage
			BufferedImage bufferedImage = ImageIO.read(new File(imagePath));

			// Convert BufferedImage to Blob using the static method
			byte[] imageBlob = ImageHandler.convertImageToBlob(bufferedImage, imageFormat);

			// Create the Image entity
			Image correctImage = new Image();
			correctImage.setName(questionName);
			correctImage.setType(type);
			correctImage.setLevel(level);
			correctImage.setLetter(isLetter);
			correctImage.setFirstLetter(firstLetter);
			correctImage.setImageData(imageBlob); // Set the converted image Blob

			// Save the Image entity
			imageRepository.save(correctImage);

			String audioPath = "src/main/resources/static/audio_files/" + folder + "/" + path + ".wav";
			System.out.println("Audio path: " + audioPath);
			// Convert audio file to Blob using AudioHandler
			byte[] audioBlob = AudioHandler.convertAudioFileToBlob(audioPath);

			// Create the Question entity and associate the correct Image
			Question question = new Question();
			question.setName(questionName);
			question.setType(type);
			question.setLevel(level);
			question.setAudioQuestion(audioBlob); // Set the audio Blob
			question.setCorrectAnswer(correctImage);

			// Save the Question entity
			questionRepository.save(question);

		} catch (IOException e) {
			throw new RuntimeException("Failed to process image or audio file", e);
		}
	}
}