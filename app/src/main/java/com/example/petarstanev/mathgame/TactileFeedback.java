package com.example.petarstanev.mathgame;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Vibrator;

public class TactileFeedback {
    MediaPlayer mediaPlayer;
    Vibrator vibrate;
    Context context;


    public TactileFeedback(Context context) {
        this.context = context;
        vibrate = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void correctAnswerFeedback(){
        playSound(R.raw.correct_answer);
    }

    public void wrongAnswerFeedback(){
        playSound(R.raw.wrong_answer);
        vibrate(200);
    }

    private void playSound(int soundId){
        mediaPlayer = MediaPlayer.create(context, soundId);
        mediaPlayer.start();
    }

    private void vibrate(int time){
        vibrate.vibrate(time);
    }
}
