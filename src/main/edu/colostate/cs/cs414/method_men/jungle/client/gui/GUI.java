package edu.colostate.cs.cs414.method_men.jungle.client.gui;

import javax.swing.*;
import java.net.Socket;

public class GUI extends JFrame {

    private JPanel currentPanel;
    private Socket socket;
    private JFrame frame;
    private String username;

    public GUI(Socket socket) {

        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.socket = socket;
        this.frame = new JFrame();
        this.username = "";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Socket getSocket() {
        return socket;
    }

    private void createAndShowGUI() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        currentPanel = new StartPage(this);
        add(currentPanel);
        setVisible(true);
        pack();
    }

    public void changePageTo(JPanel comp) {
        remove(currentPanel);
        currentPanel = comp;
        add(comp);
        repaint();
        revalidate();
        pack();
    }


    public void startGUI(){
        javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

}
