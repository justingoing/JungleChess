package edu.colostate.cs.cs414.method_men.jungle.client;

import javax.swing.*;

public class UI {

    private static void createAndShowGUI() {
        JFrame f = new JFrame();
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setLocation(360, 160);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Background b = new Background();
        f.add(b);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
}
