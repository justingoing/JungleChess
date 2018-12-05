package edu.colostate.cs.cs414.method_men.jungle.client.gui;

import edu.colostate.cs.cs414.method_men.jungle.client.socket.ClientReceive;
import edu.colostate.cs.cs414.method_men.jungle.client.socket.ClientSend;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OutgoingInvitationsPage extends Page implements ActionListener {

    private Image background;
    private JTextField  userInput;
    Object rows[][];

    OutgoingInvitationsPage(GUI frame){
        super(frame);
        this.background = Toolkit.getDefaultToolkit().createImage("src/Images/jungle.jpg");
        GridBagLayout gridbag = new GridBagLayout();
        this.setLayout(gridbag);
        GridBagConstraints c = new GridBagConstraints();

        //Title
        JLabel title = new JLabel("Online Game");
        title.setForeground(Color.white);
        title.setFont(title.getFont().deriveFont(32.0f));
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(10,25,0,25);
        gridbag.setConstraints(title,c);
        add(title);

        //Send invite: text
        JLabel inviteText = new JLabel("Send an invite to: ");
        inviteText.setForeground(Color.white);
        inviteText.setFont(inviteText.getFont().deriveFont(14.0f));
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(5,25,0,0);
        gridbag.setConstraints(inviteText,c);
        add(inviteText);

        //Send invite: user input
        userInput = new JTextField(13);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.WEST;
        //c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5,0,0,0);
        gridbag.setConstraints(userInput,c);
        add(userInput);

        //Send invite: Send button
        JButton send = new JButton("Send");
        send.setActionCommand("SendInvite");
        send.addActionListener(this);
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(5,0,0,25);
        gridbag.setConstraints(send,c);
        add(send);

        this.rows = populateTable(frame.getUsername());

        c.anchor = GridBagConstraints.CENTER;
        //Table of current sent invitations
        String columns[] = {"Friend", "Status"};
        //TODO: Populate this dynamically based on how many invites sent in DB
        //Each object in rows looks like {Friend name, status of invite}
        //Object rows[][] = {{"Justin", "pending"}, {"Julien", "rejected :rage:"}, {"Mike", "accepted"}, {"Zane", "pending"}, {"Marcel", "pending"}, {"Connor", "pending"}};
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
        c.gridy = 2;
        c.insets = new Insets(5,25,10,25);
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
        c.gridy = 3;
        c.insets = new Insets(5,25,10,25);
        gridbag.setConstraints(back,c);
        add(back);
    }

    public String getInvite(){
        return userInput.getText().trim();
    }

    public Object[][] populateTable(String username){
        ArrayList<String> invites = new ArrayList<>();
        try {
            ClientSend send = new ClientSend(frame.getSocket());
            send.lookupMySentInvites(username);
            ClientReceive rec = new ClientReceive(frame.getSocket());
            String response = rec.recieveMyInvites();

            if(response.equals("Fail")){

            }else{
                String[] holder = response.split(" ");
                for(String inv : holder){
                    invites.add(inv);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Object rows[][] = new Object[invites.size()][2];
        for(int i = 0; i < invites.size(); i++){
            rows[i][0] = invites.get(i);
            rows[i][1] = "Pending";
        }
        return rows;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case "Back":
                frame.changePageTo(new MainMenuPage(frame));
                break;
            case "SendInvite":
                //frame.changePageTo(new MainMenuPage(frame));
                String response = "";
                try{
                    ClientSend send = new ClientSend(frame.getSocket());
                    send.sendInvite(frame.getUsername(), getInvite());
                    ClientReceive rec = new ClientReceive(frame.getSocket());
                    response = rec.recieveInviteResponse();
                }catch(Exception e){}

                if(response.equals("Success")){
                    JOptionPane.showMessageDialog(frame,
                            "Invite sent to " + getInvite() + "!",
                            "Invite",
                            JOptionPane.INFORMATION_MESSAGE);
                    userInput.setText("");
                    frame.changePageTo(new OutgoingInvitationsPage(frame));
                }
                else if(response.equals("There")){
                    JOptionPane.showMessageDialog(frame,
                            "Pending Invite to " + getInvite() + " already exists!",
                            "Invite",
                            JOptionPane.ERROR_MESSAGE);
                    userInput.setText("");
                }
                else if(response.equals("Fail")){
                    JOptionPane.showMessageDialog(frame,
                            "User " + getInvite() + " does not exist",
                            "Invite",
                            JOptionPane.ERROR_MESSAGE);
                    userInput.setText("");
                }
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
