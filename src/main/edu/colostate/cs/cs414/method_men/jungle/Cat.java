package edu.colostate.cs.cs414.method_men.jungle;

class Cat extends Piece {
    public Cat(String color) {
        super("Cat", 2, color);
        if (color.equals("white")) {
            setLocation(1, 5);
        } else if (color.equals("black")) {
            setLocation(7, 1);
        }
    }
}
