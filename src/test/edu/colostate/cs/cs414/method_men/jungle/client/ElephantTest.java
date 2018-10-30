package edu.colostate.cs.cs414.method_men.jungle.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElephantTest {
    @Test
    void constructor1() {
        Elephant test1 = new Elephant("white");
        Location loc1 = test1.getLocation();
        assertTrue(loc1.getRow() == 2 && loc1.getCol() == 6);
    }

    @Test
    void constructor2() {
        Elephant test2 = new Elephant("black");
        Location loc2 = test2.getLocation();
        assertTrue(loc2.getRow() == 6 && loc2.getCol() == 0);
    }

}
