package edu.colostate.cs.cs414.method_men.jungle.client.tileTest;

import edu.colostate.cs.cs414.method_men.jungle.client.tile.Trap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrapTest {

    @Test
    void getAttribute() {
        Trap trap = new Trap();
        assertEquals(trap.getAttribute(),'T');
    }
}
