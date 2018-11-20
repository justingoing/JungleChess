package edu.colostate.cs.cs414.method_men.jungle.client.pieceTest;

import edu.colostate.cs.cs414.method_men.jungle.client.Location;
import edu.colostate.cs.cs414.method_men.jungle.client.piece.Elephant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElephantTest {
    Elephant testElephant;

    @Test
    void constructor1() {
        testElephant = new Elephant("red");
        Location loc1 = testElephant.getLocation();
        assertTrue(loc1.getRow() == 2 && loc1.getCol() == 6);
    }

    @Test
    void constructor2() {
        testElephant = new Elephant("blue");
        Location loc2 = testElephant.getLocation();
        assertTrue(loc2.getRow() == 6 && loc2.getCol() == 0);
    }

}
