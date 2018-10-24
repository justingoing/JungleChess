package edu.colostate.cs.cs414.method_men.jungle.client;


import java.util.Scanner;

public class Game {
    private Player[] players;
    private int turn;
    private Board board;

    public Game () {
        players = new Player[2];
        players[0] = new Player("white");
        players[1] = new Player("black");
        turn = 0; // white makes first move
        board = new Board();
    }

    //For CLI implementation ONLY
    public void printBoard() {
        this.board.printBoard(players);
    }

    //For CLI implementation ONLY
    public int[] getDirection(Piece p, char direction) {
        int[] rc = new int[2];
        rc[0] = p.getRow();
        rc[1] = p.getCol();
        System.out.println("from (" + rc[0] + "," + rc[1] + ")");

        if (direction =='d') {
            rc[0]++;
        } else if (direction == 'u') {
            --rc[0];
        } else if (direction == 'r') {
            ++rc[1];
        } else if (direction == 'l') {
            --rc[1];
        }
//        switch (direction) {
//        //  it did NOT like the switch(char):
//        //  'd' would execute d then u (2 steps down, one step up)
//        //  'r' would execute r then l (2 steps left, one step right)
//            case 'd':
//                ++rc[0];
//                ++rc[0];
//            case 'u':
//                --rc[0];
//            case 'r':
//                ++rc[1];
//                ++rc[1];
//            case 'l':
//                --rc[1];
//        }
        System.out.println("to (" + rc[0] + "," + rc[1] + ")");
        return rc;
    }

    // for CLI implementation ONLY
    public void whoseTurnIsIt(int turn, String message) {
        if (turn == 0) {
            System.out.println("\tTop Player" + message);
        } else {
            System.out.println("\tBottom Player" + message);
        }
    }

    // the do-while loop is for CLI implementation ONLY
    public void makeMove() {
        Scanner sc = new Scanner(System.in);
        int[] nextLocation;

        Piece piece;
        int pieceRank;
        String pieceName;

        // no need to validate the inputs, as we'll be upgrading this with front-end mouse clicks
        do {
            whoseTurnIsIt(turn, "'s turn.");

            System.out.println("What Piece number do you choose? ");
            System.out.println("  A piece can be selected by it's rank. '1' for Rat, '2' for Cat, etc");
            pieceRank = sc.nextInt();
            piece = players[turn].getPiece(pieceRank - 1);
            pieceName = piece.getName();

            System.out.println("Which direction do you want to move " + pieceName + "? ");
            System.out.println("  Directions can be 'u', 'd', 'l', or 'r'");
            char direction = Character.toLowerCase(sc.next().charAt(0));
            nextLocation = getDirection(piece, direction);
        } while (!isValidMove(piece, nextLocation[0], nextLocation[1]));

        // TODO:
        // if (a Rat currently in River Tile) {
            //it can move to any location in it's side of the River
        //}
        // else {
            // valid move, therefore move friendly Piece
            players[turn].getPiece(pieceRank - 1).setLocation(nextLocation[0], nextLocation[1]);
        //}

        int enemyPieceRank;
        if ((enemyPieceRank = containsPiece(otherPlayer(), piece.getRow(), piece.getCol())) != -1) {
            System.out.println("Capturing enemy Piece with your " + pieceName);
            players[otherPlayer()].isCaptured(enemyPieceRank); // sets captured Piece to null
        }
        System.out.println("Moved piece " + pieceName + " to (" + piece.getRow() + "," + piece.getCol() + ")\n");
    }

    /**
     * Self-explanatory, retrieves the *private* Player
     * @param whichPlayer the index in the private array of 2 Players
     * @return the Player desired
     */
    public Player getPlayer(int whichPlayer) {
        return players[whichPlayer];
    }

    /**
     * Every valid move will increment the turn so it will be the next Player's turn
     */
    public void incrementTurn() {
        this.turn = otherPlayer();
    }

    /**
     * @return the index (associated to the next Player's turn) in the array of Players
     */
    public int otherPlayer() {
        return (this.turn + 1) % 2;
    }

    /** TODO: Does not check bounds! But do we need to if only our code calls this?
     * @param thisPlayer the index of whatever Player you want
     * @return the index of the other Player
     */
    public int otherPlayer(int thisPlayer) {
        return (thisPlayer + 1) % 2;
    }

