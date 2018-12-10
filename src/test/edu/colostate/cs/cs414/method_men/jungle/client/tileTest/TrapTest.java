package edu.colostate.cs.cs414.method_men.jungle.client.tileTest;

import edu.colostate.cs.cs414.method_men.jungle.client.Game.tile.Tile;
import edu.colostate.cs.cs414.method_men.jungle.client.Game.tile.Trap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrapTest {

    @Test
    void testConstructor() {
        Tile redTrap = new Trap("red");
        Tile blueTrap = new Trap("blue");
        assertNotEquals(redTrap, blueTrap);
    }
}
