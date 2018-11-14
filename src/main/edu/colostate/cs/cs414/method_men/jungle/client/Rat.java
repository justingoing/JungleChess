package edu.colostate.cs.cs414.method_men.jungle.client;

public class Rat extends Piece {
    public Rat(String color) {
        super("Rat", 1, color);
        if (color.equals("red")) {
            setLocation(2, 0);
        } else if (color.equals("blue")) {
            setLocation(6, 6);
        }
    }
}
