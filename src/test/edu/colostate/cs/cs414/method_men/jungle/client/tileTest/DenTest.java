package edu.colostate.cs.cs414.method_men.jungle.client.tileTest;

import edu.colostate.cs.cs414.method_men.jungle.client.tile.Den;
import edu.colostate.cs.cs414.method_men.jungle.client.tile.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DenTest {

    @Test
    void testConstructor() {
        Tile redDen = new Den("red");
        Tile blueDen = new Den("blue");
        assertNotEquals(redDen, blueDen);
    }
}
