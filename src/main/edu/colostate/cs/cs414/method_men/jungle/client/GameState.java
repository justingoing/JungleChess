package edu.colostate.cs.cs414.method_men.jungle.client;

import edu.colostate.cs.cs414.method_men.jungle.client.piece.*;
import edu.colostate.cs.cs414.method_men.jungle.client.tile.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GameState {

    //we dont need this
    public static String makeGameState(String username, int winner, int turn, int moveCount, ArrayList<Piece> red, ArrayList<Piece> blue, Long ID){
        String nextTurn;
        if(turn == 0){
            nextTurn = "Red";
        }
        else{
            nextTurn = "Blue";
        }

        String state = ID + " Winner:" + winner + " " + "NextTurn:" + nextTurn + " " + "MoveCount:" + moveCount + " ";
        String redPieces = "Red:";
        String bluePieces = "Blue:";
        redPieces += getPieces(red);
        bluePieces += getPieces(blue);
        state += redPieces + " " + bluePieces;
        return state;
    }

    public static int getTurn(String gameState){
        String[] splitString = gameState.split(" ");
        String[] nextTurnStrings = splitString[1].split(":");
        int nextTurn = 1;
        if (nextTurnStrings[1].equals("Red")) {
            nextTurn = 0;
        }
        return nextTurn;
    }

    public static String getPieces(ArrayList<Piece> color){
        String pieces = "";
        for(int i = 0; i < color.size(); i++){
            int rank = color.get(i).getRank();
            int row = color.get(i).getLocation().getRow();
            int col = color.get(i).getLocation().getCol();
            pieces += rank + "," + row + "," + col;

            if(i < color.size() - 1) {
                pieces += "/";
            }
        }
        return pieces;
    }


    // This code assumes the string is in the format "BluePlayer:<username> RedPlayer:<username> Winner:<-1 for no winner, 1 or 0 if there is a winner> MoveCount:<number of moves> Red:<piece locations> Blue:<piece locations>"
    // piece locations are in the format <piece rank>:<row>:<col> where each piece is comma seperated
    public static boolean setGameState(String gameState, Game game) {
        String[] splitString = gameState.split(" ");

        // TODO we need to do something with the data about winner when the game is won
        // there isn't any way to do anything we can do with it right now because it only checks the winner by the locations of the pieces

        // Parse next turn
        String[] nextTurnStrings = splitString[1].split(":");
        int nextTurn;
        if (nextTurnStrings[1].equals("Red")) {
            nextTurn = 0;
        } else if (nextTurnStrings[1].equals("Blue")) {
            nextTurn = 1;
        } else {
            return false;
        }

        // set next turn
        if (game.getTurn() != nextTurn) {
            game.incrementTurn();
        }

        // parse red pieces
        String[] redPiecesStrings = splitString[3].split(":");
        String[] redPieceLocations = redPiecesStrings[1].split("/");

        if (!parseAndSetPieces("red", redPieceLocations, game)) {
            System.out.println("FAILED to set red pieces");
            return false;
        }

        // parse blue pieces
        String[] bluePiecesStrings = splitString[4].split(":");
        String[] bluePiecesLocations = bluePiecesStrings[1].split("/");

        if (!parseAndSetPieces("blue", bluePiecesLocations, game)) {
            System.out.println("FAILED to set blue pieces");
            return false;
        }


        return true;
    }

    // Parse the pieces strings and move/delete the necessary pieces
    public static boolean parseAndSetPieces(String color, String[] locationStrings, Game game) {
        Board board = game.getBoard();

        ArrayList<Piece> pieces = new ArrayList<Piece>();
        for (Tile tile : board.getBoard().values()) {
            if (tile.getPiece() != null){
                pieces.add(tile.getPiece());
            }
        }

        for (int currPieceIndex = 0; currPieceIndex < pieces.size(); currPieceIndex++) {
            Piece currPiece = pieces.get(currPieceIndex);
            boolean pieceInStateList = false;

            for (int currStringIndex = 0; currStringIndex < locationStrings.length; currStringIndex++) {
                String[] currStringStrings = locationStrings[currStringIndex].split(",");

                int pieceRank;
                int pieceRow;
                int pieceCol;

                try {
                    pieceRank = Integer.parseInt(currStringStrings[0]);
                    pieceRow = Integer.parseInt(currStringStrings[1]);
                    pieceCol = Integer.parseInt(currStringStrings[2]);
                } catch (NumberFormatException e) {
                    return false;
                }

                if (currPiece.getColor().equals(color) && currPiece.getRank() == pieceRank) {
                    board.move(currPiece, new Location(pieceRow, pieceCol));
                    pieceInStateList = true; // don't delete the piece
                    break;
                }
            }

            // delete the piece if it wasn't in the list
            if (pieceInStateList == false && currPiece.getColor().equals(color)) {
                Tile pieceTile = board.getTile(currPiece.getLocation());
                pieceTile.setPiece(null);
            }
        }

        return true;
    }

}
