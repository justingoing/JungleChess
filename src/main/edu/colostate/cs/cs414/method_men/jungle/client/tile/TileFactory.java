package edu.colostate.cs.cs414.method_men.jungle.client.tile;

import edu.colostate.cs.cs414.method_men.jungle.client.Board;
import edu.colostate.cs.cs414.method_men.jungle.client.Location;

public class TileFactory {

    /**
     * Create a specific type of Tile (Den, Trap, etc...) based on location.
     * @param location the coordinate of the Tile in the context of the board. Used to determine what type of Tile.
     * @return a specific Tile based on location.
     */
    public static Tile makeTile(Location location, Board board){
        //Den
        if (board.isRedDen(location)){
            return new Den("red");
        } else if (board.isBlueDen(location)){
            return new Den("blue");
        }

        //Trap
        else if (board.isRedTrap(location)){
            return new Trap("red");
        }
        else if (board.isBlueTrap(location)){
            return new Trap("blue");
        }

        //River
        else if (board.isRiver(location)){
            return new River();
        }

        //Open
        else {
            return new Open();
        }
    }

}
