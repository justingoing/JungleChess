package edu.colostate.cs.cs414.method_men.jungle.client.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPage extends Page implements ActionListener{

    public StartPage(GUI frame){
        super(frame);
        JButton login = new JButton("Login");
        JButton register = new JButton("Register");
        login.setBounds(0, 0, 20, 20);
        login.setActionCommand("Login");
        login.addActionListener(this);
        add(login);
        register.setBounds(0, 0, 20, 20);
        register.setActionCommand("Register");
        register.addActionListener(this);
        add(register);
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
