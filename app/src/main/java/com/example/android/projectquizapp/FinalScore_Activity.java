package com.example.android.projectquizapp;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class FinalScore_Activity extends MainActivity {

    TextView finalScoreTextView;
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score_);

        finalScoreTextView = findViewById(R.id.final_score_text_view);
        Bundle bundle = getIntent().getExtras();
        value = bundle.getString("final score");

        getTextColor(value);

        String finalScoreString = value + "%";
        finalScoreTextView.setText(finalScoreString);
        Toast.makeText(getApplicationContext(), finalScoreString, Toast.LENGTH_LONG).show();        // Here is a Toast of the final score
    }

    public void getTextColor(String score) {
        double v = Double.parseDouble(value);
        if (v <= 69.9) {
            finalScoreTextView.setTextColor(Color.RED);
        } else if (v >= 70.0 && v <= 99.9) {
            finalScoreTextView.setTextColor(Color.GREEN);
        } else if (v == 100.0) {
            finalScoreTextView.setTextColor(Color.BLUE);
        }
    }
}
