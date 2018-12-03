package edu.colostate.cs.cs414.method_men.jungle.client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPage extends Page implements ActionListener{

    private Image background;

    public StartPage(GUI frame){
        super(frame);
        repaint();
        this.background = Toolkit.getDefaultToolkit().createImage("src/Images/jungle.jpg");
        GridBagLayout gridbag = new GridBagLayout();
        this.setLayout(gridbag);
        GridBagConstraints c = new GridBagConstraints();

        JLabel title = new JLabel("  Welcome to Jungle  ");
        title.setForeground(Color.white);
        title.setFont(title.getFont().deriveFont(48.0f));
        c.gridx = 0;
        c.gridy = 0;
        gridbag.setConstraints(title,c);
        add(title);

        JButton login = new JButton("Login");
        JButton register = new JButton("Register");
        login.setActionCommand("Login");
        login.addActionListener(this);
        c.gridy = 1;
        c.gridx = 0;
        gridbag.setConstraints(login,c);
        add(login);

        register.setActionCommand("Register");
        register.addActionListener(this);
        c.gridy = 2;
        c.gridx = 0;
        gridbag.setConstraints(register,c);
        add(register);

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
            case "Login":
                frame.changePageTo(new LoginPage(frame));
                break;

            case "Register":
                frame.changePageTo(new RegisterPage(frame));
                break;

            default:
                break;
        }
    }
}
