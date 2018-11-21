package edu.colostate.cs.cs414.method_men.jungle.client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.Socket;

public class GUI extends JFrame {

    JPanel currentPanel;
    private Socket socket;

    public GUI(Socket socket) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    private void createAndShowGUI() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Background b = new Background("src/Images/jungle.jpg");
        add(b);

        currentPanel = new StartPage(this);
        add(currentPanel);

        pack();
        setVisible(true);
    }

    public void changePageTo(JPanel comp) {
        remove(currentPanel);
        add(comp);
        currentPanel = comp;
        revalidate();
        repaint();
        //pack();
    }
    public void startGUI(){
        javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

}
