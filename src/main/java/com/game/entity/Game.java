package com.game.entity;

import jakarta.persistence.*;

@Entity
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int type; // Game type
	private int level; // Game level
	private int score; // Game score
	private String[] options; // Options for the game
	private String correctAnswer; // Correct answer for the game

	// Constructor with parameters
	public Game(int type, int level) {
		this.type = type;
		this.level = level;
		this.score = 0; // Initialize score
	}

	// Default constructor
	public Game() {
		this.score = 0; // Initialize score
	}

	// Getters and Setters
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String[] getOptions() {
		return options;
	}

	public void setOptions(String[] options) {
		this.options = options;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
}

