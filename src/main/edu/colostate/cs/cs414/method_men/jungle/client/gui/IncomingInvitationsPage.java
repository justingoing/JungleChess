package edu.colostate.cs.cs414.method_men.jungle.client.gui;

import edu.colostate.cs.cs414.method_men.jungle.client.socket.ClientReceive;
import edu.colostate.cs.cs414.method_men.jungle.client.socket.ClientSend;
import edu.colostate.cs.cs414.method_men.jungle.server.SqlQueries;
import edu.colostate.cs.cs414.method_men.jungle.server.SqlUtils;
import org.jdbi.v3.core.Jdbi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class IncomingInvitationsPage extends Page implements ActionListener {

    private Image background;
    private Jdbi jdbi;
    private SqlQueries SQL;

    IncomingInvitationsPage(GUI frame){
        super(frame);
        this.background = Toolkit.getDefaultToolkit().createImage("src/Images/jungle.jpg");
        GridBagLayout gridbag = new GridBagLayout();
        this.setLayout(gridbag);
        GridBagConstraints c = new GridBagConstraints();

        //Title
        JLabel title = new JLabel("  Invitations to Play  ");
        title.setForeground(Color.white);
        title.setFont(title.getFont().deriveFont(32.0f));
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(25,25,0,25);
        gridbag.setConstraints(title,c);
        add(title);

        System.out.println("username: "  + frame.getUsername());

        //Table of current received invitations
        String columns[] = {"Friend", "Actions"};
        //TODO: Populate this dynamically based on how many invites sent in DB

        Object rows[][] = populateTable(frame.getUsername());

        //Each object in rows looks like {Friend name, status of invite}
        //Object rows[][] = {{"Justin", "<Accept/Reject>"}, {"Marcel", "<Accept/Reject>"}};
        DefaultTableModel model = new DefaultTableModel(rows, columns);
        JTable table = new JTable(model);
        JScrollPane sPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sPane.getViewport().add(table);
        sPane.setPreferredSize(new Dimension(200, 50));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(5,25,0,25);
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
        ArrayList<String> invites = new ArrayList<>();
        try {
            ClientSend send = new ClientSend(frame.getSocket());
            send.lookupMyInvites(username);
            ClientReceive rec = new ClientReceive(frame.getSocket());
            String response = rec.recieveMyInvites();


            if(response.equals("Fail")){
                JOptionPane.showMessageDialog(frame,
                        "You have no invites",
                        "myInvites",
                        JOptionPane.ERROR_MESSAGE);
            }else{
               String[] holder = response.split(" ");
               for(String inv : holder){
                   System.out.println("printing from split: "  + inv);
                   invites.add(inv);
               }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("invs size: " + invites.size());
        System.out.println("ele 1: " + invites.get(0));
        Object rows[][] = new Object[invites.size()][2];
        for(int i = 0; i < invites.size(); i++){
            rows[i][0] = invites.get(i);
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
