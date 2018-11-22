package edu.colostate.cs.cs414.method_men.jungle.client.pieceTest;

import edu.colostate.cs.cs414.method_men.jungle.client.Board;
import edu.colostate.cs.cs414.method_men.jungle.client.Location;
import edu.colostate.cs.cs414.method_men.jungle.client.piece.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JumperPieceTest {

    private Board board;
    private Piece p;

    @BeforeEach
    void init() {
        board = new Board();
        p = board.getTile(new Location(0,0)).getPiece();
    }

    @Test
    void testValidMoveJumperRiver(){
        //Move Lion to next to river
        board.move(p, new Location(5, 3));
        assertTrue(board.getTile(5,3).getPiece() instanceof Lion);
        assertTrue(board.getTile(0,0).getPiece() == null);

        assertFalse(p.isValidMove(new Location(5,2), board)); //River
        assertFalse(p.isValidMove(new Location(5,4), board)); //River
    }

    @Test
    void testValidMoveJumperNoMove(){
        assertFalse(p.isValidMove(new Location(0,0), board)); //No move
    }

    @Test
    void testValidMoveJumperBackAndForth(){
        //Move Lion to edge, next to lion and friendly trap
        board.move(p, new Location(0, 1));
        assertTrue(board.getTile(0,1).getPiece() instanceof Lion);
        assertTrue(board.getTile(0,0).getPiece() == null);

        assertTrue(p.isValidMove(new Location(0,0), board)); //Move back
    }

    @Test
    void testValidMoveJumperSimpleRejection(){
        //Move Lion to edge, next to lion and friendly trap
        board.move(p, new Location(0, 1));
        assertTrue(board.getTile(0,1).getPiece() instanceof Lion);
        assertTrue(board.getTile(0,0).getPiece() == null);

        assertFalse(p.isValidMove(new Location(-1,1), board)); //Out of bounds
        assertFalse(p.isValidMove(new Location(1,1), board)); //Adjacent friend
        assertFalse(p.isValidMove(new Location(2,1), board)); //Out of range ; 2 move
    }

    @Test
    void testValidMoveJumperCapture(){
        //Move Lion next to capturable enemy, and uncapturable enemy
        board.move(p, new Location(6, 1));
        assertTrue(board.getTile(6,1).getPiece() instanceof Lion);
        assertTrue(board.getTile(0,0).getPiece() == null);

        //Move enemy lion next to lion
        board.move(board.getTile(new Location(8,6)).getPiece(), new Location(6, 2));
        assertTrue(board.getTile(6,1).getPiece() instanceof Lion);
        assertTrue(board.getTile(0,0).getPiece() == null);


        //Test capture success and capture fail
        assertTrue(p.isValidMove(new Location(7,1), board)); //Enemy is lower rank
        assertTrue(p.isValidMove(new Location(6,2), board)); //Enemy same rank
        assertFalse(p.isValidMove(new Location(6,0), board)); //Enemy is higher rank

    }

    @Test
    void testValidMoveJumperCaptureOnFriendlyTrap(){
        //Move Lion next to capturable enemy, and uncapturable enemy
        board.move(p, new Location(1, 2));
        assertTrue(board.getTile(1,2).getPiece() instanceof Lion);
        assertTrue(board.getTile(0,0).getPiece() == null);

        //Move an elephant onto the friendly trap
        Piece q = board.getTile(6, 0).getPiece(); //Enemy elephant
        board.move(q, new Location(1, 3));
        assertTrue(board.getTile(1,3).getPiece() instanceof Elephant);
        assertTrue(board.getTile(6,0).getPiece() == null);

        //Test capture success on a trap
        assertTrue(p.isValidMove(new Location(1,3), board)); //Enemy is higher rank

    }

    @Test
    void testValidMoveJumperJumpingUpNoPiece(){
        //Move Lion below river tile
        board.move(p, new Location(6, 1));
        assertTrue(board.getTile(6,1).getPiece() instanceof Lion);
        assertTrue(board.getTile(0,0).getPiece() == null);

        assertTrue(p.isValidMove(new Location(2,1), board)); //Enemy is higher rank
    }

    @Test
    void testValidMoveJumperJumpingDownNoPiece(){
        //Move Lion above river tile
        board.move(p, new Location(2, 1));
        assertTrue(board.getTile(2,1).getPiece() instanceof Lion);
        assertTrue(board.getTile(0,0).getPiece() == null);

        assertTrue(p.isValidMove(new Location(6,1), board)); //Enemy is higher rank

    }

    @Test
    void testValidMoveJumperJumpingLeftNoPiece(){
        //Move Lion below river tile
        board.move(p, new Location(3, 3));
        assertTrue(board.getTile(3,3).getPiece() instanceof Lion);
        assertTrue(board.getTile(0,0).getPiece() == null);

        assertTrue(p.isValidMove(new Location(3,0), board));

    }

    @Test
    void testValidMoveJumperJumpingRightNoPiece(){
        //Move Lion below river tile
        board.move(p, new Location(3, 0));
        assertTrue(board.getTile(3,0).getPiece() instanceof Lion);
        assertTrue(board.getTile(0,0).getPiece() == null);

        assertTrue(p.isValidMove(new Location(3,3), board));

    }

    @Test
    void testValidMoveJumperJumpingUpCapturablePiece(){
        //Move Lion below river tile
        board.move(p, new Location(6, 1));
        assertTrue(board.getTile(6,1).getPiece() instanceof Lion);
        assertTrue(board.getTile(0,0).getPiece() == null);

        //Move enemy wolf across river
        Piece q = board.getTile(6, 2).getPiece(); //Enemy wolf
        board.move(q, new Location(2, 1));
        assertTrue(board.getTile(2,1).getPiece() instanceof Wolf);
        assertTrue(board.getTile(6,2).getPiece() == null);

        assertTrue(p.isValidMove(new Location(2,1), board)); //Enemy is higher rank
    }

    @Test
    void testValidMoveJumperJumpingDownCapturablePiece(){
        //Move Lion above river tile
        board.move(p, new Location(2, 1));
        assertTrue(board.getTile(2,1).getPiece() instanceof Lion);
        assertTrue(board.getTile(0,0).getPiece() == null);

        //Move enemy wolf across river
        Piece q = board.getTile(6, 2).getPiece(); //Enemy wolf
        board.move(q, new Location(6, 1));
        assertTrue(board.getTile(6,1).getPiece() instanceof Wolf);
        assertTrue(board.getTile(6,2).getPiece() == null);


        assertTrue(p.isValidMove(new Location(6,1), board));
    }

    @Test
    void testValidMoveJumperJumpingLeftCapturablePiece(){
        //Move Lion below river tile
        board.move(p, new Location(3, 3));
        assertTrue(board.getTile(3,3).getPiece() instanceof Lion);
        assertTrue(board.getTile(0,0).getPiece() == null);

        //Move enemy wolf across river
        Piece q = board.getTile(6, 2).getPiece(); //Enemy wolf
        board.move(q, new Location(3, 0));
        assertTrue(board.getTile(3,0).getPiece() instanceof Wolf);
        assertTrue(board.getTile(6,2).getPiece() == null);

        assertTrue(p.isValidMove(new Location(3,0), board));
    }

    @Test
    void testValidMoveJumperJumpingRightCapturablePiece(){
        //Move Lion below river tile
        board.move(p, new Location(3, 0));
        assertTrue(board.getTile(3,0).getPiece() instanceof Lion);
        assertTrue(board.getTile(0,0).getPiece() == null);

        //Move enemy wolf across river
        Piece q = board.getTile(6, 2).getPiece(); //Enemy wolf
        board.move(q, new Location(3, 3));
        assertTrue(board.getTile(3,3).getPiece() instanceof Wolf);
        assertTrue(board.getTile(6,2).getPiece() == null);

        assertTrue(p.isValidMove(new Location(3,3), board));

    }

    @Test
    void testValidMoveJumperJumpingUpUncapturablePiece(){
        //Move Lion below river tile
        board.move(p, new Location(6, 1));
        assertTrue(board.getTile(6,1).getPiece() instanceof Lion);
        assertTrue(board.getTile(0,0).getPiece() == null);

        //Move enemy elephant across river
        Piece q = board.getTile(6, 0).getPiece(); //Enemy elephant
        board.move(q, new Location(2, 1));
        assertTrue(board.getTile(2,1).getPiece() instanceof Elephant);
        assertTrue(board.getTile(6,0).getPiece() == null);

        assertFalse(p.isValidMove(new Location(2,1), board)); //Enemy is higher rank

    }

    @Test
    void testValidMoveJumperJumpingDownUncapturablePiece(){
        //Move Lion above river tile
        board.move(p, new Location(2, 1));
        assertTrue(board.getTile(2,1).getPiece() instanceof Lion);
        assertTrue(board.getTile(0,0).getPiece() == null);

        //Move enemy elephant across river
        Piece q = board.getTile(6, 0).getPiece(); //Enemy elephant
        board.move(q, new Location(6, 1));
        assertTrue(board.getTile(6,1).getPiece() instanceof Elephant);
        assertTrue(board.getTile(6,0).getPiece() == null);


        assertFalse(p.isValidMove(new Location(6,1), board));
    }

    @Test
    void testValidMoveJumperJumpingLeftUncapturablePiece(){
        //Move Lion below river tile
        board.move(p, new Location(3, 3));
        assertTrue(board.getTile(3,3).getPiece() instanceof Lion);
        assertTrue(board.getTile(0,0).getPiece() == null);

        //Move enemy elephant across river
        Piece q = board.getTile(6, 0).getPiece(); //Enemy elephant
        board.move(q, new Location(3, 0));
        assertTrue(board.getTile(3,0).getPiece() instanceof Elephant);
        assertTrue(board.getTile(6,0).getPiece() == null);

        assertFalse(p.isValidMove(new Location(3,0), board));
    }

    @Test
    void testValidMoveJumperJumpingRightUncapturablePiece(){
        //Move Lion below river tile
        board.move(p, new Location(3, 0));
        assertTrue(board.getTile(3,0).getPiece() instanceof Lion);
        assertTrue(board.getTile(0,0).getPiece() == null);

        //Move enemy elephant across river
        Piece q = board.getTile(6, 0).getPiece(); //Enemy elephant
        board.move(q, new Location(3, 3));
        assertTrue(board.getTile(3,3).getPiece() instanceof Elephant);
        assertTrue(board.getTile(6,0).getPiece() == null);

        assertFalse(p.isValidMove(new Location(3,3), board));

    }

    @Test
    void testValidMoveJumperJumpingUpRatInRiver(){
        //Move Lion below river tile
        board.move(p, new Location(6, 1));
        assertTrue(board.getTile(6,1).getPiece() instanceof Lion);
        assertTrue(board.getTile(0,0).getPiece() == null);

        //Move enemy rat into river
        Piece q = board.getTile(6, 6).getPiece(); //Enemy rat
        board.move(q, new Location(5, 1));
        assertTrue(board.getTile(5,1).getPiece() instanceof Rat);
        assertTrue(board.getTile(6,6).getPiece() == null);

        assertFalse(p.isValidMove(new Location(2,1), board));

        //Move enemy rat into river
        board.move(q, new Location(4, 1));
        assertTrue(board.getTile(4,1).getPiece() instanceof Rat);
        assertTrue(board.getTile(5,1).getPiece() == null);

        assertFalse(p.isValidMove(new Location(2,1), board));

        //Move enemy rat into river
        board.move(q, new Location(3, 1));
        assertTrue(board.getTile(3,1).getPiece() instanceof Rat);
        assertTrue(board.getTile(4,1).getPiece() == null);

        assertFalse(p.isValidMove(new Location(2,1), board));
    }

    @Test
    void testValidMoveJumperJumpingDownRatInRiver(){
        //Move Lion below river tile
        board.move(p, new Location(2, 1));
        assertTrue(board.getTile(2,1).getPiece() instanceof Lion);
        assertTrue(board.getTile(0,0).getPiece() == null);

        //Move enemy rat into river
        Piece q = board.getTile(6, 6).getPiece(); //Enemy rat
        board.move(q, new Location(5, 1));
        assertTrue(board.getTile(5,1).getPiece() instanceof Rat);
        assertTrue(board.getTile(6,6).getPiece() == null);

        assertFalse(p.isValidMove(new Location(6,1), board));

        //Move enemy rat into river
        board.move(q, new Location(4, 1));
        assertTrue(board.getTile(4,1).getPiece() instanceof Rat);
        assertTrue(board.getTile(5,1).getPiece() == null);

        assertFalse(p.isValidMove(new Location(6,1), board));

        //Move enemy rat into river
        board.move(q, new Location(3, 1));
        assertTrue(board.getTile(3,1).getPiece() instanceof Rat);
        assertTrue(board.getTile(4,1).getPiece() == null);

        assertFalse(p.isValidMove(new Location(6,1), board));
    }

    @Test
    void testValidMoveJumperJumpingLeftRatInRiver(){
        //Move Lion below river tile
        board.move(p, new Location(3, 3));
        assertTrue(board.getTile(3,3).getPiece() instanceof Lion);
        assertTrue(board.getTile(0,0).getPiece() == null);

        //Move enemy rat into river
        Piece q = board.getTile(6, 6).getPiece(); //Enemy rat
        board.move(q, new Location(3, 1));
        assertTrue(board.getTile(3,1).getPiece() instanceof Rat);
        assertTrue(board.getTile(6,6).getPiece() == null);

        assertFalse(p.isValidMove(new Location(3,0), board));

        //Move enemy rat into river
        board.move(q, new Location(3, 2));
        assertTrue(board.getTile(3,2).getPiece() instanceof Rat);
        assertTrue(board.getTile(3,1).getPiece() == null);

        assertFalse(p.isValidMove(new Location(3,0), board));
    }

    @Test
    void testValidMoveJumperJumpingRightRatInRiver(){
        //Move Lion below river tile
        board.move(p, new Location(3, 0));
        assertTrue(board.getTile(3,0).getPiece() instanceof Lion);
        assertTrue(board.getTile(0,0).getPiece() == null);

        //Move enemy rat into river
        Piece q = board.getTile(6, 6).getPiece(); //Enemy rat
        board.move(q, new Location(3, 1));
        assertTrue(board.getTile(3,1).getPiece() instanceof Rat);
        assertTrue(board.getTile(6,6).getPiece() == null);

        assertFalse(p.isValidMove(new Location(3,3), board));

        //Move enemy rat into river
        board.move(q, new Location(3, 2));
        assertTrue(board.getTile(3,2).getPiece() instanceof Rat);
        assertTrue(board.getTile(3,1).getPiece() == null);

        assertFalse(p.isValidMove(new Location(3,3), board));
    }

}
