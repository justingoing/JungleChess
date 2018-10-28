package edu.colostate.cs.cs414.method_men.jungle.client;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BoardTest {

    private Board test = new Board();

    @Test
    void isDenSuccess() {
        assertTrue(test.isDen(0, 3));
        assertTrue(test.isDen(8, 3));
        assertFalse(test.isDen(1, 2));
    }

    @Test
    void isTrapSuccess() {
        assertTrue(test.isTrap(0, 2));
        assertTrue(test.isTrap(0, 4));
        assertTrue(test.isTrap(1, 3));
        assertTrue(test.isTrap(7, 3));
        assertTrue(test.isTrap(8, 2));
        assertTrue(test.isTrap(8, 4));
        assertFalse(test.isTrap(4, 4));
    }

    @Test
    void isRiver() {
        assertTrue(test.isRiver(3, 1));
        assertTrue(test.isRiver(3, 2));
        assertTrue(test.isRiver(3, 4));
        assertTrue(test.isRiver(3, 5));
        assertTrue(test.isRiver(4, 1));
        assertTrue(test.isRiver(4, 2));
        assertTrue(test.isRiver(4, 4));
        assertTrue(test.isRiver(4, 5));
        assertTrue(test.isRiver(5, 1));
        assertTrue(test.isRiver(5, 2));
        assertTrue(test.isRiver(5, 4));
        assertTrue(test.isRiver(5, 5));
        assertFalse(test.isRiver(0,0));
    }

    @Test
    void isJump() {
        assertTrue(test.isJump(2, 1));
        assertTrue(test.isJump(2, 2));
        assertTrue(test.isJump(2, 4));
        assertTrue(test.isJump(2, 5));
        assertTrue(test.isJump(3, 0));
        assertTrue(test.isJump(3, 3));
        assertTrue(test.isJump(3, 6));
        assertTrue(test.isJump(4, 3));
        assertTrue(test.isJump(4, 6));
        assertTrue(test.isJump(5, 3));
        assertTrue(test.isJump(5, 6));
        assertTrue(test.isJump(6, 1));
        assertTrue(test.isJump(6, 2));
        assertTrue(test.isJump(6, 4));
        assertTrue(test.isJump(6, 5));
        assertFalse(test.isJump(0,0));
    }

    @Test
    void makeInstance() {
        Den den = new Den();
        Trap trap = new Trap();
        River river = new River();
        Jump jump = new Jump();
        Open open = new Open();
        assertEquals(den.getAttribute(), test.makeInstance(0,3).getAttribute());
        assertEquals(trap.getAttribute(), test.makeInstance(0,2).getAttribute());
        assertEquals(river.getAttribute(), test.makeInstance(3,1).getAttribute());
        assertEquals(jump.getAttribute(), test.makeInstance(3,0).getAttribute());
        assertEquals(open.getAttribute(), test.makeInstance(2,3).getAttribute());
    }

    @Test
    void makeBoard() {
        assertTrue(test.getTile(0,0).getAttribute() == '.');
        assertTrue(test.getTile(0,1).getAttribute() == '.');
        assertTrue(test.getTile(0,5).getAttribute() == '.');
        assertTrue(test.getTile(0,6).getAttribute() == '.');
        assertTrue(test.getTile(1,0).getAttribute() == '.');
        assertTrue(test.getTile(1,1).getAttribute() == '.');
        assertTrue(test.getTile(1,2).getAttribute() == '.');
        assertTrue(test.getTile(1,4).getAttribute() == '.');
        assertTrue(test.getTile(1,5).getAttribute() == '.');
        assertTrue(test.getTile(1,6).getAttribute() == '.');
        assertTrue(test.getTile(2,0).getAttribute() == '.');
        assertTrue(test.getTile(2,3).getAttribute() == '.');
        assertTrue(test.getTile(2,6).getAttribute() == '.');

        assertTrue(test.getTile(8,0).getAttribute() == '.');
        assertTrue(test.getTile(8,1).getAttribute() == '.');
        assertTrue(test.getTile(8,5).getAttribute() == '.');
        assertTrue(test.getTile(8,6).getAttribute() == '.');
        assertTrue(test.getTile(7,0).getAttribute() == '.');
        assertTrue(test.getTile(7,1).getAttribute() == '.');
        assertTrue(test.getTile(7,2).getAttribute() == '.');
        assertTrue(test.getTile(7,4).getAttribute() == '.');
        assertTrue(test.getTile(7,5).getAttribute() == '.');
        assertTrue(test.getTile(7,6).getAttribute() == '.');
        assertTrue(test.getTile(6,0).getAttribute() == '.');
        assertTrue(test.getTile(6,3).getAttribute() == '.');
        assertTrue(test.getTile(6,6).getAttribute() == '.');
    }

    @Test
    void placePieces() {
        char[][] draw = new char[9][7];
        Player[] players = new Player[2];
        players[0] = new Player("white");
        players[1] = new Player("black");
        test.placePieces(draw, players);

        assertEquals('6', draw[8][0]);
        assertEquals('7', draw[8][6]);
        assertEquals('2', draw[7][1]);
        assertEquals('4', draw[7][5]);
        assertEquals('8', draw[6][0]);
        assertEquals('3', draw[6][2]);
        assertEquals('5', draw[6][4]);
        assertEquals('1', draw[6][6]);

        assertEquals('7', draw[0][0]);
        assertEquals('6', draw[0][6]);
        assertEquals('4', draw[1][1]);
        assertEquals('2', draw[1][5]);
        assertEquals('1', draw[2][0]);
        assertEquals('5', draw[2][2]);
        assertEquals('3', draw[2][4]);
        assertEquals('8', draw[2][6]);
    }
}