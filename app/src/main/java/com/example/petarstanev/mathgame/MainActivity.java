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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    float x, y, z;


    SensorManager sensorManager;
    Sensor accelerometer;
    SensorEventListener listener = new SensorEventListener() {
        TextView textViewTop;
        TextView textViewRight;
        TextView textViewBottom;
        TextView textViewLeft;
        TextView textViewCenter;
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                x = event.values[0];
                y = event.values[1];
                z = event.values[2];
            }

            textViewTop = (TextView) findViewById(R.id.textViewTop);
            textViewRight = (TextView) findViewById(R.id.textViewRight);
            textViewBottom = (TextView) findViewById(R.id.textViewBottom);
            textViewLeft = (TextView) findViewById(R.id.textViewLeft);
            textViewCenter = (TextView) findViewById(R.id.textViewCenter);


            if (Math.abs(x) > Math.abs(y)) {
                if (x < 0) {
                    clearColors();
                    textViewRight.setBackgroundColor(Color.YELLOW);
                    textViewCenter.setText("You tilt the device right");
                }
                if (x > 0) {
                    clearColors();
                    textViewLeft.setBackgroundColor(Color.YELLOW);
                    textViewCenter.setText("You tilt the device left");
                }
            } else {
                if (y < 0) {
                    clearColors();
                    textViewTop.setBackgroundColor(Color.YELLOW);
                    textViewCenter.setText("You tilt the device up");
                }
                if (y > 0) {
                    clearColors();
                    textViewBottom.setBackgroundColor(Color.YELLOW);
                    textViewCenter.setText("You tilt the device down");
                }

            }
            if (x > (-2) && x < (2) && y > (-2) && y < (2)) {
                textViewCenter.setText("Not tilt device");
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        private void clearColors(){
            textViewTop.setBackgroundColor(Color.TRANSPARENT);
            textViewRight.setBackgroundColor(Color.TRANSPARENT);
            textViewBottom.setBackgroundColor(Color.TRANSPARENT);
            textViewLeft.setBackgroundColor(Color.TRANSPARENT);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(listener, accelerometer,
                SensorManager.SENSOR_DELAY_NORMAL);
    }
}