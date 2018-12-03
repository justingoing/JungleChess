package edu.colostate.cs.cs414.method_men.jungle.client.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OutgoingInvitationsPage extends Page implements ActionListener {

    private Image background;

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
        c.insets = new Insets(10,25,0,25);
        gridbag.setConstraints(title,c);
        add(title);

        //Send invite: text
        JLabel inviteText = new JLabel("Send an invite to: ");
        inviteText.setForeground(Color.white);
        inviteText.setFont(title.getFont().deriveFont(14.0f));
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(5,25,0,0);
        gridbag.setConstraints(inviteText,c);
        add(inviteText);

        //Send invite: user input
        JTextField userInput = new JTextField(15);
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(5,0,0,0);
        gridbag.setConstraints(userInput,c);
        add(userInput);

        //Send invite: Send button
        JButton send = new JButton("Send");
        send.setActionCommand("SendInvite");
        send.addActionListener(this);
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 2;
        c.gridy = 1;
        c.insets = new Insets(5,0,0,25);
        gridbag.setConstraints(send,c);
        add(send);

        //Table of current sent invitations
        String columns[] = {"Friend", "Status"};
        //TODO: Populate this dynamically based on how many invites sent in DB
        //Each object in rows looks like {Friend name, status of invite}
        Object rows[][] = {{"Justin", "pending"}, {"Julien", "rejected :rage:"}, {"Mike", "accepted"}
                , {"Zane", "pending"}, {"Marcel", "pending"}, {"Connor", "pending"}};
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
        c.insets = new Insets(5,25,10,5);
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
        c.gridx = 2;
        c.gridy = 2;
        c.insets = new Insets(5,0,10,25);
        gridbag.setConstraints(back,c);
        add(back);
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
