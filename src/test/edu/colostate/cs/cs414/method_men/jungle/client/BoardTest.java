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
    void testMakeDen() {
        Tile redDen = testBoard.makeTile(new Location(0, 3));
        Tile blueDen = testBoard.makeTile(new Location(8, 3));
        Den testRedDen = new Den("red");
        Den testBlueDen = new Den("blue");

        assertTrue(redDen instanceof Den);
        assertTrue(blueDen instanceof Den);
        assertTrue(blueDen.equals(testBlueDen));
        assertTrue(redDen.equals(testRedDen));
        assertNotEquals(redDen, blueDen);
    }

    @Test
    void testMakeTrap() {
        Tile redTrap1 = testBoard.makeTile(new Location(0, 2));
        Tile redTrap2 = testBoard.makeTile(new Location(0, 4));
        Tile redTrap3 = testBoard.makeTile(new Location(1, 3));

        Tile blueTrap1 = testBoard.makeTile(new Location(8, 2));
        Tile blueTrap2 = testBoard.makeTile(new Location(8, 4));
        Tile blueTrap3 = testBoard.makeTile(new Location(7, 3));


        Trap testRedTrap = new Trap("red");
        Trap testBlueTrap = new Trap("blue");

        assertTrue(redTrap1 instanceof Trap);
        assertTrue(blueTrap2 instanceof Trap);
        assertTrue(blueTrap3.equals(testBlueTrap));
        assertTrue(redTrap2.equals(testRedTrap));
        assertNotEquals(redTrap3, blueTrap1);
    }

    @Test
    void testMakeRiver() {
        Tile river1 = testBoard.makeTile(new Location(3, 1));
        Tile river2 = testBoard.makeTile(new Location(5, 5));

        River testRiver = new River();

        assertTrue(river1 instanceof River);
        assertTrue(river2 instanceof River);
        assertTrue(river1.equals(testRiver));
        assertTrue(river2.equals(testRiver));
    }

    @Test
    void testMakeOpen() {
        Tile open1 = testBoard.makeTile(new Location(0, 0));
        Tile open2 = testBoard.makeTile(new Location(6, 6));

        Open testOpen = new Open();

        assertTrue(open1 instanceof Open);
        assertTrue(open2 instanceof Open);
        assertTrue(open1.equals(testOpen));
        assertTrue(open2.equals(testOpen));
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