package edu.colostate.cs.cs414.method_men.jungle.client;

/**
 * Player class is responsible for which color the player was assigned: red or blue.
 * That's it.
 */
public class Player {
    private String color;
    private String username;

    Player(String color) {
        this.color = color;
    }

    Player(String color, String username) {
        this.color = color;
        this.username = username;
    }

    public String getColor() {
        return this.color;
    }

    public String getUsername(){return this.username;}

}
