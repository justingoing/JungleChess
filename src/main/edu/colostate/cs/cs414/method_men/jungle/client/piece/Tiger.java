package edu.colostate.cs.cs414.method_men.jungle.client.piece;

import edu.colostate.cs.cs414.method_men.jungle.client.Location;

public class Tiger extends JumperPiece {
    public Tiger(String color) {
        super("Tiger", 6, color);
        if (color.equals("red")) {
            setLocation(0, 6);
        } else if (color.equals("blue")) {
            setLocation(8, 0);
        }
    }
    public Tiger(String color, int row, int col) {
        super("Tiger", 6, color);
        setLocation(row, col);
    }
    public Tiger(String color, Location location){
        this(color, location.getRow(), location.getCol());
    }
}
