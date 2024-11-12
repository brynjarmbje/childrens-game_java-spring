package com.game.entity;

import jakarta.persistence.*;

import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id") // You can specify a column name here
    private List<Image> group;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "audio_question_id")
    private Audio audioQuestion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "correct_answer_id")
    private Image correctAnswer;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id") // Assuming wrongAnswers also references question
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
