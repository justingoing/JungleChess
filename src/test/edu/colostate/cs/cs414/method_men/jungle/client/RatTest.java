package edu.colostate.cs.cs414.method_men.jungle.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RatTest {
    Rat rat;

    @Test
    void setLocation() {
        rat = new Rat("blue");
        Location location = rat.getLocation();
        rat.setLocation(0,0);
        assertTrue(rat.getLocation()!=location);
    }

    @Test
    void getLocation() {
        rat = new Rat("blue");
        assertTrue(rat.getLocation()!=null);
    }

    @Test
    void getLocation2() {
        rat = new Rat("purple");
        assertEquals(rat.getLocation(),null);
    }

    @Test
    void getRow() {
        rat = new Rat("blue");
        assertEquals(rat.getRow(),6);
    }

    @Test
    void getCol() {
        rat = new Rat("blue");
        assertEquals(rat.getCol(),6);
    }

    @Test
    void getName() {
        rat = new Rat("blue");
        assertEquals(rat.getName(),"Rat");
    }

    @Test
    void getRank() {
        rat = new Rat("blue");
        assertEquals(rat.getRank(),1);
    }

    @Test
    void isRat() {
        rat = new Rat("blue");
        assertTrue(rat.isRat());
    }
}
