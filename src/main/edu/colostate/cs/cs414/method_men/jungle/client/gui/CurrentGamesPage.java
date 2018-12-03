package edu.colostate.cs.cs414.method_men.jungle.client.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        String columns[] = {"Opponent", "Play Game"};
        //TODO: Populate this dynamically based on how many games in DB
        Object rows[][] = {{"Justin", "<button>"}, {"Julien", "<button>"}, {"Mike", "<button>"}
                , {"Zane", "<button>"}, {"Marcel", "<button>"}, {"Connor", "<button>"}};
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
