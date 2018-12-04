package edu.colostate.cs.cs414.method_men.jungle.client;

import edu.colostate.cs.cs414.method_men.jungle.client.piece.Piece;
import edu.colostate.cs.cs414.method_men.jungle.client.tile.Tile;

import java.util.ArrayList;
import java.util.HashMap;

public class GameState {

    public static String makeGameState(String username, int winner, int turn, int moveCount, ArrayList<Piece> red, ArrayList<Piece> blue){
        String nextTurn;
        if(turn == 0){
            nextTurn = "Red";
        }
        else{
            nextTurn = "Blue";
        }
        //Gotta get username from client somehow
        String sentFrom = "SentFrom:" + username + " ";
        //hardcode to test
        String users = "BluePlayer:zane RedPlayer:steve ";
        String state = sentFrom + users + "Winner:" + winner + " " + "NextTurn:" + nextTurn + " " + "MoveCount:" + moveCount + " ";
        String redPieces = "Red:";
        String bluePieces = "Blue:";
        redPieces += getPieces(red);
        bluePieces += getPieces(blue);
        state += redPieces + " " + bluePieces;
        return state;
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
        String[] nextTurnStrings = splitString[3].split(":");
        int nextTurn;
        if (nextTurnStrings[1].equals("Red")) {
            nextTurn = 1;
        } else if (nextTurnStrings[1].equals("Blue")) {
            nextTurn = 0;
        } else {
            return false;
        }

        // set next turn
        if (game.getTurn() != nextTurn) {
            game.incrementTurn();
        }

        // parse red pieces
        String[] redPiecesStrings = splitString[5].split(":");
        String[] redPieceLocations = redPiecesStrings[1].split("/");

        if (!parseAndSetPieces("red", redPieceLocations, game)) {
            System.out.println("FAILED to set red pieces");
            return false;
        }

        // parse blue pieces
        String[] bluePiecesStrings = splitString[6].split(":");
        String[] bluePiecesLocations = bluePiecesStrings[1].split("/");

        if (!parseAndSetPieces("blue", bluePiecesLocations, game)) {
            System.out.println("FAILED to set blue pieces");
        }


        return true;
    }

    public static boolean parseAndSetPieces(String color, String[] locationStrings, Game game) {
        Board board = game.getBoard();

        ArrayList<Piece> pieces = new ArrayList<Piece>();
        for (Tile tile : board.getBoard().values()) {
            if (tile.getPiece() != null){
                pieces.add(tile.getPiece());
            }
        }

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

            for (int currPieceIndex = 0; currPieceIndex < pieces.size(); currPieceIndex++) {
                Piece currPiece = pieces.get(currPieceIndex);
                if (currPiece.getColor().equals(color) && currPiece.getRank() == pieceRank) {
                    board.move(currPiece, new Location(pieceRow, pieceCol));
                    break;
                }
            }
        }
        return true;
    }

}
