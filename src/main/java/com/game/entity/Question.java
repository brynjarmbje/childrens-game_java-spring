package com.game.entity;

import jakarta.persistence.*;

@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private int type;

	@Column(nullable = false)
	private int level;

	@Column(columnDefinition = "BYTEA")
	private byte[] audioQuestion;

	@OneToOne
	@JoinColumn(name = "correct_image_id", referencedColumnName = "id", nullable = true)
	private Image correctAnswer;

	// Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public byte[] getAudioQuestion() {
		return audioQuestion;
	}

	public void setAudioQuestion(byte[] audioQuestion) {
		this.audioQuestion = audioQuestion;
	}

	public Image getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(Image correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
}
