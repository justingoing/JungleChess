package edu.colostate.cs.cs414.method_men.jungle.client;

public class Piece {
    private String name;
    private int rank;
//    private String color;
    private Location location;

    public Piece(String name, int rank, String color) {
        this.name = name;
        this.rank = rank;
//        this.color = color;
    }

    public void setLocation(int row, int col) {
        this.location = new Location(row, col);
    }

    public void setLocation(Location loc) {
        this.location = new Location(loc);
    }

    public Location getLocation() {
        return this.location;
    }

    public int getRow() {
        return this.location.getRow();
    }

    public int getCol() {
        return this.location.getCol();
    }

    public String getName() {
        return this.name;
    }

    public int getRank() {
        return this.rank;
    }

    public boolean isRat() {
        return (this.getName().equals("Rat"));
    }
  
    String getColor() {
        return this.color;
    }
}
