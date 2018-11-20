package edu.colostate.cs.cs414.method_men.jungle.client.piece;

import edu.colostate.cs.cs414.method_men.jungle.client.*;
import edu.colostate.cs.cs414.method_men.jungle.client.tile.Den;
import edu.colostate.cs.cs414.method_men.jungle.client.tile.River;
import edu.colostate.cs.cs414.method_men.jungle.client.tile.Tile;
import edu.colostate.cs.cs414.method_men.jungle.client.tile.Trap;

public class Rat extends Piece {
    public Rat(String color) {
        super("Rat", 1, color);
        if (color.equals("red")) {
            setLocation(2, 0);
        } else if (color.equals("blue")) {
            setLocation(6, 6);
        }
    }

    @Override
    public boolean isValidMove_(Tile start, Tile end, Location location){
        //If it's obviously out of bounds or too far away
        //super.isValidMove_(start, location, end); TODO: Make the super call handle generic rules, less code copies

        if (Location.isOutOfBounds(location) || !isInRange(location)){
            return false;
        }

        //Is it an invalid tile type?
        //Invalid: START ON river, moving onto piece
        if (start instanceof River && !(end.getPiece() == null)){
            return false;
        }

        //Check friendly den
        else if (end instanceof Den){
            //If the Den has a color which is not the color of this piece
            if (((Den)end).getColor().equals(this.getColor())) {
                return false;
            }
        }

        //Check the piece on the adjacent, valid tile
        Piece q = end.getPiece();
        if (!(q == null)){
            //If friendly piece
            if (q.getColor().equals(this.getColor())){
                return false;
            }
            //Enemy piece
            //If you are moving onto a trap
            if (end instanceof Trap) {
                //... which is your enemies, and they are not an elephant
                if (!((Trap) end).getColor().equals(this.getColor()) && !(q instanceof Elephant)) {
                    return false;
                }
                //Else, it is your trap and you always outrank
            }

            //Not an elephant, and not a trap
            else if (!(q instanceof Elephant)){
                return false;
            }
        }

        //It's a valid move!
        return true;
    }
}
