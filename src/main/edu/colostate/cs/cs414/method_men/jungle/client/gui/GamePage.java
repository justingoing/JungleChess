package edu.colostate.cs.cs414.method_men.jungle.client.gui;

import edu.colostate.cs.cs414.method_men.jungle.client.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GamePage extends Page implements ActionListener {
    private final Icon CAT_ICON = new ImageIcon(new ImageIcon("src/Images/cat.jpg").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    private final Icon DEN_ICON = new ImageIcon(new ImageIcon("src/Images/den.jpg").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    private final Icon DOG_ICON = new ImageIcon(new ImageIcon("src/Images/dog.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    private final Icon ELEPHANT_ICON = new ImageIcon(new ImageIcon("src/Images/elephant.jpg").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    private final Icon GRASS_ICON = new ImageIcon(new ImageIcon("src/Images/grass.jpg").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    private final Icon LEOPARD_ICON = new ImageIcon(new ImageIcon("src/Images/leopard.jpg").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    private final Icon LION_ICON = new ImageIcon(new ImageIcon("src/Images/lion.jpg").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    private final Icon RAT_ICON = new ImageIcon(new ImageIcon("src/Images/rat.jpg").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    private final Icon TIGER_ICON = new ImageIcon(new ImageIcon("src/Images/tiger.jpg").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    private final Icon TRAP_ICON = new ImageIcon(new ImageIcon("src/Images/trap.jpg").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    private final Icon WATER_ICON = new ImageIcon(new ImageIcon("src/Images/water.jpg").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    private final Icon WOLF_ICON = new ImageIcon(new ImageIcon("src/Images/wolf.jpg").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));

    private Game game;
    private JButton[][] buttons;
    private int[] selectedButton = null;
    private ArrayList<Location> currentlyHighlighted;

    public GamePage(GUI frame) {
        super(frame);

        game = new Game();
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

        add(buttonPanel, BorderLayout.CENTER);

        updateBoard();
    }

    public void emptyBoard() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 7; col++) {
                buttons[row][col].setIcon(null);
            }
        }
    }

    public void updateBoard() {
        emptyBoard();

        for (int i = 0; i < 2; i++) {
            Player currPlayer = game.getPlayer(i);
            Piece[] pieces = currPlayer.getValidPieces();

            for (int n = 0; n < pieces.length; n++) {
                Icon icon;

                switch (pieces[n].getName()) {
                    case "Cat":
                        icon = CAT_ICON;
                        break;

                    case "Dog":
                        icon = DOG_ICON;
                        break;

                    case "Elephant":
                        icon = ELEPHANT_ICON;
                        break;

                    case "Leopard":
                        icon = LEOPARD_ICON;
                        break;

                    case "Lion":
                        icon = LION_ICON;
                        break;

                    case "Rat":
                        icon = RAT_ICON;
                        break;

                    case "Tiger":
                        icon = TIGER_ICON;
                        break;

                    case "Wolf":
                        icon = WOLF_ICON;
                        break;

                    default:
                        icon = null;
                        break;
                }

                buttons[pieces[n].getRow()][pieces[n].getCol()].setIcon(icon);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        GameButton button = (GameButton) actionEvent.getSource();

        if (((LineBorder)button.getBorder()).getLineColor().equals(Color.BLACK)) {
            button.setBorder(new LineBorder(Color.LIGHT_GRAY));
            unhighlight();
            selectedButton = null;
        } else if (selectedButton != null) {
            game.makeMoveUi(selectedButton[0], selectedButton[1], button.getRow(), button.getCol());
            updateBoard();
            buttons[selectedButton[0]][selectedButton[1]].setBorder(new LineBorder(Color.LIGHT_GRAY));
            selectedButton = null;
            unhighlight();

            if (game.winnerCheck() != -1) {
                game.endGame();
                // Can you switch panels from here? (to go back to main menu)
            }
        } else if (((LineBorder)button.getBorder()).getLineColor().equals(Color.LIGHT_GRAY)) {
            button.setBorder(new LineBorder(Color.BLACK));
            selectedButton = new int[]{button.getRow(), button.getCol()};

            ArrayList<Location> validDirectionButtons = game.retrieveValidLocations(button.getRow(), button.getCol());
            int stop = validDirectionButtons.size();
            for (int i = 0; i < stop; ++i) {
                Location curr = validDirectionButtons.remove(0);
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
