package edu.colostate.cs.cs414.method_men.jungle.client.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends Page implements ActionListener {
    public MainMenu(GUI frame) {
        super(frame);
        JButton b = new JButton("Start game");
        b.setBounds(0, 0, 20, 20);
        b.setActionCommand("StartGame");
        b.addActionListener(this);
        add(b);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case "StartGame":
                frame.changePageTo(new Game(frame));
                break;

            default:
                break;
        }
    }
}
