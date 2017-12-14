package com.example.petarstanev.mathgame;

import java.util.List;

public class Game {
    private int lives;
    private int score;
    private Question question;
    private boolean over;
    private QuestionFactory questionFactory;

    public Game() {
        lives = 2;
        score = 0;
        questionFactory = new QuestionFactory();
        question = questionFactory.generateQuestion(getDifficulty());
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
            question = questionFactory.generateQuestion(getDifficulty());
            return true;
        }else{
            return false;
        }
    }

    public int getDifficulty(){
        if (score <3)
            return 0;

        if (score <8)
            return 1;

        return 2;
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
