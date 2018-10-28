package edu.colostate.cs.cs414.method_men.jungle.client;

public class Board {
    private final int HEIGHT = 9;
    private final int WIDTH = 7;
    private Tile[][] board;

    public Board() {
        board = new Tile[HEIGHT][WIDTH];
        makeBoard();
    }

    /**
     * If the Tile at (row, col) is suppose to be a River Tile
     * @param row horizontal location on board
     * @param col vertical location on board
     * @return true if it's suppose to be a River Tile. false if not
     */
    public boolean isDen(int row, int col) {
        return ((row == 0 || row == 8) && col == 3);
    }

    /**
     * If the Tile at (row, col) is suppose to be a Trap Tile
     * @param row horizontal location on board
     * @param col vertical location on board
     * @return true if it's suppose to be a Trap Tile. false if not
     */
    public boolean isTrap(int row, int col) {
        // if there is a Den below, above, to right or to left
        if (isDen(row + 1, col) || isDen(row - 1, col)
                || isDen(row, col + 1) || isDen(row, col - 1)) {
            return true;
        }
        return false;
    }

    /**
     * If the Tile at (row, col) is suppose to be a River Tile
     * @param row horizontal location on board
     * @param col vertical location on board
     * @return true if it's suppose to be a River Tile. false if not
     */
    public boolean isRiver(int row, int col) {
        return ((row >= 3 && row <= 5) && (col == 1 || col == 2 || col == 4 || col == 5));
    }

    /**
     * If the Tile at (row, col) is suppose to be a Jump Tile
     * @param row horizontal location on board
     * @param col vertical location on board
     * @return true if it's suppose to be a Jump Tile. false if not
     */
    public boolean isJump(int row, int col) {
        //if there is a River Tile above, below, to right, or to left
        if (isRiver(row + 1, col) || isRiver(row - 1, col)
                || isRiver(row, col + 1) || isRiver(row, col - 1)) {
            return true;
        }
        return false;
    }

    /**Make instance of Tile inside the 2d array of Tiles
     * based off the (row, col) location inside the board.
     * @param row horizontal location on board
     * @param col vertical location on board
     * @return a new instance of it's corresponding Tiles
     */
    public Tile makeInstance(int row, int col) {
        if (isDen(row, col)) {
            return new Den();
        } else if (isTrap(row, col)) {
            return new Trap();
        } else if (isRiver(row, col)) {
            return new River();
        } else if (isJump(row, col)) {
            return new Jump();
        } else {
            return new Open();
        }
    }

    /**
     * Iterates through each row, then each column, and
     * instantiates each Tile (subtype) based off the location
     */
    public void makeBoard() {
        for (int row = 0; row < HEIGHT; ++row) {
            for (int col = 0; col < WIDTH; ++col) {
                board[row][col] = makeInstance(row, col);
            }
        }
    }

    /**
     * Iterates through each player, then each piece, and
     * places pieces on the empty temp board
     *
     * @param draw the temp board only used to display the current board
     * @param players both Players in an array
     */
    public void placePieces(char[][] draw, Player[] players) {
        for (Player player : players) {
            for (Piece piece : player.getValidPieces()) {
                draw[piece.getRow()][piece.getCol()] = (char) (piece.getRank() + '0');
            }
        }
    }

    /**     For crude implementation ONLY
     * Creates a new 2d array of char to render as the output
     * calls placePieces which places the Pieces on an otherwise empty board
     * then iterates through each char and if there isn't a piece there, retrieve the real board's Tile.
     * After completing construction, it prints the temp board
     * @param players the array of two Players that have Pieces on the real board
     */

    public void printBoard(Player[] players) {
        char[][] draw = new char[HEIGHT][WIDTH];
        placePieces(draw, players);

        for (int row = 0; row < HEIGHT; ++row) {
            for (int col = 0; col < WIDTH; ++col) {
                if (draw[row][col] == '\0') {
                    draw[row][col] = board[row][col].getAttribute();
                }
            }
        }

        for (int row = 0; row < HEIGHT; ++row) {
            for (int col = 0; col < WIDTH; ++col) {
                if (col != WIDTH - 1) {
                    System.out.print(draw[row][col] + " ");
                } else {
                    System.out.println(draw[row][col]);
                }
            }
        }
    }
}
