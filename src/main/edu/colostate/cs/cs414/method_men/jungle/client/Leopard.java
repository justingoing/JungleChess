package edu.colostate.cs.cs414.method_men.jungle.client;

public class Leopard extends Piece {
    public Leopard(String color) {
        super("Leopard", 5, color);
        if (color.equals("red")) {
            setLocation(2, 2);
        } else if (color.equals("blue")) {
            setLocation(6, 4);
        }
    }
}
