package com.example.petarstanev.mathgame;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {
    @Test
    public void submit_falseAnswer() {
        Game game = new Game();
        Answer correctAnswer = new Answer(2,false);
        assertEquals(false,game.submitAnswer(correctAnswer));
    }

    @Test
    public void submit_trueAnswer() {
        Game game = new Game();
        Answer correctAnswer = new Answer(2,true);
        assertEquals(true,game.submitAnswer(correctAnswer));
    }
}
