package edu.colostate.cs.cs414.method_men.jungle.client;

public class Cli {
    public static void main(String[] args) {
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
