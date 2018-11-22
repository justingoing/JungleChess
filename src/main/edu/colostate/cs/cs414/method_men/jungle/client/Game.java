package edu.colostate.cs.cs414.method_men.jungle.client;

import edu.colostate.cs.cs414.method_men.jungle.client.piece.*;

import java.util.ArrayList;
//import java.util.Random;

public class Game {
    private Player[] players;
    private int turn;
    private Board board;

    //Constructor
    public Game () {
        players = new Player[2];
        players[0] = new Player("red");
        players[1] = new Player("blue");
        turn = 1; // 1 means that Bottom Player makes the first move
        board = new Board();
    }

    /***NEW MOVE CODE***/

    //Takes current location and end location as int's, returns makeMove(Location,Location).
    public boolean makeMove(int currentRow, int currentCol, int nextRow, int nextCol){
        return makeMove(new Location(currentRow, currentCol), new Location(nextRow, nextCol));
    }

    //Takes a starting location and end location as arguments. Returns true if piece at start is moved to end location.
    public boolean makeMove(Location start, Location end){
        System.out.println("Game.makeMove()");
        Piece piece = board.getTile(start).getPiece();

        //Check if we are trying to move not a piece.
        if (piece == null){
            System.out.println(players[turn].getColor() + "'s trying to move a fricking empty spot");
            return false;
        }

        System.out.println(players[turn].getColor() + "'s trying to move " + piece.getColor() + " " + piece.getName());

        if (!(piece.getColor().equals(players[turn].getColor()))){
            System.out.println("Not your piece");
            return false;
        }

        else if (piece.isValidMove(end, board)){
            System.out.println(players[turn].getColor() + "'s move is valid ");
            board.move(piece, end);
            turn = (turn + 1)%2;
            return true;
        }
        System.out.println(players[turn].getColor() + "'s move is INvalid ");
        return false;
    }

    /**
     * There are two ways to win in Jungle:
     * 1. You reach the enemy Den
     * 2. You have no more Pieces (the count variable is 0)
     * @return 0 for top Player, 1 for bottom Player, -1 for no Winners yet
     */
    public int winnerCheck(){
        Player redPlayer = players[0];
        Player bluePlayer = players[1];

        //First, check if blue has won:
        //Look to see if blue piece on red den
        if (board.getTile(0, 3).getPiece() != null) {
            if (!(board.getTile(0, 3).getPiece().getColor().equals(redPlayer.getColor()))) {
                return 1; //Blue victory aka bot victory
            }
        }
        //Look to see if no red pieces
        if (board.getPieces("red").isEmpty()){
           return 1; //Blue victory aka bot victory
        }

        //Second, check if red has won:
        //Look to see if red piece on blue den
        if (board.getTile(8, 3).getPiece() != null) {
            if (!(board.getTile(8, 3).getPiece().getColor().equals(bluePlayer.getColor()))) {
                return 0; //Red victory aka top victory
            }
        }
        //Look to see if no blue pieces
        if (board.getPieces("blue").isEmpty()){
            return 0; //Red victory aka top victory
        }

        //No winner yet
        return -1;
    }

    /**
     * Match has concluded. Prints who won this match.
     */
    public void endGame() {
        if (turn == 0) {
            System.out.println("\tBottom Player is the winner!");
        } else {
            System.out.println("\tTop Player is the winner!");
        }
    }

    public ArrayList<Location> getValidLocations(int row, int col) {
        return getValidLocations(new Location(row, col));
    }

    public ArrayList<Location> getValidLocations(Location location) {
        Piece piece = board.getBoard().get(location).getPiece();

        //There is a piece there
        if (piece != null) {
            //..., and the current player owns it.
            if (piece.getColor().equals(players[turn].getColor())) {
                //Get the valid moves for that piece
                return piece.getAllValidMoves(board);
            }
        }
        return new ArrayList<>();
    }

    /**
     * Used to acces the private variable. Will be useful for testing & Ui interface
     * @return turn {0, 1}
     */
    public int getTurn() {
        return this.turn;
    }

    public Board getBoard() {
        return board;
    }

    /**
     * Every valid move will increment the turn so it will be the next Player's turn
     */
    public void incrementTurn() {
        this.turn = (this.turn + 1) % 2;
    }






}
