package edu.colostate.cs.cs414.method_men.jungle.client;

public class NextMove {
    Object[] pieceMove;

    public NextMove(Piece p, int[] nextLocation) {
        pieceMove = new Object[3];
        pieceMove[0] = p;
        pieceMove[1] = nextLocation[0];
        pieceMove[2] = nextLocation[1];
    }

    public NextMove(Player myTurn, int currRow, int currCol, int nextRow, int nextCol) {
        pieceMove = new Object[3];
        pieceMove[0] = myTurn.retrievePieceByLocation(currRow, currCol);
        pieceMove[1] = nextRow;
        pieceMove[2] = nextCol;
    }

    public Piece getPiece() {
        return (Piece) pieceMove[0];
    }

    public int getRow() {
        return (int) pieceMove[1];
    }

    public int getCol() {
        return (int) pieceMove[2];
    }

    public void incRow() {
        pieceMove[1] = (int) pieceMove[1] + 1;
    }

    public void decRow() {
        pieceMove[1] = (int) pieceMove[1] - 1;
    }

    public void incCol() {
        pieceMove[2] = (int) pieceMove[2] + 1;
    }

    public void decCol() {
        pieceMove[2] = (int) pieceMove[2] - 1;
    }

    public void setLocation(int[] nextLocation) {
        pieceMove[1] = nextLocation[0];
        pieceMove[2] = nextLocation[1];
    }
}
