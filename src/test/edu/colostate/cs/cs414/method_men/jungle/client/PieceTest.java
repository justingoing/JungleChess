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
    void testValidMoveGenericAdjacentOpen(){
        Board board = new Board();
        Tile start = board.getTile_(1,1);
        Piece p = start.getPiece();

        //Easy moves: open Tile with no pieces, which are adjacent
        assertTrue(p.isValidMove_(start, board.getTile_(1,2), new Location(1,2))); //Adjacent open
        assertTrue(p.isValidMove_(start, board.getTile_(2,1), new Location(2,1))); //Adjacent open
        assertTrue(p.isValidMove_(start, board.getTile_(0,1), new Location(0,1))); //Adjacent open
        assertTrue(p.isValidMove_(start, board.getTile_(1,0), new Location(1,0))); //Adjacent open


    }

    @Test
    void testValidMoveGenericEnemyDen(){
        Board board = new Board();
        Tile start = board.getTile_(1,1);
        Piece p = start.getPiece();

        //Move dog next to enemy den
        board.move(p, new Location(8, 2));
        assertTrue(board.getTile_(8,2).getPiece() instanceof Dog);
        assertTrue(board.getTile_(1,1).getPiece() == null);

        assertTrue(p.isValidMove_(start, board.getTile_(8,3), new Location(8,3))); //Enemy den for the win
    }

    @Test
    void testValidMoveGenericFriendlyDen(){
        Board board = new Board();
        Tile start = board.getTile_(1,1);
        Piece p = start.getPiece();

        //Move dog next to friendly den
        board.move(p, new Location(0, 2));
        assertTrue(board.getTile_(0,2).getPiece() instanceof Dog);
        assertTrue(board.getTile_(1,1).getPiece() == null);



        assertFalse(p.isValidMove_(start, board.getTile_(0,3), new Location(0,3))); //Friendly den
    }

    @Test
    void testValidMoveGenericFriendlyTrap() {
        Board board = new Board();
        Tile start = board.getTile_(1,1);
        Piece p = start.getPiece();

        //Move dog to edge, next to lion and friendly trap
        board.move(p, new Location(0, 1));
        assertTrue(board.getTile_(0,1).getPiece() instanceof Dog);
        assertTrue(board.getTile_(1,1).getPiece() == null);

        //Test less simple move cases
        assertTrue(p.isValidMove_(start, board.getTile_(0,2), new Location(0,2))); //Adjacent trap

    }

    @Test
    void testValidMoveGenericNoMove(){
        Board board = new Board();
        Tile start = board.getTile_(1,1);
        Piece p = start.getPiece();

        assertFalse(p.isValidMove_(start, board.getTile_(1,1), new Location(1,1))); //No move
    }

    @Test
    void testValidMoveGenericBackAndForth(){
        Board board = new Board();
        Tile start = board.getTile_(1,1);
        Piece p = start.getPiece();

        //Move dog to edge, next to lion and friendly trap
        board.move(p, new Location(0, 1));
        assertTrue(board.getTile_(0,1).getPiece() instanceof Dog);
        assertTrue(board.getTile_(1,1).getPiece() == null);
        start = board.getTile_(0,1);

        assertFalse(p.isValidMove_(start, board.getTile_(0,1), new Location(0,1))); //Same Tile ; no move
    }

    @Test
    void testValidMoveGenericSimpleRejection(){

        Board board = new Board();
        Tile start = board.getTile_(1,1);
        Piece p = start.getPiece();

        //Move dog to edge, next to lion and friendly trap
        board.move(p, new Location(0, 1));
        assertTrue(board.getTile_(0,1).getPiece() instanceof Dog);
        assertTrue(board.getTile_(1,1).getPiece() == null);
        start = board.getTile_(0,1);

        assertFalse(p.isValidMove_(start, board.getTile_(-1,1), new Location(-1,1))); //Out of bounds
        assertFalse(p.isValidMove_(start, board.getTile_(0,0), new Location(0,0))); //Adjacent friend
        assertFalse(p.isValidMove_(start, board.getTile_(2,1), new Location(2,1))); //Out of range ; 2 move
    }

    @Test
    void testValidMoveGenericEnemyTrap() {
        Board board = new Board();
        Tile start = board.getTile_(1,1);
        Piece p = start.getPiece();

        //Move dog next to enemy trap
        board.move(p, new Location(7, 4));
        assertTrue(board.getTile_(7,4).getPiece() instanceof Dog);
        assertTrue(board.getTile_(1,1).getPiece() == null);
        start = board.getTile_(7, 4);

        assertTrue(p.isValidMove_(start, board.getTile_(7,3), new Location(7,3))); //Enemy trap
        assertTrue(p.isValidMove_(start, board.getTile_(8,4), new Location(8,4))); //Enemy trap
    }

    @Test
    void testValidMoveGenericRiver(){
        Board board = new Board();
        Tile start = board.getTile_(1,1);
        Piece p = start.getPiece();

        //Move dog to next to river
        board.move(p, new Location(5, 3));
        assertTrue(board.getTile_(5,3).getPiece() instanceof Dog);
        assertTrue(board.getTile_(1,1).getPiece() == null);
        start = board.getTile_(5,3);

        assertFalse(p.isValidMove_(start, board.getTile_(5,2), new Location(5,2))); //River
        assertFalse(p.isValidMove_(start, board.getTile_(5,4), new Location(5,4))); //River
    }

    @Test
    void testValidMoveGenericCapture(){
        Board board = new Board();
        Tile start = board.getTile_(1,1);
        Piece p = start.getPiece();

        //Move dog next to capturable enemy, and uncapturable enemy
        board.move(p, new Location(6, 5));
        assertTrue(board.getTile_(6,5).getPiece() instanceof Dog);
        assertTrue(board.getTile_(1,1).getPiece() == null);
        start = board.getTile_(6,5);

        //Test capture success and capture fail
        assertTrue(p.isValidMove_(start, board.getTile_(6,6), new Location(6,6))); //Enemy is lower rank
        assertTrue(p.isValidMove_(start, board.getTile_(7,5), new Location(7,5))); //Enemy same rank
        assertFalse(p.isValidMove_(start, board.getTile_(6,4), new Location(6,4))); //Enemy is higher rank

    }

    @Test
    void testValidMoveGenericCaptureOnFriendlyTrap(){
        Board board = new Board();
        Tile start = board.getTile_(1,1);
        Piece p = start.getPiece();

        //Move dog next to capturable enemy, and uncapturable enemy
        board.move(p, new Location(1, 2));
        assertTrue(board.getTile_(1,2).getPiece() instanceof Dog);
        assertTrue(board.getTile_(1,1).getPiece() == null);
        start = board.getTile_(1,2);

        Piece q = board.getTile_(6, 0).getPiece(); //Enemy elephant
        board.move(q, new Location(1, 3));
        assertTrue(board.getTile_(1,3).getPiece() instanceof Elephant);
        assertTrue(board.getTile_(6,0).getPiece() == null);

        //Test capture success on a trap
        assertTrue(p.isValidMove_(start, board.getTile_(1,3), new Location(1,3))); //Enemy is higher rank

    }

    @Test
    void testValidMoveRatOntoElephant(){
        Board board = new Board();
        Tile start = board.getTile_(2,0);
        Piece p = start.getPiece();

        //Move rat next to enemy elephant
        board.move(p, new Location(5, 0));
        assertTrue(board.getTile_(5,0).getPiece() instanceof Rat);
        assertTrue(board.getTile_(2,0).getPiece() == null);
        start = board.getTile_(5,0);

        //Onto elephant is valid move
        assertTrue(p.isValidMove_(start, board.getTile_(6,0), new Location(6,0)));
    }

    @Test
    void testValidMoveRatIntoRiver(){
        Board board = new Board();
        Tile start = board.getTile_(2,0);
        Piece p = start.getPiece();

        //Move rat next to enemy elephant
        board.move(p, new Location(3, 0));
        assertTrue(board.getTile_(3,0).getPiece() instanceof Rat);
        assertTrue(board.getTile_(2,0).getPiece() == null);
        start = board.getTile_(3,0);

        //Onto elephant is valid move
        assertTrue(p.isValidMove_(start, board.getTile_(3,1), new Location(3,1)));
    }


}
