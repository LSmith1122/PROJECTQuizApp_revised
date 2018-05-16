package com.example.android.projectquizapp;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Type of Questions to populate
    String testType;
    // Content Variables
    static boolean q1, q2, q3, q4, q5, allEditTextsAnswered = false;         // determines whether or not a question has been answered
    boolean answerCorrectQ1, answerCorrectQ2, answerCorrectQ3, answerCorrectQ4, answerCorrectQ5, answerCorrectQ5_A1, answerCorrectQ5_A2, answerCorrectQ5_A3;        // determines whether the answer chosen for its respective question is correct
    boolean areAllQuestionsAnswered = false;        // determines if all questions have been answered - not necessarily correct
    int q2_a2_stat, q2_a1_stat = 0;     // current state of whether both 'correct' options are selected for com.example.android.projectquizapp.Question 2
    static int q1_checkedOptionsQuantity, q2_checkedOptionsQuantity, q3_checkedOptionsQuantity, q4_checkedOptionsQuantity, q5_checkedOptionsQuantity, photography_score1, photography_score2, photography_score3 = 0;     // quantity of objects checked within its respective group
    String q_string;        // com.example.android.projectquizapp.Question 5's TextBox text (String)
    int maxScore = 5;

    static ArrayList<Question> questionList = new ArrayList<Question>();
    static ArrayList<Boolean> booleanArrayList = new ArrayList<Boolean>();
    static ArrayList<EditText> editTextArrayList = new ArrayList<EditText>();
    static ArrayList<String> editTextAnswerList = new ArrayList<String>();
    static ArrayList<String> answerList_q1 = new ArrayList<String>();
    static ArrayList<String> answerList_q2 = new ArrayList<String>();
    static ArrayList<String> answerList_q3 = new ArrayList<String>();
    static ArrayList<String> answerList_q4 = new ArrayList<String>();
    static ArrayList<String> answerList_q5 = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This is in place to keep each variable in chronological order and reference them accordingly
        booleanArrayList.add(q1);
        booleanArrayList.add(q2);
        booleanArrayList.add(q3);
        booleanArrayList.add(q4);
        booleanArrayList.add(q5);

        populateActivity();
    }

    private final void populateActivity() {
        String[] questionResourceList = getResources().getStringArray(R.array.photography_questions);

        Question question1 = new Question(1, questionResourceList[0], "RadioButton");
        Question question2 = new Question(2, questionResourceList[1], "CheckBox");
        Question question3 = new Question(3, questionResourceList[2], "RadioButton");
        Question question4 = new Question(4, questionResourceList[3], "CheckBox", "cameras");
        Question question5 = new Question(5, questionResourceList[4], "Text");

        questionList.add(question1);
        questionList.add(question2);
        questionList.add(question3);
        questionList.add(question4);
        questionList.add(question5);

        QuestionAdapter adapter = new QuestionAdapter(this, questionList);
        ListView listView = (ListView) findViewById(R.id.parent_group);
        listView.setAdapter(adapter);
    }

    public void checkResponse(View view) {
        if (q1 && q2 && q3 && q4) {

            // TODO: Implement a method that checks the answers of all RadioButtons and CheckBoxes

            checkEditTextAnswers();
            if (allEditTextsAnswered) {
                
            }

//        if (areAllQuestionsAnswered()) {         // if all questions answered...
//            String finalScore = String.valueOf(tallyAnswers());
//            Intent intent = new Intent(this, FinalScore_Activity.class);
//            Bundle bundle = new Bundle();
//            bundle.putString("final score", finalScore);
//            intent.putExtras(bundle);
//            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.questions_not_answered), Toast.LENGTH_SHORT).show(); // TODO: MANDATORY: DO NOT REMOVE
        }
    }

    private void checkEditTextAnswers() {
        allEditTextsAnswered = false;
        // Add answers to an array list & list each answer within the appropriate index number - based on EditText tag number
        for (int i = 0; i < editTextArrayList.size(); i++) {
            EditText editTextObject = editTextArrayList.get(i);
            if (isEditTextAnswered(editTextObject)) {
                allEditTextsAnswered = true;
                int index = Integer.valueOf(editTextObject.getTag().toString()) - 1;
                for (Question question : questionList) {
                    question.checkAnswers(editTextObject.getText().toString());
                }
//                editTextAnswerList.add(index, object.getText().toString());
            } else {
                allEditTextsAnswered = false;
                Toast.makeText(getApplicationContext(), getString(R.string.questions_not_answered), Toast.LENGTH_SHORT).show(); // TODO: MANDATORY: DO NOT REMOVE
                break;
            }
        }
    }

    private boolean isEditTextAnswered(EditText object) {
        boolean status = false;
        for (int i = 0; i < booleanArrayList.size(); i++) {
            int alignedIndexNumber = Integer.valueOf(object.getTag().toString()) - 1;
            if (!object.getText().equals("") && !object.getText().equals(null)) {
                booleanArrayList.set(alignedIndexNumber, true);
                status = true;
                return status;
            }
        }
        return status;
    }
