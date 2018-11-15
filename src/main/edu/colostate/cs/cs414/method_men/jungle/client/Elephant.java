package edu.colostate.cs.cs414.method_men.jungle.client;

public class Elephant extends Piece {
    public Elephant(String color) {
        super("Elephant", 8, color);
        if (color.equals("red")) {
            setLocation(2, 6);
        } else if (color.equals("blue")) {
            setLocation(6, 0);
        }
    }
}
