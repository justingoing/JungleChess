package edu.colostate.cs.cs414.method_men.jungle.client.gui;

import edu.colostate.cs.cs414.method_men.jungle.client.socket.ClientReceive;
import edu.colostate.cs.cs414.method_men.jungle.client.socket.ClientSend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPage extends Page implements ActionListener {
    private Image background;

    MainMenuPage(GUI frame) {
        //Start off by calling super constructor, initializing the grids and getting the background.
        super(frame);
        /*try {
            new ClientReceive(frame.getSocket()).start();
        } catch (Exception e) {
        }*/

        this.background = Toolkit.getDefaultToolkit().createImage("src/Images/jungle.jpg");
        GridBagLayout gridbag = new GridBagLayout();
        this.setLayout(gridbag);
        GridBagConstraints c = new GridBagConstraints();


        //My Profile button
        JButton profile = new JButton("Profile");
        profile.setActionCommand("Profile");
        profile.addActionListener(this);
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(25, 25, 0, 0);
        gridbag.setConstraints(profile, c);
        add(profile);

        //Current games
        JButton games = new JButton("Games");
        games.setActionCommand("Games");
        games.addActionListener(this);
        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(25, 0, 0, 0);
        gridbag.setConstraints(games, c);
        add(games);

        //Check incoming invitations
        JButton invitations = new JButton("Invitations");
        invitations.setActionCommand("IncomingInvitations");
        invitations.addActionListener(this);
        c.gridx = 2;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(25, 0, 0, 25);
        gridbag.setConstraints(invitations, c);
        add(invitations);

        //Start Game button
        JButton startGame = new JButton("Online Game");
        startGame.setActionCommand("OnlineGame");
        startGame.addActionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(5, 25, 0, 25);
        gridbag.setConstraints(startGame, c);
        add(startGame);

        //Play a local game
        JButton localGame = new JButton("Local Game");
        localGame.setActionCommand("LocalGame");
        localGame.addActionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(5, 25, 25, 25);
        gridbag.setConstraints(localGame, c);
        add(localGame);

        //This is probably unnecessary
        c.insets = new Insets(0, 0, 0, 0);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case "LocalGame":
                frame.changePageTo(new GamePage(frame));
                break;
            case "OnlineGame":
                frame.changePageTo(new OutgoingInvitationsPage(frame));
                break;
            case "Profile":
                frame.changePageTo(new ProfilePage(frame));
                break;
            case "Games":
                String s = "";
                /*
                try{
                    ClientSend send = new ClientSend(frame.getSocket());
                    send.lookupMyGames(frame.getUsername());
                    ClientReceive rec = new ClientReceive(frame.getSocket());
                    s = rec.recieveGames();
                    System.out.println(s);
                }catch(Exception e){}
                */
                frame.changePageTo(new CurrentGamesPage(frame));
                break;
            case "IncomingInvitations":
                frame.changePageTo(new IncomingInvitationsPage(frame));
                break;
            default:
                break;
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(this.background, 0, 0, this);
        g2d.dispose();
    }
}
