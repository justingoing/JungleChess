package edu.colostate.cs.cs414.method_men.jungle.client.piece;

import edu.colostate.cs.cs414.method_men.jungle.client.*;
import edu.colostate.cs.cs414.method_men.jungle.client.tile.Den;
import edu.colostate.cs.cs414.method_men.jungle.client.tile.Tile;

import java.util.ArrayList;

public class Piece {
    private String name;
    private int rank;
    private String color;
    private Location location;

    public Piece(String name, int rank, String color) {
        this.name = name;
        this.rank = rank;
        this.color = color;
    }

    public void setLocation(int row, int col) {
        this.location = new Location(row, col);
    }

    public void setLocation(Location loc) {
        this.location = new Location(loc);
    }

    public Location getLocation() {
        return this.location;
    }

    public int getRow() {
        return this.location.getRow();
    }

    public int getCol() {
        return this.location.getCol();
    }

    public String getName() {
        return this.name;
    }

    public int getRank() {
        return this.rank;
    }

    public String getColor() {
        return this.color;
    }

    //Takes a board state, and returns the valid moves for this piece.
    public ArrayList<Location> getAllValidMoves(Board board){
        ArrayList<Location> adjacent = Location.getAdjacent(getLocation());
        ArrayList<Location> valid = new ArrayList<>();


        //Look at each adjacent move
        for (int i = 0 ; i < adjacent.size() ; i++){
            //..., if the move is valid
            if (isValidMove(adjacent.get(i), board)){
                //... then add it to list of valid moves.
                valid.add(adjacent.get(i));
                System.out.println(adjacent.get(i));
            }
        }
        //Return all the valid moves
        return valid;
    }

    //Checks if the piece can move to the location
    //It will be used for the generic pieces: dog, leopard, wolf, cat
    //It will be overwritten for fancy pieces: lion, rat, tiger, elephant
    public boolean isValidMove(Location end, Board board){
        Tile endTile = board.getTile(end);

        //If it's obviously out of bounds or too far away
        if (Location.isOutOfBounds(end)){
            return false;
        }

        //If trying to move onto friendly den
        if (endTile instanceof Den){
            //If the Den has a color which is not the color of this piece
            if (((Den)endTile).getColor().equals(this.getColor())) {
                return false;
            }
        }

        //It's a valid move!
        return true;
    }

    public boolean isInRange(Location location){
        return this.location.isAdjacent(location);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Piece piece = (Piece) o;

        if (rank != piece.rank) return false;
        if (name != null ? !name.equals(piece.name) : piece.name != null) return false;
        return color != null ? color.equals(piece.color) : piece.color == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + rank;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }
}
