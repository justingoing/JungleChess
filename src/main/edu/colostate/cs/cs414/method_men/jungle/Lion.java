package edu.colostate.cs.cs414.method_men.jungle;

class Lion extends Piece {
    public Lion(String color) {
        super("Lion", 7, color);
        if (color.equals("white")) {
            setLocation(0, 0);
        } else if (color.equals("black")) {
            setLocation(8, 6);
        }
    }
}
