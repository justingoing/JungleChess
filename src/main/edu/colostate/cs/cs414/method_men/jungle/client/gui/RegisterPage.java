package edu.colostate.cs.cs414.method_men.jungle.client.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPage extends Page implements ActionListener{

    //need text boxes for username, password, verify password, email(?)
    //need register and cancel buttons

    public RegisterPage(GUI frame){
        super(frame);

        JButton cancel = new JButton("Cancel");
        cancel.setActionCommand("Cancel");
        cancel.addActionListener(this);
        cancel.setBounds(100,0,20,20);
        add(cancel);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case "Cancel":
                frame.changePageTo(new StartPage(frame));
                break;
        }

    }
}
