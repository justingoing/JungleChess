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
    void init() {
        g = new Game();
        top = g.getPlayer(0);
        bot = g.getPlayer(1);
        if (g.getTurn() == 1) {
            g.incrementTurn();
        }
    }

    @Test void testDebugPrint() {
        g.debugPrint("debugPrint only works when true");
    }

    @Test void testPrintBoard() {
        g.printBoard();
    }

    @Test
    void testGetDirectionDown() {
        p1 = top.getPiece(7 - 1);
        char direction = 'd';
        n = g.getDirection(p1, direction);

        assertTrue(n.getRow() == 1 && n.getCol() == 0);
    }

    @Test
    void testGetDirectionUp() {
        p1 = top.getPiece(1 - 1);
        char direction = 'u';
        n = g.getDirection(p1, direction);

        assertTrue(n.getRow() == 1 && n.getCol() == 0);
    }

    @Test
    void testGetDirectionLeft() {
        p1 = top.getPiece(8 - 1);
        char direction = 'l';
        n = g.getDirection(p1, direction);

        assertTrue(n.getRow() == 2 && n.getCol() == 5);
    }

    @Test
    void testGetDirectionRight() {
        p1 = top.getPiece(3 - 1);
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
        g.incrementTurn(); // bot's turn

        assertEquals(turn, g.otherPlayer());
    }

    @Test
    void testIncrementTurnBottom() {
        turn = g.getTurn();
        g.incrementTurn(); // bot's turn

        assertEquals(1, g.getTurn());
    }

    @Test
    void testGetTurn() {
        turn = g.getTurn();

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
        p1 = top.getPiece(7 - 1);
        Location lionLoc = p1.getLocation();

        assertTrue(g.doesPieceLocationMatch(lionLoc, 0, 0));
    }

    @Test
    void testDoesPieceLocationMatch_FirstFail() {
        p1 = top.getPiece(7 - 1);
        Location lionLoc = p1.getLocation();

        assertFalse(g.doesPieceLocationMatch(lionLoc, 8, 6));
    }

    @Test
    void testDoesPieceLocationMatch_SecondPass() {
        p1 = top.getPiece(7 - 1);

        assertTrue(g.doesPieceLocationMatch(p1, 0, 0));
    }

    @Test
    void testDoesPieceLocationMatch_SecondFail() {
        p1 = top.getPiece(7 - 1);

        assertFalse(g.doesPieceLocationMatch(p1, 8, 6));
    }

    @Test
    void testDoesPieceLocationMatch_ThirdPass() {
        p1 = top.getPiece(7 - 1);
        n = new NextMove(p1, 1, 0);

        assertTrue(g.doesPieceLocationMatch(n, 1, 0));
    }

    @Test
    void testDoesPieceLocationMatch_ThirdFail() {
        p1 = top.getPiece(7 - 1);
        n = new NextMove(p1, 1, 0);

        assertFalse(g.doesPieceLocationMatch(n, -1, -1));
    }

    @Test
    void testIsValidMove_ContainsPieceTopPass_FriendlyFail() {
        p1 = top.getPiece(8 - 1); // top Elephant
        row = 1;
        col = 0;
        p1.setLocation(row, 0);
        n = new NextMove(p1, row + 1, 0); // Move down

        assertTrue(g.doesPieceLocationMatch(g.isValidMove(n), -1, -1));
    }

    @Test
    void testIsValidMove_OutOfBounds_L() {
        p1 = top.getPiece(7 - 1);
        n = new NextMove(p1, 0, -1);

        assertTrue(g.doesPieceLocationMatch(g.isValidMove(n), -1, -1));
    }

    @Test
    void testIsValidMove_OutOfBounds_U_L() {
        p1 = top.getPiece(7 - 1);
        row = 0;
        col = 0;

        n = new NextMove(p1, row - 1, col);
        boolean u = g.doesPieceLocationMatch(g.isValidMove(n), -1, -1);

        n = new NextMove(p1, row, col - 1);
        boolean l = g.doesPieceLocationMatch(g.isValidMove(n), -1, -1);

        assertTrue(u && l);
    }

    @Test
    void testIsValidMove_OutOfBounds_R_D() {
        g.incrementTurn(); // bot's turn
        p2 = bot.getPiece(7 - 1);
        row = 8;
        col = 6;

        p2.setLocation(row, col);
        n = new NextMove(p2, row, col + 1); // move right
        boolean r = g.doesPieceLocationMatch(g.isValidMove(n), -1, -1);

        n = new NextMove(p2, row + 1, col); // move down
        boolean d = g.doesPieceLocationMatch(g.isValidMove(n), -1, -1);

        assertTrue(r && d);
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
    void testIsValidMove_ContainsPieceBot_EnemyPass() {
        g.incrementTurn(); // bot's turn
        p2 = bot.getPiece(7 - 1); // bot Lion
        row = 3;
        col = 0;
        p2.setLocation(row, col);
        n = new NextMove(p2, row - 1, 0); // Move up

        assertTrue(g.doesPieceLocationMatch(g.isValidMove(n), row - 1, col));
    }

    @Test
    void testIsValidMove_ContainsPieceBot_EnemyFail_LowerRank() {
        g.incrementTurn(); // bot's turn
        p2 = bot.getPiece(1 - 1); // bot Rat
        row = 1;
        col = 0;
        p2.setLocation(row, col);
        n = new NextMove(p2, row - 1, 0); // Move up to top Lion

        assertTrue(g.doesPieceLocationMatch(g.isValidMove(n), -1, -1));
    }

    @Test
    void testIsValidMove_ContainsPieceBot_EnemyPass_EnemyInTrap() {
        p2 = bot.getPiece(8 - 1); // bot Elephant
        row = 1;
        col = 3;
        p2.setLocation(row, col); // bot Rat is in top's Trap

        p1 = top.getPiece(2 - 1); // top Cat (rank = 2)
        p1.setLocation(row + 1, 3); // Rat is one Tile down from Elephant
        n = new NextMove(p1, row, col); // Move up to capture Elephant

        assertTrue(g.doesPieceLocationMatch(g.isValidMove(n), row, col));
    }

    @Test
    void testIsValidMove_RatCapturesElephantTopPass() {
        p1 = top.getPiece(1 - 1); // top Rat
        row = 5;
        col = 0;
        p1.setLocation(row, col);  // top Rat is one Tile up from bot Elephant

        p2 = bot.getPiece(8 - 1); // bot Elephant
        p2.setLocation(row + 1, col); // bot Elephant stays in the same location
        n = new NextMove(p1, row + 1, col); // Rat wants to capture Elephant

        assertTrue(g.doesPieceLocationMatch(g.isValidMove(n), row + 1, col));
    }

    @Test
    void testIsValidMove_RatCapturesElephantBotPass() {
        g.incrementTurn(); // bot's turn
        p1 = bot.getPiece(1 - 1); // bot Rat
        row = 3;
        col = 6;
        p1.setLocation(row, col); // bot Rat is one Tile down from top Elephant

        p2 = top.getPiece(8 - 1); // top Elephant
        p2.setLocation(row - 1, col); // top Elephant stays in the same location
        n = new NextMove(p1, row - 1, col); // Rat wants to capture Elephant

        assertTrue(g.doesPieceLocationMatch(g.isValidMove(n), row - 1, col));
    }

    @Test
    void testIsValidMove_RatCapturesElephantFail_FriendlyElephant() {
        p1 = top.getPiece(1 - 1); // top Rat
        row = 3;
        col = 6;
        p1.setLocation(row, col);

        p2 = top.getPiece(8 - 1); // top Elephant
        p2.setLocation(row - 1, col); // Elephant stays in place
        n = new NextMove(p1, row - 1, col); // Rat wants to capture Elephant

        assertTrue(g.doesPieceLocationMatch(g.isValidMove(n), -1, -1));
    }

    @Test
    void testRatCapturesElephantTopFail_PieceIsNotARat() {
        p1 = top.getPiece(7 - 1); // top Lion
        row = 5;
        col = 0;
        p1.setLocation(row, col); // Lion is one Tile above Rat

        p2 = bot.getPiece(8 - 1); // bot Elephant
        p2.setLocation(row + 1, col); // Elephant stays in place
        n = new NextMove(p1, row + 1, col); // Lion wants to capture the Elephant

        assertTrue(g.doesPieceLocationMatch(g.isValidMove(n), -1, -1));
    }

    @Test
    void testIsValidMove_ElephantTryingToCaptureRat_TopFail() {
        p1 = top.getPiece(8 - 1); // top Elephant
        row = 5;
        col = 6;
        p1.setLocation(row, col);

        p2 = bot.getPiece(1 - 1); // bot Rat
        p2.setLocation(row + 1, col);
        n = new NextMove(p1, row + 1, col);

        assertTrue(g.doesPieceLocationMatch(g.isValidMove(n), -1, -1));
    }

    @Test
    void testIsValidMove_ElephantTryingToCaptureRat_BotFail() {
        g.incrementTurn(); // bot's turn
        p2 = bot.getPiece(8 - 1); // bot Elephant
        row = 3;
        col = 0;
        p2.setLocation(row, col);

        p1 = top.getPiece(1 - 1); // top Rat
        p1.setLocation(row - 1, 0);
        n = new NextMove(p2, row - 1, col);

        assertTrue(g.doesPieceLocationMatch(g.isValidMove(n), -1, -1));
    }

    @Test
    void testIsValidMove_ElephantTryingToCaptureRat_TopFail_FriendlyRat() {
        p1 = top.getPiece(8 - 1); // top Elephant
        row = 3;
        col = 0;
        p1.setLocation(row, col);

        p2 = top.getPiece(1 - 1); // top Rat
        p2.setLocation(row - 1, col);
        n = new NextMove(p1, row - 1, col);

        assertTrue(g.doesPieceLocationMatch(g.isValidMove(n), -1, -1));
    }

    @Test
    void testElephantTryingToCaptureRat_TopFail_RatIsNotAnElephant() {
        g.incrementTurn(); // bot's turn
        p2 = bot.getPiece(1 - 1); // bot Rat
        p1 = top.getPiece(8 - 1); // top Elephant

        assertFalse(g.elephantTryingToCaptureRat(p2, p1.getRow(), p1.getCol()));
    }

    @Test
    void testIsValidMove_ElephantTryingToCaptureRat_TopFail_EnemyRatIsAlreadyCaptured() {
        p1 = top.getPiece(8 - 1); // top Elephant
        row = 5;
        col = 6;
        p1.setLocation(row, col);

        p2 = bot.getPiece(1 - 1); // bot Rat
        p2.setLocation(row + 1, col);
        bot.isCaptured(1); // Rat = null
        n = new NextMove(p1, row + 1, col);

        assertTrue(g.doesPieceLocationMatch(g.isValidMove(n), row + 1, col));
    }

    @Test
    void testIsValidMove_IsLandingValid_TopPass_EqualRankAtLanding() {
        for (rank = 6; rank <= 7; ++rank) { // Tiger, then Lion
            p1 = top.getPiece(rank - 1); // top Tiger
            row = 2;
            col = 1;
            p1.setLocation(row, col); // top-left corner of River

            p2 = bot.getPiece(rank - 1); // bot Tiger
            p2.setLocation(row + 4, col); // bot-left corner
            n = new NextMove(p1, row + 1, col);

            assertTrue(g.doesPieceLocationMatch(g.isValidMove(n), row + 4, col));
        }
    }


    @Test
    void testIsValidMove_IsLandingValid_TopFail_HigherRankAtLanding() {
        p1 = top.getPiece(7 - 1); // top Lion
        row = 2;
        col = 1;
        p1.setLocation(row, col); // move to top-left corner of River

        p2 = bot.getPiece(8 - 1); // bot Elephant
        p2.setLocation(row + 4, col); // move to bot-left corner
        n = new NextMove(p1, row + 1, 1);

        assertTrue(g.doesPieceLocationMatch(g.isValidMove(n), -1, -1));
    }

    @Test
    void testIsValidMove_IsLandingValidTopFail_FriendlyAtLanding() {
        p1 = top.getPiece(7 - 1); // top Lion
        row = 2;
        col = 1;
        p1.setLocation(row, col); // move to top-left corner of River

        p2 = top.getPiece(7 - 1); // top Elephant
        p2.setLocation(row + 4, col); // move to bot-left corner
        n = new NextMove(p1, row + 4, col);

        assertTrue(g.doesPieceLocationMatch(g.isValidMove(n), -1, -1));
    }

    @Test
    void testIsValidMoveTopPass_CheckVerticalCols_IncludesRatInRiver() {
        top.getPiece(5 - 1).setLocation(1, 2); // move up one Tile to get out of the way
        top.getPiece(3 - 1).setLocation(1, 4); // move up one Tile to get out of the way
        // you will capture the enemy 3 and 5 by jumping on them
        p2 = bot.getPiece(1-1); // bot Rat

        boolean a; // jump down should be true
        boolean b; // jump up should be true
        boolean f1;
        boolean f2;
        boolean f3;

        for (rank = 6; rank <= 7; ++rank) {
            top.getPiece(6 - 1).setLocation(1, 6); // move Tiger out of the way for when the Lion comes
            p1 = top.getPiece(rank - 1); // top: Tiger, then Lion

            for (col = 1; col <= 5; ++col) {
                p1.setLocation(2, col); // Set the Lion up for his jump down
                n = new NextMove(p1,3, col);

                a = g.doesPieceLocationMatch(g.isValidMove(n),6, col);

                p2.setLocation(3, col);  // Move Rat into the jump path
                f1 = g.doesPieceLocationMatch(g.isValidMove(n),-1, -1);

                p2.setLocation(4, col);  // Move Rat into the jump path
                f2 = g.doesPieceLocationMatch(g.isValidMove(n),-1, -1);

                p2.setLocation(5, col);  // Move Rat into the jump path
                f3 = g.doesPieceLocationMatch(g.isValidMove(n),-1, -1);

                p2.setLocation(1,0); // Move the Rat out of the jump path

                ////////////////////////////////////////////////////////////////////

                p1.setLocation(6, col); // Set the Lion up for his jump up
                n = new NextMove(p1,5, col);

                b = g.doesPieceLocationMatch(g.isValidMove(n),2, col);

                if (col == 2) {
                    ++col; // Skip the middle_column bc it doesn't have River Tiles to jump
                }


                assertTrue(a && b && f1 && f2 && f3);
            }
        }
    }

    @Test
    void testIsValidMoveTopPass_CheckHorizontalRows_IncludesRatInRiver() {
        p2 = bot.getPiece(1 - 1); // bot Rat

        boolean a;
        boolean b;
        boolean c;
        boolean d;
        boolean f1;
        boolean f2;
        boolean f3;
        boolean f4;

        for (rank = 6; rank <= 7; ++rank) {
            top.getPiece(6 - 1).setLocation(2, 3); // move Tiger out of the way for when the Lion comes
            p1 = top.getPiece(rank - 1); // top: Tiger, then Lion

            for (row = 3; row <= 5; ++row) {
                p1.setLocation(row, 0); // Set the Lion up for his jump right
                n = new NextMove(p1, row,1); // The direction of where the Lion wants to move

                a = g.doesPieceLocationMatch(g.isValidMove(n), row, 3);

                p2.setLocation(row, 1); // Move Rat into the jump path
                f1 = g.doesPieceLocationMatch(g.isValidMove(n), -1, -1);

                p2.setLocation(row, 2); // Move Rat into the jump path
                f2 = g.doesPieceLocationMatch(g.isValidMove(n), -1, -1);

                p2.setLocation(1, 0); // Move the Rat out of the jump path

                ////////////////////////////////////////////////////////////////////

                p1.setLocation(row, 3); // Set the Lion up for his jump right
                n = new NextMove(p1, row, 4); // The direction of where the Lion wants to move

                b = g.doesPieceLocationMatch(g.isValidMove(n), row, 6);

                p2.setLocation(row, 4); // Move Rat into the jump path
                f3 = g.doesPieceLocationMatch(g.isValidMove(n), -1, -1);

                p2.setLocation(row, 5); // Move Rat into the jump path
                f4 = g.doesPieceLocationMatch(g.isValidMove(n), -1, -1);

                p2.setLocation(1, 0); // Move the Rat out of the jump path

                ////////////////////////////////////////////////////////////////////

                p1.setLocation(row, 6); // Set the Lion up for his jump left
                n = new NextMove(p1, row, 5); // The direction of where the Lion wants to move
                c = g.doesPieceLocationMatch(g.isValidMove(n), row, 3);

                ////////////////////////////////////////////////////////////////////

                p1.setLocation(row, 3); // Set the Lion up for his jump left
                n = new NextMove(p1, row, 2); // The direction of where the Lion wants to move
                d = g.doesPieceLocationMatch(g.isValidMove(n), row, 0);


                assertTrue(a && b && c && d && f1 && f2 && f3 && f4);
            }
        }
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
        System.out.println(); // First test -- makes "\n"
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
    void testIsDen_InsideGameClass() {
    }

    @Test
    void testWinnerCheck() {
    }
}