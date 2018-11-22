package edu.colostate.cs.cs414.method_men.jungle.client.pieceTest;

import edu.colostate.cs.cs414.method_men.jungle.client.Board;
import edu.colostate.cs.cs414.method_men.jungle.client.Location;
import edu.colostate.cs.cs414.method_men.jungle.client.piece.Piece;
import edu.colostate.cs.cs414.method_men.jungle.client.piece.Rat;
import edu.colostate.cs.cs414.method_men.jungle.client.piece.Elephant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RatTest {
    private Board board;
    private Piece rat;

    @BeforeEach
    void init() {
        board = new Board();
        rat = board.getTile(new Location(2,0)).getPiece();
    }

    @Test
    void testValidMoveRatOpenTileOntoElephant(){
        //Move rat next to enemy elephant
        board.move(rat, new Location(5, 0));
        assertTrue(board.getTile(5,0).getPiece() instanceof Rat);
        assertTrue(board.getTile(2,0).getPiece() == null);

        //Onto elephant is valid move
        assertTrue(rat.isValidMove(new Location(6,0), board));
    }

    @Test
    void testValidMoveRatRiverTileOntoElephant(){
        Piece enemyElephant = board.getTile(new Location(6,0)).getPiece();

        //Move rat next into river
        board.move(rat, new Location(5, 1));
        assertTrue(board.getTile(5,1).getPiece() instanceof Rat);
        assertTrue(board.getTile(2,0).getPiece() == null);

        //Move elephant next to rat in river
        board.move(enemyElephant, new Location(5, 0));
        assertTrue(board.getTile(5,0).getPiece() instanceof Elephant);
        assertTrue(board.getTile(6,0).getPiece() == null);

        //Onto elephant is invalid move
        assertFalse(rat.isValidMove(new Location(5,0), board));
    }

    @Test
    void testValidMoveRatIntoRiver(){
        board.move(rat, new Location(3, 0));
        assertTrue(board.getTile(3,0).getPiece() instanceof Rat);
        assertTrue(board.getTile(2,0).getPiece() == null);

        //Onto elephant is valid move
        assertTrue(rat.isValidMove(new Location(3,1), board));
    }

    @Test
    void testValidMoveRatOpenTileOntoOpenTileWithRat(){
        //Move rat next to enemy rat
        board.move(rat, new Location(6, 5));
        assertTrue(board.getTile(6,5).getPiece() instanceof Rat);
        assertTrue(board.getTile(2,0).getPiece() == null);


        assertTrue(rat.isValidMove(new Location(6,6), board));
    }

    @Test
    void testValidMoveRatRiverTileOntoRiverTileWithRat(){
        Piece enemyRat = board.getTile(new Location(6,6)).getPiece();

        //Move rat into river
        board.move(rat, new Location(3, 1));
        assertTrue(board.getTile(3,1).getPiece() instanceof Rat);
        assertTrue(board.getTile(2,0).getPiece() == null);

        //Move ENEMY rat into river, next to friendly rat
        board.move(enemyRat, new Location(4, 1));
        assertTrue(board.getTile(4,1).getPiece() instanceof Rat);
        assertTrue(board.getTile(6,6).getPiece() == null);

        assertTrue(rat.isValidMove(new Location(4,1), board));
    }

    @Test
    void testValidMoveRatOpenTileOntoOpenTileWithHigherRankedEnemy(){
        //Move rat next to enemy rat
        board.move(rat, new Location(6, 1));
        assertTrue(board.getTile(6,1).getPiece() instanceof Rat);
        assertTrue(board.getTile(2,0).getPiece() == null);


        assertFalse(rat.isValidMove(new Location(7,1), board));
        assertFalse(rat.isValidMove(new Location(6,2), board));
    }




}
