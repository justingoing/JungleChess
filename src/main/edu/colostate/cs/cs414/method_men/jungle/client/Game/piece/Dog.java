package edu.colostate.cs.cs414.method_men.jungle.client.Game.piece;


import edu.colostate.cs.cs414.method_men.jungle.client.Game.Location;

public class Dog extends GenericPiece {
    public Dog(String color) {
        super("Dog", 4, color);
        if (color.equals("red")) {
            setLocation(1, 1);
        } else if (color.equals("blue")) {
            setLocation(7, 5);
        }
    }

    public Dog(String color, int row, int col) {
        super("Dog", 4, color);
        setLocation(row, col);
    }
    public Dog(String color, Location location){
        this(color, location.getRow(), location.getCol());
    }
}
