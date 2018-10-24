package edu.colostate.cs.cs414.method_men.jungle.client;


public class Dog extends Piece {
    public Dog(String color) {
        super("Dog", 4, color);
        if (color.equals("white")) {
            setLocation(1, 1);
        } else if (color.equals("black")) {
            setLocation(7, 5);
        }
    }
}
