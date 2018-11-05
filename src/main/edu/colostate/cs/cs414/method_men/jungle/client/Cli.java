package edu.colostate.cs.cs414.method_men.jungle.client;

public class Cli {

    //TODO: Put this in Main.java
    public static void main(String[] args) {
        //GUI gui = new GUI();
        //TODO: Pass Game.java the GUI or CLI ?
        //TODO: Pass Game() to GUI or CLI ?
        Game game = new Game();
        game.printBoard();

        //Only implements the CLI interface for now.
        while (game.winnerCheck() == -1) {
            game.makeMoveCli();
            game.printBoard();
        }
        game.endGame();
    }
}
