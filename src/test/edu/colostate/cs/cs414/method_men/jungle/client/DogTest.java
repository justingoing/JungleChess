package edu.colostate.cs.cs414.method_men.jungle.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DogTest {
    Dog dogTest;

    @Test
    void constructor1() {
        dogTest = new Dog("red");
        Location loc1 = dogTest.getLocation();
        assertTrue(loc1.getRow() == 1 && loc1.getCol() == 1);

    }

    @Test
    void constructor2() {
        dogTest = new Dog("blue");
        Location loc2 = dogTest.getLocation();
        assertTrue(loc2.getRow() == 7 && loc2.getCol() == 5);
    }
}
