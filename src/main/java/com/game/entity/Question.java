
package com.game.entity;

public class Question {

    private Long id;

    private Integer type;

    private Integer level;

    private Audio audioQuestion;

    private Image correctAnswer;

    private Image wrongAnswer;

    public Question(Integer type, Integer level, Audio audioQuestion, Image correctAnswer, Image wrongAnswer) {
        this.type = type;
        this.level = level;
        this.audioQuestion = audioQuestion;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer = wrongAnswer;
    }

}
