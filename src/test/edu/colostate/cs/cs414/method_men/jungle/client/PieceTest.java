package edu.colostate.cs.cs414.method_men.jungle.client;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    Piece test = new Piece("Rat", 1, "white");
    Piece test1 = new Piece("tiger", 6, "white");

    @Test
    void constructor(){
        assertEquals("Rat", test.getName());
        assertEquals(1, test.getRank());
        assertEquals("white", test.getColor());
    }

    @Test
    void setLocation() {
        test.setLocation(0,0);
        assertEquals(test.getRow(), 0);
        assertEquals(test.getCol(), 0);
    }

    @Test
    void getLocation() {
        int[] loc = test.getLocation();
        assertEquals(loc[0], 0);
        assertEquals(loc[1], 0);
    }

    @Test
    void getRow() {
        assertEquals(0, test.getRow());
    }

    @Test
    void getCol() {
        assertEquals(0, test.getCol());
    }

    @Test
    void getName() {
        assertEquals("Rat", test.getName());
    }

    @Test
    void getRank() {
        assertEquals(1, test.getRank());
    }

    @Test
    void getColor(){
        assertEquals("white", test.getColor());
    }

    @Test
    void isRat() {
        assertTrue(test.isRat());
        assertFalse(test1.isRat());
    }
}
