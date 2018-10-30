package edu.colostate.cs.cs414.method_men.jungle.client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CatTest {

    @Test
    void constructor() {
        Cat test1 = new Cat("white");
        Location loc1 = test1.getLocation();
        assertTrue(loc1.getRow() == 1 && loc1.getCol() == 5);

    }

    @Test
    void constructor2() {
        Cat test2 = new Cat("black");
        Location loc2 = test2.getLocation();
        assertTrue(loc2.getRow() == 7 && loc2.getCol() == 1);
    }
}
