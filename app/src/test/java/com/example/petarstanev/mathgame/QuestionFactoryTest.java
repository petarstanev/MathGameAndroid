package com.example.petarstanev.mathgame;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuestionFactoryTest {
    @Test
    public void is_EasyQuestion_Returned_From_Factory(){
        boolean answer =true;
        QuestionFactory factory = new QuestionFactory();
        Question question = factory.generateQuestion(0);

        Assert.assertNotNull(question);
        assertEquals(answer,question instanceof EasyQuestion);
    }

    @Test
    public void is_MediumQuestion_Returned_From_Factory(){
        boolean answer =true;
        QuestionFactory factory = new QuestionFactory();
        Question question = factory.generateQuestion(1);

        Assert.assertNotNull(question);
        assertEquals(answer,question instanceof MediumQuestion);
    }

    @Test
    public void is_HardQuestion_Returned_From_Factory(){
        boolean answer =true;
        QuestionFactory factory = new QuestionFactory();
        Question question = factory.generateQuestion(2);

        Assert.assertNotNull(question);
        assertEquals(answer,question instanceof HardQuestion);
    }
}
