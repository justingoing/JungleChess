package edu.colostate.cs.cs414.method_men.jungle;

class Player {
    String color;
    Piece[] pieces;

    public Player(String color) {
        this.color = color;
        pieces = new Piece[8];
        makePieceInstances();
    }

    public void makePieceInstances() {
        pieces[0] = new Rat(color);
        pieces[1] = new Cat(color);
        pieces[2] = new Wolf(color);
        pieces[3] = new Dog(color);
        pieces[4] = new Leopard(color);
        pieces[5] = new Tiger(color);
        pieces[6] = new Lion(color);
        pieces[7] = new Elephant(color);
    }

    public int getValidPiecesCount() {
        int numValid = 0;

        for (Piece currPiece : pieces) {
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

        for (Piece currPiece : pieces) {
            if (currPiece != null) {
                validPieces[index++] = currPiece;
            }
        }

        return validPieces;
    }

    public Piece getPiece(int pieceRank) {
        return this.pieces[pieceRank];
    }

    public String getColor() {
        return this.color;
    }

    public void isCaptured(int rank) {
        pieces[rank-1] = null;
    }
}
