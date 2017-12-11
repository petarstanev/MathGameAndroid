package com.example.petarstanev.mathgame;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Petar Stanev on 10/12/2017.
 */

public class Question {
   private ArrayList<Answer> answers;
    private int numberOne;
    private int numberTwo;
    private char symbol;
    private Random rand;

    public Question() {
        answers = new ArrayList<>();
        rand = new Random();
        generateQuestion();
        generateAnswers();
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    private void generateQuestion(){
        symbol = '+';
        numberOne = rand.nextInt(20);
        numberTwo = rand.nextInt(20);
    }

    private void generateAnswers(){
        int correctResult = generateCorrectAnswer();
        generateWrongAnswer(correctResult);
        shuffleAnswer();
    }

    private int generateCorrectAnswer() {
        int result;
        switch (symbol) {
            case '+':
                result = numberOne + numberTwo;
                break;
            case '-':
                result = numberOne - numberTwo;
                break;
            case '*':
                result = numberOne * numberTwo;
                break;
            case '/':
                result = numberOne / numberTwo;
                break;
            default:
                result = 0;
                break;
        }

        answers.add(new Answer(result,true));
        return result;
    }

    private void generateWrongAnswer(int correctResult){
        for (int i = 0; i < 3; i++) {
            int result = rand.nextInt(correctResult*2);
            answers.add(new Answer(result,false));
        }
    }

    private void shuffleAnswer(){
        Collections.shuffle(answers);
    }


    @Override
    public String toString() {
        return numberOne + " "+ symbol + " " + numberTwo + "=";
    }
}
