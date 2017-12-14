package com.example.petarstanev.mathgame;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Vibrator;

public class TactileFeedback {
    MediaPlayer mediaPlayer;
    Vibrator vibrate;
    Context context;
    SharedPreferences sharedPref;

    public TactileFeedback(Context context) {
        this.context = context;
        vibrate = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        sharedPref =  context.getSharedPreferences("settings", 0);
    }

    public void correctAnswerFeedback(){
        playSound(R.raw.correct_answer);
    }

    public void wrongAnswerFeedback(){
        playSound(R.raw.wrong_answer);
        if (checkOption("vibration"))
            vibrate.vibrate(200);
    }

    private boolean checkOption(String name){
        if (sharedPref.contains(name) && !sharedPref.getBoolean(name,false))
            return false;

        return true;
    }

    private void playSound(int soundId){
        if (checkOption("sound")) {
            mediaPlayer = MediaPlayer.create(context, soundId);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
            });
        }
    }
}
