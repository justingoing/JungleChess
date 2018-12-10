package edu.colostate.cs.cs414.method_men.jungle.client.Game.tile;

import edu.colostate.cs.cs414.method_men.jungle.client.Game.Board;
import edu.colostate.cs.cs414.method_men.jungle.client.Game.Location;

public class TileFactory {

    /**
     * Create a specific type of Tile (Den, Trap, etc...) based on location.
     * @param location the coordinate of the Tile in the context of the board. Used to determine what type of Tile.
     * @return a specific Tile based on location.
     */
    public static Tile makeTile(Location location, Board board){
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

    /**
     *  makeTile(Location) helpers.
     */
    public static boolean isRedDen(Location location){
        return location.equals(new Location(0, 3));
    }

    public static boolean isBlueDen(Location location){
        return location.equals(new Location(8, 3));
    }

    public static boolean isRedTrap(Location location){
        return location.equals(new Location(0, 2)) ||
                location.equals(new Location(0, 4))||
                location.equals(new Location(1, 3));
    }

    public static boolean isBlueTrap(Location location){
        return location.equals(new Location(8, 2)) ||
                location.equals(new Location(8, 4)) ||
                location.equals(new Location(7, 3));
    }

    public static boolean isRiver(Location location){
        return location.equals(new Location(3, 1)) || location.equals(new Location(3, 4)) ||
                location.equals(new Location(4, 1)) || location.equals(new Location(4, 4)) ||
                location.equals(new Location(5, 1)) || location.equals(new Location(5, 4)) ||
                location.equals(new Location(3, 2)) || location.equals(new Location(3, 5)) ||
                location.equals(new Location(4, 2)) || location.equals(new Location(4, 5)) ||
                location.equals(new Location(5, 2)) || location.equals(new Location(5, 5));
    }


}
