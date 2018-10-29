package edu.colostate.cs.cs414.method_men.jungle.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LionTest {

    @Test
    void constructor() {
        Lion test1 = new Lion("white");
        Lion test2 = new Lion("black");
        int[] loc1 = test1.getLocation();
        assertTrue(loc1[0] == 0 && loc1[1] == 0);
        int[] loc2 = test2.getLocation();
        assertTrue(loc2[0] == 8 && loc2[1] == 6);
    }
}
