package com.example.petarstanev.mathgame;

public class MediumQuestion extends Question {

    @Override
    public void generateQuestion() {

        switch (randomGenerator.nextInt(2)) {
            case 0:
                generateUniqueNumbersForAddition();
                symbol = '+';
                break;
            case 1:
                generateUniqueNumbersForSubtraction();
                symbol = '-';
                break;
        }
    }
}