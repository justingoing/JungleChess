package edu.colostate.cs.cs414.method_men.jungle.client;

import edu.colostate.cs.cs414.method_men.jungle.client.piece.*;
import edu.colostate.cs.cs414.method_men.jungle.client.tile.*;
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
    void testSetBoardOpen() {
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
        assertEquals('.', testBoard.getTile(2,1).getAttribute());
        assertEquals('.', testBoard.getTile(2,2).getAttribute());
        assertEquals('.', testBoard.getTile(2,3).getAttribute());
        assertEquals('.', testBoard.getTile(2,4).getAttribute());
        assertEquals('.', testBoard.getTile(2,5).getAttribute());
        assertEquals('.', testBoard.getTile(2,6).getAttribute());
        assertEquals('.', testBoard.getTile(3,0).getAttribute());
        assertEquals('.', testBoard.getTile(3,3).getAttribute());
        assertEquals('.', testBoard.getTile(3,6).getAttribute());
        assertEquals('.', testBoard.getTile(4,0).getAttribute());
        assertEquals('.', testBoard.getTile(4,3).getAttribute());
        assertEquals('.', testBoard.getTile(4,6).getAttribute());
        assertEquals('.', testBoard.getTile(5,0).getAttribute());
        assertEquals('.', testBoard.getTile(5,3).getAttribute());
        assertEquals('.', testBoard.getTile(5,6).getAttribute());
        assertEquals('.', testBoard.getTile(6,0).getAttribute());
        assertEquals('.', testBoard.getTile(6,1).getAttribute());
        assertEquals('.', testBoard.getTile(6,2).getAttribute());
        assertEquals('.', testBoard.getTile(6,3).getAttribute());
        assertEquals('.', testBoard.getTile(6,4).getAttribute());
        assertEquals('.', testBoard.getTile(6,5).getAttribute());
        assertEquals('.', testBoard.getTile(6,6).getAttribute());
        assertEquals('.', testBoard.getTile(7,0).getAttribute());
        assertEquals('.', testBoard.getTile(7,1).getAttribute());
        assertEquals('.', testBoard.getTile(7,2).getAttribute());
        assertEquals('.', testBoard.getTile(7,4).getAttribute());
        assertEquals('.', testBoard.getTile(7,5).getAttribute());
        assertEquals('.', testBoard.getTile(7,6).getAttribute());
        assertEquals('.', testBoard.getTile(8,0).getAttribute());
        assertEquals('.', testBoard.getTile(8,1).getAttribute());
        assertEquals('.', testBoard.getTile(8,5).getAttribute());
        assertEquals('.', testBoard.getTile(8,6).getAttribute());
    }

    @Test
    void testSetBoardDen(){
        assertEquals('D', testBoard.getTile(0,3).getAttribute());
        assertEquals('D', testBoard.getTile(8,3).getAttribute());
    }

    @Test
    void testSetBoardTrap(){
        assertEquals('T', testBoard.getTile(1,3).getAttribute());
        assertEquals('T', testBoard.getTile(0,2).getAttribute());
        assertEquals('T', testBoard.getTile(0,4).getAttribute());
        assertEquals('T', testBoard.getTile(7,3).getAttribute());
        assertEquals('T', testBoard.getTile(8,2).getAttribute());
        assertEquals('T', testBoard.getTile(8,4).getAttribute());
    }

    @Test
    void testSetBoardRiver(){
        assertEquals('~', testBoard.getTile(3,1).getAttribute());
        assertEquals('~', testBoard.getTile(3,2).getAttribute());
        assertEquals('~', testBoard.getTile(3,4).getAttribute());
        assertEquals('~', testBoard.getTile(3,5).getAttribute());
        assertEquals('~', testBoard.getTile(4,1).getAttribute());
        assertEquals('~', testBoard.getTile(4,2).getAttribute());
        assertEquals('~', testBoard.getTile(4,4).getAttribute());
        assertEquals('~', testBoard.getTile(4,5).getAttribute());
        assertEquals('~', testBoard.getTile(5,1).getAttribute());
        assertEquals('~', testBoard.getTile(5,2).getAttribute());
        assertEquals('~', testBoard.getTile(5,4).getAttribute());
        assertEquals('~', testBoard.getTile(5,5).getAttribute());
    }

    @Test
    void testSetBoardLion(){
        assertEquals(new Lion("red"), testBoard.getTile(0,0).getPiece());
        assertEquals(new Lion("blue"), testBoard.getTile(8,6).getPiece());
    }

    @Test
    void testSetBoardTiger(){
        assertEquals(new Tiger("red"), testBoard.getTile(0,6).getPiece());
        assertEquals(new Tiger("blue"), testBoard.getTile(8,0).getPiece());
    }

    @Test
    void testSetBoardDog(){
        assertEquals(new Dog("red"), testBoard.getTile(1,1).getPiece());
        assertEquals(new Dog("blue"), testBoard.getTile(7,5).getPiece());
    }

    @Test
    void testSetBoardCat(){
        assertEquals(new Cat("red"), testBoard.getTile(1,5).getPiece());
        assertEquals(new Cat("blue"), testBoard.getTile(7,1).getPiece());
    }

    @Test
    void testSetBoardRat(){
        assertEquals(new Rat("red"), testBoard.getTile(2,0).getPiece());
        assertEquals(new Rat("blue"), testBoard.getTile(6,6).getPiece());
    }

    @Test
    void testSetBoardLeopard(){
        assertEquals(new Leopard("red"), testBoard.getTile(2,2).getPiece());
        assertEquals(new Leopard("blue"), testBoard.getTile(6,4).getPiece());
    }

    @Test
    void testSetBoardWolf(){
        assertEquals(new Wolf("red"), testBoard.getTile(2,4).getPiece());
        assertEquals(new Wolf("blue"), testBoard.getTile(6,2).getPiece());
    }

    @Test
    void testSetBoardElephant(){
        assertEquals(new Elephant("red"), testBoard.getTile(2,6).getPiece());
        assertEquals(new Elephant("blue"), testBoard.getTile(6,0).getPiece());
    }

    @Test
    void testMoveNoCapture(){
        //Get piece, make sure it's a lion
        Piece piece = testBoard.getTile(0, 0).getPiece();
        assertTrue(piece instanceof Lion);

        //Make sure there is not already a lion where we are about to move
        assertFalse(testBoard.getTile(0, 1).getPiece() instanceof Lion);

        //Move the lion
        testBoard.move(piece, new Location(0,1));

        //Make sure the lion did indeed move.
        assertTrue(testBoard.getTile(0, 1).getPiece() instanceof Lion);
        assertEquals(testBoard.getTile(0, 1).getPiece(), piece);
    }

    @Test
    void testMoveCaptureEqualRank(){
        //Get piece, make sure it's a lion, make sure it's red
        Piece red = testBoard.getTile(0, 0).getPiece();
        assertTrue(red instanceof Lion);
        assertEquals("red", red.getColor());

        //Set a blue lion in the space we are about to move
        Piece blue = new Lion("blue");
        testBoard.getTile(new Location(0,1)).setPiece(blue);
        assertTrue(testBoard.getTile(0, 1).getPiece() instanceof Lion);
        assertEquals("blue", testBoard.getTile(0, 1).getPiece().getColor());

        //Move the red lion
        testBoard.move(red, new Location(0,1));

        //Make sure the lion did indeed move.
        assertTrue(testBoard.getTile(0, 1).getPiece() instanceof Lion);
        assertEquals(testBoard.getTile(0, 1).getPiece(), red);
        assertEquals("red", testBoard.getTile(0, 1).getPiece().getColor());
    }


}