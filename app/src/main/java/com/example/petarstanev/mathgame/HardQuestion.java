package com.example.petarstanev.mathgame;

public class HardQuestion extends Question {

    @Override
    public void generateQuestion() {
        switch (randomGenerator.nextInt(4)) {
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
    }
}