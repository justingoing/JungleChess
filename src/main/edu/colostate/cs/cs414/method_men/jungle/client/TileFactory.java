package edu.colostate.cs.cs414.method_men.jungle.client;

public class TileFactory {
    public Tile makeTile(Location location) {
        //Den
        if (isRedDen(location)){
            return new Den("Red");
        } else if (isBlueDen(location)){
            return new Den("Blue");
        }

        //Trap
        else if (isRedTrap(location)){
            return new Trap("Red");
        }
        else if (isBlueTrap(location)){
            return new Trap("Blue");
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
