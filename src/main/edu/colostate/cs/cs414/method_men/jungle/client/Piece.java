package edu.colostate.cs.cs414.method_men.jungle.client;

public class Piece {
    private String name;
    private int rank;
    private String color;
    private int row;
    private int col;

    Piece(String name, int rank, String color) {
        this.name = name;
        this.rank = rank;
        this.color = color;
    }

    void setLocation(int row, int col) {
        this.row = row;
        this.col = col;
    }

    int[] getLocation() {
        int[] loc = new int[2];
        loc[0] = this.row;
        loc[1] = this.col;
        return loc;
    }

    int getRow() {
        return this.row;
    }

    int getCol() {
        return this.col;
    }

    String getName() {
        return this.name;
    }

    int getRank() {
        return this.rank;
    }

    String getColor(){return this.color;}

    boolean isRat() {
        if (name.equals("Rat")) {
            return true;
        }
        return false;
    }
}
