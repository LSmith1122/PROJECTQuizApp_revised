package com.example.android.projectquizapp;

// App was created by Lloyd Robert Smith

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    static ArrayList<Question> questionList = new ArrayList<Question>();
    static ArrayList<EditText> editTextArrayList = new ArrayList<EditText>();
    static int answeredEditTextCounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateActivity();
    }
    private void populateActivity() {
        String[] questionResourceList = getResources().getStringArray(R.array.photography_questions);
        for (int i = 0; i < questionResourceList.length; i++) {
            String[] rawString = questionResourceList[i].split(" ~ ");
            String answerSelectionType = rawString[0];
            String questionString = rawString[1];
            int tag = i + 1;
            if (rawString.length >= 3) {
                String img = rawString[2];
                Question question = new Question(tag, questionString, answerSelectionType, img);
                questionList.add(question);
            } else {
                Question question = new Question(tag, questionString, answerSelectionType);
                questionList.add(question);
            }
        }
        QuestionAdapter adapter = new QuestionAdapter(this, questionList);
        ListView listView = (ListView) findViewById(R.id.parent_group);
        listView.setAdapter(adapter);
    }
    public void checkResponse(View view) {
        checkButtonAnswers();
        checkEditTextAnswers();
        int maxAmount = questionList.size();
        int counter = 0;
        for (int i = 0; i < questionList.size(); i++) {
            Question question = questionList.get(i);
            if (question.isQuestionAnswered()) {
                counter++;
            } else {
                break;
            }
        }
        if (counter == maxAmount) {
            String finalScore = String.valueOf(tallyAnswers());
            Intent intent = new Intent(this, FinalScore_Activity.class);
            Bundle bundle = new Bundle();
            bundle.putString("final score", finalScore);
            intent.putExtras(bundle);
            startActivity(intent);
        } else {
            questionsNotAnswered();
            String questionsToAnswer = "";
            for (int i = 0; i < questionList.size(); i++) {
                Question question = questionList.get(i);
                if (!question.isQuestionAnswered()) {
                    if (questionsToAnswer.equals("")) {
                        questionsToAnswer = String.valueOf(question.getTag());
                    } else {
                        questionsToAnswer = questionsToAnswer + ", " + String.valueOf(question.getTag());
                    }
                }
            }
            Toast.makeText(getApplicationContext(), "Questions " + questionsToAnswer + " not answered", Toast.LENGTH_LONG).show();
        }
    }
    public double tallyAnswers() {
        double currentScore = 0.0;
        double maxScore = 0.0;
        for (int i = 0; i < questionList.size(); i++) {
            Question question = questionList.get(i);
            maxScore++;
            if (question.isAnswerCorrect()) {
                currentScore++;
            }
        }
        return currentScore / maxScore * 100;
    }
    private void checkButtonAnswers() {
        for (int i = 0; i < questionList.size(); i++) {
            if (!questionList.get(i).getAnswerType().equals("Text")) { // For RadioButtons and CheckBoxes only
                Question currentQuestion = questionList.get(i);
                if (currentQuestion.getAnswerType().contains("RadioButton")) {
                    if (currentQuestion.getAnswerInputList().size() > 0) {
                        currentQuestion.checkAnswers();
                    }
                }
                if (currentQuestion.getAnswerType().contains("CheckBox")) {
                    currentQuestion.checkAnswers();
                }
            }
        }
    }
    private void checkEditTextAnswers() {
        // Add answers to an array list & list each answer within the appropriate index number - based on EditText tag number
        for (int i = 0; i < editTextArrayList.size(); i++) {
            EditText editTextObject = editTextArrayList.get(i);
            if (isEditTextAnswered(editTextObject)) {
                answeredEditTextCounter++;
                for (int q = 0; q < questionList.size(); q++) {
                    Question question = questionList.get(q);
                    if (question.getTag() == Integer.valueOf(editTextObject.getTag().toString())) {
                        question.checkAnswers(editTextObject.getText().toString());
                    }
                }
            }
        }
    }
    private boolean isEditTextAnswered(EditText object) {
        boolean status = false;
        for (int i = 0; i < questionList.size(); i++) {
            if (!object.getText().toString().equals("") && !object.getText().toString().equals(null)) {
                status = true;
                for (int n = 0; n < questionList.size(); n++) {
                    if (Integer.valueOf(object.getTag().toString()) == questionList.get(n).getTag()) {
                        Question question = questionList.get(n);
                        question.getAnswerInputList().add(object.getText().toString());
                    }
                }
                return status;
            }
        }
        return status;
    }
    public void questionsNotAnswered() {
        Toast.makeText(getApplicationContext(), getString(R.string.questions_not_answered), Toast.LENGTH_SHORT).show(); // TODO: MANDATORY: DO NOT REMOVE
    }
}