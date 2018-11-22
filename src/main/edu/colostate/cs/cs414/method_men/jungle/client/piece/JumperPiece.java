package edu.colostate.cs.cs414.method_men.jungle.client.piece;

import edu.colostate.cs.cs414.method_men.jungle.client.Board;
import edu.colostate.cs.cs414.method_men.jungle.client.Location;
import edu.colostate.cs.cs414.method_men.jungle.client.tile.Open;
import edu.colostate.cs.cs414.method_men.jungle.client.tile.River;
import edu.colostate.cs.cs414.method_men.jungle.client.tile.Tile;
import edu.colostate.cs.cs414.method_men.jungle.client.tile.Trap;

import java.util.ArrayList;

public class JumperPiece extends Piece{
    public JumperPiece (String name, int rank, String color) {
        super(name, rank, color);
    }

    @Override
    public boolean isValidMove_(Location end, Board board){
        Tile endTile = board.getTile_(end);

        //Super check
        if (!super.isValidMove_(end, board)){
            return false;
        }

        if (isJumping(end, board)){
            Piece q = endTile.getPiece();
            if (!(q == null)) {
                //If friendly piece
                if (q.getColor().equals(this.getColor())) {
                    return false;
                }

                //if higher rank, but is not on a trap
                else if (q.getRank() > this.getRank()){
                    return false;
                }

            }
            return true;
        }

        //If trying to move more than 1 tile, no jump
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

    //isValidMove helper
    public boolean isJumping(Location end, Board board) {
        //Can't jump 1 Tile away lol
        int distance = Location.getDistance(getLocation(), end);
        String direction = null;

        //If moving 3 or 4 places away, could be jumping.
        //Check that all of the spaces inbetween getLocation and end are river.
        if (distance == 3 || distance == 4) {
            direction = Location.getDirection(getLocation(), end);

            //Check that the next 2 or 3 tiles are river, and that there are no pieces in any of them.
            for (int i = 0; i < distance - 1; i++) {
                Location next;
                //Make new location based on direction
                if (direction.equals("up")) {
                    next = new Location(getLocation().getRow() - i - 1, getLocation().getCol());
                } else if (direction.equals("down")) {
                    next = new Location(getLocation().getRow() + i + 1, getLocation().getCol());
                } else if (direction.equals("left")) {
                    next = new Location(getLocation().getRow(), getLocation().getCol() - i - 1);
                } else if (direction.equals("right")) {
                    next = new Location(getLocation().getRow(), getLocation().getCol() + i + 1);
                } else {
                    return false; //Bad direction, flaw in move definitely.
                }

                //Check that the tile is a river!
                if (!(board.getTile_(next) instanceof River)) {
                    return false;
                }

                //Check that rat is not in the river
                else if (board.getTile_(next).getPiece() instanceof Rat){
                    return false;
                }
            }

            //Check that landing spot is Open tile
            if (!(board.getTile_(end) instanceof Open)) {
                return false;
            }

            //Must be jumping
            return true;
        }

        //Easy, not a valid distance
        return false;
    }

    @Override
    public ArrayList<Location> getAllValidMoves(Board board){
        //TODO: Come up with a more clever way of doing this, if we care
        ArrayList<Location> valid = new ArrayList<>();
        //Look through every location on the board
        for (Location location : board.getBoard_().keySet()){
            //If the jumper can move there
            if (isValidMove_(location,board)){
                //..., add it to valid set.
                valid.add(location);
            }
        }
        return valid;
    }
}