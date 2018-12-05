package edu.colostate.cs.cs414.method_men.jungle.client;

import edu.colostate.cs.cs414.method_men.jungle.client.piece.*;
import edu.colostate.cs.cs414.method_men.jungle.client.tile.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GameState {

    public static String makeGameState(String username, int winner, int turn, int moveCount, ArrayList<Piece> red, ArrayList<Piece> blue, Long ID){
        String nextTurn;
        if(turn == 0){
            nextTurn = "Red";
        }
        else{
            nextTurn = "Blue";
        }
        //Gotta get username from client somehow
        //String sentFrom = "SentFrom:" + username + " ";
        //hardcode to test
        //String users = "BluePlayer:zane RedPlayer:steve ";

        //
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
        int nextTurn = 0;
        if (nextTurnStrings[1].equals("Red")) {
            nextTurn = 1;
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
    public static ArrayList<Piece> setGameState(String gameState, Game game) {
        String[] splitString = gameState.split(" ");

        // TODO we need to do something with the data about winner when the game is won
        // there isn't any way to do anything we can do with it right now because it only checks the winner by the locations of the pieces

        // Parse next turn
        String[] nextTurnStrings = splitString[1].split(":");
        int nextTurn;
        if (nextTurnStrings[1].equals("Red")) {
            nextTurn = 1;
        } else if (nextTurnStrings[1].equals("Blue")) {
            nextTurn = 0;
        }

        // parse red pieces
        String[] redPiecesStrings = splitString[3].split(":");
        String[] redPieceLocations = redPiecesStrings[1].split("/");
        ArrayList<Piece> red = createPiecesArray("red", redPieceLocations);
        /*
        if (!parseAndSetPieces("red", redPieceLocations, game)) {
            System.out.println("FAILED to set red pieces");
            return false;
        }
        */
        // parse blue pieces
        String[] bluePiecesStrings = splitString[4].split(":");
        String[] bluePiecesLocations = bluePiecesStrings[1].split("/");
        ArrayList<Piece> blue = createPiecesArray("blue", bluePiecesLocations);

        /*
        if (!parseAndSetPieces("blue", bluePiecesLocations, game)) {
            System.out.println("FAILED to set blue pieces");
        }
        */
        red.addAll(blue);
        //System.out.println(red.toString());
        return red;
    }

    public static ArrayList<Piece> createPiecesArray(String color, String[] pieceLocations){
        ArrayList<Piece> pieces = new ArrayList<Piece>();
        for (int i = 0; i < pieceLocations.length; i++) {
            String[] currStringStrings = pieceLocations[i].split(",");

            int pieceRank = Integer.parseInt(currStringStrings[0]);
            int pieceRow = Integer.parseInt(currStringStrings[1]);
            int pieceCol = Integer.parseInt(currStringStrings[2]);

            if(pieceRank == 1){
                Rat r = new Rat(color, pieceRow, pieceCol);
                pieces.add(r);
            }
            if(pieceRank == 2){
                Cat c = new Cat(color, pieceRow, pieceCol);
                pieces.add(c);
            }
            if(pieceRank == 3){
                Wolf w = new Wolf(color, pieceRow, pieceCol);
                pieces.add(w);
            }
            if(pieceRank == 4){
                Dog d = new Dog(color, pieceRow, pieceCol);
                pieces.add(d);
            }
            if(pieceRank == 5){
                Leopard l = new Leopard(color, pieceRow, pieceCol);
                pieces.add(l);
            }
            if(pieceRank == 6){
                Tiger t = new Tiger(color, pieceRow, pieceCol);
                pieces.add(t);
            }
            if(pieceRank == 7){
                Lion l = new Lion(color, pieceRow, pieceCol);
                pieces.add(l);
            }
            if(pieceRank == 8){
                Elephant e = new Elephant(color, pieceRow, pieceCol);
                pieces.add(e);
            }
        }
        return pieces;
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
