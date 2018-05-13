package com.example.android.projectquizapp;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
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
                        answeredQuestion(button, Integer.valueOf(view.getTag().toString()),true);
                    } else {
                        answeredQuestion(button, Integer.valueOf(view.getTag().toString()),false);
                    }
                }
                break;
            case "android.support.v7.widget.AppCompatCheckBox":
                if (((CheckBox)view).isChecked()) {
                    answeredQuestion(view, Integer.valueOf(view.getTag().toString()),true);
                    break;
                } else {
                    if (quantityAmount((CheckBox)view) > 0) {
                        answeredQuestion(view, Integer.valueOf(view.getTag().toString()),false);
                    }
                    break;
                }
        }
    }

    public int quantityAmount(CheckBox button) {        // Checks the amount of answers that are selected based on the tag of the view supplied...
        int amount = 0;
        switch (Integer.valueOf(button.getTag().toString())) {
            case 1:
                amount = MainActivity.q1_checkedOptionsQuantity;
                break;
            case 2:
                amount = MainActivity.q2_checkedOptionsQuantity;
                break;
            case 3:
                amount = MainActivity.q3_checkedOptionsQuantity;
                break;
            case 4:
                amount = MainActivity.q4_checkedOptionsQuantity;
                break;
            case 5:
                amount = MainActivity.q5_checkedOptionsQuantity;
                break;
            // Add more cases below as needed...
        }
        return amount;
    }

    public void answeredQuestion(View view, int tagNumber, boolean status) {
        if (status) {       // If currently focused button is being Checked...
            switch (tagNumber) {
                case 1:
                    MainActivity.q1 = true;
                    MainActivity.q1_checkedOptionsQuantity++;
                    if (view.getClass().toString().contains("RadioButton")) {
                        if (!MainActivity.answerList_q1.contains(((RadioButton)view).getText())) {
                            MainActivity.answerList_q1.clear();
                            MainActivity.answerList_q1.add(((RadioButton)view).getText().toString());
                        }
                    } else {
                        MainActivity.answerList_q1.add(((CheckBox)view).getText().toString());
                    }
                    break;
                case 2:
                    MainActivity.q2 = true;
                    MainActivity.q2_checkedOptionsQuantity++;
                    if (view.getClass().toString().contains("RadioButton")) {
                        if (!MainActivity.answerList_q2.contains(((RadioButton)view).getText())) {
                            MainActivity.answerList_q2.clear();
                            MainActivity.answerList_q2.add(((RadioButton)view).getText().toString());
                        }
                    } else {
                        MainActivity.answerList_q2.add(((CheckBox)view).getText().toString());
                    }
                    break;
                case 3:
                    MainActivity.q3 = true;
                    MainActivity.q3_checkedOptionsQuantity++;
                    if (view.getClass().toString().contains("RadioButton")) {
                        if (!MainActivity.answerList_q3.contains(((RadioButton)view).getText())) {
                            MainActivity.answerList_q3.clear();
                            MainActivity.answerList_q3.add(((RadioButton)view).getText().toString());
                        }
                    } else {
                        MainActivity.answerList_q3.add(((CheckBox)view).getText().toString());
                    }
                    break;
                case 4:
                    MainActivity.q4 = true;
                    MainActivity.q4_checkedOptionsQuantity++;
                    if (view.getClass().toString().contains("RadioButton")) {
                        if (!MainActivity.answerList_q4.contains(((RadioButton)view).getText())) {
                            MainActivity.answerList_q4.clear();
                            MainActivity.answerList_q4.add(((RadioButton)view).getText().toString());
                        }
                    } else {
                        MainActivity.answerList_q4.add(((CheckBox)view).getText().toString());
                    }
                    break;
                case 5:
                    MainActivity.q5 = true;
                    MainActivity.q5_checkedOptionsQuantity++;
                    if (view.getClass().toString().contains("RadioButton")) {
                        if (!MainActivity.answerList_q5.contains(((RadioButton)view).getText())) {
                            MainActivity.answerList_q5.clear();
                            MainActivity.answerList_q5.add(((RadioButton)view).getText().toString());
                        }
                    } else {
                        MainActivity.answerList_q5.add(((CheckBox)view).getText().toString());
                    }
                    break;
            }
        } else {        // If button is unchecked...
            switch (tagNumber) {
                case 1:
                    if (MainActivity.q1_checkedOptionsQuantity >= 1) {
                        MainActivity.q1_checkedOptionsQuantity--;
                        if (view.getClass().toString().contains("RadioButton")) {
                            MainActivity.answerList_q1.remove(((RadioButton)view).getText().toString());
                            Log.i("TEST: ", "Tag: " + tagNumber + " contains: " + MainActivity.answerList_q1);
                        } else {
                            MainActivity.answerList_q1.remove(((CheckBox)view).getText().toString());
                            Log.i("TEST: ", "Tag: " + tagNumber + " contains: " + MainActivity.answerList_q1);
                        }
                    }
                    if (MainActivity.q1_checkedOptionsQuantity <= 0) {
                        MainActivity.q1 = false;
                    }
                    break;
                case 2:
                    if (MainActivity.q2_checkedOptionsQuantity >= 1) {
                        MainActivity.q2_checkedOptionsQuantity--;
                        if (view.getClass().toString().contains("RadioButton")) {
                            MainActivity.answerList_q2.remove(((RadioButton)view).getText().toString());
                            Log.i("TEST: ", "Tag: " + tagNumber + " contains: " + MainActivity.answerList_q2);
                        } else {
                            MainActivity.answerList_q2.remove(((CheckBox)view).getText().toString());
                            Log.i("TEST: ", "Tag: " + tagNumber + " contains: " + MainActivity.answerList_q2);
                        }
                    }
                    if (MainActivity.q2_checkedOptionsQuantity <= 0) {
                        MainActivity.q2 = false;
                    }
                    break;
                case 3:
                    if (MainActivity.q3_checkedOptionsQuantity >= 1) {
                        MainActivity.q3_checkedOptionsQuantity--;
                        if (view.getClass().toString().contains("RadioButton")) {
                            MainActivity.answerList_q3.remove(((RadioButton)view).getText().toString());
                            Log.i("TEST: ", "Tag: " + tagNumber + " contains: " + MainActivity.answerList_q3);
                        } else {
                            MainActivity.answerList_q3.remove(((CheckBox)view).getText().toString());
                            Log.i("TEST: ", "Tag: " + tagNumber + " contains: " + MainActivity.answerList_q3);
                        }
                    }
                    if (MainActivity.q3_checkedOptionsQuantity <= 0) {
                        MainActivity.q3 = false;
                    }
                    break;
                case 4:
                    if (MainActivity.q4_checkedOptionsQuantity >= 1) {
                        MainActivity.q4_checkedOptionsQuantity--;
                        if (view.getClass().toString().contains("RadioButton")) {
                            MainActivity.answerList_q4.remove(((RadioButton)view).getText().toString());
                            Log.i("TEST: ", "Tag: " + tagNumber + " contains: " + MainActivity.answerList_q4);
                        } else {
                            MainActivity.answerList_q4.remove(((CheckBox)view).getText().toString());
                            Log.i("TEST: ", "Tag: " + tagNumber + " contains: " + MainActivity.answerList_q4);
                        }
                    }
                    if (MainActivity.q4_checkedOptionsQuantity <= 0) {
                        MainActivity.q4 = false;
                    }
                    break;
                case 5:
                    if (MainActivity.q5_checkedOptionsQuantity >= 1) {
                        MainActivity.q5_checkedOptionsQuantity--;
                        if (view.getClass().toString().contains("RadioButton")) {
                            MainActivity.answerList_q5.remove(((RadioButton)view).getText().toString());
                        } else {
                            MainActivity.answerList_q5.remove(((CheckBox)view).getText().toString());
                        }
                    }
                    if (MainActivity.q5_checkedOptionsQuantity <= 0) {
                        MainActivity.q5 = false;
                    }
                    break;
                // Add more cases below as needed...
            }
        }
    }
}
