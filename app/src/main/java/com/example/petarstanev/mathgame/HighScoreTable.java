package com.example.petarstanev.mathgame;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScoreTable {
    private List<Score> scores;
    private Context context;

    public HighScoreTable(Context context) {
        scores = new ArrayList<Score>();
        retrieveScores();
        this.context = context;
    }

    public boolean checkHighScore(Score testScore){
        Collections.sort(scores);

        if (scores.size() <= 3)
            return true;

        if (scores.get(2).getPoints() < testScore.getPoints())
            return true;

        return false;
    }

    public void addScore(Score testScore){
        scores.add(testScore);
        Collections.sort(scores);
        try {
            InternalStorage.writeObject(context,"highscores", scores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Score> retrieveScores(){
        try {
            scores = (List<Score>) InternalStorage.readObject(context, "highscores");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scores;
    }
}

