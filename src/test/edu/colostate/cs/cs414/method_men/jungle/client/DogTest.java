package edu.colostate.cs.cs414.method_men.jungle.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DogTest {

    @Test
    void constructor1() {
        Dog test1 = new Dog("white");
        Location loc1 = test1.getLocation();
        assertTrue(loc1.getRow() == 1 && loc1.getCol() == 1);

    }

    @Test
    void constructor2() {
        Dog test2 = new Dog("black");
        Location loc2 = test2.getLocation();
        assertTrue(loc2.getRow() == 7 && loc2.getCol() == 5);
    }
}
