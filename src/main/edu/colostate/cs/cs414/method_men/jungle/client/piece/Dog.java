package edu.colostate.cs.cs414.method_men.jungle.client.piece;


public class Dog extends Piece {
    public Dog(String color) {
        super("Dog", 4, color);
        if (color.equals("red")) {
            setLocation(1, 1);
        } else if (color.equals("blue")) {
            setLocation(7, 5);
        }
    }
}
