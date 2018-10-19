package edu.colostate.cs.cs414.method_men.jungle;

class Board {
    private final int height = 9;
    private final int width = 7;
    Tile[][] board;

    public Board() {
        board = new Tile[height][width];
        makeBoard();
    }

    public boolean isDen(int i, int j) {
        if (j == 3) {
            if (i == 0 || i == 8) {
                return true;
            }
        }
        return false;
    }

    public boolean isTrap(int i, int j) {
        if ((j == 2 || j == 4) && (i == 0 || i == 8)) {
            return true;
        } else if ((j == 3) && (i == 1 || i == 7)) {
            return true;
        }
        return false;
    }

    public boolean isRiver(int i, int j) {
        if (i >= 3 && i <= 5) {
            if (j == 1 || j == 2 || j == 4 || j == 5) {
                return true;
            }
        }
        return false;
    }

    public boolean isJump(int i, int j) {
        //if there is a River Tile above, below, to left, or to right
        if (isRiver(i+1, j) || isRiver(i-1, j) || isRiver(i, j+1) || isRiver(i, j-1)) {
            return true;
        }
        //if (i == 2 || i == 6) {
        //	if (j >= 1 && j <= 2) {
        //		return true;
        //    } else if (j >= 4 && j <= 5) {
        //		return true;
        //    }
        //} else if (i >= 3 && i <= 5) {
        //	if (j == 0 || j == 3 || j == 6) {
        //		return true;
        //	}
        //}
        return false;
    }

    public Tile makeInstance(int i, int j) {
        if (isDen(i, j)) {
            return new Den();
        } else if (isTrap(i, j)) {
            return new Trap();
        } else if (isRiver(i, j)) {
            return new River();
        } else if (isJump(i, j)) {
            return new Jump();
        } else {
            return new Open();
        }
    }

    public void makeBoard() {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                board[i][j] = makeInstance(i, j);
            }
        }
    }

    public void placePieces(char[][] draw, Player[] players) {
        for (Player player : players) {
            for (Piece piece : player.getValidPieces()) {
                draw[piece.getRow()][piece.getCol()] = (char) (piece.getRank() + '0');
            }
        }
    }

    //For crude implementation ONLY
    public void printBoard(Player[] players) {
        char[][] draw = new char[height][width];
        placePieces(draw, players);

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (draw[i][j] == '\0') {
                    draw[i][j] = board[i][j].getAttribute();
                }
            }
        }

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (j != width - 1) {
                    System.out.print(draw[i][j] + " ");
                } else {
                    System.out.println(draw[i][j]);
                }
            }
        }
    }
}
