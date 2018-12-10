package edu.colostate.cs.cs414.method_men.jungle.client.Game.piece;

import edu.colostate.cs.cs414.method_men.jungle.client.Game.Location;

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
    public Cat(String color, Location location){
        this(color, location.getRow(), location.getCol());
    }
}
