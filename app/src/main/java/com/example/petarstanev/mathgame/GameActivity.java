package com.example.petarstanev.mathgame;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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
    Question question = new Question();
    TextView textViewTop;
    TextView textViewRight;
    TextView textViewBottom;
    TextView textViewLeft;
    TextView textViewQuestion;

    private void printQuestion(){
        clearColors();
        textViewQuestion.setText(question.toString());
        textViewTop.setText(String.valueOf(question.getAnswers().get(0).getNumber()));
        textViewRight.setText(String.valueOf(question.getAnswers().get(1).getNumber()));
        textViewBottom.setText(String.valueOf(question.getAnswers().get(2).getNumber()));
        textViewLeft.setText(String.valueOf(question.getAnswers().get(3).getNumber()));
    }

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
                    validateAnswer(textViewRight,question.getAnswers().get(1));
                }
                if (y < -3) {
                    validateAnswer(textViewLeft,question.getAnswers().get(3));
                }
            } else {
                if (x < -3) {
                    validateAnswer(textViewTop,question.getAnswers().get(0));
                }
                if (x > 3) {
                    validateAnswer(textViewBottom,question.getAnswers().get(2));
                }
            }

            if (x > (-1) && x < (1) && y > (-1) && y < (1)) {
                if ( question.isAnswered()) {
                    generateNewQuestion();
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        private void validateAnswer(TextView textView, Answer answer){
            if (!question.isAnswered())
                if (answer.iscorrect()) {
                    question.setAnswered(true);
                    textView.setBackgroundColor(Color.GREEN);
                }else{
                    textView.setBackgroundColor(Color.RED);
                }
        }
    };

    private void setupTextValues(){
        textViewTop = (TextView) findViewById(R.id.textViewTop);
        textViewRight = (TextView) findViewById(R.id.textViewRight);
        textViewBottom = (TextView) findViewById(R.id.textViewBottom);
        textViewLeft = (TextView) findViewById(R.id.textViewLeft);
        textViewQuestion = (TextView) findViewById(R.id.textViewQuestion);
        printQuestion();
    }

    private void generateNewQuestion(){
        question = new Question();
        clearColors();
        printQuestion();
    }

    private void clearColors(){
        textViewTop.setBackgroundColor(Color.TRANSPARENT);
        textViewRight.setBackgroundColor(Color.TRANSPARENT);
        textViewBottom.setBackgroundColor(Color.TRANSPARENT);
        textViewLeft.setBackgroundColor(Color.TRANSPARENT);
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
