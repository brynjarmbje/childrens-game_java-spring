package com.game.service;
import com.game.data.AudioHandler;
import com.game.entity.Image;
import com.game.repository.ImageRepository;
import com.game.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.game.entity.Question;
import com.game.data.ImageHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;

@Service
public class QuestionService {
	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private ImageRepository imageRepository;

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
}