//    public boolean areAllQuestionsAnswered() {
//        if (q1 && q2 && q3 && q4) {
//            EditText textboxS = findViewById(R.id.question_5_answer_1);
//            EditText textboxL = findViewById(R.id.question_5_answer_2);
//            EditText textboxR = findViewById(R.id.question_5_answer_3);
//            if (answeredQ5(textboxS) && answeredQ5(textboxL) && answeredQ5(textboxR)) {
//                areAllQuestionsAnswered = true;
//            } else {
//                Toast.makeText(getApplicationContext(), "The last question is not answered thoroughly", Toast.LENGTH_SHORT).show();
//            }
//        } else {            // All questions within 1-4 were NOT answered...
//            Toast.makeText(getApplicationContext(), "Please answer all questions", Toast.LENGTH_SHORT).show(); // TODO: MANDATORY: DO NO REMOVE
//        }
//        return areAllQuestionsAnswered;
//    }
//    public double tallyAnswers() {
//        double photography_score = 0;
//        if (answerCorrectQ1) {
//            photography_score++;
//        }
//        if (answerCorrectQ2) {
//            photography_score++;
//        }
//        if (answerCorrectQ3) {
//            photography_score++;
//        }
//        if (answerCorrectQ4) {
//            photography_score++;
//        }
//        if (answerCorrectQ5()) {
//            photography_score = photography_score1 + photography_score2 + photography_score3;
//        }
//        return photography_score / maxScore * 100;
//    }
//    public boolean answeredQ5(EditText textBox) {
//        String nullString = "";
//        if (!textBox.getText().toString().equals(nullString)) {
//            q5 = true;
//            q_string = textBox.getText().toString();
//            return true;
//        } else {        // if there is no input
//            q5 = false;
//            return false;
//        }
//    }
//    public boolean answerCorrectQ5() {
//        EditText textboxS = findViewById(R.id.question_5_answer_1);
//        EditText textboxL = findViewById(R.id.question_5_answer_2);
//        EditText textboxR = findViewById(R.id.question_5_answer_3);
//        checkTextBoxAnswers(textboxS);
//        checkTextBoxAnswers(textboxL);
//        checkTextBoxAnswers(textboxR);
//        if (answerCorrectQ5_A1 || answerCorrectQ5_A2 || answerCorrectQ5_A3) {
//            if (answerCorrectQ5_A1) {
//                photography_score1 = 1;
//            }
//            if (answerCorrectQ5_A2) {
//                photography_score2 = 1;
//            }
//            if (answerCorrectQ5_A3) {
//                photography_score3 = 1;
//            }
//            return answerCorrectQ5 = true;
//        }
//        else {
//            return answerCorrectQ5 = false;
//        }
//    }
//    public void checkTextBoxAnswers(EditText tB) {
//        String answerInput = tB.getText().toString().toLowerCase();
//        switch (tB.getId()) {
//            case R.id.question_5_answer_1:
//                answerCorrectQ5_A1 = checkAnswersForSLR( "single", answerInput);
//            case R.id.question_5_answer_2:
//                answerCorrectQ5_A2 = checkAnswersForSLR( "lens", answerInput);
//            case R.id.question_5_answer_3:
//                answerCorrectQ5_A3 = checkAnswersForSLR( "reflex", answerInput);
//        }
//    }
//    public boolean checkAnswersForSLR(String correctAnswer, String answerInput) {
//        if (correctAnswer.equalsIgnoreCase(answerInput)) {
//            if (answerInput.contains(correctAnswer)) {           // correct
//                return true;
//            }
//        }
//        return false;
//    }


