package edu.colostate.cs.cs414.method_men.jungle.client.pieceTest;

import edu.colostate.cs.cs414.method_men.jungle.client.Board;
import edu.colostate.cs.cs414.method_men.jungle.client.Location;
import edu.colostate.cs.cs414.method_men.jungle.client.piece.Dog;
import edu.colostate.cs.cs414.method_men.jungle.client.piece.Elephant;
import edu.colostate.cs.cs414.method_men.jungle.client.piece.Piece;
import edu.colostate.cs.cs414.method_men.jungle.client.piece.Rat;
import edu.colostate.cs.cs414.method_men.jungle.client.tile.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {
    private Piece testRat;
    private Piece testTiger;
    private Board board;
    private Piece p;

    @BeforeEach
    void init() {
        testRat = new Piece("Rat", 1, "red");
        testTiger = new Piece("Tiger", 6, "blue");
        board = new Board();
        p = board.getTile_(new Location(1,1)).getPiece();
    }

    @Test
    void testConstructor() {
        assertEquals("Rat", testRat.getName());
        assertEquals(1, testRat.getRank());
        assertEquals("red", testRat.getColor());
        assertTrue(testRat instanceof Piece);

        assertEquals("Tiger", testTiger.getName());
        assertEquals(6, testTiger.getRank());
        assertEquals("blue", testTiger.getColor());
        assertTrue(testTiger instanceof Piece);
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
    void testValidMoveAdjacentOpen(){
        //Easy moves: open Tile with no pieces, which are adjacent
        assertTrue(p.isValidMove_(new Location(1,2), board)); //Adjacent open
        assertTrue(p.isValidMove_(new Location(2,1), board)); //Adjacent open
        assertTrue(p.isValidMove_(new Location(0,1), board)); //Adjacent open
        assertTrue(p.isValidMove_(new Location(1,0), board)); //Adjacent open

    }

    @Test
    void testValidMoveFriendlyDen(){
        //Move dog next to friendly den
        board.move(p, new Location(0, 2));
        assertTrue(board.getTile_(0,2).getPiece() instanceof Dog);
        assertTrue(board.getTile_(1,1).getPiece() == null);

        assertFalse(p.isValidMove_(new Location(0,3), board)); //Friendly den
    }

    @Test
    void testValidMoveEnemyDen(){
        //Move dog next to enemy den
        board.move(p, new Location(8, 2));
        assertTrue(board.getTile_(8,2).getPiece() instanceof Dog);
        assertTrue(board.getTile_(1,1).getPiece() == null);

        assertTrue(p.isValidMove_(new Location(8,3), board)); //Adjacent open
    }

    @Test
    void testValidMoveFriendlyTrap() {
        //Move dog to edge, next to lion and friendly trap
        board.move(p, new Location(0, 1));
        assertTrue(board.getTile_(0,1).getPiece() instanceof Dog);
        assertTrue(board.getTile_(1,1).getPiece() == null);

        //Test less simple move cases
        assertTrue(p.isValidMove_(new Location(0,2), board)); //Adjacent trap

    }

    @Test
    void testValidMoveEnemyTrap() {
        //Move dog next to enemy trap
        board.move(p, new Location(7, 4));
        assertTrue(board.getTile_(7,4).getPiece() instanceof Dog);
        assertTrue(board.getTile_(1,1).getPiece() == null);

        assertTrue(p.isValidMove_(new Location(7,3), board)); //Enemy trap
        assertTrue(p.isValidMove_(new Location(8,4), board)); //Enemy trap
    }

}
