package com.example.petarstanev.mathgame;

public class Answer {
    private int number;
    private boolean correct;
    private boolean isSelected;

    public Answer(int number, boolean correct) {
        this.number = number;
        this.correct = correct;
        isSelected = false;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
