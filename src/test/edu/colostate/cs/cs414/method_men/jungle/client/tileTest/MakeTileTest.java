package edu.colostate.cs.cs414.method_men.jungle.client.tileTest;

import edu.colostate.cs.cs414.method_men.jungle.client.Board;
import edu.colostate.cs.cs414.method_men.jungle.client.Location;
import edu.colostate.cs.cs414.method_men.jungle.client.tile.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MakeTileTest {
    private Board testBoard;

    @BeforeEach
    void init() {
        testBoard = new Board();
    }

    @Test
    void testMakeDen() {
        Tile redDen = TileFactory.makeTile(new Location(0, 3), testBoard);
        Tile blueDen = TileFactory.makeTile(new Location(8, 3), testBoard);
        Den testRedDen = new Den("red");
        Den testBlueDen = new Den("blue");

        assertTrue(redDen instanceof Den);
        assertTrue(blueDen instanceof Den);
        assertEquals(blueDen, testBlueDen);
        assertEquals(redDen, testRedDen);
        assertNotEquals(redDen, blueDen);
    }

    @Test
    void testMakeTrap() {
        Tile redTrap1 = TileFactory.makeTile(new Location(0, 2), testBoard);
        Tile redTrap2 = TileFactory.makeTile(new Location(0, 4), testBoard);
        Tile redTrap3 = TileFactory.makeTile(new Location(1, 3), testBoard);

        Tile blueTrap1 = TileFactory.makeTile(new Location(8, 2), testBoard);
        Tile blueTrap2 = TileFactory.makeTile(new Location(8, 4), testBoard);
        Tile blueTrap3 = TileFactory.makeTile(new Location(7, 3), testBoard);


        Trap testRedTrap = new Trap("red");
        Trap testBlueTrap = new Trap("blue");

        assertTrue(redTrap1 instanceof Trap);
        assertTrue(blueTrap2 instanceof Trap);
        assertEquals(blueTrap3, testBlueTrap);
        assertEquals(redTrap2, testRedTrap);
        assertNotEquals(redTrap3, blueTrap1);
    }

    @Test
    void testMakeRiver() {
        Tile river1 = TileFactory.makeTile(new Location(3, 1), testBoard);
        Tile river2 = TileFactory.makeTile(new Location(5, 5), testBoard);

        River testRiver = new River();

        assertTrue(river1 instanceof River);
        assertTrue(river2 instanceof River);
        assertEquals(river1, testRiver);
        assertEquals(river2, testRiver);
    }

    @Test
    void testMakeOpen() {
        Tile open1 = TileFactory.makeTile(new Location(0, 0), testBoard);
        Tile open2 = TileFactory.makeTile(new Location(6, 6), testBoard);

        Open testOpen = new Open();

        assertTrue(open1 instanceof Open);
        assertTrue(open2 instanceof Open);
        assertEquals(open1, testOpen);
        assertEquals(open2, testOpen);
    }

}
