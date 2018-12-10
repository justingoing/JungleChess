package edu.colostate.cs.cs414.method_men.jungle.client.pieceTest;

import edu.colostate.cs.cs414.method_men.jungle.client.Game.Location;
import edu.colostate.cs.cs414.method_men.jungle.client.Game.piece.Leopard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeopardTest {
    Leopard testLeopard;

    @Test
    void constructor1() {
        testLeopard = new Leopard("red");
        Location loc1 = testLeopard.getLocation();
        assertTrue(loc1.getRow() == 2 && loc1.getCol() == 2);
    }

    @Test
    void constructor2() {
        testLeopard = new Leopard("blue");
        Location loc2 = testLeopard.getLocation();
        assertTrue(loc2.getRow() == 6 && loc2.getCol() == 4);
    }
}
