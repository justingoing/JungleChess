package edu.colostate.cs.cs414.method_men.jungle.client;

public class Tile {
    private char attribute;
    private String type;
    private Piece piece;

    public Tile(char attribute) {
        this.attribute = attribute;
    }

    //Builds a tile based on location
    public Tile(Location location){
        if (isDen(location)){
            attribute = 'D';
            type = "Den";
        }
        else if (isTrap(location)){
            attribute = 'T';
            type = "Trap";
        }
        else if (isRiver(location)){
            attribute = 'R';
            type = "River";
        } else {
            attribute = 'O';
            type = "Open";
        }
    }

    public Tile (Location location, Piece piece){
        this(location);
        setPiece(piece);
    }

    private boolean isDen(Location location){
        if (location.equals(new Location(0, 3)) || location.equals(new Location(8, 3))){
            return true;
        }
        return false;
    }

    private boolean isTrap(Location location){
        if (location.equals(new Location(0, 2)) || location.equals(new Location(8, 2)) ||
            location.equals(new Location(0, 4)) || location.equals(new Location(8, 4)) ||
            location.equals(new Location(1, 3)) || location.equals(new Location(7, 3))){
            return true;
        }
        return false;
    }

    private boolean isRiver(Location location){
        if (location.equals(new Location(3, 1)) || location.equals(new Location(3, 4)) ||
            location.equals(new Location(4, 1)) || location.equals(new Location(4, 4)) ||
            location.equals(new Location(5, 1)) || location.equals(new Location(5, 4)) ||
            location.equals(new Location(3, 2)) || location.equals(new Location(3, 5)) ||
            location.equals(new Location(4, 2)) || location.equals(new Location(4, 5)) ||
            location.equals(new Location(5, 2)) || location.equals(new Location(5, 5))){
            return true;
        }
        return false;
    }

    public char getAttribute() {
        return attribute;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
    }



}