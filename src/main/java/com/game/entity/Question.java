package com.game.entity;

import java.sql.Blob;

import com.game.data.AudioHandler;

import jakarta.persistence.*;

@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String connectionId;

	@Column(nullable = false)
	private int type;

	@Column(nullable = false)
	private int level;

	@Lob
	private Blob audioQuestion;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "correct_answer_id")
	private Image correctAnswer;

	// Constructor
	public Question(String connectionId, int type, int level, String audioQuestionFilePath) {
		this.connectionId = connectionId;
		this.type = type;
		this.level = level;
		this.audioQuestion = AudioHandler.convertAudioFileToBlob(audioQuestionFilePath);
	}

	// Default Constructor
	public Question() {
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Blob getAudioQuestion() {
		return audioQuestion;
	}

	public void setAudioQuestion(String audioQuestionBlobFilePath) {
		this.audioQuestion = AudioHandler.convertAudioFileToBlob(audioQuestionBlobFilePath);
	}

	public Image getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(Image correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

}
