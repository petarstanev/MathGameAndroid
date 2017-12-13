package com.example.petarstanev.mathgame;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class OptionsActivity extends AppCompatActivity {
    Switch switchSound;
    Switch switchVibration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        setupButtons();
    }

    private void setupButtons(){
        switchSound =  (Switch) findViewById(R.id.switchSound);
        switchVibration =  (Switch) findViewById(R.id.switchVibration);

        switchSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences settings= getApplicationContext().getSharedPreferences("settings", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("sound", isChecked);
                editor.apply();
            }
        });

        switchVibration.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences settings= getApplicationContext().getSharedPreferences("settings", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("vibration", isChecked);
                editor.apply();
            }
        });
        setSwitchesValue();
    }

    private void setSwitchesValue(){
        SharedPreferences sharedPref= getApplicationContext().getSharedPreferences("settings", 0);

        if (sharedPref.contains("sound") && !sharedPref.getBoolean("sound",false))
            switchSound.setChecked(false);

        if (sharedPref.contains("vibration") && !sharedPref.getBoolean("vibration",false))
            switchVibration.setChecked(false);
    }
}
