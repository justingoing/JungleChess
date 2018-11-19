package edu.colostate.cs.cs414.method_men.jungle.client;

public class Tiger extends Piece {
    public Tiger(String color) {
        super("Tiger", 6, color);
        if (color.equals("red")) {
            setLocation(0, 6);
        } else if (color.equals("blue")) {
            setLocation(8, 0);
        }
    }
}
