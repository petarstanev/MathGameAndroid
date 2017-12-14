package com.example.petarstanev.mathgame;


public class QuestionFactory implements FactoryInterface{
    Question question;

    public Question generateQuestion(int difficulty) {
        switch (difficulty) {
            case 0:
                return question = new EasyQuestion();
            case 1:
                return question = new MediumQuestion();
            case 2:
                return question = new HardQuestion();
        }
        return question;
    }
}