package com.example.petarstanev.mathgame;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QuestionTest {
    @Test
    public void are_NumbersForSubtraction_Valid(){
        Question question = new MediumQuestion();
        question.generateUniqueNumbersForDivision();

        assertTrue(question.numberOne > question.numberTwo);
    }

    @Test
    public void are_NumbersForDivision_Valid(){
        Question question = new MediumQuestion();
        question.generateUniqueNumbersForDivision();

        assertTrue(question.numberOne % question.numberTwo == 0);
    }
}