    /**
     * Called from isValidMove method and is used for when validating a move from user.
     * If there is a Piece, it will return the rank, else -1.
     * @param playerNumber used for checking either enemy Pieces or friendly Pieces
     * @param row horizontal location on the board
     * @param col vertical location on the board
     * @return the rank (1-8) of a Piece, else -1 for no Pieces present
     */
    public int containsPiece(int playerNumber, int row, int col) {
        for (Piece currPiece : players[playerNumber].getValidPieces()) {
            if (row == currPiece.getRow() && col == currPiece.getCol()) {
                return currPiece.getRank();
            }
        }
        return -1;
    }

    /**
     * Called from makeMove method and is used to validate if the next move desired is valid by checking:
     * 1. Is the next Tile out of bounds?
     * 2. Is the next Tile a River? Is p a Rat?
     * 3. Does the next Tile have an enemy Piece? Does p outrank it? Exception: Trap Tile overrides rank.
     * 4. Is p a Lion or Tiger? After it lands the jump, is there an enemy Piece? Does p outrank it?
     * 5. Is p a Rat?
     *        Is Rat's current location River?
     *            Is there any Piece that blocks the non-River Tile desired?
     *        (Rat is on land) Is enemy an Elephant?
     * 6. Does the next Tile contain a friendly Piece?
     * 7. Then it's a valid move.
     * @param p the desired Piece that the Player wants to move.
     * @param row the desired Tile's next horizontal location on the board
     * @param col the desired Tile's next vertical location on the board
     * @return true if valid, else false
     */
    public boolean isValidMove(Piece p, int row, int col) {
        int enemyPieceRank;
        if (row < 0 || row > 8 || col < 0 || col > 6) {
            System.out.println("Out of bounds!");
            return false;
        } else if (board.isRiver(row, col)) {
            // Return true if the next Tile is a River, and the piece is a Rat
            System.out.println("Next Tile is a River. Only Rats can enter this Tile");
            System.out.println("Is Piece a Rat? " + p.isRat());
            return p.isRat();
        } else if ((enemyPieceRank = containsPiece(otherPlayer(), row, col)) != -1) {
            // There is an enemy located in this next Tile
            System.out.println("Next Tile contains an enemy Piece.");

            if (board.isTrap(row, col)) {
                // next Tile is a Trap
                System.out.println("Next Tile is also a Trap!");
                System.out.println("You will capture the enemy Piece.");
                return true;
                    //check Tile for enemy piece (isTrap==F && (p.rank < ePiece.rank))
            }
            // Returns true if my Piece's ranks beats the enemy Piece's rank
            System.out.println("Only an equal or higher rank can capture an enemy Piece.");
            System.out.println("Your Piece's rank: " + p.getRank());
            return (p.getRank() >= enemyPieceRank);
        }

        // TODO: implement special rules:
        //          Lion & Tiger jumping across
        //              Check if there is a Piece @ the other spot
        //              if (friendly Piece) return false
        //              else return (p.getRank() > enemyPieceRank)
        //          Rat captures Elephant

        else if (containsPiece(turn, row, col) != -1) {
            System.out.println("There is a friendly Piece located here.");
            System.out.println("You can't capture your own Piece.");
            return false;
        }

        // this is a valid move
        return true;
    }

    /**
     * Called from winnerCheck method and used for determining if any Piece got the the enemy's Den
     * @param whichPlayer which Player's turn is it?
     * @param location Array containing (row, col) of the Piece's current location
     * @return true if a Player reached the enemy's Den, false if no winner
     */
    public boolean isDen(int whichPlayer, int[] location) {
        if (whichPlayer == 1) { // bottom player at top Den
            if (location[0] == 0 && location[1] == 3) {
                return true;
            }
        } else { // top player at bottom Den
            if (location[0] == 8 && location[1] == 3) {
                return true;
            }
        }
        return false;
    }

    /**
     * There are two ways to win in Jungle:
     * 1. You reach the enemy Den
     * 2. You have no more Pieces (count variable)
     * @return
     */
    public int winnerCheck() {
        int count;

        // iterate through both players
        for (int p = 0; p < 2; ++p) {
            count = 0;

            // iterate through the players' pieces
            for (Piece piece : players[p].getValidPieces()) {
                ++count;

                // if a Piece is at the enemy den
                if (isDen(p, piece.getLocation())) {
                    return p;
                }
            }

            // if this player doesn't have any remaining Pieces
            if (count == 0) {
                return otherPlayer(p); // the other Player is the winner
            }
        }

        return -1; // no winner
    }
}