//    public void onButtonClicked(View view) {
//        if (view.getClass().getName().equals("android.support.v7.widget.AppCompatCheckBox")) {
//            Boolean checked = ((CheckBox) view).isChecked();
//            if (checked) {
//                switch (view.getTag().toString()) {
//                    case "question 2":      // If Tag is for 2nd com.example.android.projectquizapp.Question
//                        q2 = true;
//                        q2_checkedOptionsQuantity += 1;
//                        switch (view.getId()) {
//                            case R.id.question_2_option_1:      // if 1st Option...
//                                if (q2_a2_stat == 1 && q2_checkedOptionsQuantity == 2) {      // Only Option Checked...
//                                    answerCorrectQ2 = true;     // Correct Answers
//                                } else {     // Second Option already Checked...
//                                    q2_a1_stat = 1;
//                                    answerCorrectQ2 = false;
//                                }
//                                break;
//                            case R.id.question_2_option_2:      // if 2nd Option...
//                                if (q2_a1_stat == 1 && q2_checkedOptionsQuantity == 2) {      // Only Option Checked...
//                                    answerCorrectQ2 = true;     // Correct Answers
//                                } else {        // Second Option already Checked...
//                                    q2_a2_stat = 1;
//                                    answerCorrectQ2 = false;
//                                }
//                                break;
//                            case R.id.question_2_option_3:
//                                answerCorrectQ2 = false;
//                                break;
//                            case R.id.question_2_option_4:
//                                answerCorrectQ2 = false;
//                                break;
//                        }
//                    case "question 4":      // If Tag is for 4th com.example.android.projectquizapp.Question
//                        answerCorrectQ4 = testAnswers(view, q4, q4_checkedOptionsQuantity, 1, getString(R.string.correct_answer_q4));
////                        q4 = true;
////                        q4_checkedOptionsQuantity += 1;
////                        switch (view.getId()) {
////                            case R.id.question_4_option_1:
////                                answerCorrectQ4 = false;
////                                break;
////                            case R.id.question_4_option_2:
////                                answerCorrectQ4 = false;
////                                break;
////                            case R.id.question_4_option_3:
////                                answerCorrectQ4 = false;
////                                break;
////                            case R.id.question_4_option_4:
////                                answerCorrectQ4 = true;     // Correct com.example.android.projectquizapp.Answer
////                                break;
////                        }
//                }
//            } else {        // Unchecked Object...
//                switch (view.getTag().toString()) {
//                    case "question 2":
//                        if (q2_checkedOptionsQuantity > 0) {
//                            q2_checkedOptionsQuantity -= 1;
//                            if (q2_checkedOptionsQuantity == 0) {
//                                q2 = false;
//                                q2_a1_stat = 0;
//                                q2_a2_stat = 0;
//                            } else {
//                                switch (view.getId()) {
//                                    case R.id.question_2_option_1:      // if 1st Option...
//                                        q2_a1_stat = 0;
//                                        answerCorrectQ2 = false;
//                                        break;
//                                    case R.id.question_2_option_2:
//                                        q2_a2_stat = 0;
//                                        answerCorrectQ2 = false;
//                                        break;
//                                    case R.id.question_2_option_3:
//                                        break;
//                                    case R.id.question_2_option_4:
//                                        break;
//                                }
//                            }
//                        } else {
//                            break;
//                        }
//                    case "question 4":
//                        if (q4_checkedOptionsQuantity > 0) {
//                            q4 = false;
//                            q4_checkedOptionsQuantity -= 1;
//                            Log.i("xxxxOOOOxxxx: ", "FALSE");
//                            break;
//                        } else {
//                            break;
//                        }
//                }
//            }
//        } else if (view.getClass().getName().equals("android.support.v7.widget.AppCompatRadioButton")) {
//            Boolean checked = ((RadioButton) view).isChecked();
//            if (checked) {
//                switch (view.getTag().toString()) {
//                    case "question 1":      // If Tag is for 1st com.example.android.projectquizapp.Question
//                        answerCorrectQ1 = testAnswers(view, q1, q1_checkedOptionsQuantity, 1, getString(R.string.correct_answer_q1));
//                        break;
////                        q1 = true;
////                        q1_checkedOptionsQuantity += 1;
////                        switch (view.getId()) {
////                            case R.id.question_1_option_1:
////                                answerCorrectQ1 = false;
////                                break;
////                            case R.id.question_1_option_2:
////                                answerCorrectQ1 = false;
////                                break;
////                            case R.id.question_1_option_3:
////                                answerCorrectQ1 = false;
////                                break;
////                            case R.id.question_1_option_4:
////                                answerCorrectQ1 = true;     // Correct com.example.android.projectquizapp.Answer
////                                break;
////                        }
//
//                    case "question 3":      // If Tag is for 3rd com.example.android.projectquizapp.Question
//                        answerCorrectQ3 = testAnswers(view, q3, q3_checkedOptionsQuantity, 1, getString(R.string.correct_answer_q3));
//                        break;
////                        q3 = true;
////                        q3_checkedOptionsQuantity += 1;
////                        switch (view.getId()) {
////                            case R.id.question_3_option_1:
////                                answerCorrectQ3 = true;     // Correct com.example.android.projectquizapp.Answer
////                                break;
////                            case R.id.question_3_option_2:
////                                answerCorrectQ3 = false;
////                                break;
////                        }
//                }
//            } else {
//                switch (view.getTag().toString()) {
//                    case "question 1":
//                        q1 = false;
//                        q1_checkedOptionsQuantity -= 1;
//                        break;
//                    case "question 3":
//                        q3 = false;
//                        q3_checkedOptionsQuantity -= 1;
//                        break;
//                }
//            }
//        }
//    }
//    public boolean testAnswers(View view, boolean qNum, int qQuantity, int maxNum, String correct) {
//        qNum = true;
//        qQuantity += 1;
//        boolean bool = false;
//        if (qQuantity == maxNum) {
//            if (view.getClass().getName().equals("android.support.v7.widget.AppCompatRadioButton")) {
//                if (((RadioButton) view).getText().toString().equals(correct)) {
//                    bool = true;
//                    Log.i("xxxxOOOOxxxx: ", "TRUE " + String.valueOf(qQuantity));
//                    return bool;     // Correct com.example.android.projectquizapp.Answer
//                }
//            }
//            if (view.getClass().getName().equals("android.support.v7.widget.AppCompatCheckBox")) {
//                if (((CheckBox) view).getText().toString().equals(correct)) {
//                    bool = true;
//                    Log.i("xxxxOOOOxxxx: ", "TRUE " + String.valueOf(qQuantity));
//                    return bool;     // Correct com.example.android.projectquizapp.Answer
//                }
//            }
//        } else {
//            bool = false;
//            return bool;
//        }
//        Log.i("xxxxOOOOxxxx: ", "FALSE");
//        return bool;
//        // TODO: Issue: When more than 1 CheckBox is checked in order of [incorrect] > [correct] the answer comes out correct // Problem: Figure out a way to return false if more than one answer is checked, but only one is correct
//        // TODO: Issue: Within testAnswers(), qQuantity is not a global variable, therefore local variable will not 'remember' if another answer was selected - its value will always be 1

        //  ---------------------------------------------------------------------------------------------------------------------

        // can you access the placeholder's original variable within the method?
//    }
//    public void testAnswers(View view, boolean qNum, int qQuantity, String correct1, String correct2, boolean answerCorrect) {
//        qNum = true;
//        qQuantity += 1;
//        if (view.getClass().getName().equals("android.support.v7.widget.AppCompatRadioButton")) {
//            if (((RadioButton) view).getText().toString().equals(correct)) {
//                answerCorrect = true;     // Correct com.example.android.projectquizapp.Answer
//            } else {
//                answerCorrect = false;
//            }
//        }
//    }
}