package edu.colostate.cs.cs414.method_men.jungle.client.tileTest;

import edu.colostate.cs.cs414.method_men.jungle.client.tile.River;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RiverTest {

    @Test
    void testGetAttribute() {
        River river = new River();
        assertEquals(river.getAttribute(),'~');
    }
}
