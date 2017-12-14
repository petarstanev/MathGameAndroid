package com.example.petarstanev.mathgame;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Petar Stanev on 10/12/2017.
 */

public abstract class Question {
    public static final int WRONG_ANSWERS_COUNT = 3;
    public static final int MAX_RESULT_NUMBER = 50;

    private ArrayList<Answer> answers;
    protected int numberOne;
    protected int numberTwo;
    protected char symbol;
    protected Random randomGenerator;
    private boolean answered;
    private int errorNumber;
    private int correctResult;
    private int difficulty;


    public Question() {
        answers = new ArrayList<>();
        randomGenerator = new Random();
        generateQuestion();
        generateAnswers();
        answered = false;
        errorNumber = 0;
        difficulty = 1;
    }

    public Question(int difficulty) {
        answers = new ArrayList<>();
        randomGenerator = new Random();
        generateQuestion();
        generateAnswers();
        answered = false;
        errorNumber = 0;
        this.difficulty = difficulty;
    }


    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    private void generateAnswers(){
        correctResult = generateCorrectAnswer();
        generateWrongAnswers();
        shuffleAnswer();
    }

    public abstract void generateQuestion();

/*    private void generateSymbol(){
        switch (randomGenerator.nextInt(difficulty)) {
            case 0:
                generateUniqueNumbersForAddition();
                symbol = '+';
                break;
            case 1:
                generateUniqueNumbersForSubtraction();
                symbol = '-';
                break;
            case 2:
                generateUniqueNumbersForMultiplication();
                symbol = '*';
                break;
            case 3:
                generateUniqueNumbersForDivision();
                symbol = '/';
                break;
        }
    }*/

    protected void generateUniqueNumbersForAddition(){
        numberOne = randomGenerator.nextInt(50);
        numberTwo = randomGenerator.nextInt(50);
    }

    protected void generateUniqueNumbersForSubtraction(){
        numberOne = randomGenerator.nextInt(100);
        int save = numberOne;
        numberTwo = randomGenerator.nextInt(save++);
    }

    protected void generateUniqueNumbersForMultiplication(){
        numberOne = randomGenerator.nextInt(10);
        numberTwo = randomGenerator.nextInt(10);
    }

    protected void generateUniqueNumbersForDivision(){
        int save = 	 randomGenerator.nextInt(MAX_RESULT_NUMBER)+1;
        numberTwo = randomGenerator.nextInt(MAX_RESULT_NUMBER)+1;
        numberOne= save * numberTwo;
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
           int maxNumber = correctResult*2 ;
           if (correctResult*2 < 10){
               maxNumber = 20;
           }
            do {
                result = randomGenerator.nextInt(maxNumber);
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
