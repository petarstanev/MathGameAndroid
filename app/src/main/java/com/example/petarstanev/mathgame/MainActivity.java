package com.example.petarstanev.mathgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    float x, y, z;
    SensorManager sensorManager;
    Sensor accelerometer;
    Question question = new Question();
    TextView textViewTop;
    TextView textViewRight;
    TextView textViewBottom;
    TextView textViewLeft;
    TextView textViewQuestion;
    Button buttonNextQuestion;

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
                    question = new Question();
                    clearColors();
                    printQuestion();
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        private void validateAnswer(TextView textView, Answer answer){
            if (!question.isAnswered())
                if (answer.isCorrect()) {
                    question.setAnswered(true);
                    textView.setBackgroundColor(Color.GREEN);
                }else{
                    textView.setBackgroundColor(Color.RED);
                }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        setupTextValues();
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(listener, accelerometer,
                SensorManager.SENSOR_DELAY_NORMAL);
    }


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
        printQuestion();
    }



    private void clearColors(){
        textViewTop.setBackgroundColor(Color.TRANSPARENT);
        textViewRight.setBackgroundColor(Color.TRANSPARENT);
        textViewBottom.setBackgroundColor(Color.TRANSPARENT);
        textViewLeft.setBackgroundColor(Color.TRANSPARENT);
    }
}