package edu.colostate.cs.cs414.method_men.jungle.client.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends Page implements ActionListener {
    public Game(GUI frame) {
        super(frame);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(9, 7));
        buttonPanel.setPreferredSize(new Dimension(7*75, 9*75));
        JButton [][] buttons = new JButton[9][7];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 7; j++) {
                JButton button = new JButton();
                button.setBorder(new LineBorder(Color.LIGHT_GRAY));
                button.addActionListener(this);
                buttons[i][j] = button;

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
        buttons[8][3].setIcon(new ImageIcon(new ImageIcon("src/Images/den.jpeg").getImage().getScaledInstance(75,75, Image.SCALE_SMOOTH)));
        buttons[7][3].setIcon(new ImageIcon(new ImageIcon("src/Images/trap.jpg").getImage().getScaledInstance(75,75, Image.SCALE_SMOOTH)));
        buttons[6][3].setIcon(new ImageIcon(new ImageIcon("src/Images/grass.jpeg").getImage().getScaledInstance(75,75, Image.SCALE_SMOOTH)));
        buttons[5][4].setIcon(new ImageIcon(new ImageIcon("src/Images/water.jpeg").getImage().getScaledInstance(75,75, Image.SCALE_SMOOTH)));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 7; j++) {
                buttonPanel.add(buttons[i][j]);
            }
        }
        add(buttonPanel, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JButton button = (JButton) actionEvent.getSource();

        if (((LineBorder)button.getBorder()).getLineColor().equals(Color.LIGHT_GRAY)) {
            button.setBorder(new LineBorder(Color.BLACK));
        } else if (((LineBorder)button.getBorder()).getLineColor().equals(Color.BLACK)) {
            button.setBorder(new LineBorder(Color.LIGHT_GRAY));
        }

    }
}
