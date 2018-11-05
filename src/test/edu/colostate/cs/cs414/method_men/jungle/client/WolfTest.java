package edu.colostate.cs.cs414.method_men.jungle.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WolfTest {
    Wolf wolf;

    @Test
    void setLocation() {
        wolf = new Wolf("black");
        Location location = wolf.getLocation();
        wolf.setLocation(0,0);
        assertTrue(wolf.getLocation()!=location);
    }

    @Test
    void getLocation() {
        wolf = new Wolf("black");
        assertTrue(wolf.getLocation()!=null);
    }

    @Test
    void getLocation2() {
        wolf = new Wolf("purple");
        assertEquals(wolf.getLocation(),null);
    }

    @Test
    void getRow() {
        wolf = new Wolf("black");
        assertEquals(wolf.getRow(),6);
    }

    @Test
    void getCol() {
        wolf = new Wolf("black");
        assertEquals(wolf.getCol(),2);
    }

    @Test
    void getName() {
        wolf = new Wolf("black");
        assertEquals(wolf.getName(),"Wolf");
    }

    @Test
    void getRank() {
        wolf = new Wolf("black");
        assertEquals(wolf.getRank(),3);
    }

    @Test
    void isRat() {
        wolf = new Wolf("black");
        assertFalse(wolf.isRat());
    }
}
