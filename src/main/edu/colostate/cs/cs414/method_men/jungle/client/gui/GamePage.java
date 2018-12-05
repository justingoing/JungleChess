package edu.colostate.cs.cs414.method_men.jungle.client.gui;

import edu.colostate.cs.cs414.method_men.jungle.client.*;
import edu.colostate.cs.cs414.method_men.jungle.client.piece.Piece;
import edu.colostate.cs.cs414.method_men.jungle.client.socket.ClientSend;
import edu.colostate.cs.cs414.method_men.jungle.client.tile.Tile;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GamePage extends Page implements ActionListener {
    // Blue Pieces
    private final Icon RED_CAT_ICON = new ImageIcon(new ImageIcon("src/Images/cat-red.jpg").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    private final Icon RED_DOG_ICON = new ImageIcon(new ImageIcon("src/Images/dog-red.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    private final Icon RED_ELEPHANT_ICON = new ImageIcon(new ImageIcon("src/Images/elephant-red.jpg").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    private final Icon RED_LEOPARD_ICON = new ImageIcon(new ImageIcon("src/Images/leopard-red.jpg").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    private final Icon RED_LION_ICON = new ImageIcon(new ImageIcon("src/Images/lion-red.jpg").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    private final Icon RED_RAT_ICON = new ImageIcon(new ImageIcon("src/Images/rat-red.jpg").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    private final Icon RED_TIGER_ICON = new ImageIcon(new ImageIcon("src/Images/tiger-red.jpg").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    private final Icon RED_WOLF_ICON = new ImageIcon(new ImageIcon("src/Images/wolf-red.jpg").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));

    // Red Pieces
    private final Icon BLUE_CAT_ICON = new ImageIcon(new ImageIcon("src/Images/cat-blue.jpg").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    private final Icon BLUE_DOG_ICON = new ImageIcon(new ImageIcon("src/Images/dog-blue.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    private final Icon BLUE_ELEPHANT_ICON = new ImageIcon(new ImageIcon("src/Images/elephant-blue.jpg").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    private final Icon BLUE_LEOPARD_ICON = new ImageIcon(new ImageIcon("src/Images/leopard-blue.jpg").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    private final Icon BLUE_LION_ICON = new ImageIcon(new ImageIcon("src/Images/lion-blue.jpg").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    private final Icon BLUE_RAT_ICON = new ImageIcon(new ImageIcon("src/Images/rat-blue.jpg").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    private final Icon BLUE_TIGER_ICON = new ImageIcon(new ImageIcon("src/Images/tiger-blue.jpg").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    private final Icon BLUE_WOLF_ICON = new ImageIcon(new ImageIcon("src/Images/wolf-blue.jpg").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));


    // Tiles
    private final Icon DEN_ICON = new ImageIcon(new ImageIcon("src/Images/den.jpg").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    private final Icon GRASS_ICON = new ImageIcon(new ImageIcon("src/Images/grass.jpg").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    private final Icon TRAP_ICON = new ImageIcon(new ImageIcon("src/Images/trap.jpg").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    private final Icon WATER_ICON = new ImageIcon(new ImageIcon("src/Images/water.jpg").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));

    private Game game;
    private Long gameID;
    private Boolean useState;
    private String state;
    private JButton[][] buttons;
    private int[] selectedButton = null;
    private ArrayList<Location> currentlyHighlighted;
    private boolean stateLoaded = false;


    //if flag = true,
    public GamePage(GUI frame, Game gameIn, String state, boolean useState, Long id) {
        super(frame);
        //need this to send updated game state to server
        this.gameID = id;
        this.useState = useState;
        this.state = state;
        GridBagLayout gridbag = new GridBagLayout();
        this.setLayout(gridbag);
        GridBagConstraints c = new GridBagConstraints();

        //Game board
        if(useState == true){
            this.game = gameIn;
            System.out.println("useState = " + useState);
        }
        else{
            game = new Game(frame.getSocket(), frame.getUsername());
            System.out.println("useState = " + useState);
        }
        currentlyHighlighted = new ArrayList<>();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(9, 7));
        buttonPanel.setPreferredSize(new Dimension(7*75, 9*75));
        buttons = new JButton[9][7];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 7; j++) {
                JButton button = new GameButton(i, j);
                button.setBorder(new LineBorder(Color.LIGHT_GRAY));
                button.addActionListener(this);
                buttons[i][j] = button;
                buttonPanel.add(button);
            }
        }
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5,5,5,5);
        c.anchor = GridBagConstraints.NORTHWEST;
        add(buttonPanel, c);

        //Rules
        JTextArea textArea = new JTextArea(31, 20);
        textArea.setEditable(false);
        textArea.append("Rules:\n");
        textArea.append("Blue goes first, then red.\n");
        textArea.append("During a turn, the player must move.\n");
        textArea.append("\n");
        textArea.append("Win conditions:\n");
        textArea.append("1. Move a piece into your opponents den.\n");
        textArea.append("2. Eliminate all opponents pieces.\n");
        textArea.append("\n");
        textArea.append("Movement:\n");
        textArea.append("A player may only move their own pieces.\n");
        textArea.append("A player may not move a piece onto their own piece.\n");
        textArea.append("All pieces may move 1 horizontal, or 1 vertical.\n");
        textArea.append("Only a rat may enter/exit the river.\n");
        textArea.append("Lion and Tiger may jump a river horizontal or vertical,\n");
        textArea.append("  but only if no rat is in the river, in their path,\n");
        textArea.append("  and the move otherwise follows capturing rules.\n");
        textArea.append("\n");
        textArea.append("Capturing:\n");
        textArea.append("A piece can capture any enemy piece that has the same\n");
        textArea.append("  or lower rank, with the following exceptions:\n");
        textArea.append("A rat can capture an elephant while both are on land.\n");
        textArea.append("While on an enemy trap, any piece's rank is 0\n");
        textArea.append("\n");
        textArea.append("Ranks:\n");
        textArea.append("1 - Rat\n");
        textArea.append("2 - Cat\n");
        textArea.append("3 - Wolf\n");
        textArea.append("4 - Dog\n");
        textArea.append("5 - Leopard\n");
        textArea.append("6 - Tiger\n");
        textArea.append("7 - Lion\n");
        textArea.append("8 - Elephant\n");
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTHEAST;
        c.insets = new Insets(5,0,0,5);
        add(textArea, c);

        //Back to main menu button
        JButton back = new JButton("Back to main menu");
        back.setActionCommand("Back");
        back.addActionListener(this);
        c.gridx = 1;
        c.gridy = 0;
        c.ipady = 40;
        c.anchor = GridBagConstraints.SOUTHEAST;
        c.insets = new Insets(0,0,5,5);
        add(back, c);

        resetBoard();
        updateBoard();
    }

    public void resetBoard() {
        // Fill the entire board with grass (some tiles will change in the next few lines)
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 7; col++) {
                buttons[row][col].setIcon(GRASS_ICON);
            }
        }

        // Draw left river
        buttons[3][1].setIcon(WATER_ICON);
        buttons[3][2].setIcon(WATER_ICON);
        buttons[4][1].setIcon(WATER_ICON);
        buttons[4][2].setIcon(WATER_ICON);
        buttons[5][1].setIcon(WATER_ICON);
        buttons[5][2].setIcon(WATER_ICON);

        // Draw right river
        buttons[3][4].setIcon(WATER_ICON);
        buttons[3][5].setIcon(WATER_ICON);
        buttons[4][4].setIcon(WATER_ICON);
        buttons[4][5].setIcon(WATER_ICON);
        buttons[5][4].setIcon(WATER_ICON);
        buttons[5][5].setIcon(WATER_ICON);

        // Draw Dens
        buttons[0][3].setIcon(DEN_ICON);
        buttons[8][3].setIcon(DEN_ICON);

        // Draw Top Traps
        buttons[1][3].setIcon(TRAP_ICON);
        buttons[0][2].setIcon(TRAP_ICON);
        buttons[0][4].setIcon(TRAP_ICON);

        // Draw Bottom Traps
        buttons[7][3].setIcon(TRAP_ICON);
        buttons[8][2].setIcon(TRAP_ICON);
        buttons[8][4].setIcon(TRAP_ICON);
    }

    public void updateBoard() {
        resetBoard();
        for (int i = 0; i < 2; i++) {
            //Piece[] pieces = currPlayer.getValidPieces();
            // TODO Gotta build this pieces array from game state then execute all the following lines
            ArrayList<Piece> pieces = new ArrayList<Piece>();
            if(this.useState == false) {
                for (Tile tile : this.game.getBoard().getBoard().values()) {
                    if (tile.getPiece() != null) {
                        pieces.add(tile.getPiece());
                    }
                }
            }

            else{
                pieces = GameState.setGameState(state, this.game);
                this.game.setTurn(GameState.getTurn(state));
                System.out.println(this.game.getTurn());
            }

            for (int n = 0; n < pieces.size() ; n++) {
                Icon icon = null;

                switch (pieces.get(n).getName()) {
                    case "Cat":
                        if (pieces.get(n).getColor().equals("red")) {
                            icon = RED_CAT_ICON;
                        } else if (pieces.get(n).getColor().equals("blue")) {
                            icon = BLUE_CAT_ICON;
                        }
                        break;

                    case "Dog":
                        if (pieces.get(n).getColor().equals("red")) {
                            icon = RED_DOG_ICON;
                        } else if (pieces.get(n).getColor().equals("blue")) {
                            icon = BLUE_DOG_ICON;
                        }
                        break;

                    case "Elephant":
                        if (pieces.get(n).getColor().equals("red")) {
                            icon = RED_ELEPHANT_ICON;
                        } else if (pieces.get(n).getColor().equals("blue")) {
                            icon = BLUE_ELEPHANT_ICON;
                        }
                        break;

                    case "Leopard":
                        if (pieces.get(n).getColor().equals("red")) {
                            icon = RED_LEOPARD_ICON;
                        } else if (pieces.get(n).getColor().equals("blue")) {
                            icon = BLUE_LEOPARD_ICON;
                        }
                        break;

                    case "Lion":
                        if (pieces.get(n).getColor().equals("red")) {
                            icon = RED_LION_ICON;
                        } else if (pieces.get(n).getColor().equals("blue")) {
                            icon = BLUE_LION_ICON;
                        }
                        break;

                    case "Rat":
                        if (pieces.get(n).getColor().equals("red")) {
                            icon = RED_RAT_ICON;
                        } else if (pieces.get(n).getColor().equals("blue")) {
                            icon = BLUE_RAT_ICON;
                        }
                        break;

                    case "Tiger":
                        if (pieces.get(n).getColor().equals("red")) {
                            icon = RED_TIGER_ICON;
                        } else if (pieces.get(n).getColor().equals("blue")) {
                            icon = BLUE_TIGER_ICON;
                        }
                        break;

                    case "Wolf":
                        if (pieces.get(n).getColor().equals("red")) {
                            icon = RED_WOLF_ICON;
                        } else if (pieces.get(n).getColor().equals("blue")) {
                            icon = BLUE_WOLF_ICON;
                        }
                        break;
                }
                buttons[pieces.get(n).getLocation().getRow()][pieces.get(n).getLocation().getCol()].setIcon(icon);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("Back")){
            frame.changePageTo(new MainMenuPage(frame));
            return;
        }
        GameButton button = (GameButton) actionEvent.getSource();
        if (((LineBorder)button.getBorder()).getLineColor().equals(Color.BLACK)) {
            button.setBorder(new LineBorder(Color.LIGHT_GRAY));
            unhighlight();
            selectedButton = null;
        } else if (selectedButton != null) {
            //game.makeMoveUi(selectedButton[0], selectedButton[1], button.getRow(), button.getCol());
            game.makeMove(selectedButton[0], selectedButton[1], button.getRow(), button.getCol(), this.gameID);
            updateBoard();
            buttons[selectedButton[0]][selectedButton[1]].setBorder(new LineBorder(Color.LIGHT_GRAY));
            selectedButton = null;
            unhighlight();

            if (game.winnerCheck() == 1) {
                game.endGame();
                frame.changePageTo(new GameEndPage(frame, false));
            } else if (game.winnerCheck() == 0) {
                game.endGame();
                frame.changePageTo(new GameEndPage(frame, true));
            }

        } else if (((LineBorder)button.getBorder()).getLineColor().equals(Color.LIGHT_GRAY)) {
            button.setBorder(new LineBorder(Color.BLACK));
            selectedButton = new int[]{button.getRow(), button.getCol()};

            ArrayList<Location> validDirectionButtons = game.getValidMoves(button.getRow(), button.getCol());
            for (int i = 0; i < validDirectionButtons.size(); ++i) {
                Location curr = validDirectionButtons.get(i);
                int row = curr.getRow();
                int col = curr.getCol();
                buttons[row][col].setBorder(new LineBorder(Color.BLUE));

                currentlyHighlighted.add(curr);
            }
        }
    }

    /**
     * Takes all highlighted squares are reverts them back to LIGHT_GRAY
     */
    public void unhighlight() {
        for (Location curr : currentlyHighlighted) {
            int row = curr.getRow();
            int col = curr.getCol();
            buttons[row][col].setBorder(new LineBorder(Color.LIGHT_GRAY)); // take back your voodoo magic of color;
        }

        currentlyHighlighted = new ArrayList<>();
    }
}
