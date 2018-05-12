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

public class QuestionAdapter extends ArrayAdapter<Question> implements View.OnClickListener {

    public QuestionAdapter(Activity context, ArrayList<Question> questions) {
        super(context, 0, questions);
    }

    View listItemView;
    Question currentQuestion;

    RadioGroup radioGroup;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioButton radioButton4;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        listItemView = convertview;
        currentQuestion = getItem(position);
        int tag = currentQuestion.getTag();

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
            radioButton1 = (RadioButton) listItemView.findViewById(R.id.question_option_1);
            radioButton2 = (RadioButton) listItemView.findViewById(R.id.question_option_2);
            radioButton3 = (RadioButton) listItemView.findViewById(R.id.question_option_3);
            radioButton4 = (RadioButton) listItemView.findViewById(R.id.question_option_4);

            radioButton1.setTag(tag);
            radioButton2.setTag(tag);
            radioButton3.setTag(tag);
            radioButton4.setTag(tag);
            radioButton1.setOnClickListener(this);
            radioButton2.setOnClickListener(this);
            radioButton3.setOnClickListener(this);
            radioButton4.setOnClickListener(this);

            radioButton1.setText(currentQuestionRadioButton.getAnswer(0));
            radioButton2.setText(currentQuestionRadioButton.getAnswer(1));

            if (currentQuestionRadioButton.getAnswerSize() == 2) {
                radioButton3.setVisibility(radioButton3.GONE);
                radioButton4.setVisibility(radioButton4.GONE);
            }
            if (currentQuestionRadioButton.getAnswerSize() == 3) {
                radioButton3.setText(currentQuestionRadioButton.getAnswer(2));
                radioButton4.setVisibility(radioButton4.GONE);
            }
            if (currentQuestionRadioButton.getAnswerSize() == 4){
                radioButton3.setText(currentQuestionRadioButton.getAnswer(2));
                radioButton4.setText(currentQuestionRadioButton.getAnswer(3));
            }
        }
        if (currentQuestion.getAnswerType().equals("CheckBox")) {
            ((LinearLayout) listItemView.findViewById(R.id.checkbox_group)).setVisibility(View.VISIBLE);
            ((RadioGroup) listItemView.findViewById(R.id.radio_group)).setVisibility(View.GONE);
            ((EditText) listItemView.findViewById(R.id.textbox)).setVisibility(View.GONE);
            checkBox1 = (CheckBox) listItemView.findViewById(R.id.checkbox_question_option_1);
            checkBox2 = (CheckBox) listItemView.findViewById(R.id.checkbox_question_option_2);
            checkBox3 = (CheckBox) listItemView.findViewById(R.id.checkbox_question_option_3);
            checkBox4 = (CheckBox) listItemView.findViewById(R.id.checkbox_question_option_4);

            checkBox1.setTag(tag);
            checkBox2.setTag(tag);
            checkBox3.setTag(tag);
            checkBox4.setTag(tag);
            checkBox1.setOnClickListener(this);
            checkBox2.setOnClickListener(this);
            checkBox3.setOnClickListener(this);
            checkBox4.setOnClickListener(this);

            checkBox1.setText(currentQuestionRadioButton.getAnswer(0));
            checkBox2.setText(currentQuestionRadioButton.getAnswer(1));

            if (currentQuestionRadioButton.getAnswerSize() == 2) {
                checkBox3.setVisibility(checkBox3.GONE);
                checkBox4.setVisibility(checkBox4.GONE);
            }
            if (currentQuestionRadioButton.getAnswerSize() == 3) {
                checkBox3.setText(currentQuestionRadioButton.getAnswer(2));
                checkBox4.setVisibility(checkBox4.GONE);
            }
            if (currentQuestionRadioButton.getAnswerSize() == 4){
                checkBox3.setText(currentQuestionRadioButton.getAnswer(2));
                checkBox4.setText(currentQuestionRadioButton.getAnswer(3));
            }
        }
        if (currentQuestion.getAnswerType().equals("Text")) {
            ((LinearLayout) listItemView.findViewById(R.id.checkbox_group)).setVisibility(View.GONE);
            ((RadioGroup) listItemView.findViewById(R.id.radio_group)).setVisibility(View.GONE);

            EditText textBox = (EditText) listItemView.findViewById(R.id.textbox);
            textBox.setVisibility(View.VISIBLE);
            textBox.setTag(tag);
        }
            return listItemView;
    }

    @Override
    public void onClick (View view){
        switch (view.getClass().getName()) {
            case "android.support.v7.widget.AppCompatRadioButton":
                radioGroup = (RadioGroup) view.getParent();
                int count = radioGroup.getChildCount();
                ArrayList<RadioButton> buttonGroup = new ArrayList<RadioButton>();
                for (int i = 0; i < count; i++) {
                    RadioButton button = (RadioButton) radioGroup.getChildAt(i);
                    if (button.getId() == radioGroup.getCheckedRadioButtonId()) {
                        if (!MainActivity.selectedAnswerList.contains(button.getText())) {
                            MainActivity.selectedAnswerList.add(button.getText().toString());
                        }
                    } else {
                        MainActivity.selectedAnswerList.remove(button.getText().toString());
                    }
                }
                break;
            case "android.support.v7.widget.AppCompatCheckBox":
                if (((CheckBox)view).isChecked()) {
                    MainActivity.selectedAnswerList.add(((CheckBox)view).getText().toString());
                    break;
                } else {
                    MainActivity.selectedAnswerList.remove(((CheckBox) view).getText().toString());
                    break;
                }
        }
    }
}
