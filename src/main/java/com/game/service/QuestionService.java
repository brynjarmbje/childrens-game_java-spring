package com.game.service;
import com.game.data.AudioHandler;
import com.game.entity.Image;
import com.game.errors.QuestionNotFoundException;
import com.game.repository.ImageRepository;
import com.game.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.game.entity.Question;
import com.game.data.ImageHandler;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private ImageRepository imageRepository;

	// Retrieve a question by ID
	public Question getQuestionById(Long id) {
		try {
			return questionRepository.findById(id)
					.orElseThrow(() -> new QuestionNotFoundException("Question with ID " + id + " not found"));
		} catch (Exception e) {
			throw new RuntimeException("Error while retrieving question by ID: " + id, e);
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
			Character firstLetter // Whether the image is the first letter
	) {
		try {
			String imagePath = "src/main/resources/static/image_files/" + folder + "/" + path + ".png";
			System.out.println("Image path: " + imagePath);
			// Load the image file as a BufferedImage
			BufferedImage bufferedImage = ImageIO.read(new File(imagePath));

			// Convert BufferedImage to Blob using the static method
			Blob imageBlob = ImageHandler.convertImageToBlob(bufferedImage, imageFormat);

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
			Blob audioBlob = AudioHandler.convertAudioFileToBlob(audioPath);

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

	public void playAudioBlob(Blob audioBlob) {
		try {
			// Extract byte array from Blob
			byte[] audioBytes = audioBlob.getBytes(1, (int) audioBlob.length());
			try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(audioBytes)) {
				// Create an AudioInputStream from the byte array
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(byteArrayInputStream);

				// Get a Clip instance
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);

				// Start playing the audio
				clip.start();
				System.out.println("Audio from Blob is playing...");

				// Keep the method alive while the audio is playing
				while (clip.isRunning()) {
					Thread.sleep(50); // Polling for completion
				}

				// Close the clip after playback
				clip.close();
			}
		} catch (SQLException | IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
			e.printStackTrace();
			System.err.println("Failed to play audio from Blob.");
		}
	}

	// Display an image directly from a Blob
	public void displayBlob(Blob imageBlob) {
		try {
			// Convert Blob to InputStream
			InputStream inputStream = imageBlob.getBinaryStream();
			// Read InputStream into a BufferedImage
			BufferedImage blobImage = ImageIO.read(inputStream);

			// Display the image
			if (blobImage != null) {
				JFrame frame = new JFrame("Image Display from Blob");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(blobImage.getWidth(), blobImage.getHeight());
				JLabel label = new JLabel(new ImageIcon(blobImage));
				frame.add(label);
				frame.pack();
				frame.setVisible(true);
			} else {
				System.out.println("Failed to decode image from Blob.");
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			System.out.println("Error displaying image from Blob.");
		}
	}
}