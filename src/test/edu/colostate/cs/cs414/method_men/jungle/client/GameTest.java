package edu.colostate.cs.cs414.method_men.jungle.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game g;
    private Player top;
    private Player bot;
    private int turn;
    private Piece p1;
    private Piece p2;
    private int rank;
    private NextMove n;
    private int row;
    private int col;

    @BeforeEach
    void setUp() {
        g = new Game();
        top = g.getPlayer(0);
        bot = g.getPlayer(1);
    }

    @Test void testDebugPrint() { g.debugPrint("Start of Game.java Test cases");}
    @Test void testPrintBoard() { g.printBoard(); }

    @Test
    void testGetDirectionDown() {
        rank = 7;
        p1 = top.getPiece(rank - 1);
        char direction = 'd';
        n = g.getDirection(p1, direction);
        assertTrue(n.getRow() == 1 && n.getCol() == 0);
    }

    @Test
    void testGetDirectionUp() {
        rank = 1;
        p1 = top.getPiece(rank - 1);
        char direction = 'u';
        n = g.getDirection(p1, direction);
        assertTrue(n.getRow() == 1 && n.getCol() == 0);
    }

    @Test
    void testGetDirectionLeft() {
        rank = 8;
        p1 = top.getPiece(rank - 1);
        char direction = 'l';
        n = g.getDirection(p1, direction);
        assertTrue(n.getRow() == 2 && n.getCol() == 5);
    }

    @Test
    void testGetDirectionRight() {
        rank = 3;
        p1 = top.getPiece(rank - 1);
        char direction = 'r';
        n = g.getDirection(p1, direction);
        assertTrue(n.getRow() == 2 && n.getCol() == 5);
    }

    @Test
    void testWhoseTurnIsItTop() {
        g.whoseTurnIsIt(0, "'s turn.");
    }

    @Test
    void testWhoseTurnIsItBottom() {
        g.whoseTurnIsIt(1, "'s turn.");
    }

    @Test
    void testGetPlayerTop() {
        assertEquals(top, g.getPlayer(0));
    }

    @Test
    void testGetPlayerBottom() {
        assertEquals(bot, g.getPlayer(1));
    }

    @Test
    void testIncrementTurnTop() {
        turn = g.getTurn();
        g.incrementTurn();
        assertEquals(turn, g.otherPlayer());
    }

    @Test
    void testIncrementTurnBottom() {
        turn = g.getTurn();
        if (turn == 1) {
            g.incrementTurn();
        } // turn = 0;
        g.incrementTurn(); // turn = 1;
        assertEquals(1, g.getTurn());
    }

    @Test
    void testGetTurn() {
        turn = g.getTurn();
        if (turn == 1) {
            g.incrementTurn();
        }
        assertEquals(0, g.getTurn());
    }

    @Test
    void testOtherPlayer_First() {
        turn = g.getTurn();
        assertFalse(turn == g.otherPlayer());
    }

    @Test
    void testOtherPlayer_Second() {
        turn = g.getTurn();
        assertFalse(turn == g.otherPlayer(turn));
    }

    @Test
    void testDoesPieceLocationMatch_FirstPass() {
        rank = 7;
        p1 = top.getPiece(rank - 1);
        Location lionLoc = p1.getLocation();
        assertTrue(g.doesPieceLocationMatch(lionLoc, 0, 0));
    }

    @Test
    void testDoesPieceLocationMatch_FirstFail() {
        rank = 7;
        p1 = top.getPiece(rank - 1);
        Location lionLoc = p1.getLocation();
        assertFalse(g.doesPieceLocationMatch(lionLoc, 8, 6));
    }

    @Test
    void testDoesPieceLocationMatch_SecondPass() {
        rank = 7;
        p1 = top.getPiece(rank - 1);
        assertTrue(g.doesPieceLocationMatch(p1, 0, 0));
    }

    @Test
    void testDoesPieceLocationMatch_SecondFail() {
        rank = 7;
        p1 = top.getPiece(rank - 1);
        assertFalse(g.doesPieceLocationMatch(p1, 8, 6));
    }

    @Test
    void testDoesPieceLocationMatch_ThirdPass() {
        rank = 7;
        p1 = top.getPiece(rank - 1);
        n = new NextMove(p1, 1, 0);
        assertTrue(g.doesPieceLocationMatch(n, 1, 0));
    }

    @Test
    void testDoesPieceLocationMatch_ThirdFail() {
        rank = 7;
        p1 = top.getPiece(rank - 1);
        n = new NextMove(p1, 1, 0);
        assertFalse(g.doesPieceLocationMatch(n, -1, -1));
    }

    @Test
    void testContainsPieceTopPass() {
        for (int i = 1; i <= 8; ++i) {
            rank = i;
            p1 = top.getPiece(rank - 1);
            row = p1.getRow();
            col = p1.getCol();

            int outcome = g.containsPiece(0, p1.getRow(), p1.getCol());
            assertEquals(outcome, rank);
        }
    }

    @Test
    void testContainsPieceTopFail() {
        rank = 7;
        p1 = top.getPiece(rank - 1);
        row = p1.getRow();
        col = p1.getCol();

        int outcome = g.containsPiece(0, row + 1, col);
        assertFalse(outcome == rank);
    }

    @Test
    void testContainsPieceTopPassBot() {
        rank = 7;
        p2 = bot.getPiece(rank - 1);

        int outcome = g.containsPiece(0, p2.getRow(), p2.getCol());
        assertEquals(-1, outcome);
    }

    @Test
    void testRatCapturesElephantPass() {
    }

    @Test
    void testElephantTryingToCaptureRat() {
    }

    @Test
    void testIsLandingValid() {
    }

    @Test
    void testIsAbleToJump() {
    }

    @Test
    void testIsTryingToJump_First() {
    }

    @Test
    void testIsTryingToJump_Second() {
    }

    @Test
    void testIsRatTryingToEmerge() {
    }

    @Test
    void testIsAbleToEmerge() {
    }

    @Test
    void testIsValidMove() {
    }

    @Test
    void testRetrieveCliPieceRank() {
    }

    @Test
    void testRetrieveCliDirection() {
    }

    @Test
    void testRetrieveCliInput() {
    }

    @Test
    void testMakeMoveUi() {
    }

    @Test
    void testMakeMoveCli() {
    }

    @Test
    void testMoveThePiece() {
    }

    @Test
    void testIsDen_Game() {
    }

    @Test
    void testWinnerCheck() {
    }
}