package com.example.petarstanev.mathgame;

public class EasyQuestion extends Question{

    @Override
    public void generateQuestion() {
        generateUniqueNumbersForAddition();
        symbol = '+';
    }

    protected void generateUniqueNumbersForAddition(){
        numberOne = randomGenerator.nextInt(MAX_RESULT_NUMBER);
        numberTwo = randomGenerator.nextInt(MAX_RESULT_NUMBER);
    }
}