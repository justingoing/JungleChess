package edu.colostate.cs.cs414.method_men.jungle.client.pieceTest;

import edu.colostate.cs.cs414.method_men.jungle.client.Board;
import edu.colostate.cs.cs414.method_men.jungle.client.Location;
import edu.colostate.cs.cs414.method_men.jungle.client.piece.Dog;
import edu.colostate.cs.cs414.method_men.jungle.client.piece.Elephant;
import edu.colostate.cs.cs414.method_men.jungle.client.piece.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GenericPieceTest {

    private Board board;
    private Piece p;

    @BeforeEach
    void init() {
        board = new Board();
        p = board.getTile_(new Location(1,1)).getPiece();
    }

    @Test
    void testValidMoveGenericRiver(){
        //Move dog to next to river
        board.move(p, new Location(5, 3));
        assertTrue(board.getTile_(5,3).getPiece() instanceof Dog);
        assertTrue(board.getTile_(1,1).getPiece() == null);

        assertFalse(p.isValidMove_(new Location(5,2), board)); //River
        assertFalse(p.isValidMove_(new Location(5,4), board)); //River
    }

    @Test
    void testValidMoveGenericNoMove(){
        assertFalse(p.isValidMove_(new Location(1,1), board)); //No move
    }

    @Test
    void testValidMoveGenericBackAndForth(){
        //Move dog to edge, next to lion and friendly trap
        board.move(p, new Location(0, 1));
        assertTrue(board.getTile_(0,1).getPiece() instanceof Dog);
        assertTrue(board.getTile_(1,1).getPiece() == null);

        assertTrue(p.isValidMove_(new Location(1,1), board)); //Same Tile ; no move
    }

    @Test
    void testValidMoveGenericSimpleRejection(){
        //Move dog to edge, next to lion and friendly trap
        board.move(p, new Location(0, 1));
        assertTrue(board.getTile_(0,1).getPiece() instanceof Dog);
        assertTrue(board.getTile_(1,1).getPiece() == null);

        assertFalse(p.isValidMove_(new Location(-1,1), board)); //Out of bounds
        assertFalse(p.isValidMove_(new Location(0,0), board)); //Adjacent friend
        assertFalse(p.isValidMove_(new Location(2,1), board)); //Out of range ; 2 move
    }

    @Test
    void testValidMoveGenericCapture(){
        //Move dog next to capturable enemy, and uncapturable enemy
        board.move(p, new Location(6, 5));
        assertTrue(board.getTile_(6,5).getPiece() instanceof Dog);
        assertTrue(board.getTile_(1,1).getPiece() == null);

        //Test capture success and capture fail
        assertTrue(p.isValidMove_(new Location(6,6), board)); //Enemy is lower rank
        assertTrue(p.isValidMove_(new Location(7,5), board)); //Enemy same rank
        assertFalse(p.isValidMove_(new Location(6,4), board)); //Enemy is higher rank

    }

    @Test
    void testValidMoveGenericCaptureOnFriendlyTrap(){
        //Move dog next to capturable enemy, and uncapturable enemy
        board.move(p, new Location(1, 2));
        assertTrue(board.getTile_(1,2).getPiece() instanceof Dog);
        assertTrue(board.getTile_(1,1).getPiece() == null);

        //Move an elephant onto the friendly trap
        Piece q = board.getTile_(6, 0).getPiece(); //Enemy elephant
        board.move(q, new Location(1, 3));
        assertTrue(board.getTile_(1,3).getPiece() instanceof Elephant);
        assertTrue(board.getTile_(6,0).getPiece() == null);

        //Test capture success on a trap
        assertTrue(p.isValidMove_(new Location(1,3), board)); //Enemy is higher rank

    }


}
