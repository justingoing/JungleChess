package edu.colostate.cs.cs414.method_men.jungle.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TigerTest {
    Tiger tiger;

    @Test
    void setLocation() {
        tiger = new Tiger("blue");
        Location location = tiger.getLocation();
        tiger.setLocation(0,0);
        assertTrue(tiger.getLocation()!=location);
    }

    @Test
    void getLocation() {
        tiger = new Tiger("blue");
        assertTrue(tiger.getLocation()!=null);
    }

    @Test
    void getLocation2() {
        tiger = new Tiger("purple");
        assertEquals(tiger.getLocation(),null);
    }

    @Test
    void getRow() {
        tiger = new Tiger("blue");
        assertEquals(tiger.getRow(),8);
    }

    @Test
    void getCol() {
        tiger = new Tiger("blue");
        assertEquals(tiger.getCol(),0);
    }

    @Test
    void getName() {
        tiger = new Tiger("blue");
        assertEquals(tiger.getName(),"Tiger");
    }

    @Test
    void getRank() {
        tiger = new Tiger("blue");
        assertEquals(tiger.getRank(),6);
    }

    @Test
    void isRat() {
        tiger = new Tiger("blue");
        assertFalse(tiger.isRat());
    }
}
