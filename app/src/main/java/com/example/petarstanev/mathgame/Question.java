package com.example.petarstanev.mathgame;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Petar Stanev on 10/12/2017.
 */

public class Question {
    public static final int WRONG_ANSWERS_COUNT = 3;
    public static final int MAX_RESULT_NUMBER = 10;

    private ArrayList<Answer> answers;
    private int numberOne;
    private int numberTwo;
    private char symbol;
    private Random rand;
    private boolean answered;
    private int errorNumber;
    private int correctResult;

    public Question() {
        answers = new ArrayList<>();
        rand = new Random();
        generateQuestion();
        generateAnswers();
        answered = false;
        errorNumber = 0;
    }


    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    private void generateQuestion(){
        symbol = '+';
        numberOne = rand.nextInt(MAX_RESULT_NUMBER);
        numberTwo = rand.nextInt(MAX_RESULT_NUMBER);
    }

    private void generateAnswers(){
        correctResult = generateCorrectAnswer();
        generateWrongAnswers();
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

    private void generateWrongAnswers(){
       while (answers.size() < 4){
            int result;
            do {
                result = rand.nextInt(correctResult*2);
            }while(!checkIfAnswerIsUnique(result));

            answers.add(new Answer(result,false));
        }
    }

    private boolean checkIfAnswerIsUnique(int number){
        for (Answer answer:answers) {
            if (answer.getNumber()==number)
                return false;
        }
        return true;
    }

    private void shuffleAnswer(){
        Collections.shuffle(answers);
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public int getErrorNumber() {
        return errorNumber;
    }

    public void increaseErrorNumber() {
        errorNumber++;
    }


    public String printQuestion(boolean includeAnswer){
        if (includeAnswer)
            return numberOne + " "+ symbol + " " + numberTwo + " = " + correctResult;

        return numberOne + " "+ symbol + " " + numberTwo + " = ";
    }
}
