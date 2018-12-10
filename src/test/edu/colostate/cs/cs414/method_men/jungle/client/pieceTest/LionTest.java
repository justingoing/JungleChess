package edu.colostate.cs.cs414.method_men.jungle.client.pieceTest;

import edu.colostate.cs.cs414.method_men.jungle.client.Game.Location;
import edu.colostate.cs.cs414.method_men.jungle.client.Game.piece.Lion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LionTest {
    Lion testLion;

    @Test
    void constructor1() {
        testLion = new Lion("red");
        Location loc1 = testLion.getLocation();
        assertTrue(loc1.getRow() == 0 && loc1.getCol() == 0);
    }

    @Test
    void constructor2() {
        testLion = new Lion("blue");
        Location loc2 = testLion.getLocation();
        assertTrue(loc2.getRow() == 8 && loc2.getCol() == 6);
    }
}
