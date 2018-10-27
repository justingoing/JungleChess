package edu.colostate.cs.cs414.method_men.jungle.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DogTest {

    @Test
    void constructor() {
        Dog test1 = new Dog("white");
        Dog test2 = new Dog("black");
        int[] loc1 = test1.getLocation();
        assertTrue(loc1[0] == 1 && loc1[1] == 1);
        int[] loc2 = test2.getLocation();
        assertTrue(loc2[0] == 7 && loc2[1] == 5);
    }
}
