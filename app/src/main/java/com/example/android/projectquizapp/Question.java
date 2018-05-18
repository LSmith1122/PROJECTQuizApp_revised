package com.example.android.projectquizapp;
import android.util.Log;
import java.util.ArrayList;
public class Question {
    private int tagNumber;
    private String imageResourceID;
    private Boolean imageAvailable;
    private Boolean questionAnsweredStatus;
    private Boolean answerCorrect;
    private int checkedOptionsQuantity;
    private String questionString, answerLongString, correctAnswerLongString, answerType;
    private ArrayList<String> answerList = new ArrayList<String>();
    private ArrayList<String> answerInputList = new ArrayList<String>();
    private ArrayList<String> correctAnswerList = new ArrayList<String>();
    private int wrongAnswerCount = 0;

    Question(int tag, String questionStringList, String answerSelectionType) {
        tagNumber = tag;
        answerType = answerSelectionType;
        imageAvailable = false;
        checkedOptionsQuantity = 0;
        answerCorrect = false;
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
        checkedOptionsQuantity = 0;
        answerCorrect = false;
        compileStrings(questionStringList);
        String[] correctAnswerSimpleList = correctAnswerLongString.split(", ");
        for (String stringNuggets : correctAnswerSimpleList) {
            correctAnswerList.add(stringNuggets);
        }
    }
    public int getTag() { return tagNumber; }
    public String getAnswerType() { return answerType; }
    public String getQuestion() { return questionString; }
    public String getAnswerAtIndex(int position) { return answerList.get(position); }
    public ArrayList<String> getAnswerList() {
        return answerList;
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
    public int getAnswerSize() { return answerList.size(); }
    public Boolean isImageVisible() { return imageAvailable; }
    public String getImageResourceID() {
        return imageResourceID; }
    public void checkAnswers(String response) {     // For EditText Questions only
        answerCorrect = false;
        wrongAnswerCount = 0;
        for (int i = 0; i < response.length(); i++) {
            if (response.endsWith(" ")) {
                response = response.substring(0, response.length() - 1);
            }
        }
        if (correctAnswerLongString.equalsIgnoreCase(response)) {
            answerCorrect = true;
        } else {
            answerCorrect = false;
        }
    }
    public void checkAnswers() {      // For RadioButton and CheckBox Questions
        answerCorrect = false;
        wrongAnswerCount = 0;
        int counter = 0;
        int maxCounter = getCorrectAnswers().size();
        for (int a = 0; a < getAnswerInputList().size(); a++) {
            if (getCorrectAnswers().contains(getAnswerInputList().get(a))) {
                counter++;
            } else {
                wrongAnswerCount++;
            }
        }
        if (getAnswerType().contains("RadioButton")) {
            if (counter == maxCounter && wrongAnswerCount <= 0) {
                answerCorrect = true;
            } else {
                answerCorrect = false;
            }
        }
        if (getAnswerType().contains("CheckBox")) {
            if (wrongAnswerCount <= 0) {
                answerCorrect = true;
            } else {
                answerCorrect = false;
            }
        }
    }
    public boolean isAnswerCorrect() { return answerCorrect; }
    public void questionAnswered(Boolean status) { questionAnsweredStatus = status; }   // For EditText Questions only
    public Boolean isQuestionAnswered() {
        boolean status = false;
        if (getAnswerInputList().size() > 0) {
            status = true;
        } else {
            status = false;
            Log.i("TEST", "Question " + getTag() + " is not answered. Input: " + getAnswerInputList());
        }
        return status;
    }
    public int getCheckedOptionsQuantity() { return checkedOptionsQuantity; }
    public void checkedOptionsQuantityIncrement() { checkedOptionsQuantity++; }
    public void checkedOptionsQuantityDecrement() { checkedOptionsQuantity--; }
    public ArrayList<String> getAnswerInputList() {
        return answerInputList;
    }
}