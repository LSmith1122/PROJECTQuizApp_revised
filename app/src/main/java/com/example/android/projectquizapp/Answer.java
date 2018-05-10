package com.example.android.projectquizapp;

import java.util.ArrayList;

public class Answer {
    String answerString;

    Answer(Class classType, String answerLongString) {
        answerString = answerLongString;
        checkClassType(classType);


    }

    Answer(Class classType, String answer, int imgResourceID) {

    }

    public String getAnswer() {
        return answerString;
    }
    private void checkClassType(Class type) {

    }
}