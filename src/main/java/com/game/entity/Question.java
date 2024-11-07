package com.game.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;

@Entity
public class Question {

    @Id
    private String id;

    private int type;
    private int level;

    @OneToMany
    private List<Image> group;

    @OneToOne
    private Audio audioQuestion;

    @OneToOne
    private Image correctAnswer;

    @OneToMany
    private List<Image> wrongAnswers;

    // Constructor
    public Question(String id, int type, int level) {
        this.id = id;
        this.type = type;
        this.level = level;
    }

    // Default Constructor
    public Question() {}

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

    public List<Image> getGroup() {
        return group;
    }

    public void setGroup(List<Image> group) {
        this.group = group;
    }

    public Audio getAudioQuestion() {
        return audioQuestion;
    }

    public void setAudioQuestion(Audio audioQuestion) {
        this.audioQuestion = audioQuestion;
    }

    public Image getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Image correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<Image> getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(List<Image> wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }
}
