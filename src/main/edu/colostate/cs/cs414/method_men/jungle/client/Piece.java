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

    public boolean isRat() {
        return (this.getName().equals("Rat"));
    }
  
    public String getColor() {
         return this.color;
    }

    //Checks if the piece can move to the location
    //This isValidMove_ will be used for the generic pieces: dog, leopard, wolf, cat
    //It will be overwritten for fancy pieces: lion, rat, tiger, elephant
    public boolean isValidMove_(Tile tile, Location location){
        //1. Check out of bounds
        if (Location.isOutOfBounds(location)){
            return false;
        }

        //2. Is out of move range
        if (!isInRange(location)){
            return false;
        }

        //3. Is it an invalid tile type?
        //Invalid: river, friendly den
        if (tile instanceof River){
            return false;
        }
        //Invalid: friendly den
        else if (tile instanceof Den){
            //If the Den has a color which is not the color of this piece
            if (!((Den)tile).getColor().equals(this.getColor())) {
                return false;
            }
        }

        //4. Check if piece on tile
        Piece q = tile.getPiece();
        if (!(q == null)){
            //If friendly piece
            if (q.getColor().equals(this.getColor())){
                return false;
            }
            //Enemy piece: if higher rank
            if (q.getRank() > this.getRank()){
                return false;
            }
        }

        //It's a valid move!
        return true;
    }

    public boolean isInRange(Location location){
        if (this.location.isAdjacent(location)){
            return true;
        }
        return false;
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
