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
        JButton [][] buttons = new JButton[9][7];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 7; j++) {
                buttons[i][j] = new JButton();
            }
        }
        buttons[6][2].setIcon(new ImageIcon(new ImageIcon("src/Images/wolf.jpg").getImage().getScaledInstance(75,75, Image.SCALE_SMOOTH)));
        buttons[7][1].setIcon(new ImageIcon(new ImageIcon("src/Images/cat.jpg").getImage().getScaledInstance(75,75, Image.SCALE_SMOOTH)));
        buttons[8][0].setIcon(new ImageIcon(new ImageIcon("src/Images/tiger.jpg").getImage().getScaledInstance(75,75, Image.SCALE_SMOOTH)));
        buttons[6][6].setIcon(new ImageIcon(new ImageIcon("src/Images/rat.jpeg").getImage().getScaledInstance(75,75, Image.SCALE_SMOOTH)));
        buttons[7][5].setIcon(new ImageIcon(new ImageIcon("src/Images/dog.png").getImage().getScaledInstance(75,75, Image.SCALE_SMOOTH)));
        buttons[6][0].setIcon(new ImageIcon(new ImageIcon("src/Images/elephant.jpg").getImage().getScaledInstance(75,75, Image.SCALE_SMOOTH)));
        buttons[6][4].setIcon(new ImageIcon(new ImageIcon("src/Images/leopard.JPG").getImage().getScaledInstance(75,75, Image.SCALE_SMOOTH)));
        buttons[8][6].setIcon(new ImageIcon(new ImageIcon("src/Images/lion.jpg").getImage().getScaledInstance(75,75, Image.SCALE_SMOOTH)));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 7; j++) {
                buttonPanel.add(buttons[i][j]);
            }
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
