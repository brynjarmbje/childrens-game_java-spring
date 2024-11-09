package com.game.model;

public class Card {
    private int id;
    private String imageUrl;
    private char letter;
    private boolean flipped;
    private boolean matched;

    public Card(int id, String imageUrl, char letter) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.letter = letter;
        this.flipped = false;
        this.matched = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }
}


