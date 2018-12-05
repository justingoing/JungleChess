package edu.colostate.cs.cs414.method_men.jungle.client.gui;

import javax.swing.*;

public class    GameButton extends JButton {
    private int row;
    private int col;

    public GameButton(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

}
