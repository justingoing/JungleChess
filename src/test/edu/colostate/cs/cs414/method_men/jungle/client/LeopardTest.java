package edu.colostate.cs.cs414.method_men.jungle.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeopardTest {

    @Test
    void constructor() {
        Leopard test1 = new Leopard("white");
        Leopard test2 = new Leopard("black");
        int[] loc1 = test1.getLocation();
        assertTrue(loc1[0] == 2 && loc1[1] == 2);
        int[] loc2 = test2.getLocation();
        assertTrue(loc2[0] == 6 && loc2[1] == 4);
    }
}
