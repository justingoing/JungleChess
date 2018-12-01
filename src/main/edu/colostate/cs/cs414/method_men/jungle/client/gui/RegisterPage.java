package edu.colostate.cs.cs414.method_men.jungle.client.gui;

import javax.swing.*;
import java.awt.*;
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

        GridBagLayout gridbag = new GridBagLayout();
        this.setLayout(gridbag);
        GridBagConstraints c = new GridBagConstraints();

        //Username text field
        lbUsername = new JLabel("Username: ");
        add(lbUsername);
        c.gridx = 0;
        c.gridy = 0;
        gridbag.setConstraints(lbUsername,c);

        tfUsername = new JTextField(20);
        c.gridx = 1;
        c.gridy = 0;
        gridbag.setConstraints(tfUsername,c);
        add(tfUsername);

        //Password text field
        lbPassword = new JLabel("Password: ");
        c.gridx = 0;
        c.gridy = 1;
        gridbag.setConstraints(lbPassword,c);
        add(lbPassword);
        pfPassword = new JPasswordField(20);
        c.gridx = 1;
        c.gridy = 1;
        gridbag.setConstraints(pfPassword,c);
        add(pfPassword);

        //Verify Password text field
        lbVerifyPassword = new JLabel("Verify Password: ");
        c.gridx = 0;
        c.gridy = 2;
        gridbag.setConstraints(lbVerifyPassword,c);
        add(lbVerifyPassword);
        pfVerifyPassword = new JPasswordField(20);
        c.gridx = 1;
        c.gridy = 2;
        gridbag.setConstraints(pfVerifyPassword,c);
        add(pfVerifyPassword);

        //Register button
        JButton register = new JButton("Register");
        register.setActionCommand("Register");
        register.addActionListener(this);
        c.gridx = 1;
        c.gridy = 3;
        c.anchor = GridBagConstraints.CENTER;
        gridbag.setConstraints(register,c);
        add(register);

        //Cancel Button
        JButton cancel = new JButton("Cancel");
        cancel.setActionCommand("Cancel");
        cancel.addActionListener(this);
        c.gridx = 1;
        c.gridy = 4;
        c.anchor = GridBagConstraints.CENTER;
        gridbag.setConstraints(cancel,c);
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
                //check for blank entries
                if(getUsername().equals("") || getPassword().equals("") || getVerifyPassword().equals("")){
                    JOptionPane.showMessageDialog(frame,
                            "Username or password fields cannot be blank",
                            "Register",
                            JOptionPane.ERROR_MESSAGE);
                    tfUsername.setText("");
                    pfPassword.setText("");
                    pfVerifyPassword.setText("");
                    break;
                }
                //Do we need to check passwords and usernames for things like improper symbols, spaces, etc?
                //Need a Register class that will send info to server
                else{
                    boolean registerSuccess = false;
                    try{
                        registerSuccess = Register.register(getUsername(), getPassword(), getClient());
                    }catch(Exception e){}
                    if(registerSuccess == true){
                        JOptionPane.showMessageDialog(frame,
                                "Hi " + getUsername() + "! You have successfully logged in.",
                                "Register",
                                JOptionPane.INFORMATION_MESSAGE);
                        frame.changePageTo(new MainMenuPage(frame));
                        break;
                    }
                    else{
                        JOptionPane.showMessageDialog(frame,
                                "Invalid username, please try again",
                                "Register",
                                JOptionPane.ERROR_MESSAGE);
                        tfUsername.setText("");
                        pfPassword.setText("");
                        break;
                    }

                }

        }

    }
}
