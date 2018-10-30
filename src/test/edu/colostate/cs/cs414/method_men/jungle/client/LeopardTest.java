package edu.colostate.cs.cs414.method_men.jungle.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeopardTest {

    @Test
    void constructor1() {
        Leopard test1 = new Leopard("white");
        Location loc1 = test1.getLocation();
        assertTrue(loc1.getRow() == 2 && loc1.getCol() == 2);
    }

    @Test
    void constructor2() {
        Leopard test2 = new Leopard("black");
        Location loc2 = test2.getLocation();
        assertTrue(loc2.getRow() == 6 && loc2.getCol() == 4);
    }
}
