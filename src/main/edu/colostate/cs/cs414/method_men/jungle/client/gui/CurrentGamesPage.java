package edu.colostate.cs.cs414.method_men.jungle.client.gui;

import edu.colostate.cs.cs414.method_men.jungle.client.Game;
import edu.colostate.cs.cs414.method_men.jungle.client.GameState;
import edu.colostate.cs.cs414.method_men.jungle.client.socket.ClientReceive;
import edu.colostate.cs.cs414.method_men.jungle.client.socket.ClientSend;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
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
        JLabel title = new JLabel("         Current Games  ");
        title.setForeground(Color.white);
        title.setFont(title.getFont().deriveFont(32.0f));
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(25,25,0,25);
        gridbag.setConstraints(title,c);
        add(title);

        //Table of current games
        String columns[] = {"Blue Player", "Red Player", "Game ID", "Open"};
        Object rows[][] = populateTable(frame.getUsername());

        DefaultTableModel model = new DefaultTableModel(rows, columns);
        JTable table = new JTable(model);

        //Create buttons for accept and reject and have them be associated to the users in the rows.
        table.getColumn("Open").setCellRenderer(new ButtonRenderer());
        table.getColumn("Open").setCellEditor(
                new ButtonEditor(new JCheckBox(), frame));

        JScrollPane sPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sPane.getViewport().add(table);
        sPane.setPreferredSize(new Dimension(400, 60));
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
        Object rows[][] = new Object[Games.size()][4];
        for(int i = 0; i < Games.size(); i++){
            String [] t = Games.get(i).split(",");
            String z = Arrays.toString(t);
            System.out.println(z);
            System.out.println(t[0]);
            rows[i][0] = t[0];
            rows[i][1] = t[1];
            rows[i][2] = t[2];
            rows[i][3] = "Open";
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
                if (label.equals("Open")) {
                    //JOptionPane.showMessageDialog(button, "Accepted invitation from: " + table.getValueAt(row, 0));
                    String state = "";
                    try{
                        ClientSend cSend = new ClientSend(frame.getSocket());
                        // TODO implement that function
                        //System.out.println(table.getValueAt(row, 2));
                        cSend.sendGameStateRequest(table.getValueAt(row, 2).toString());
                        ClientReceive rec = new ClientReceive(frame.getSocket());
                        state = rec.receiveState();
                        //System.out.println("State = " + state);
                        //TODO (probably) wait for server to create the game and give us the gameID
                    }catch(Exception e){}
                    //get usernames for blue and red players

                    String blue = table.getValueAt(row, 0).toString();
                    String red = table.getValueAt(row, 1).toString();
                    System.out.println("players = " + blue + " " + red);
                    //get next move from game state
                    int nextMove = GameState.getTurn(state);
                    Game game = new Game(frame.getSocket(), blue, red);

                    System.out.println("blue? " + blue);
                    game.setTurn(nextMove);
                    //Gimme that game ID
                    Long id = new Long(Integer.parseInt(table.getValueAt(row, 2).toString()));
                    frame.changePageTo(new GamePage(frame, game, state, true, id));
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
