package edu.colostate.cs.cs414.method_men.jungle.client;

import edu.colostate.cs.cs414.method_men.jungle.client.piece.Piece;

import java.util.ArrayList;

public class GameState {

    public static String makeGameState(int winner, int turn, int moveCount, ArrayList<Piece> red, ArrayList<Piece> blue){
        String nextTurn;
        if(turn == 0){
            nextTurn = "Red";
        }
        else{
            nextTurn = "Blue";
        }

        String state = "Winner:" + winner + " " + "NextTurn:" + nextTurn + " " + "MoveCount:" + moveCount + " ";
        String redPieces = "Red: ";
        String bluePieces = "Blue: ";
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
            pieces += rank + ":" + row + ":" + col + " ";
        }
        return pieces;
    }

}
