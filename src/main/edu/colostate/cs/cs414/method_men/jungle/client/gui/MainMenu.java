package edu.colostate.cs.cs414.method_men.jungle.client.gui;

import javax.swing.*;

public class MainMenu extends Page {
    public MainMenu(JFrame frame) {
        super(frame);
        JButton b = new JButton("Start game");
        b.setBounds(0, 0, 20, 20);
        add(b);
    }

}
