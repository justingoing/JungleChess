package edu.colostate.cs.cs414.method_men.jungle.client.Game;

import edu.colostate.cs.cs414.method_men.jungle.client.Game.piece.*;

import java.util.ArrayList;

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

        String[] redPiecesStrings = splitString[3].split(":");
        String[] redPieceLocations = redPiecesStrings[1].split("/");

        String[] bluePiecesStrings = splitString[4].split(":");
        String[] bluePieceLocations = bluePiecesStrings[1].split("/");

        ArrayList<Piece> pieces = new ArrayList<Piece>();
        pieces = parsePiecesAndAddToList(pieces, "red", redPieceLocations, game);

        if (pieces == null) {
            return false;
        }

        pieces = parsePiecesAndAddToList(pieces, "blue", bluePieceLocations, game);

        if (pieces == null) {
            return false;
        }

        game.getBoard().setBoard(pieces);

        return true;
    }

    public static ArrayList<Piece> parsePiecesAndAddToList(ArrayList<Piece> pieces, String color, String[] piecesStrings, Game game) {
        for (int currPiecesStringIndex = 0;  currPiecesStringIndex < piecesStrings.length; currPiecesStringIndex++) {
            String[] currPiecesNums = piecesStrings[currPiecesStringIndex].split(",");

            int pieceRank;
            int pieceRow;
            int pieceCol;

            try {
                pieceRank = Integer.parseInt(currPiecesNums[0]);
                pieceRow = Integer.parseInt(currPiecesNums[1]);
                pieceCol = Integer.parseInt(currPiecesNums[2]);
            } catch (NumberFormatException e) {
                return null;
            }

            Piece currPiece = game.getBoard().makePiece(color, pieceRank, new Location(pieceRow, pieceCol));
            pieces.add(currPiece);

        }

        return pieces;
    }

}
