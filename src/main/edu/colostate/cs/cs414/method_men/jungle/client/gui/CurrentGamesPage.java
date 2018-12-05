package edu.colostate.cs.cs414.method_men.jungle.client.gui;

import edu.colostate.cs.cs414.method_men.jungle.client.socket.ClientReceive;
import edu.colostate.cs.cs414.method_men.jungle.client.socket.ClientSend;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class CurrentGamesPage extends Page implements ActionListener {

    private Image background;

    CurrentGamesPage(GUI frame){
        super(frame);
        this.background = Toolkit.getDefaultToolkit().createImage("src/Images/jungle.jpg");
        GridBagLayout gridbag = new GridBagLayout();
        this.setLayout(gridbag);
        GridBagConstraints c = new GridBagConstraints();

        //Title
        JLabel title = new JLabel("  Current Games  ");
        title.setForeground(Color.white);
        title.setFont(title.getFont().deriveFont(32.0f));
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(25,25,0,25);
        gridbag.setConstraints(title,c);
        add(title);

        //Table of current games
        String columns[] = {"Blue Player", "Red Player", "Game ID"};
        //TODO: Populate this dynamically based on how many games in DB
        Object rows[][] = populateTable(frame.getUsername());
        DefaultTableModel model = new DefaultTableModel(rows, columns);
        JTable table = new JTable(model);
        JScrollPane sPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sPane.getViewport().add(table);
        sPane.setPreferredSize(new Dimension(300, 50));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10,25,10,25);
        gridbag.setConstraints(sPane,c);
        add(sPane);


        //Back to main menu button
        JButton back = new JButton("Back to main menu");
        back.setActionCommand("Back");
        back.addActionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(10,25,25,25);
        gridbag.setConstraints(back,c);
        add(back);
    }


    public Object[][] populateTable(String username){
        ArrayList<String> Games = new ArrayList<>();
        //String [] gamess;
        String response = "";

        try {
            ClientSend send = new ClientSend(frame.getSocket());
            send.lookupMyGames(username);
            ClientReceive rec = new ClientReceive(frame.getSocket());
            response = rec.recieveGames();
            String [] s = response.split(" ");
            for(int i = 0; i < s.length; i ++){
                Games.add(s[i]);
            }
        }catch(Exception e){}


        System.out.println(response);
        System.out.println("ele 1: " + Games.get(0));
        System.out.println("games.size: " + Games.size());
        Object rows[][] = new Object[Games.size()][3];
        for(int i = 0; i < Games.size(); i++){
            String [] t = Games.get(i).split(",");
            String z = Arrays.toString(t);
            System.out.println(z);
            System.out.println(t[0]);
            rows[i][0] = t[0];
            rows[i][1] = t[1];
            rows[i][2] = t[2];
        }
        return rows;
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case "Back":
                frame.changePageTo(new MainMenuPage(frame));
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
