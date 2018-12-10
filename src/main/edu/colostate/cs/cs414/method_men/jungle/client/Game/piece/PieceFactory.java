package edu.colostate.cs.cs414.method_men.jungle.client.piece;

import edu.colostate.cs.cs414.method_men.jungle.client.Location;

public class PieceFactory {
    /**
     * Create a specific type of Piece (Lion, Elephant, etc...) based on location.
     * @param location the coordinate of the Piece in the context of the board. Used to determine what type of Piece.
     * @return a specific Piece based on location.
     */
    public static Piece makePiece(Location location){
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

    public static Piece makePiece(String color, int rank, Location location){
        switch (rank) {
            case 8:
                return new Elephant(color, location);
            //Lion
            case 7:     return new Lion(color, location);
            //Tiger
            case 6:
                return new Tiger(color, location);
            //Leopard
            case 5:
                return new Leopard(color, location);
            //Dog
            case 4:
                return new Dog(color, location);
            //Wolf
            case 3:
                return new Wolf(color, location);
            //Cat
            case 2:
                return new Cat(color, location);
            //Rat
            case 1:
                return new Rat(color, location);
            default:
                return null;
        }
    }

}
