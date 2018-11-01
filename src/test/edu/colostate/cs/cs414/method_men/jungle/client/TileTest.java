package edu.colostate.cs.cs414.method_men.jungle.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    @Test
    void getAttribute() {
        Tile tile = new Tile('p');
        assertTrue(tile.getAttribute()=='p');
    }
}
