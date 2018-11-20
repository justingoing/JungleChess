package edu.colostate.cs.cs414.method_men.jungle.client;

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

    public boolean isRat() {
        return (this.getName().equals("Rat"));
    }

    //Checks if the piece can move to the location
    //It will be used for the generic pieces: dog, leopard, wolf, cat
    //It will be overwritten for fancy pieces: lion, rat, tiger, elephant
    public boolean isValidMove_(Tile start, Tile end, Location location){
        //If it's obviously out of bounds or too far away
        if (Location.isOutOfBounds(location) || !isInRange(location)){
            return false;
        }

        //Check friendly den
        if (end instanceof Den){
            //If the Den has a color which is not the color of this piece
            if (((Den)end).getColor().equals(this.getColor())) {
                return false;
            }
        }

        //Invalid: river, friendly den
        if (end instanceof River){
            return false;
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
                //... which is your enemies, and they outrank you
                if (!((Trap) end).getColor().equals(this.getColor()) && q.getRank() > this.getRank()) {
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
