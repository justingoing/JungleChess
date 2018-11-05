package edu.colostate.cs.cs414.method_men.jungle.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TileFactoryTest {
    TileFactory tf;

    @BeforeEach
    void init() {
        tf = new TileFactory();
    }

    @Test
    void testMakeDen() {
        Tile redDen = tf.makeTile(new Location(0, 3));
        Tile blueDen = tf.makeTile(new Location(8, 3));
        Den testRedDen = new Den("Red");
        Den testBlueDen = new Den("Blue");

        assertTrue(redDen instanceof Den);
        assertTrue(blueDen instanceof Den);
        assertTrue(blueDen.equals(testBlueDen));
        assertTrue(redDen.equals(testRedDen));
        assertNotEquals(redDen, blueDen);
    }

    @Test
    void testMakeTrap() {
        Tile redTrap1 = tf.makeTile(new Location(0, 2));
        Tile redTrap2 = tf.makeTile(new Location(0, 4));
        Tile redTrap3 = tf.makeTile(new Location(1, 3));

        Tile blueTrap1 = tf.makeTile(new Location(8, 2));
        Tile blueTrap2 = tf.makeTile(new Location(8, 4));
        Tile blueTrap3 = tf.makeTile(new Location(7, 3));


        Trap testRedTrap = new Trap("Red");
        Trap testBlueTrap = new Trap("Blue");

        assertTrue(redTrap1 instanceof Trap);
        assertTrue(blueTrap2 instanceof Trap);
        assertTrue(blueTrap3.equals(testBlueTrap));
        assertTrue(redTrap2.equals(testRedTrap));
        assertNotEquals(redTrap3, blueTrap1);
    }


    @Test
    void testMakeRiver() {
        Tile river1 = tf.makeTile(new Location(3, 1));
        Tile river2 = tf.makeTile(new Location(5, 5));

        River testRiver = new River();

        assertTrue(river1 instanceof River);
        assertTrue(river2 instanceof River);
        assertTrue(river1.equals(testRiver));
        assertTrue(river2.equals(testRiver));
    }

    @Test
    void testMakeOpen() {
        Tile open1 = tf.makeTile(new Location(0, 0));
        Tile open2 = tf.makeTile(new Location(6, 6));

        Open testOpen = new Open();

        assertTrue(open1 instanceof Open);
        assertTrue(open2 instanceof Open);
        assertTrue(open1.equals(testOpen));
        assertTrue(open2.equals(testOpen));
    }
}
