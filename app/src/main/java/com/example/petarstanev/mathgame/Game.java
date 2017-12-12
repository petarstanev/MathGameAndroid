package com.example.petarstanev.mathgame;

import java.util.List;

public class Game {
    private int lives;
    private int score;
    private Question question;
    private boolean gameOver;

    public Game() {
        lives = 3;
        score = 0;
        question = new Question();
        gameOver = false;
    }

    public Question getQuestion() {
        return question;
    }

    public boolean GenerateQuestion(){
        if (question.isAnswered()) {
            question = new Question();
            return true;
        }else{
            return false;
        }
    }

    public boolean submitAnswer(Answer answer){
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
                gameOver = true;
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
