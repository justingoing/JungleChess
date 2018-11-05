package edu.colostate.cs.cs414.method_men.jungle.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LocationTest {

    @Test
    public void testEquals(){
        Location loc1 = new Location(1, 1);
        Location loc2 = new Location(1, 1);
        Location loc3 = new Location(5, 5);

        assertEquals(loc1, loc2);
        assertNotEquals(loc1, loc3);
        assertNotEquals(loc2, loc3);
    }
}
