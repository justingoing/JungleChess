package edu.colostate.cs.cs414.method_men.jungle.client.piece;

import edu.colostate.cs.cs414.method_men.jungle.client.Board;
import edu.colostate.cs.cs414.method_men.jungle.client.Location;
import edu.colostate.cs.cs414.method_men.jungle.client.tile.River;
import edu.colostate.cs.cs414.method_men.jungle.client.tile.Tile;
import edu.colostate.cs.cs414.method_men.jungle.client.tile.Trap;

public class Elephant extends GenericPiece {
    public Elephant(String color) {
        super("Elephant", 8, color);
        if (color.equals("red")) {
            setLocation(2, 6);
        } else if (color.equals("blue")) {
            setLocation(6, 0);
        }
    }

    @Override
    public boolean isValidMove_(Location end, Board board) {
        Tile endTile = board.getTile_(end);

        //If it's obviously out of bounds or too far away
        if (!super.isValidMove_(end, board)) {
            return false;
        }

        //Check the piece on the adjacent, valid tile
        Piece q = endTile.getPiece();
        if (!(q == null)){
            //If friendly piece
            if (q.getColor().equals(this.getColor())){
                return false;
            }
            //Enemy piece
            //If you are moving onto a trap
            if (endTile instanceof Trap) {
                //... which is your enemies, and they outrank you
                if (!((Trap) endTile).getColor().equals(this.getColor()) && (q instanceof Rat)) {
                    return false;
                }
                //Else, it is your trap and you always outrank
            }

            //Can't kill a rat!
            else if (q instanceof Rat){
                return false;
            }
        }

        //It's a valid move!
        return true;
    }
}
