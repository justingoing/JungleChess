package edu.colostate.cs.cs414.method_men.jungle.client;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private Player[] players;
    private int turn;
    private Board board;
    private final int FAILURE = -1;
    private final int SUCCESS = 100;
    private final int[] FAILURE_DESTINATION = {FAILURE, FAILURE};
    private final int[] SUCCESS_DESTINATION = {SUCCESS, SUCCESS};

    public Game () {
        players = new Player[2];
        players[0] = new Player("white");
        players[1] = new Player("black");
//        Random r = new Random(System.currentTimeMillis());
//        turn = r.nextInt(999999) % 2;
        turn = 0; // top Player makes first move
        debugPrint(turn);
        board = new Board();
    }

    /**
     * Feel free to change the debug to true or false depending on if we're demo-ing or not
     * @param message Could be an int, String, Object, doesn't matter it will print what you want
     */
    public void debugPrint(Object message) {
        boolean debug = false;

        if (debug) {
            System.out.println("" + message);
        }
    }

    //For CLI implementation ONLY
    public void printBoard() {
        this.board.printBoard(players);
    }

    //For CLI implementation ONLY
    public NextMove getDirection(Piece p, char direction) {
        NextMove nextMove = new NextMove(p, populateLocation(p.getRow(), p.getCol()));
        debugPrint("\tfrom (" + nextMove.getRow() + ", " + nextMove.getCol() + ")");

        if (direction =='d') {
            nextMove.incRow();
        } else if (direction == 'u') {
            nextMove.decRow();
        } else if (direction == 'r') {
            nextMove.incCol();
        } else if (direction == 'l') {
            nextMove.decCol();
        }

        debugPrint("\tto   (" + nextMove.getRow() + ", " + nextMove.getCol() + ")");
        return nextMove;
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
     * There were many instance of the following 4 lines, so I made a method for it
     * @param row the next move's horizontal location on the board
     * @param col the next move's vertical location on the board
     * @return the int array with [row, col]
     */
    public int[] populateLocation(int row, int col) {
        int[] nextLocation = new int[2];
        nextLocation[0] = row;
        nextLocation[1] = col;
        return nextLocation;
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
            Piece enemyElephant = players[otherPlayer()].getPiece(7);

            if (enemyElephant != null) {
                int[] elephantsLocation = enemyElephant.getLocation();

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
        debugPrint("Your Piece is not an Elephant.");
        return false;
    }

    public boolean isLandingValid(Piece p, int row, int col) {
        // Check if a Rat is in the River (specifically if it is in the path of the jump)
        int[] currLocation = p.getLocation();
        for (Player currPlayer : players) {
            Piece rat = currPlayer.getValidPieces()[0];
            if (rat != null) {
                int min;
                int max;

                //determine if the Lion|Tiger is moving horizontally|vertically
                if ((currLocation[0] - row) != 0) {
                    // Moving vertically across the River
                    // We're going to check up to down regardless of direction of travel
                    min = (currLocation[0] < row) ? currLocation[0] : row;
                    max = (currLocation[0] > row) ? currLocation[0] : row;

                    debugPrint("Checking for Rat from ([" + (min+1) + " to " + (max-1) + "], " + col + ").");

                    for (int checkThisRow = min + 1; checkThisRow < max; ++checkThisRow) {
                        if (rat.getRow() == checkThisRow && rat.getCol() == col) {
                            System.out.println("The vertical jump is blocked by a Rat in the River.");
                            return false;
                        }
                    }
                } else if ((currLocation[1] - col) != 0) {
                    // Moving horizontally across the River
                    // We're going to check left to right regardless of direction of travel
                    min = (currLocation[1] < col) ? currLocation[1] : col;
                    max = (currLocation[1] > col) ? currLocation[1] : col;
                    debugPrint("Checking for Rat from (" + row + ", [" + (min+1) + " to " + (max-1) + "]).");

                    for (int checkThisCol = min + 1; checkThisCol < max; ++checkThisCol) {
                        if (rat.getRow() == row && rat.getCol() == checkThisCol){
                            System.out.println("The horizontal jump is blocked by a Rat in the River.");
                            return false;
                        }
                    }
                }
            }
        } // There aren't any Rat's in the jump path
        debugPrint("There aren't any Rats in the jump path over the River.");

        // Check the landing spot to see if the Lion||Tiger can land there
        for (Player currPlayer : players) {
            for (Piece piece : currPlayer.getValidPieces()) {
                int[] location = piece.getLocation();
                if (row == location[0] && col == location[1]) {
                    if (currPlayer.equals(players[otherPlayer()])) {
                        debugPrint("There is an enemy in your landing spot.");
                        return (p.getRank() >= piece.getRank()); // returns true if I am of an equal or higher rank than you
                    } else if (currPlayer.equals(players[turn])) {
                        System.out.println("Cannot jump across and capture a friendly Piece.");
                        return false;
                    }
                }
            }
        } // There aren't any more Pieces to check

        debugPrint("Good news, everyone!");
        debugPrint("\tThere is nothing blocking your " + p.getName() + "'s jump to (" + row + ", " + col + ").");
        return true; // Landing Tile is not occupied
    }

    public int[] isAbleToJump(Piece p, int nextRow, int nextCol, String typeOfMove) {
        int startingRow = nextRow;
        int startingCol = nextCol;
        int[] returnDestination = populateLocation(FAILURE, FAILURE);


        if (p instanceof Tiger || p instanceof Lion) {
            int[] currLocation = p.getLocation();
            int currRow = currLocation[0];
            int currCol = currLocation[1];
            debugPrint("Current location: (" + currRow + ", " + currCol + ")");

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
                debugPrint("Your Lion or Tiger is NOT trying to jump across the River.");
                return returnDestination;
            }

            if (typeOfMove.equals("testing trying to jump")) {
                debugPrint("Row transformation: " + startingRow + " => " + nextRow);
                debugPrint("Col transformation: " + startingCol + " => " + nextCol);
                if (!(startingRow == nextRow && startingCol == nextCol)) {
                    return SUCCESS_DESTINATION;//return "Yes, Lion|Tiger is trying to Jump"
                } else {
                    return FAILURE_DESTINATION;
                }
            } else {
                debugPrint("Your Lion or Tiger is trying to jump across the River to (" + nextRow + ", " + nextCol + ").");
                boolean isThisAValidJump = isLandingValid(p, nextRow, nextCol);
                debugPrint("This is a valid jump: " + isThisAValidJump);
                debugPrint("The jump is shooting for " + nextRow + " " + nextCol);
                if (isThisAValidJump) {
                    returnDestination = populateLocation(nextRow, nextCol);
                    debugPrint("The jump is successfully going to land on (" + returnDestination[0] + ", " + returnDestination[1] + ").");
                    return returnDestination;
                } else {
                    return FAILURE_DESTINATION;
                }
            }
        }

        debugPrint("Your " + p.getName() + " is apparently not a Lion or Tiger");
        return FAILURE_DESTINATION;
    }

    public boolean isTryingToJump(Piece p, int row, int col) {
        int[] nextDestination = isAbleToJump(p, row, col, "testing trying to jump");
        return (nextDestination[0] == SUCCESS && nextDestination[1] == SUCCESS);
    }

    /**
     * Tests if:
     * 1. Piece in question is a Rat
     * 2. Rat is currently located in the River and
     * 3. Rat wants to emerge from the River (onto a Jump Tile).
     * @param p the piece in question
     * @param row the next move's horizontal location on the board
     * @param col the next move's vertical location on the board
     * @return true only if all 3 conditions are true
     */
    public boolean isRatTryingToEmerge(Piece p, int row, int col) {
        debugPrint("Is p a Rat: " + p.isRat());
        debugPrint("Is (" + p.getRow() + ", " + p.getCol()  + ") in River: " + board.isRiver(p.getRow(), p.getCol()));
        debugPrint("Is (" + row + ", " + col  + ") Land: " + board.isLand(row, col));

        return (p.isRat() && board.isRiver(p.getRow(), p.getCol()) && board.isLand(row, col));
    }

    /**
     * Once the piece is deemed a Rat, in the River and is attempting to emerge:
     * we will now iterate through both Players' Pieces to see if the movement is blocked
     * @param row the next move's horizontal location on the board
     * @param col the next move's vertical location on the board
     * @return true if there aren't any Pieces at the next move's location
     */
    public boolean isAbleToEmerge(int row, int col) {
        for (Player currPlayer : players) {
            for (Piece currPiece : currPlayer.getValidPieces()) {
                if (currPiece.getRow() == row && currPiece.getCol() == col) {
                    if (currPlayer == players[turn]) {
                        System.out.println("Cannot capture a friendly piece.");
                    } else {
                        System.out.println("Cannot attack from the water.");
                    }
                    return false;
                }
            }
        }
        // No Pieces are blocking the move to exit the water
        return true;
    }

    /**
     * Called from makeMove method and is used to validate if the next move desired is valid.
     * If the move enters on of the 8 xceptions listed, then it will return within the same conditional, regardless of outcome.
     * // TODO Should we not return FAILURE_DESTINATION so many times (read one line above), and instead return the failure after exception #8?
     *
     * The order of checking for exceptions is as follows:
     *
     * 1. Is the next move's location out of bounds horizontally or vertically? [fail]
     * 2. Is the p a Lion or Tiger and attempting to jump across the River?
     *      Is the path blocked by a Rat in the River? [fail]
     *      Is there a friendly piece in the landing location? [fail]
     *      Is there an enemy in the landing location?
     *          Does p outrank the enemy? [succ]
     * 3. Is p a Rat, currently in the River, and wants to emerge from the River?
     *      Is there a Piece located in the next move's location that will block this move? [fail]
     * 4. Is the next move's location a River Tile?
     *      Is p a Rat? [succ]
     * 5. Is p a Rat and want to capture the enemy Elephant? [succ]
     * 6. Is p an Elephant and is trying to capture the enemy Rat? [fail]
     * 7. Does the next move's location contain an enemy Piece?
     *      Is the next move's location on a Trap Tile? [succ]
     *      Does p outrank the enemy? [succ]
     * 8. Does the next move's location contain a friendly Piece? [fail]
     * 9. If no aforementioned conditions are true, then it's a valid move.
     * @param nextMove
     * @return [-1, -1] called "FAILURE_DESTINATION" to represent an invalid move.
     *         [nextMovesRow, nextMovesCol] to represent a valid move in an int array
     */
    public int[] isValidMove(NextMove nextMove) {
        int enemyPieceRank;
        Piece p = nextMove.getPiece();
        int row = nextMove.getRow();
        int col = nextMove.getCol();

        if (row < 0 || row > 8 || col < 0 || col > 6) {
            // 1. The next move's location out of bounds
            System.out.println("Out of bounds!");
            return FAILURE_DESTINATION;

        } else if (isTryingToJump(p, row, col)) {
            // 2. p is a Lion or Tiger and is attempting to jump across the River
            int[] nextDestination = isAbleToJump(p, row, col, "trying to jump for real");
            if (nextDestination[0] != FAILURE && nextDestination[1] != FAILURE) {
                return nextDestination;
            } else {
                System.out.println("Your " + p.getName() + " cannot jump across at this time.");
                return FAILURE_DESTINATION;
            }

        } else if (isRatTryingToEmerge(p, row, col)) {
            // 3. p is a Rat, is currently in the River, and wants to emerge from the River
            debugPrint("Rat is trying to emerge from the River onto a Land-like Tile.");
            if (isAbleToEmerge(row, col)) {
                debugPrint("Rat will successfully emerge from the River.");
                return populateLocation(row, col);
            } else {
                return FAILURE_DESTINATION;
            }

        } else if (board.isRiver(row, col)) {
            // 4. The next move's location a River Tile
            debugPrint("Next Tile is a River. Only Rats can enter this Tile.");
            if (p.isRat()) {
                debugPrint("Rat will successfully move to (" + row + ", " + col + ")");
                return populateLocation(row, col);
            } else {
                System.out.println("Cannot move " + p.getName() + " into the River.");
                return FAILURE_DESTINATION;
            }

        } else if (ratCapturesElephant(p, row, col)) {
            // 5. p is a Rat and wants to capture the enemy Elephant
            System.out.println("Rat will sneak up and eat the Elephant's brain!");
            return populateLocation(row, col);

        } else if (elephantTryingToCaptureRat(p, row, col)) {
            // 6. p is an Elephant and is trying to capture the enemy Rat
            System.out.println("Elephant cannot capture the Rat because he's too afraid of the Rat...");
            return FAILURE_DESTINATION;

        } else if ((enemyPieceRank = containsPiece(otherPlayer(), row, col)) != -1) {
            // 7. The next move's location contains an enemy Piece

            if (board.isTrap(row, col)) {
                // next Tile is a Trap
                System.out.println("You will capture the enemy Piece in the Trap\n\tbut you get stuck in the process...");
                return populateLocation(row, col);
            } else {
                debugPrint("Only an equal or higher rank can capture an enemy Piece.");
                debugPrint("Your Piece's rank: " + p.getRank());
                if (p.getRank() >= enemyPieceRank) {
                    // Returns if my Piece's ranks beats the enemy Piece's rank
                    return populateLocation(row, col);
                } else {
                    System.out.println("You cannot capture an enemy with a higher rank.");
                    return FAILURE_DESTINATION;
                }
            }

        } else if (containsPiece(turn, row, col) != -1) {
            // 8. The next move's location contains a friendly Piece
            debugPrint("There is a friendly Piece located here.");
            System.out.println("You can't capture your own Piece.");
            return FAILURE_DESTINATION;
        } else {
            // 9. It is a valid move
            debugPrint("\tJust a regular move without any exceptions, thus is a valid move");
            return populateLocation(row, col);
        }
    }

    public int retrieveCliPieceRank(Scanner sc) {
        String pieceInput;
        int pieceRank;

        while (true) {
            System.out.println("What Piece number do you choose? ");
            System.out.println("  A piece can be selected by it's rank. '1' for Rat, '2' for Cat, etc");
            pieceInput = sc.nextLine();
            if (!pieceInput.isEmpty()) {
                try {
                    pieceRank = Integer.parseInt(pieceInput.trim());
                    if (pieceRank >= 1 && pieceRank <= 8) {
                        return pieceRank;
                    } else {
                        System.out.println("\tERROR: " + pieceRank + " must be between 1 and 8");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\tERROR: " + pieceInput + " is not a valid rank");
                }
            } else {
                System.out.println("\tERROR: must specify a rank");
            }
        }
    }

    public char retrieveCliDirection(Scanner sc) {
        String directionInput = sc.nextLine();

        if (!directionInput.isEmpty()) {
            char direction = Character.toLowerCase(directionInput.trim().charAt(0));
            if (direction == 'u' || direction == 'd' || direction == 'l' || direction == 'r') {
                return direction;
            } else {
                System.out.println("\tERROR: " + direction + " is not a valid direction");
            }
        } else {
            System.out.println("\tERROR: must specify a direction");
        }
        return '#';
    }

    public NextMove retrieveCliInput() {
        Scanner sc = new Scanner(System.in);
        NextMove nextMove = null;

        boolean continueAsking = true;
        boolean validPiece = false;
        boolean validDirection = false;

        Piece piece = null;
        int pieceRank = FAILURE;
        char direction = '#';

        while (continueAsking) {
            //printBoard();
            validDirection = false;
            whoseTurnIsIt(turn, "'s turn.");

            while (!validPiece) {
                if ((pieceRank = retrieveCliPieceRank(sc)) != FAILURE) {
                    if (players[turn].getPiece(pieceRank - 1) != null) {
                        validPiece = true;
                    } else {
                        System.out.println("\tERROR: " + pieceRank + " no longer exists");
                    }
                } else {
                    System.out.println("Not a valid Piece");
                }
            } // We now have a valid Piece.rank: {1, 2, 3, 4, 5, 6, 7, 8}

            piece = players[turn].getPiece(pieceRank - 1);

            while (!validDirection) {
                System.out.println("Which direction do you want to move " + piece.getName() + "? ");
                System.out.println("  Directions can be 'u', 'd', 'l', or 'r'");
                if ((direction = retrieveCliDirection(sc)) != '#') {
                    validDirection = true;
                }
            } // We now have a valid direction: {u, d, l, r}

            nextMove = getDirection(piece, direction);
            nextMove.setLocation(isValidMove(nextMove));
            debugPrint("Sanity Check: " + nextMove.getRow() + ", " + nextMove.getCol());

            if (nextMove.getRow() != FAILURE && nextMove.getCol() != FAILURE) {
                System.out.println("\t\t\t Valid move");
                continueAsking = false;
            } else {
                System.out.println("\t\t\t Invalid move");
                printBoard();
            }
        }

        return nextMove;
    }

    // TODO
    public NextMove retrieveUiInput() {
        int currRow = -1;
        int currCol = -1;
        int nextRow = -1;
        int nextCol = -1;

        return new NextMove(players[turn], currRow, currCol, nextRow, nextCol);
    }


    // the do-while loop is for CLI implementation ONLY
    public void makeMove(String interfaceType) {
        NextMove nextMove;

        if (interfaceType.equals("cli")) {
            nextMove = retrieveCliInput();
        } else {
            // TODO retrieve from the UI:
            // currentRow
            // currentCol
            // nextRow (or deltaRow (i.e.: -1 == move up one Tile))
            // nextCol (or deltaCol (i.e.: 1 == move right one Tile))
            nextMove = retrieveUiInput();
        }

        Piece piece = nextMove.getPiece();
        int nextRow = nextMove.getRow();
        int nextCol = nextMove.getCol();
        int pieceRank = piece.getRank();

        // Move the Piece
        players[turn].getPiece(pieceRank - 1).setLocation(nextRow, nextCol);

        int enemyPieceRank;
        if ((enemyPieceRank = containsPiece(otherPlayer(), piece.getRow(), piece.getCol())) != -1) {
            System.out.println("Capturing enemy Piece with your " + piece.getName());
            players[otherPlayer()].isCaptured(enemyPieceRank); // sets captured Piece to null
        }
        debugPrint("Moved piece " + piece.getName()+ " to (" + piece.getRow() + "," + piece.getCol() + ")\n");
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
