package com.example.petarstanev.mathgame;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScoreTable {
    private ArrayList<Score> scores;
    private Context context;
    private SharedPreferences highscore;
    private SharedPreferences.Editor editor;

    public HighScoreTable() {

        scores = new ArrayList<Score>();
        highscore = context.getSharedPreferences("highScore", 0);
        editor = highscore.edit();
    }




    public void uploadScores(){
        Collections.sort(scores);

        for (int i = 0; i < 3; i++) {
            if (scores.size()>i){
                Score score = scores.get(i);
                editor.putString("player_name"+i, score.getName());
                editor.putInt("player_points"+i, score.getPoints());
            }
        }
        editor.apply();
    }

    public String getScoreAsString(int placeNumber){
        String name,points;
        if (highscore.contains("player_name"+placeNumber)){
            name = highscore.getString("player_name"+placeNumber,"");
            points = highscore.getString("player_points"+placeNumber,"");
            return name + " - "  + points;
        }

        return  "";
    }

    
}

