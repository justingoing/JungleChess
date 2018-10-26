package edu.colostate.cs.cs414.method_men.jungle.client;

import javax.swing.*;
import java.awt.*;

public class UI {

    private static void createAndShowGUI() {
        JFrame f = new JFrame();
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Background b = new Background("src/Images/jungle.jpg");
        Container contentPane = f.getContentPane();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(9, 7));
        buttonPanel.setPreferredSize(new Dimension(7*75, 9*75));
        JButton [] buttons = new JButton[63];
        for (int i = 0; i < 63; i++) {
            buttons[i] = new JButton("Button");
                buttonPanel.add(buttons[i]);
        }

        contentPane.add(buttonPanel, BorderLayout.CENTER);
        f.pack();
        f.add(b);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
}
