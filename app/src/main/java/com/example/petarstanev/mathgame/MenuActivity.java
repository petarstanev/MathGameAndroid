package com.example.petarstanev.mathgame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    Button buttonNewGame;
    Button buttonOptions;
    Button buttonHighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setupButtons();
    }

    private void setupButtons(){
        buttonNewGame = (Button) findViewById(R.id.buttonNewGame);
        buttonOptions = (Button) findViewById(R.id.buttonOptions);
        buttonHighScore = (Button) findViewById(R.id.buttonHighScore);

        buttonNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),GameActivity.class);
                startActivity(i);
            }
        });
    }
}
