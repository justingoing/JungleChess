package edu.colostate.cs.cs414.method_men.jungle.client.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class RegisterPage extends Page implements ActionListener{

    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JPasswordField pfVerifyPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JLabel lbVerifyPassword;
    private Socket client;

    //need text boxes for username, password, verify password, email(?)
    //need register and cancel buttons
    public RegisterPage(GUI frame){
        super(frame);
        this.client = frame.getSocket();

        //Username text field
        lbUsername = new JLabel("Username: ");
        add(lbUsername);
        tfUsername = new JTextField(20);
        tfUsername.setBounds(50,0,20,20);
        add(tfUsername);

        //Password text field
        lbPassword = new JLabel("Password: ");
        add(lbPassword);
        pfPassword = new JPasswordField(20);
        pfPassword.setBounds(75,0,20,20);
        add(pfPassword);

        //Verify Password text field
        lbVerifyPassword = new JLabel("Verify Password: ");
        add(lbVerifyPassword);
        pfVerifyPassword = new JPasswordField(20);
        pfVerifyPassword.setBounds(75,0,20,20);
        add(pfVerifyPassword);

        //Register button
        JButton register = new JButton("Register");
        register.setActionCommand("Register");
        register.addActionListener(this);
        register.setBounds(100,0,20,20);
        add(register);

        //Cancel Button
        JButton cancel = new JButton("Cancel");
        cancel.setActionCommand("Cancel");
        cancel.addActionListener(this);
        cancel.setBounds(100,0,20,20);
        add(cancel);
    }

    public String getUsername() {
        return tfUsername.getText().trim();
    }

    public Socket getClient() {
        return client;
    }

    public String getPassword() {
        String s = new String(pfPassword.getPassword()).trim();
        return s;
    }

    public String getVerifyPassword() {
        String s = new String(pfVerifyPassword.getPassword()).trim();
        return s;
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case "Cancel":
                frame.changePageTo(new StartPage(frame));
                break;

            case "Register":
                //Passwords don't match
                if(!getPassword().equals(getVerifyPassword())){
                    JOptionPane.showMessageDialog(frame,
                            "Passwords do not match",
                            "Register",
                            JOptionPane.ERROR_MESSAGE);
                    pfVerifyPassword.setText("");
                    pfPassword.setText("");
                    break;
                }
                //Do we need to check passwords and usernames for things like improper symbols, spaces, etc?
                //Need a Register class that will send info to server

                frame.changePageTo(new StartPage(frame));
                break;
        }

    }
}
