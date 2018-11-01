package edu.colostate.cs.cs414.method_men.jungle.client;

public class Cli {
    public static void main(String[] args) {
        Game game = new Game();
        int winner;
        game.printBoard();

        //Only implements the CLI interface for now.
        while ((winner = game.winnerCheck()) == -1) {
            game.makeMoveCli();
            game.incrementTurn();
            game.printBoard();
        }

        // Prints the Winner statement
        game.whoseTurnIsIt(winner, " is the winner!");
    }
}
