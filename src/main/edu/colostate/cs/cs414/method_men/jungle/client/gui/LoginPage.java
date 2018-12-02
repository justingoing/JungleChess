package edu.colostate.cs.cs414.method_men.jungle.client.gui;

import edu.colostate.cs.cs414.method_men.jungle.client.socket.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class LoginPage extends Page implements ActionListener{

    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private Socket client;

    public LoginPage(GUI frame){
        super(frame);
        this.client = frame.getSocket();
        GridBagLayout gridbag = new GridBagLayout();
        this.setLayout(gridbag);
        GridBagConstraints c = new GridBagConstraints();


        lbUsername = new JLabel("Username: ");
        c.gridx = 0;
        c.gridy = 0;
        gridbag.setConstraints(lbUsername,c);
        add(lbUsername);

        tfUsername = new JTextField(15);
        c.gridx = 1;
        c.gridy = 0;
        gridbag.setConstraints(tfUsername,c);
        add(tfUsername);

        lbPassword = new JLabel("Password: ");
        c.gridx = 0;
        c.gridy = 1;
        gridbag.setConstraints(lbPassword,c);
        add(lbPassword);

        pfPassword = new JPasswordField(15);
        c.gridx = 1;
        c.gridy = 1;
        gridbag.setConstraints(pfPassword,c);
        add(pfPassword);


        JButton login = new JButton("Login");
        login.setActionCommand("Login");
        login.addActionListener(this);
        c.gridy = 2;
        c.gridx = 1;
        c.anchor = GridBagConstraints.CENTER;
        gridbag.setConstraints(login,c);
        add(login);

        JButton cancel = new JButton("Cancel");
        cancel.setActionCommand("Cancel");
        cancel.addActionListener(this);
        c.gridy = 3;
        c.gridx = 1;
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


    @Override
    public void actionPerformed(ActionEvent actionEvent){
        switch (actionEvent.getActionCommand()) {

            case "Cancel":
                frame.changePageTo(new StartPage(frame));
                break;

            case "Login":
                boolean authLogin = false;
                if(getUsername().equals("") || getPassword().equals("")){
                    JOptionPane.showMessageDialog(frame,
                            "Username or password cannot be blank",
                            "Login",
                            JOptionPane.ERROR_MESSAGE);
                    tfUsername.setText("");
                    pfPassword.setText("");
                    break;
                }
                try{
                    authLogin = Login.authenticate(getUsername(), getPassword(), getClient());
                }catch(Exception e){}
                if(authLogin == true){
                    JOptionPane.showMessageDialog(frame,
                            "Hi " + getUsername() + "! You have successfully logged in.",
                            "Login",
                            JOptionPane.INFORMATION_MESSAGE);
                    frame.changePageTo(new MainMenuPage(frame));
                    break;
                }
                else{
                    JOptionPane.showMessageDialog(frame,
                            "Invalid username or password",
                            "Login",
                            JOptionPane.ERROR_MESSAGE);
                    tfUsername.setText("");
                    pfPassword.setText("");
                    break;
                }


            default:
                break;
        }
    }
}
