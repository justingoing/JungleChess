package edu.colostate.cs.cs414.method_men.jungle.client.piece;

public class Cat extends GenericPiece {
    public Cat(String color) {
        super("Cat", 2, color);
        if (color.equals("red")) {
            setLocation(1, 5);
        } else if (color.equals("blue")) {
            setLocation(7, 1);
        }
    }

    public Cat(String color, int row, int col) {
        super("Cat", 2, color);
        setLocation(row, col);
    }
}
