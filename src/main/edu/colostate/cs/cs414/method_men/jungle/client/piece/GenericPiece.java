package edu.colostate.cs.cs414.method_men.jungle.client.piece;

import edu.colostate.cs.cs414.method_men.jungle.client.Board;
import edu.colostate.cs.cs414.method_men.jungle.client.Location;
import edu.colostate.cs.cs414.method_men.jungle.client.tile.River;
import edu.colostate.cs.cs414.method_men.jungle.client.tile.Tile;
import edu.colostate.cs.cs414.method_men.jungle.client.tile.Trap;

public class GenericPiece extends Piece{
    public GenericPiece (String name, int rank, String color) {
        super(name, rank, color);
    }

    @Override
    public boolean isValidMove(Location end, Board board){
        Tile endTile = board.getTile(end);

        //Super check
        if (!super.isValidMove(end, board)){
            return false;
        }

        //If trying to move more than 1 tile
        if (!isInRange(end)){
            return false;
        }

        //If trying to move into a river
        if (endTile instanceof River){
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
                if (!((Trap) endTile).getColor().equals(this.getColor()) && q.getRank() > this.getRank()) {
                    return false;
                }
                //Else, it is your trap and you always outrank
            }

            //if higher rank, but is not on a trap
            else if (q.getRank() > this.getRank()){
                return false;
            }
        }
        //It's a valid move!
        return true;
    }


}
