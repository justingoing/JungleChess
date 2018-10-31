package edu.colostate.cs.cs414.method_men.jungle.client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CatTest {
    Cat testCat;

    @Test
    void constructor() {
        testCat = new Cat("white");
        Location loc1 = testCat.getLocation();
        assertTrue(loc1.getRow() == 1 && loc1.getCol() == 5);

    }

    @Test
    void constructor2() {
        testCat = new Cat("black");
        Location loc2 = testCat.getLocation();
        assertTrue(loc2.getRow() == 7 && loc2.getCol() == 1);
    }
}
