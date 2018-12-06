package edu.colostate.cs.cs414.method_men.jungle.client.piece;

import edu.colostate.cs.cs414.method_men.jungle.client.Location;

public class Wolf extends GenericPiece {
    public Wolf(String color) {
        super("Wolf", 3, color);
        if (color.equals("red")) {
            setLocation(2, 4);
        } else if (color.equals("blue")) {
            setLocation(6, 2);
        }
    }

    public Wolf(String color, int row, int col) {
        super("Wolf", 3, color);
        setLocation(row, col);
    }
    public Wolf(String color, Location location){
        this(color, location.getRow(), location.getCol());
    }
}
