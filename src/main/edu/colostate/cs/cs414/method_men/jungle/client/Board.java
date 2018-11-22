package edu.colostate.cs.cs414.method_men.jungle.client;

import edu.colostate.cs.cs414.method_men.jungle.client.piece.*;
import edu.colostate.cs.cs414.method_men.jungle.client.tile.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Board {
    private HashMap<Location, Tile> board;

    public Board() {
        this.board = new HashMap<>();
        setBoard();
    }

    /**
     * Iterates through each row, then each column, and
     * instantiates each Tile (subtype) based off the location
     */
    public void setBoard() {
        int HEIGHT = 9;
        int WIDTH = 7;
        for (int row = 0; row < HEIGHT; ++row) {
            for (int col = 0; col < WIDTH; ++col) {
                Location location = new Location(row, col);
                Piece piece = makePiece(location);
                Tile tile = makeTile(location);
                tile.setPiece(piece); //If there is a piece (i.e., (0,0) gets a Lion), set it on the Tile
                board.put(location, tile);
            }
        }
    }

    public HashMap<Location, Tile> getBoard() {
        return board;
    }

    //TODO: Tile Factory
    public Tile makeTile(Location location){
        //Den
        if (isRedDen(location)){
            return new Den("red");
        } else if (isBlueDen(location)){
            return new Den("blue");
        }

        //Trap
        else if (isRedTrap(location)){
            return new Trap("red");
        }
        else if (isBlueTrap(location)){
            return new Trap("blue");
        }

        //River
        else if (isRiver(location)){
            return new River();
        }

        //Open
        else {
            return new Open();
        }
    }

    //TODO: Piece factory
    public Piece makePiece(Location location){
        //Lion
        if (location.equals(new Location(0, 0))){
            return new Lion("red");
        } else if (location.equals(new Location(8, 6))){
            return new Lion("blue");
        }

        //Rat
        else if (location.equals(new Location(2, 0))){
            return new Rat("red");
        } else if (location.equals(new Location(6, 6))){
            return new Rat("blue");
        }

        //Dog
        else if (location.equals(new Location(1, 1))){
            return new Dog("red");
        } else if (location.equals(new Location(7, 5))){
            return new Dog("blue");
        }

        //Leopard
        else if (location.equals(new Location(2, 2))){
            return new Leopard("red");
        } else if (location.equals(new Location(6, 4))){
            return new Leopard("blue");
        }

        //Wolf
        else if (location.equals(new Location(2, 4))){
            return new Wolf("red");
        } else if (location.equals(new Location(6, 2))){
            return new Wolf("blue");
        }

        //Cat
        else if (location.equals(new Location(1, 5))){
            return new Cat("red");
        } else if (location.equals(new Location(7, 1))){
            return new Cat("blue");
        }

        //Tiger
        else if (location.equals(new Location(0, 6))){
            return new Tiger("red");
        } else if (location.equals(new Location(8, 0))){
            return new Tiger("blue");
        }

        //Elephant
        else if (location.equals(new Location(2, 6))){
            return new Elephant("red");
        } else if (location.equals(new Location(6, 0))){
            return new Elephant("blue");
        }
        return null;
    }

    //Takes a piece and moves it to the new location
    public void move(Piece piece, Location location){
        //Pick up the piece
        board.get(piece.getLocation()).setPiece(null);

        //Set it down
        board.get(location).setPiece(piece);
        piece.setLocation(location);
    }

    /**
     * Returns a tile on the board
     * @param row horizontal location on board
     * @param col vertical location on board
     * @return requested tile
     */
    public Tile getTile(int row, int col) {
        return getTile(new Location(row, col));
    }

    public Tile getTile(Location loc) {
        return board.get(loc);
    }

    public ArrayList<Piece> getPieces(String color){
        ArrayList<Piece> bluePieces = new ArrayList<>();
        //Look through every tile on the board
        for (Tile tile : board.values()){
            //If the tile has a piece on it
            if (tile.getPiece() != null){
                //..., and that piece is the right color
                if (tile.getPiece().getColor().equals(color)){
                    bluePieces.add(tile.getPiece());
                }
            }
        }
        return bluePieces;
    }

    /**
     *  makeTile(Location) helpers
     */
    private boolean isRedDen(Location location){
        if (location.equals(new Location(0, 3))){
            return true;
        }
        return false;
    }

    private boolean isBlueDen(Location location){
        if (location.equals(new Location(8, 3))){
            return true;
        }
        return false;
    }

    private boolean isRedTrap(Location location){
        if (location.equals(new Location(0, 2)) ||
                location.equals(new Location(0, 4))||
                location.equals(new Location(1, 3))){
            return true;
        }
        return false;
    }

    private boolean isBlueTrap(Location location){
        if (location.equals(new Location(8, 2)) ||
                location.equals(new Location(8, 4)) ||
                location.equals(new Location(7, 3))){
            return true;
        }
        return false;
    }

    private boolean isRiver(Location location){
        if (location.equals(new Location(3, 1)) || location.equals(new Location(3, 4)) ||
                location.equals(new Location(4, 1)) || location.equals(new Location(4, 4)) ||
                location.equals(new Location(5, 1)) || location.equals(new Location(5, 4)) ||
                location.equals(new Location(3, 2)) || location.equals(new Location(3, 5)) ||
                location.equals(new Location(4, 2)) || location.equals(new Location(4, 5)) ||
                location.equals(new Location(5, 2)) || location.equals(new Location(5, 5))){
            return true;
        }
        return false;
    }

}
