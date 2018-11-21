package edu.colostate.cs.cs414.method_men.jungle.client.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;

public class LoginPage extends Page implements ActionListener{

    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private Socket client;

    public LoginPage(GUI frame){
        super(frame);
        this.client = frame.getSocket();

        lbUsername = new JLabel("Username: ");
        add(lbUsername);

        tfUsername = new JTextField(20);
        tfUsername.setBounds(50,0,20,20);
        add(tfUsername);

        lbPassword = new JLabel("Password: ");
        add(lbPassword);

        pfPassword = new JPasswordField(20);
        pfPassword.setBounds(75,0,20,20);
        add(pfPassword);


        JButton login = new JButton("Login");
        login.setActionCommand("Login");
        login.addActionListener(this);
        login.setBounds(100,0,20,20);
        add(login);

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
