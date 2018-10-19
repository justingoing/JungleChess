package edu.colostate.cs.cs414.method_men.jungle;

class Elephant extends Piece {
    public Elephant(String color) {
        super("Elephant", 8, color);
        if (color.equals("white")) {
            setLocation(2, 6);
        } else if (color.equals("black")) {
            setLocation(6, 0);
        }
    }
}
