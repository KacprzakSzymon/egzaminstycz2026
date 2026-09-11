package com.example.egzaminstyczen2026;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

class Question {
    private int imageResId;
    private String questionText;
    private String answer1;
    private String answer2;
    private String answer3;
    private int correctAnswerIndex;

    public Question(int imageResId, String questionText, String answer1, String answer2, String answer3, int correctAnswerIndex) {
        this.imageResId = imageResId;
        this.questionText = questionText;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public int getImageResId() { return imageResId; }
    public String getQuestionText() { return questionText; }
    public String getAnswer1() { return answer1; }
    public String getAnswer2() { return answer2; }
    public String getAnswer3() { return answer3; }
    public int getCorrectAnswerIndex() { return correctAnswerIndex; }
}

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewQuestion;
    private TextView textViewQuestion;
    private RadioGroup radioGroupAnswers;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private Button buttonNext;

    private List<Question> questionsList;
    private int currentQuestionIndex = 0;
    private int userPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initQuestions();
        displayQuestion(currentQuestionIndex);

        buttonNext.setOnClickListener(v -> checkAnswerAndAdvance());
    }

    private void initViews() {
        imageViewQuestion = findViewById(R.id.imageViewQuestion);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        radioGroupAnswers = findViewById(R.id.radioGroupAnswers);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        buttonNext = findViewById(R.id.buttonNext);
    }

    private void initQuestions() {
        questionsList = new ArrayList<>();
        questionsList.add(new Question(
                R.drawable.zad1,
                "Które to schronisko?",
                "Na Rysiance.",
                "Na Wielkiej Raczy.",
                "Na Wielkiej Rycerzowej.",
                1
        ));
        questionsList.add(new Question(
                R.drawable.zad2,
                "Zwierzę na zdjęciu to",
                "Owczarek.",
                "Wilk.",
                "Kojot.",
                0
        ));
        questionsList.add(new Question(
                R.drawable.zad3,
                "W oddali są widoczne",
                "Himalaje.",
                "Alpy.",
                "Tatry.",
                2
        ));
    }

    private void displayQuestion(int index) {
        Question currentQuestion = questionsList.get(index);

        imageViewQuestion.setImageResource(currentQuestion.getImageResId());
        textViewQuestion.setText(currentQuestion.getQuestionText());
        radioButton1.setText(currentQuestion.getAnswer1());
        radioButton2.setText(currentQuestion.getAnswer2());
        radioButton3.setText(currentQuestion.getAnswer3());

        radioGroupAnswers.clearCheck();
    }

    private void checkAnswerAndAdvance() {
        int selectedRadioButtonId = radioGroupAnswers.getCheckedRadioButtonId();


        if (selectedRadioButtonId == R.id.radioButton1 && questionsList.get(currentQuestionIndex).getCorrectAnswerIndex() == 0) {
            userPoints++;
        } else if (selectedRadioButtonId == R.id.radioButton2 && questionsList.get(currentQuestionIndex).getCorrectAnswerIndex() == 1) {
            userPoints++;
        } else if (selectedRadioButtonId == R.id.radioButton3 && questionsList.get(currentQuestionIndex).getCorrectAnswerIndex() == 2) {
            userPoints++;
        }


        currentQuestionIndex = (currentQuestionIndex + 1) % questionsList.size();
        displayQuestion(currentQuestionIndex);
    }
}