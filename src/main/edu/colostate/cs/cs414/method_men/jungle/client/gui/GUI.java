package edu.colostate.cs.cs414.method_men.jungle.client.gui;

import javax.swing.*;

public class GUI extends JFrame {

    JPanel currentPanel;

    public GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createAndShowGUI() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Background b = new Background("src/Images/jungle.jpg");
        add(b);

        currentPanel = new MainMenuPage(this);
        add(currentPanel);

        pack();
        setVisible(true);
    }

    public void changePage(String panelName) {
        switch (panelName) {
            case "StartGame":
                remove(currentPanel);
                currentPanel = new GamePage(this);
                add(currentPanel);
                break;

            default:
                break;
        }

        revalidate();
        repaint();
    }

    public void changePageTo(JPanel comp) {
        remove(currentPanel);
        add(comp);
        currentPanel = comp;
        revalidate();
        repaint();
        pack();
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's gui.
        GUI g = new GUI();
        javax.swing.SwingUtilities.invokeLater(() -> g.createAndShowGUI());
    }
}
