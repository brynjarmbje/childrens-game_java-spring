package com.game.model;

public class Icon {
    private int id;
    private String imageUrl;
    private char letter;
    private boolean matched;

    public Icon(int id, String imageUrl, char letter) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.letter = letter;
        this.matched = false;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
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
}
