package edu.colostate.cs.cs414.method_men.jungle.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {
    private Piece testRat;
    private Piece testTiger;

    @BeforeEach
    void init() {
        testRat = new Piece("Rat", 1, "white");
        testTiger = new Piece("tiger", 6, "white");
    }

    @Test
    void testConstructor() {
        assertEquals("Rat", testRat.getName());
        assertEquals(1, testRat.getRank());
        assertEquals("white", testRat.getColor());
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
    void testGetName() {
        assertEquals("Rat", testRat.getName());
    }

    @Test
    void testGetRank() {
        assertEquals(1, testRat.getRank());
    }

    @Test
    void testGetColor(){
        assertEquals("white", testRat.getColor());
    }

    @Test
    void testIsRat() {
        assertTrue(testRat.isRat()  && !testTiger.isRat());
    }
}
