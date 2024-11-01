package com.game.entity;

public class Card {
    private int id;
    private String imageUrl;
    private char letter;
    private boolean flipped;
    private boolean locked;

    public Card(int id, String imageUrl, char letter) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.letter = letter;
        this.flipped = false;
        this.locked = false;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLocked() {
        return locked; // Add return statement here
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

