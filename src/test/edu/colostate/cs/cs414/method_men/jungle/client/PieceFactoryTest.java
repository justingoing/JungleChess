package edu.colostate.cs.cs414.method_men.jungle.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PieceFactoryTest {
    PieceFactory pf;

    @BeforeEach
    void init() {
        pf = new PieceFactory();
    }

    @Test
    void testMakeLion() {
        Piece redLion = pf.makePiece(new Location(0, 0));
        Piece blueLion = pf.makePiece(new Location(8, 6));
        Lion testRedLion = new Lion("red");
        Lion testBlueLion = new Lion("blue");

        assertTrue(redLion instanceof Lion);
        assertTrue(blueLion instanceof Lion);
        assertTrue(blueLion.equals(testBlueLion));
        assertTrue(redLion.equals(testRedLion));
    }
}
