package edu.colostate.cs.cs414.method_men.jungle.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LionTest {

    @Test
    void constructor1() {
        Lion test1 = new Lion("white");
        Location loc1 = test1.getLocation();
        assertTrue(loc1.getRow() == 0 && loc1.getCol() == 0);

    }

    @Test
    void constructor2() {
        Lion test2 = new Lion("black");
        Location loc2 = test2.getLocation();
        assertTrue(loc2.getRow() == 8 && loc2.getCol() == 6);
    }
}
