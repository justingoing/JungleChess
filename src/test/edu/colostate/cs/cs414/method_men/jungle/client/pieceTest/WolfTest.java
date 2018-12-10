package edu.colostate.cs.cs414.method_men.jungle.client.pieceTest;

import edu.colostate.cs.cs414.method_men.jungle.client.Game.Location;
import edu.colostate.cs.cs414.method_men.jungle.client.Game.piece.Wolf;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WolfTest {
    Wolf wolf;

    @Test
    void setLocation() {
        wolf = new Wolf("blue");
        Location location = wolf.getLocation();
        wolf.setLocation(0,0);
        assertTrue(wolf.getLocation()!=location);
    }

    @Test
    void getLocation() {
        wolf = new Wolf("blue");
        assertTrue(wolf.getLocation()!=null);
    }

    @Test
    void getLocation2() {
        wolf = new Wolf("purple");
        assertEquals(wolf.getLocation(),null);
    }

    @Test
    void getRow() {
        wolf = new Wolf("blue");
        assertEquals(wolf.getRow(),6);
    }

    @Test
    void getCol() {
        wolf = new Wolf("blue");
        assertEquals(wolf.getCol(),2);
    }

    @Test
    void getName() {
        wolf = new Wolf("blue");
        assertEquals(wolf.getName(),"Wolf");
    }

    @Test
    void getRank() {
        wolf = new Wolf("blue");
        assertEquals(wolf.getRank(),3);
    }

}
