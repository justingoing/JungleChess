package edu.colostate.cs.cs414.method_men.jungle.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {
    private Piece testRat;
    private Piece testTiger;

    @BeforeEach
    void init() {
        testRat = new Piece("Rat", 1, "red");
        testTiger = new Piece("tiger", 6, "red");
    }

    @Test
    void testConstructor() {
        assertEquals("Rat", testRat.getName());
        assertEquals(1, testRat.getRank());
        assertEquals("red", testRat.getColor());
    }

    @Test
    void testSetLocation() {
        testRat.setLocation(0,0);
        assertEquals(testRat.getRow(), 0);
        assertEquals(testRat.getCol(), 0);
    }

    @Test
    void testGetLocation() {
        testRat.setLocation(0,0);
        Location loc = testRat.getLocation();
        assertTrue(loc.getRow() == 0 && loc.getCol() == 0);
    }

    @Test
    void testGetRow() {
        testRat.setLocation(0,0);
        assertEquals(0, testRat.getRow());
    }

    @Test
    void testGetCol() {
        testRat.setLocation(0,0);
        assertEquals(0, testRat.getCol());
    }

    @Test
    void testEquals(){
        Piece p = new Piece("Elephant", 8, "red");
        Piece q = new Piece("Elephant", 8, "red");
        assertEquals(p, q);
        q = new Piece("Elephant", 8, "blue");
        assertNotEquals(p, q);
        q = new Piece("Rat", 8, "blue");
        assertNotEquals(p, q);
        q = new Piece("Elephant", 32, "blue");
        assertNotEquals(p, q);
    }

    @Test
    void testGetName() {
        assertEquals("Rat", testRat.getName());
    }

    @Test
    void testGetRank() {
        assertEquals(1, testRat.getRank());
    }

    @Test
    void testGetColor(){
        assertEquals("red", testRat.getColor());
    }

    @Test
    void testIsRat() {
        assertTrue(testRat.isRat()  && !testTiger.isRat());
    }

    @Test
    void testValidMoveGeneric(){
        Board board = new Board();
        Piece p = board.getTile_(1,1).getPiece();

        //Easy moves: open Tile with no pieces, which are adjacent
        assertTrue(p.isValidMove_(board.getTile_(1,2), new Location(1,2))); //Adjacent open
        assertTrue(p.isValidMove_(board.getTile_(2,1), new Location(2,1))); //Adjacent open
        assertTrue(p.isValidMove_(board.getTile_(0,1), new Location(0,1))); //Adjacent open
        assertTrue(p.isValidMove_(board.getTile_(1,0), new Location(1,0))); //Adjacent open

        //Move dog to edge, next to lion and friendly trap
        board.move(p, new Location(0, 1));

        //Assure I did move my piece, and did not make a new copy.
        assertTrue(board.getTile_(0,1).getPiece() instanceof Dog);
        assertTrue(board.getTile_(1,1).getPiece() == null);

        //Test less simple move cases
        assertTrue(p.isValidMove_(board.getTile_(0,2), new Location(0,2))); //Adjacent trap
        assertTrue(p.isValidMove_(board.getTile_(1,1), new Location(1,1))); //Back to original spot

        assertFalse(p.isValidMove_(board.getTile_(0,1), new Location(0,1))); //Same Tile ; no move
        assertFalse(p.isValidMove_(board.getTile_(-1,1), new Location(-1,1))); //Out of bounds
        assertFalse(p.isValidMove_(board.getTile_(0,0), new Location(0,0))); //Adjacent friend
        assertFalse(p.isValidMove_(board.getTile_(2,1), new Location(2,1))); //Out of range ; 2 moves
    }


}
