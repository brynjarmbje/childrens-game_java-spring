package com.example.game.entity;


import jakarta.persistence.*;


@Entity
public class Game {


    @Id
    private String id;  // Game ID


    private int type;   // Game type


    private int level;  // Game level


    // Constructor with parameters
    public Game(String id, int type, int level) {
        this.id = id;
        this.type = type;
        this.level = level;
    }


    // Default constructor
    public Game() {}


    // Getters and Setters


    public String getId() {
        return id;
    }


    public void setId(String id) {
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
}
