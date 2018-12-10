package edu.colostate.cs.cs414.method_men.jungle.client.Game.piece;

import edu.colostate.cs.cs414.method_men.jungle.client.Game.Location;

public class Leopard extends GenericPiece {
    public Leopard(String color) {
        super("Leopard", 5, color);
        if (color.equals("red")) {
            setLocation(2, 2);
        } else if (color.equals("blue")) {
            setLocation(6, 4);
        }
    }

    public Leopard(String color, int row, int col) {
        super("Leopard", 5, color);
        setLocation(row, col);
    }
    public Leopard(String color, Location location){
        this(color, location.getRow(), location.getCol());
    }
}
