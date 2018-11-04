package edu.colostate.cs.cs414.method_men.jungle.client;

public class PieceFactory {

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
        } else if (location.equals(new Location(8, 5))){
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
        else if (location.equals(new Location(2, 7))){
            return new Elephant("red");
        } else if (location.equals(new Location(6, 0))){
            return new Elephant("blue");
        }
        return null; //TODO: Throw error instead? Whats the best way to handle this?
    }



}
