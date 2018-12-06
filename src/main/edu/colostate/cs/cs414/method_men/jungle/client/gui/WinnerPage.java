package edu.colostate.cs.cs414.method_men.jungle.client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinnerPage extends Page implements ActionListener{

    private Image background;

    public WinnerPage(GUI frame, String username){
        super(frame);
        this.background = Toolkit.getDefaultToolkit().createImage("src/Images/jungle.jpg");
        GridBagLayout gridbag = new GridBagLayout();
        this.setLayout(gridbag);
        GridBagConstraints c = new GridBagConstraints();

        String winner = username;

        JLabel title = new JLabel("  " + winner + " Wins!  ");
        title.setForeground(Color.white);
        title.setFont(title.getFont().deriveFont(48.0f));
        c.gridx = 0;
        c.gridy = 0;
        gridbag.setConstraints(title,c);
        add(title);

        JButton menu = new JButton("Main Menu");
        JButton exit = new JButton("Exit");
        menu.setActionCommand("Menu");
        menu.addActionListener(this);
        c.gridy = 1;
        c.gridx = 0;
        gridbag.setConstraints(menu,c);
        add(menu);

        exit.setActionCommand("Exit");
        exit.addActionListener(this);
        c.gridy = 2;
        c.gridx = 0;
        gridbag.setConstraints(exit,c);
        add(exit);

        JLabel spaces = new JLabel("       ");
        spaces.setFont(title.getFont().deriveFont(48.0f));
        c.gridx = 0;
        c.gridy = 3;
        gridbag.setConstraints(spaces,c);
        add(spaces);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(this.background, 0, 0, this);
        g2d.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case "Menu":
                frame.changePageTo(new MainMenuPage(frame));
                break;

            case "Exit":
                System.exit(0);

            default:
                break;
        }
    }
}

