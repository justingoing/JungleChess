package edu.colostate.cs.cs414.method_men.jungle.client.gui;

import javax.swing.*;

public class GUI extends JFrame {

    public GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private void createAndShowGUI() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Background b = new Background("src/Images/jungle.jpg");
        add(b);

        Game g = new Game(this);
        add(g);


        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's gui.
        GUI g = new GUI();
        javax.swing.SwingUtilities.invokeLater(() -> g.createAndShowGUI());
    }
}
