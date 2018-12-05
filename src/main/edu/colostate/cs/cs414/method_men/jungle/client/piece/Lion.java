package edu.colostate.cs.cs414.method_men.jungle.client.piece;

public class Lion extends JumperPiece {
    public Lion(String color) {
        super("Lion", 7, color);
        if (color.equals("red")) {
            setLocation(0, 0);
        } else if (color.equals("blue")) {
            setLocation(8, 6);
        }
    }
    public Lion(String color, int row, int col) {
        super("Lion", 7, color);
        setLocation(row, col);
    }
}
