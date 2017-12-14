package com.example.petarstanev.mathgame;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HighScoreActivity extends AppCompatActivity {
    private HighScoreTable table;
    TextView textViewFirstPlace;
    TextView textViewSecondPlace;
    TextView textViewThirdPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_high_score);
        table = new HighScoreTable(this);
        setupButtons();
        loadHighScore();
    }

    private void setupButtons(){
        textViewFirstPlace = (TextView) findViewById(R.id.textViewFirstPlace);
        textViewSecondPlace = (TextView) findViewById(R.id.textViewSecondPlace);
        textViewThirdPlace = (TextView) findViewById(R.id.textViewThirdPlace);
    }

    private void loadHighScore(){
        List<Score> scores =  table.retrieveScores();
        Collections.sort(scores);
        if (scores.size()>0)
            textViewFirstPlace.setText(scores.get(0).getName() + " - " + scores.get(0).getPoints());
        if (scores.size()>1)
            textViewSecondPlace.setText(scores.get(1).getName() + " - " + scores.get(1).getPoints());
        if (scores.size()>2)
            textViewThirdPlace.setText(scores.get(2).getName() + " - " + scores.get(2).getPoints());
    }
}
