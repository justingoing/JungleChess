package edu.colostate.cs.cs414.method_men.jungle.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game g;
    private int turn;


    @BeforeEach
    void init() {
        g = new Game();
        if (g.getTurn() == 1) {
            g.incrementTurn();
        } // it's always top player's turn
    }

    @Test
    void testIncrementTurnTop() {
        turn = g.getTurn();
        g.incrementTurn(); // bot's turn

        assertNotEquals(turn, g.getTurn());
    }

    @Test
    void testIncrementTurnBottom() {
        turn = g.getTurn();
        g.incrementTurn(); // bot's turn

        assertNotEquals(turn, g.getTurn());
    }

    @Test
    void testGetTurn() {
        turn = g.getTurn();

        assertEquals(0, g.getTurn());
    }

    @Test
    void testWinnerCheck_Fail() {
        assertEquals(-1, g.winnerCheck());
    }

}
