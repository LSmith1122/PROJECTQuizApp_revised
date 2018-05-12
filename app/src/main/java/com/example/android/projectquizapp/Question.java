package com.example.android.projectquizapp;

import android.util.Log;

import java.util.ArrayList;

public class Question {
    private int tagNumber;
    private String imageResourceID;
    private Boolean imageAvailable;
    private String questionString, answerLongString, correctAnswerLongString, answerType;
    private ArrayList<String> answerList = new ArrayList<String>();
    private ArrayList<String> correctAnswerList = new ArrayList<String>();
    private ArrayList<String> selectedAnswerList = new ArrayList<String>();

    Question(int tag, String questionStringList, String answerSelectionType) {
        tagNumber = tag;
        answerType = answerSelectionType;
        imageAvailable = false;

        compileStrings(questionStringList);
        String[] correctAnswerSimpleList = correctAnswerLongString.split(", ");
        for (String stringNuggets : correctAnswerSimpleList) {
            correctAnswerList.add(stringNuggets);
        }
    }

    Question(int tag, String questionStringList, String answerSelectionType, String imageResourcePath) {
        tagNumber = tag;
        answerType = answerSelectionType;
        imageAvailable = true;
        imageResourceID = imageResourcePath;

        compileStrings(questionStringList);
        String[] correctAnswerSimpleList = correctAnswerLongString.split(", ");
        for (String stringNuggets : correctAnswerSimpleList) {
            correctAnswerList.add(stringNuggets);
        }
    }

    public int getTag() { return tagNumber; }

    public String getAnswerType() {
        return answerType;
    }

    public String getQuestion() { return questionString; }

    public String getAnswer(int position) {
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

    public Boolean isImageVisible() { return imageAvailable; }

    public String getImageResourceID() {
        return imageResourceID; }
}