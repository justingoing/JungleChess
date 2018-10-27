package edu.colostate.cs.cs414.method_men.jungle.client;


import java.util.Scanner;

public class Game {
    private Player[] players;
    private int turn;
    private Board board;
    private final int FAILURE = -1;
    private final int SUCCESS = 100;
    private int[] failureDestination;
    private int[] successDestination;

    public Game () {
        players = new Player[2];
        players[0] = new Player("white");
        players[1] = new Player("black");
        turn = 0; // white makes first move
        board = new Board();

        failureDestination = new int[2];
        failureDestination[0] = FAILURE;
        failureDestination[1] = FAILURE;

        successDestination = new int[2];
        successDestination[0] = SUCCESS;
        successDestination[1] = SUCCESS;
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

    public boolean ratCapturesElephant(Piece p, int row, int col) {
        if (p instanceof Rat) {
            Piece[] enemyPieces = players[otherPlayer()].getValidPieces();

            if (enemyPieces[7] != null) {
                int[] elephantsLocation = enemyPieces[7].getLocation();

                return (elephantsLocation[0] == row && elephantsLocation[1] == col);
            }
        }
        return false;
    }

    public boolean elephantTryingToCaptureRat(Piece p, int row, int col) {
        if (p instanceof Elephant) {
            Piece[] enemyPieces = players[otherPlayer()].getValidPieces();

            if (enemyPieces[0] != null) {
                int[] ratsLocation = enemyPieces[0].getLocation();
                return (ratsLocation[0] == row && ratsLocation[1] == col);
            }
        }
        System.out.println("Your Piece is not an Elephant.");
        return false;
    }

    public boolean isLandingValid(Piece p, int row, int col) {
        // Check if a Rat is in the River (specifically if it is in the path of the jump)
        int[] currLocation = p.getLocation();
        for (Player currPlayer : players) {
            Piece rat = currPlayer.getValidPieces()[0];
            if (rat != null) {

                //determine if the Lion|Tiger is moving horizontally|vertically
                if ((currLocation[0] - row) != 0) {
                    //moving vertically
                    for (int checkThisRow = currLocation[0]; checkThisRow <= row; ++checkThisRow) {
                        if (rat.getRow() == checkThisRow && rat.getCol() == col) {
                            System.out.println("The vertical jump is blocked by a Rat in the River.");
                            return false;
                        }
                    }
                } else if ((currLocation[1] - col) != 0) {
                    //moving horizontally
                    for (int checkThisCol = currLocation[1]; checkThisCol <= col; ++checkThisCol) {
                        if (rat.getRow() == row && rat.getCol() == checkThisCol){
                            System.out.println("The horizontal jump is blocked by a Rat in the River.");
                            return false;
                        }
                    }
                }
            }
        } // There aren't any Rat's in the jump path
        System.out.println("There aren't any Rats in the jump path over the River.");

        // Check the landing spot to see if the Lion||Tiger can land there
        for (Player currPlayer : players) {
            for (Piece piece : currPlayer.getValidPieces()) {
                int[] location = piece.getLocation();
                if (row == location[0] && col == location[1]) {
                    if (currPlayer.equals(players[otherPlayer()])) {
                        System.out.println("There is an enemy in your landing spot.");
                        return (p.getRank() >= piece.getRank()); // returns true if I am of an equal or higher rank than you
                    } else if (currPlayer.equals(players[turn])) {
                        System.out.println("Cannot jump across and capture a friendly Piece.");
                        return false;
                    }
                }
            }
        } // There aren't any more Pieces to check

        return true; // Landing Tile is not occupied
    }

    public int[] isAbleToJump(Piece p, int nextRow, int nextCol, String typeOfMove) {
        int startingRow = nextRow;
        int startingCol = nextCol;
        int[] returnDestination = failureDestination; // [-1, -1]

        if (p instanceof Tiger || p instanceof Lion) {
            int[] currLocation = p.getLocation();
            int currRow = currLocation[0];
            int currCol = currLocation[1];
            System.out.println("Current location: (" + currRow + ", " + currCol + ")");

            if (currRow == 2 && nextRow == 3 && (currCol == 1 || currCol == 2 || currCol == 4 || currCol == 5)) {
                nextRow = 6;
            } else if (currRow == 6 && nextRow == 5 && (currCol == 1 || currCol == 2 || currCol == 4 || currCol == 5)) {
                nextRow = 2;
            } else if (currCol == 0 && nextCol == 1 && (currRow >= 3 && currRow <= 5)) {
                nextCol = 3;
            } else if (currCol == 3 && (currRow >= 3 && currRow <= 5)) {
                if (nextCol == 2) {
                    nextCol = 0;
                } else if (nextCol == 4) {
                    nextCol = 6;
                }
            } else if (currCol == 6 && nextCol == 5 && (currRow >= 3 && currRow <= 5)) {
                nextCol = 3;
            } else {
                System.out.println("Your Lion or Tiger is NOT trying to jump across the River.");
                return returnDestination;
            }

            if (typeOfMove.equals("testing trying to jump")) {
                System.out.println("Row transformation: " + startingRow + " => " + nextRow);
                System.out.println("Col transformation: " + startingCol + " => " + nextCol);
                if (!(startingRow == nextRow && startingCol == nextCol)) {
                    return successDestination;//return "Yes, Lion|Tiger is trying to Jump"
                }
            } else {
                System.out.println("Your Lion or Tiger is trying to jump across the River to (" + nextRow + ", " + nextCol + ").");
                if (isLandingValid(p, nextRow, nextCol)) {
                    returnDestination[0] = nextRow;
                    returnDestination[1] = nextCol;
                    return returnDestination;
                }
            }
        }

        System.out.println("Your Piece is not a Lion or Tiger");
        return failureDestination;
    }

    public boolean isTryingToJump(Piece p, int row, int col) {
        int[] nextDestination = isAbleToJump(p, row, col, "testing trying to jump");
        return (nextDestination == successDestination);
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
    public int[] isValidMove(Piece p, int row, int col) {
        int enemyPieceRank;
        int[] nextDestination = new int[2];

        if (row < 0 || row > 8 || col < 0 || col > 6) {
            System.out.println("Out of bounds!");
            return failureDestination;

        } else if (isTryingToJump(p, row, col)) {
            System.out.println("Lion or Tiger is trying to jump across the River");
            nextDestination = isAbleToJump(p, row, col, "trying to jump for real");
            if (nextDestination[0] == -1) {

                return nextDestination;
            } else {
                return failureDestination;
            }

        } else if (board.isRiver(row, col)) {
            System.out.println("Next Tile is a River. Only Rats can enter this Tile");
            if (p.isRat()) {
                System.out.println("Rat will move to (" + row + ", " + col + ")");
                nextDestination[0] = row; // Valid Move
                nextDestination[1] = col;
                return nextDestination;
            } else {
                System.out.println("Cannot move " + p.getName() + " into the River.");
                return failureDestination;
            }

        } else if (ratCapturesElephant(p, row, col)) {
            System.out.println("Rat will sneak up and eat the Elephant's brain!");
            nextDestination[0] = row;
            nextDestination[1] = col;
            return nextDestination;

        } else if (elephantTryingToCaptureRat(p, row, col)) {
            System.out.println("Elephant cannot capture the Rat because he's too afraid of the Rat...");
            return failureDestination;

        } else if ((enemyPieceRank = containsPiece(otherPlayer(), row, col)) != -1) {
            // There is an enemy located in this next Tile
            System.out.println("Next Tile contains an enemy Piece.");

            if (board.isTrap(row, col)) {
                // next Tile is a Trap
                System.out.println("Next Tile is also a Trap!");
                System.out.println("You will capture the enemy Piece.");
                nextDestination[0] = row;
                nextDestination[1] = col;
            }
            System.out.println("Only an equal or higher rank can capture an enemy Piece.");
            System.out.println("Your Piece's rank: " + p.getRank());
            if (p.getRank() >= enemyPieceRank) {
                // Returns if my Piece's ranks beats the enemy Piece's rank
                nextDestination[0] = row;
                nextDestination[1] = col;
            }
            return nextDestination;

        } else if (containsPiece(turn, row, col) != -1) {
            System.out.println("There is a friendly Piece located here.");
            System.out.println("You can't capture your own Piece.");
            return failureDestination;
        }

        // this is a valid move
        nextDestination[0] = row;
        nextDestination[1] = col;
        return nextDestination;
    }


    // the do-while loop is for CLI implementation ONLY
    public void makeMove(String interfaceType) {
        Scanner sc = new Scanner(System.in);
        int[] nextLocation = new int[2];

        boolean continueAsking = true;

        Piece piece = null;
        String pieceInput;
        int pieceRank = -1;
        boolean validPiece;
        boolean validPieceInner;
        String pieceName = null;
        char direction = '#';
        boolean validDirection;

        if (interfaceType.equals("cli")) {
            while (continueAsking) {
                //printBoard();
                validPiece = false;
                validPieceInner = false;
                validDirection = false;

                whoseTurnIsIt(turn, "'s turn.");

                while (!validPiece) {
                    while (!validPieceInner) {
                        System.out.println("What Piece number do you choose? ");
                        System.out.println("  A piece can be selected by it's rank. '1' for Rat, '2' for Cat, etc");
                        pieceInput = sc.nextLine();
                        try {
                            pieceRank = Integer.parseInt(pieceInput.trim());
                            validPieceInner = true;
                        } catch (NumberFormatException e) {
                            System.out.println("\tERROR: " + pieceInput + " is not a valid rank");
                        }
                    }
                    if (players[turn].getPiece(pieceRank - 1) != null) {
                        validPiece = true;
                    } else {
                        System.out.println("\tERROR: " + pieceRank + " not longer exists");
                    }
                }
                piece = players[turn].getPiece(pieceRank - 1);
                pieceName = piece.getName();

                while (!validDirection) {
                    System.out.println("Which direction do you want to move " + pieceName + "? ");
                    System.out.println("  Directions can be 'u', 'd', 'l', or 'r'");
                    direction = Character.toLowerCase(sc.nextLine().charAt(0));

                    if (direction == 'u' || direction == 'd' || direction == 'l' || direction == 'r') {
                        validDirection = true;
                    } else {
                        System.out.println("\tERROR: " + direction + " is not a valid direction");
                    }
                }

                nextLocation = getDirection(piece, direction);
                System.out.println("Hey Mike: " + nextLocation[0] + nextLocation[1]);

                nextLocation = isValidMove(piece, nextLocation[0], nextLocation[1]);
                System.out.println("Hey Mike: " + nextLocation[0] + nextLocation[1]);
                if (nextLocation[0] != FAILURE && nextLocation[1] != FAILURE) {
                    continueAsking = false;
                }
            }
        } else {
            // TODO retrieve from the UI:
            // currentRow
            // currentCol
            // nextRow (or deltaRow (i.e.: -1 == move up one Tile))
            // nextCol (or deltaCol (i.e.: 1 == move right one Tile))
            piece = new Rat("white");
            pieceRank = 1;
            pieceName = "Rat";
            nextLocation = new int[2];
            nextLocation[0] = 1;
            nextLocation[1] = 1;
        }

        // Move the Piece
        players[turn].getPiece(pieceRank - 1).setLocation(nextLocation[0], nextLocation[1]);

        int enemyPieceRank;
        if ((enemyPieceRank = containsPiece(otherPlayer(), piece.getRow(), piece.getCol())) != -1) {
            System.out.println("Capturing enemy Piece with your " + pieceName);
            players[otherPlayer()].isCaptured(enemyPieceRank); // sets captured Piece to null
        }
        System.out.println("Moved piece " + pieceName + " to (" + piece.getRow() + "," + piece.getCol() + ")\n");
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
