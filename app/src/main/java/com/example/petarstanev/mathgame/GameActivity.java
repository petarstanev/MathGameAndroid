package com.example.petarstanev.mathgame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class GameActivity extends AppCompatActivity {

    float x, y, z;
    SensorManager sensorManager;
    Sensor accelerometer;
    TextView textViewTop;
    TextView textViewRight;
    TextView textViewBottom;
    TextView textViewLeft;
    TextView textViewQuestion;
    TextView textViewLives;
    TextView textViewScore;
    TextView textViewGameOver;
    Button buttonNewGame;
    Button buttonMenu;
    Game game;

    SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                x = event.values[0];
                y = event.values[1];
                z = event.values[2];
            }
            /*textViewTop.setText(Float.toString(x));
            textViewLeft.setText(Float.toString(y));
            textViewRight.setText(Float.toString(z));*/

            if (Math.abs(y) > Math.abs(x)) {
                if (y > 3) {
                    validateAnswer(textViewRight,game.getQuestion().getAnswers().get(1));
                }
                if (y < -3) {
                    validateAnswer(textViewLeft,game.getQuestion().getAnswers().get(3));
                }
            } else {
                if (x < -3) {
                    validateAnswer(textViewTop,game.getQuestion().getAnswers().get(0));
                }
                if (x > 3) {
                    validateAnswer(textViewBottom,game.getQuestion().getAnswers().get(2));
                }
            }

            if (x > (-1) && x < (1) && y > (-1) && y < (1)) {
                if(game.GenerateQuestion())
                    printQuestion();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        private void validateAnswer(TextView textView, Answer answer){
            if (!game.getQuestion().isAnswered()){
                if (!answer.isSelected()){
                    if (game.submitAnswer(answer)){
                        textView.setBackgroundColor(Color.GREEN);
                    }else{
                        textView.setBackgroundColor(Color.RED);

                    }
                    updateScore();
                    if (game.isOver())
                        gameOverScreen();
                }
            }
        }
    };

    private void gameOverScreen(){
        textViewGameOver.setVisibility(View.VISIBLE);
        buttonMenu.setVisibility(View.VISIBLE);
        buttonNewGame.setVisibility(View.VISIBLE);
        buttonNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupGame();
            }
        });
        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(i);
            }
        });
    }

    private void setupGame(){
        game =  new Game();
        printQuestion();
        textViewGameOver.setVisibility(View.GONE);
        buttonMenu.setVisibility(View.GONE);
        buttonNewGame.setVisibility(View.GONE);
    }

    private void setupTextValues(){
        textViewTop = (TextView) findViewById(R.id.textViewTop);
        textViewRight = (TextView) findViewById(R.id.textViewRight);
        textViewBottom = (TextView) findViewById(R.id.textViewBottom);
        textViewLeft = (TextView) findViewById(R.id.textViewLeft);
        textViewQuestion = (TextView) findViewById(R.id.textViewQuestion);
        textViewLives = (TextView) findViewById(R.id.textViewLives);
        textViewScore = (TextView) findViewById(R.id.textViewScore);
        textViewGameOver = (TextView) findViewById(R.id.textViewGameOver);
        buttonNewGame = (Button) findViewById(R.id.buttonNewGame);
        buttonMenu = (Button) findViewById(R.id.buttonMenu);
        setupGame();
    }

    private void printQuestion(){
        clearColors();
        textViewQuestion.setText(game.getQuestion().toString());
        textViewTop.setText(String.valueOf(game.getQuestion().getAnswers().get(0).getNumber()));
        textViewRight.setText(String.valueOf(game.getQuestion().getAnswers().get(1).getNumber()));
        textViewBottom.setText(String.valueOf(game.getQuestion().getAnswers().get(2).getNumber()));
        textViewLeft.setText(String.valueOf(game.getQuestion().getAnswers().get(3).getNumber()));
    }

    private void clearColors(){
        textViewTop.setBackgroundColor(Color.TRANSPARENT);
        textViewRight.setBackgroundColor(Color.TRANSPARENT);
        textViewBottom.setBackgroundColor(Color.TRANSPARENT);
        textViewLeft.setBackgroundColor(Color.TRANSPARENT);
    }

    private void updateScore(){
        textViewScore.setText("Score: " + game.getScore());
        textViewLives.setText("Lives: " + game.getLives());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        setupTextValues();
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(listener, accelerometer,
                SensorManager.SENSOR_DELAY_NORMAL);
    }
}
