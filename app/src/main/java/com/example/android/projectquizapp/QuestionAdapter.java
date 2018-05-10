package com.example.android.projectquizapp;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionAdapter extends ArrayAdapter<Question> {

    public QuestionAdapter(Activity context, ArrayList<Question> questions) {
        super(context, 0, questions);
    }
    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        View listItemView = convertview;
        Question currentQuestion = getItem(position);

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.custom_question_item, parent, false);
            }

        Question currentQuestionRadioButton = currentQuestion;
        TextView questionTextView = (TextView) listItemView.findViewById(R.id.question);
        questionTextView.setText(currentQuestionRadioButton.getQuestion());
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.question_image);

        if (currentQuestion.isImageVisible()) {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(listItemView.getResources().getIdentifier(currentQuestion.getImageResourceID(),
                    "drawable", getContext().getPackageName()));
        } else {
            imageView.setVisibility(View.GONE);
        }

        if (currentQuestion.getAnswerType().equals("RadioButton")) {
            ((RadioGroup) listItemView.findViewById(R.id.radio_group)).setVisibility(View.VISIBLE);
            ((LinearLayout) listItemView.findViewById(R.id.checkbox_group)).setVisibility(View.GONE);
            ((EditText) listItemView.findViewById(R.id.textbox)).setVisibility(View.GONE);
            RadioButton questionRadioButton1 = (RadioButton) listItemView.findViewById(R.id.question_option_1);
            questionRadioButton1.setText(currentQuestionRadioButton.getAnswer(0));
            RadioButton questionRadioButton2 = (RadioButton) listItemView.findViewById(R.id.question_option_2);
            questionRadioButton2.setText(currentQuestionRadioButton.getAnswer(1));
            RadioButton questionRadioButton3 = (RadioButton) listItemView.findViewById(R.id.question_option_3);
            RadioButton questionRadioButton4 = (RadioButton) listItemView.findViewById(R.id.question_option_4);

            if (currentQuestionRadioButton.getAnswerSize() == 2) {
                questionRadioButton3.setVisibility(questionRadioButton3.GONE);
                questionRadioButton4.setVisibility(questionRadioButton4.GONE);
            }
            if (currentQuestionRadioButton.getAnswerSize() == 3) {
                questionRadioButton3.setText(currentQuestionRadioButton.getAnswer(2));
                questionRadioButton4.setVisibility(questionRadioButton4.GONE);
            }
            if (currentQuestionRadioButton.getAnswerSize() == 4){
                questionRadioButton3.setText(currentQuestionRadioButton.getAnswer(2));
                questionRadioButton4.setText(currentQuestionRadioButton.getAnswer(3));
            }
        }
        if (currentQuestion.getAnswerType().equals("CheckBox")) {
            ((LinearLayout) listItemView.findViewById(R.id.checkbox_group)).setVisibility(View.VISIBLE);
            ((RadioGroup) listItemView.findViewById(R.id.radio_group)).setVisibility(View.GONE);
            ((EditText) listItemView.findViewById(R.id.textbox)).setVisibility(View.GONE);
            CheckBox questionRadioButton1 = (CheckBox) listItemView.findViewById(R.id.checkbox_question_option_1);
            questionRadioButton1.setText(currentQuestionRadioButton.getAnswer(0));
            CheckBox questionRadioButton2 = (CheckBox) listItemView.findViewById(R.id.checkbox_question_option_2);
            questionRadioButton2.setText(currentQuestionRadioButton.getAnswer(1));
            CheckBox questionRadioButton3 = (CheckBox) listItemView.findViewById(R.id.checkbox_question_option_3);
            CheckBox questionRadioButton4 = (CheckBox) listItemView.findViewById(R.id.checkbox_question_option_4);

            if (currentQuestionRadioButton.getAnswerSize() == 2) {
                questionRadioButton3.setVisibility(questionRadioButton3.GONE);
                questionRadioButton4.setVisibility(questionRadioButton4.GONE);
            }
            if (currentQuestionRadioButton.getAnswerSize() == 3) {
                questionRadioButton3.setText(currentQuestionRadioButton.getAnswer(2));
                questionRadioButton4.setVisibility(questionRadioButton4.GONE);
            }
            if (currentQuestionRadioButton.getAnswerSize() == 4){
                questionRadioButton3.setText(currentQuestionRadioButton.getAnswer(2));
                questionRadioButton4.setText(currentQuestionRadioButton.getAnswer(3));
            }
        }
        if (currentQuestion.getAnswerType().equals("Text")) {
            ((EditText) listItemView.findViewById(R.id.textbox)).setVisibility(View.VISIBLE);
            ((LinearLayout) listItemView.findViewById(R.id.checkbox_group)).setVisibility(View.GONE);
            ((RadioGroup) listItemView.findViewById(R.id.radio_group)).setVisibility(View.GONE);
        }
            return listItemView;
    }
}
