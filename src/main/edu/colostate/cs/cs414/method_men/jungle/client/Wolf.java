package edu.colostate.cs.cs414.method_men.jungle.client;

class Wolf extends Piece {
    public Wolf(String color) {
        super("Wolf", 3, color);
        if (color.equals("white")) {
            setLocation(2, 4);
        } else if (color.equals("black")) {
            setLocation(6, 2);
        }
    }
}
