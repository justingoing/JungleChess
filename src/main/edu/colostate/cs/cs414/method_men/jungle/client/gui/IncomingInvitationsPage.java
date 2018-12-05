package edu.colostate.cs.cs414.method_men.jungle.client.gui;

import edu.colostate.cs.cs414.method_men.jungle.client.socket.ClientReceive;
import edu.colostate.cs.cs414.method_men.jungle.client.socket.ClientSend;
import edu.colostate.cs.cs414.method_men.jungle.server.SqlQueries;
import org.jdbi.v3.core.Jdbi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        String columns[] = {"Friend", "Accept", "Reject"};
        Object rows[][] = populateTable(frame.getUsername());

        //Each object in rows looks like {Friend name, status of invite}
        DefaultTableModel model = new DefaultTableModel(rows, columns);

        //Create buttons for accept and reject and have them be associated to the users in the rows.
        JTable table = new JTable(model);
        table.getColumn("Accept").setCellRenderer(new ButtonRenderer());
        table.getColumn("Accept").setCellEditor(
                new ButtonEditor(new JCheckBox(), frame));
        table.getColumn("Reject").setCellRenderer(new ButtonRenderer());
        table.getColumn("Reject").setCellEditor(
                new ButtonEditor(new JCheckBox(), frame));

        //Create the scroll pane. Essentially just puts the table inside of a box with scroll bars, with a certain size.
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
        //System.out.println("ele 1: " + invites.get(0));
        Object rows[][] = new Object[invites.size()][3];
        for(int i = 0; i < invites.size(); i++){
            rows[i][0] = invites.get(i);
            rows[i][1] = "Accept";
            rows[i][2] = "Reject";
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


    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }


    /**
     * This class allows us to have buttons in our JTable.
     * It is janky, but it is effective!
     */
    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private JTable table;
        private int row;
        private int column;
        private String label;
        GUI frame;

        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox, GUI frame) {
            super(checkBox);
            this.frame = frame;
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            this.table = table;
            this.row = row;
            this.column = column;
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            if (isPushed) {
                if (label.equals("Accept")) {
                    JOptionPane.showMessageDialog(button, "Accepted invitation from: " + table.getValueAt(row, 0));
                    try{
                        ClientSend cSend = new ClientSend(frame.getSocket());
                        cSend.sendAccept(table.getValueAt(row, 0).toString(), frame.getUsername());
                    }catch(Exception e){}
                    frame.changePageTo(new IncomingInvitationsPage(frame));
                }
                else if (label.equals("Reject")) {
                    JOptionPane.showMessageDialog(button, "Rejected invitation from: " + table.getValueAt(row, 0));
                    try{
                        ClientSend cSend = new ClientSend(frame.getSocket());
                        cSend.sendReject(table.getValueAt(row, 0).toString(), frame.getUsername());
                    }catch(Exception e){}
                    frame.changePageTo(new IncomingInvitationsPage(frame));
                }
            }
            isPushed = false;
            return new String(label);
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
}
