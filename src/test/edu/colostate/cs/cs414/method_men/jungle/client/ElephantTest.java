package edu.colostate.cs.cs414.method_men.jungle.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElephantTest {
    @Test
    void constructor() {
        Elephant test1 = new Elephant("white");
        Elephant test2 = new Elephant("black");
        int[] loc1 = test1.getLocation();
        assertTrue(loc1[0] == 2 && loc1[1] == 6);
        int[] loc2 = test2.getLocation();
        assertTrue(loc2[0] == 6 && loc2[1] == 0);
    }
}
