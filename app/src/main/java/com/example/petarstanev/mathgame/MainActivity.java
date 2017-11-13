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

public class MainActivity extends AppCompatActivity {
    float x, y, z;
    MyView v;

    SensorManager sensorManager;
    Sensor accelerometer;
    SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                x= event.values[0];
                y= event.values[1];
                z= event.values[2];
            }


            TextView myTextView = (TextView) findViewById(R.id.textViewLeft);
            myTextView.setText("x - " + Float.toString(x));

            myTextView = (TextView) findViewById(R.id.textViewTop);
            myTextView.setText("y - " + Float.toString(y));

            myTextView = (TextView) findViewById(R.id.textViewRight);
            myTextView.setText("z - " + Float.toString(z));
            v.setX(x);
            v.setY(y);
            v.setZ(z);
            v.invalidate();
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(listener,  accelerometer,
                SensorManager.SENSOR_DELAY_NORMAL);
        v= new MyView(this);
    }
}

class MyView extends View {
    private float x,y,z;
    Paint p = new Paint();
    public MyView(Context context) {
        super(context);
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public void setZ(float z) {
        this.z = z;
    }
}
