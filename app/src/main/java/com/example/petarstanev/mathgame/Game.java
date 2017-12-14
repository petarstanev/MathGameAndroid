package com.example.petarstanev.mathgame;

import java.util.List;

public class Game {
    private int lives;
    private int score;
    private Question question;
    private boolean over;


    public Game() {
        lives = 1;
        score = 0;
        question = new Question();
        over = false;
    }

    public boolean isOver() {
        return over;
    }

    public Question getQuestion() {
        return question;
    }

    public boolean GenerateQuestion(){
        if (question.isAnswered() && !over) {
            question = new Question();
            return true;
        }else{
            return false;
        }
    }

    public boolean submitAnswer(Answer answer){
        answer.setSelected(true);
        if (answer.isCorrect()) {
            question.setAnswered(true);
            if (question.getErrorNumber() == 0) {
                score += 10;//increase score only if is answered correctly from first time
            }
            return true;
        } else {
            if (question.getErrorNumber() == 0)
                lives--;

            if (lives == 0 )
                over = true;
            question.increaseErrorNumber();
            return false;
        }
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
