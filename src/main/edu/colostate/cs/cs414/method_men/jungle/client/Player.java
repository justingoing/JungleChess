package edu.colostate.cs.cs414.method_men.jungle.client;

public class Player {
    String color;
    Piece[] pieces;

    public Player(String color) {
        this.color = color;
        this.pieces = new Piece[8];
        makePieceInstances();
    }

    public void makePieceInstances() {
        this.pieces[0] = new Rat(color);
        this.pieces[1] = new Cat(color);
        this.pieces[2] = new Wolf(color);
        this.pieces[3] = new Dog(color);
        this.pieces[4] = new Leopard(color);
        this.pieces[5] = new Tiger(color);
        this.pieces[6] = new Lion(color);
        this.pieces[7] = new Elephant(color);
    }

    public int getValidPiecesCount() {
        int numValid = 0;

        for (Piece currPiece : this.pieces) {
            if (currPiece != null) {
                ++numValid;
            }
        }
        return numValid;
    }

    public Piece[] getValidPieces() {
        int numValid = getValidPiecesCount();
        Piece[] validPieces = new Piece[numValid];
        int index = 0;

        for (Piece currPiece : this.pieces) {
            if (currPiece != null) {
                validPieces[index++] = currPiece;
            }
        }

        return validPieces;
    }

    public Piece retrievePieceByLocation(int currRow, int currCol) {
        for (Piece piece : getValidPieces()) {
            if (piece.getRow() == currRow && piece.getCol() == currCol) {
                return piece;
            }
        }
        return null;
    }

    public Piece getPiece(int pieceRank) {
        return this.pieces[pieceRank];
    }

    public String getColor() {
        return this.color;
    }

    public void isCaptured(int rank) {
        this.pieces[rank-1] = null;
    }
}
