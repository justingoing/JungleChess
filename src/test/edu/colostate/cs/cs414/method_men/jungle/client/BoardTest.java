package edu.colostate.cs.cs414.method_men.jungle.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BoardTest {
    private Board testBoard;

    @BeforeEach
    void init() {
        testBoard = new Board();
    }

    @Test
    void testIsDenSuccess() {
        assertTrue(testBoard.isDen(0, 3));
        assertTrue(testBoard.isDen(8, 3));
        assertFalse(testBoard.isDen(1, 2));
    }

    @Test
    void testIsTrapSuccess() {
        assertTrue(testBoard.isTrap(0, 2));
        assertTrue(testBoard.isTrap(0, 4));
        assertTrue(testBoard.isTrap(1, 3));
        assertTrue(testBoard.isTrap(7, 3));
        assertTrue(testBoard.isTrap(8, 2));
        assertTrue(testBoard.isTrap(8, 4));
        assertFalse(testBoard.isTrap(4, 4));
    }

    @Test
    void testIsRiver() {
        assertTrue(testBoard.isRiver(3, 1));
        assertTrue(testBoard.isRiver(3, 2));
        assertTrue(testBoard.isRiver(3, 4));
        assertTrue(testBoard.isRiver(3, 5));
        assertTrue(testBoard.isRiver(4, 1));
        assertTrue(testBoard.isRiver(4, 2));
        assertTrue(testBoard.isRiver(4, 4));
        assertTrue(testBoard.isRiver(4, 5));
        assertTrue(testBoard.isRiver(5, 1));
        assertTrue(testBoard.isRiver(5, 2));
        assertTrue(testBoard.isRiver(5, 4));
        assertTrue(testBoard.isRiver(5, 5));
        assertFalse(testBoard.isRiver(0,0));
    }

    @Test
    void testIsJump() {
        assertTrue(testBoard.isJump(2, 1));
        assertTrue(testBoard.isJump(2, 2));
        assertTrue(testBoard.isJump(2, 4));
        assertTrue(testBoard.isJump(2, 5));
        assertTrue(testBoard.isJump(3, 0));
        assertTrue(testBoard.isJump(3, 3));
        assertTrue(testBoard.isJump(3, 6));
        assertTrue(testBoard.isJump(4, 3));
        assertTrue(testBoard.isJump(4, 6));
        assertTrue(testBoard.isJump(5, 3));
        assertTrue(testBoard.isJump(5, 6));
        assertTrue(testBoard.isJump(6, 1));
        assertTrue(testBoard.isJump(6, 2));
        assertTrue(testBoard.isJump(6, 4));
        assertTrue(testBoard.isJump(6, 5));
        assertFalse(testBoard.isJump(0,0));
    }

    @Test
    void testMakeInstance() {
        Den den = new Den();
        Trap trap = new Trap();
        River river = new River();
        Jump jump = new Jump();
        Open open = new Open();
        assertEquals(den.getAttribute(), testBoard.makeInstance(0,3).getAttribute());
        assertEquals(trap.getAttribute(), testBoard.makeInstance(0,2).getAttribute());
        assertEquals(river.getAttribute(), testBoard.makeInstance(3,1).getAttribute());
        assertEquals(jump.getAttribute(), testBoard.makeInstance(3,0).getAttribute());
        assertEquals(open.getAttribute(), testBoard.makeInstance(2,3).getAttribute());
    }

    @Test
    void testSetBoard() {
        assertEquals('.', testBoard.getTile(0,0).getAttribute());
        assertEquals('.', testBoard.getTile(0,1).getAttribute());
        assertEquals('.', testBoard.getTile(0,5).getAttribute());
        assertEquals('.', testBoard.getTile(0,6).getAttribute());
        assertEquals('.', testBoard.getTile(1,0).getAttribute());
        assertEquals('.', testBoard.getTile(1,1).getAttribute());
        assertEquals('.', testBoard.getTile(1,2).getAttribute());
        assertEquals('.', testBoard.getTile(1,4).getAttribute());
        assertEquals('.', testBoard.getTile(1,5).getAttribute());
        assertEquals('.', testBoard.getTile(1,6).getAttribute());
        assertEquals('.', testBoard.getTile(2,0).getAttribute());
        assertEquals('.', testBoard.getTile(2,3).getAttribute());
        assertEquals('.', testBoard.getTile(2,6).getAttribute());

        assertEquals('.', testBoard.getTile(8,0).getAttribute());
        assertEquals('.', testBoard.getTile(8,1).getAttribute());
        assertEquals('.', testBoard.getTile(8,5).getAttribute());
        assertEquals('.', testBoard.getTile(8,6).getAttribute());
        assertEquals('.', testBoard.getTile(7,0).getAttribute());
        assertEquals('.', testBoard.getTile(7,1).getAttribute());
        assertEquals('.', testBoard.getTile(7,2).getAttribute());
        assertEquals('.', testBoard.getTile(7,4).getAttribute());
        assertEquals('.', testBoard.getTile(7,5).getAttribute());
        assertEquals('.', testBoard.getTile(7,6).getAttribute());
        assertEquals('.', testBoard.getTile(6,0).getAttribute());
        assertEquals('.', testBoard.getTile(6,3).getAttribute());
        assertEquals('.', testBoard.getTile(6,6).getAttribute());
    }

    @Test
    void testSetBoard_() {
        Tile tile = testBoard.getTile_(0,0);
        System.out.println(tile);
        assertEquals('.', testBoard.getTile_(0,0).getAttribute());
        assertEquals('.', testBoard.getTile_(0,1).getAttribute());
        assertEquals('.', testBoard.getTile_(0,5).getAttribute());
        assertEquals('.', testBoard.getTile_(0,6).getAttribute());
        assertEquals('.', testBoard.getTile_(1,0).getAttribute());
        assertEquals('.', testBoard.getTile_(1,1).getAttribute());
        assertEquals('.', testBoard.getTile_(1,2).getAttribute());
        assertEquals('.', testBoard.getTile_(1,4).getAttribute());
        assertEquals('.', testBoard.getTile_(1,5).getAttribute());
        assertEquals('.', testBoard.getTile_(1,6).getAttribute());
        assertEquals('.', testBoard.getTile_(2,0).getAttribute());
        assertEquals('.', testBoard.getTile_(2,3).getAttribute());
        assertEquals('.', testBoard.getTile_(2,6).getAttribute());

        assertEquals('.', testBoard.getTile_(8,0).getAttribute());
        assertEquals('.', testBoard.getTile_(8,1).getAttribute());
        assertEquals('.', testBoard.getTile_(8,5).getAttribute());
        assertEquals('.', testBoard.getTile_(8,6).getAttribute());
        assertEquals('.', testBoard.getTile_(7,0).getAttribute());
        assertEquals('.', testBoard.getTile_(7,1).getAttribute());
        assertEquals('.', testBoard.getTile_(7,2).getAttribute());
        assertEquals('.', testBoard.getTile_(7,4).getAttribute());
        assertEquals('.', testBoard.getTile_(7,5).getAttribute());
        assertEquals('.', testBoard.getTile_(7,6).getAttribute());
        assertEquals('.', testBoard.getTile_(6,0).getAttribute());
        assertEquals('.', testBoard.getTile_(6,3).getAttribute());
        assertEquals('.', testBoard.getTile_(6,6).getAttribute());
    }

    @Test
    void placePieces() {
        char[][] draw = new char[9][7];
        Player[] players = new Player[2];
        players[0] = new Player("white");
        players[1] = new Player("black");
        testBoard.placePieces(draw, players);

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