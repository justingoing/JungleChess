package edu.colostate.cs.cs414.method_men.jungle.client;

public class NextMove {
    Object[] pieceMove;

    public NextMove(Piece p, Location nextLocation) {
        this.pieceMove = new Object[3];
        this.pieceMove[0] = p;
        this.pieceMove[1] = nextLocation.getRow();
        this.pieceMove[2] = nextLocation.getCol();
    }

    public NextMove(Piece p, int row, int col) {
        this.pieceMove = new Object[3];
        this.pieceMove[0] = p;
        this.pieceMove[1] = row;
        this.pieceMove[2] = col;
    }

    public NextMove(Player myTurn, int currRow, int currCol, int nextRow, int nextCol) {
        this.pieceMove = new Object[3];
        this.pieceMove[0] = myTurn.retrievePieceByLocation(currRow, currCol);
        this.pieceMove[1] = nextRow;
        this.pieceMove[2] = nextCol;
    }

    public Piece getPiece() {
        return (Piece) this.pieceMove[0];
    }

    public int getRow() {
        return (int) this.pieceMove[1];
    }

    public int getCol() {
        return (int) this.pieceMove[2];
    }

    public void incRow() {
        this.pieceMove[1] = this.getRow() + 1;
    }

    public void decRow() {
        this.pieceMove[1] = this.getRow() - 1;
    }

    public void incCol() {
        this.pieceMove[2] = this.getCol() + 1;
    }

    public void decCol() {
        this.pieceMove[2] = this.getCol() - 1;
    }

    public void setLocation(int[] nextLocation) {
        this.pieceMove[1] = nextLocation[0];
        this.pieceMove[2] = nextLocation[1];
    }
}
