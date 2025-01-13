package com.example.quizapp;

public class Question {
    private String questionText;
    private String option1;
    private String option2;
    private int correctAnswerIndex;

    public Question(String questionText, String option1, String option2, int correctAnswerIndex) {
        this.questionText = questionText;
        this.option1 = option1;
        this.option2 = option2;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}
