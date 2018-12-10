package edu.colostate.cs.cs414.method_men.jungle.client.pieceTest;

import edu.colostate.cs.cs414.method_men.jungle.client.Game.piece.*;
import edu.colostate.cs.cs414.method_men.jungle.client.Game.Location;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class PieceFactoryTest {
    @Test
    public void testMakeLion() {
        Piece redLion = PieceFactory.makePiece(new Location(0, 0));
        Piece blueLion = PieceFactory.makePiece(new Location(8,6));

        assertTrue(redLion instanceof Lion);
        assertTrue(blueLion instanceof Lion);
    }

    @Test
    public void testMakeRat() {
        Piece redRat = PieceFactory.makePiece(new Location(2, 0));
        Piece blueRat = PieceFactory.makePiece(new Location(6, 6));

        assertTrue(redRat instanceof Rat);
        assertTrue(blueRat instanceof Rat);
    }

    @Test
    public void testMakeDog() {
        Piece redDog = PieceFactory.makePiece(new Location(1, 1));
        Piece blueDog = PieceFactory.makePiece(new Location(7, 5));

        assertTrue(redDog instanceof Dog);
        assertTrue(blueDog instanceof Dog);
    }

    @Test
    public void testMakeLeopard() {
        Piece redLeopard = PieceFactory.makePiece(new Location(2, 2));
        Piece blueLeopard = PieceFactory.makePiece(new Location(6, 4));

        assertTrue(redLeopard instanceof Leopard);
        assertTrue(blueLeopard instanceof Leopard);
    }

    @Test
    public void testMakeWolf() {
        Piece redWolf = PieceFactory.makePiece(new Location(2, 4));
        Piece blueWolf = PieceFactory.makePiece(new Location(6, 2));

        assertTrue(redWolf instanceof Wolf);
        assertTrue(blueWolf instanceof Wolf);
    }

    @Test
    public void testMakeCat() {
        Piece redCat = PieceFactory.makePiece(new Location(1, 5));
        Piece blueCat = PieceFactory.makePiece(new Location(7, 1));

        assertTrue(redCat instanceof Cat);
        assertTrue(blueCat instanceof Cat);
    }

    @Test
    public void testMakeTiger() {
        Piece redTiger = PieceFactory.makePiece(new Location(0, 6));
        Piece blueTiger = PieceFactory.makePiece(new Location(8, 0));

        assertTrue(redTiger instanceof Tiger);
        assertTrue(blueTiger instanceof Tiger);
    }

    @Test
    public void testMakeElephant() {
        Piece redElephant = PieceFactory.makePiece(new Location(2, 6));
        Piece blueElephant = PieceFactory.makePiece(new Location(6, 0));

        assertTrue(redElephant instanceof Elephant);
        assertTrue(blueElephant instanceof Elephant);
    }

}
