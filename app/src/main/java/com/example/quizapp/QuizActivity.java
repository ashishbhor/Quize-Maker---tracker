package com.example.quizapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    private TextView questionText;
    private TextView progressText;
    private RadioGroup optionsGroup;
    private RadioButton option1;
    private RadioButton option2;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        initializeViews();
        initializeQuestions();
        displayQuestion();

        submitButton.setOnClickListener(v -> handleSubmission());
    }

    private void initializeViews() {
        questionText = findViewById(R.id.questionText);
        progressText = findViewById(R.id.progressText);
        optionsGroup = findViewById(R.id.optionsGroup);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        submitButton = findViewById(R.id.submitButton);
    }

    private void initializeQuestions() {
        questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", "Paris", "London", 0));
        questions.add(new Question("Which planet is known as the Red Planet?", "Venus", "Mars", 1));
        questions.add(new Question("What is the largest mammal?", "African Elephant", "Blue Whale", 1));
        questions.add(new Question("Who painted the Mona Lisa?", "Leonardo da Vinci", "Pablo Picasso", 0));
        questions.add(new Question("What is the chemical symbol for gold?", "Ag", "Au", 1));
        questions.add(new Question("Which country is known as the Land of the Rising Sun?", "China", "Japan", 1));
        questions.add(new Question("What is the largest organ in the human body?", "Heart", "Skin", 1));
        questions.add(new Question("Who wrote 'Romeo and Juliet'?", "William Shakespeare", "Charles Dickens", 0));
        questions.add(new Question("What is the hardest natural substance?", "Diamond", "Gold", 0));
        questions.add(new Question("Which is the largest ocean?", "Atlantic Ocean", "Pacific Ocean", 1));
    }

    private void displayQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        progressText.setText("Question " + (currentQuestionIndex + 1) + "/10");
        questionText.setText(currentQuestion.getQuestionText());
        option1.setText(currentQuestion.getOption1());
        option2.setText(currentQuestion.getOption2());
        optionsGroup.clearCheck();

        if (currentQuestionIndex == questions.size() - 1) {
            submitButton.setText("Submit Quiz");
        }
    }

    private void handleSubmission() {
        if (optionsGroup.getCheckedRadioButtonId() == -1) {
            showAlert("Please select an answer");
            return;
        }

        Question currentQuestion = questions.get(currentQuestionIndex);
        int selectedAnswerIndex = optionsGroup.indexOfChild(findViewById(optionsGroup.getCheckedRadioButtonId()));

        if (selectedAnswerIndex == currentQuestion.getCorrectAnswerIndex()) {
            score++;
        }

        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
            displayQuestion();
        } else {
            showFinalScore();
        }
    }

    private void showAlert(String message) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    private void showFinalScore() {
        String message = "Quiz completed!\nYour score: " + score + " out of " + questions.size();
        new AlertDialog.Builder(this)
                .setTitle("Quiz Results")
                .setMessage(message)
                .setPositiveButton("Finish", (dialog, which) -> finish())
                .setCancelable(false)
                .show();
    }
}


