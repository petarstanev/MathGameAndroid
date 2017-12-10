package com.example.petarstanev.mathgame;

/**
 * Created by Petar Stanev on 10/12/2017.
 */

public class Answer {
    private int number;
    private boolean correct;

    public Answer(int number, boolean correct) {
        this.number = number;
        this.correct = correct;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean iscorrect() {
        return correct;
    }

    public void setcorrect(boolean correct) {
        this.correct = correct;
    }
}
