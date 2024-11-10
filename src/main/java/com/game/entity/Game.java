package com.game.entity;

import jakarta.persistence.*;

@Entity
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int type; // Game type

	private int level; // Game level

	// Constructor with parameters
	public Game(int type, int level) {
		this.type = type;
		this.level = level;
	}

	// Default constructor
	public Game() {
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
}
