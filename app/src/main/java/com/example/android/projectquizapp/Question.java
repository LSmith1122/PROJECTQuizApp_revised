package com.example.android.projectquizapp;

import android.util.Log;

import java.util.ArrayList;

public class Question {
    public int tagNumber;
    public String questionString, answerLongString, correctAnswerLongString, answerType;
    ArrayList<String> answerList = new ArrayList<String>();
    ArrayList<String> correctAnswerList = new ArrayList<String>();

    Question(int tag, String questionStringList, String answerSelectionType) {
        tagNumber = tag;
        answerType = answerSelectionType;

        compileStrings(questionStringList);
        String[] correctAnswerSimpleList = correctAnswerLongString.split(", ");
        for (String stringNuggets : correctAnswerSimpleList) {
            correctAnswerList.add(stringNuggets);
        }
    }

//    Question(String tag, String question, String answer, String correctAnswer, String answerSelectionType, int imgResourceID) {
//
//    }

    public int getTag() { return tagNumber; }

    public String getAnswerType() {
        return answerType;
    }

    public String getQuestion() { return questionString; }

    public String getAnswer(int position) {
        Log.i("xxxxOOOOxxxx Error: ", "Position - " + answerList.toString());
        return answerList.get(position);
    }

    public ArrayList<String> getCorrectAnswers() { return correctAnswerList; }

    private void compileStrings(String set) {
        String[] combinedList = set.split(" > ");
        int tracker = 1;
        for (String strings : combinedList) {
            if (tracker == 1) {
                questionString = strings;
            }
            if (tracker == 2){
                answerLongString = strings;
            }
            if (tracker == 3) {
                correctAnswerLongString = strings;
            }
            tracker++;
        }
        String[] answerSimpleList = answerLongString.split(", ");
        for (String answerStringNuggets : answerSimpleList) {
            answerList.add(answerStringNuggets);
        }
    }

    public int getAnswerSize() {
        return answerList.size();
    }
}