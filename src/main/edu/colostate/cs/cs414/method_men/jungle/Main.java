package edu.colostate.cs.cs414.method_men.jungle;

public class Main {
    public static void main(String args[]) {
        Game game = new Game();
        int winner;
        game.printBoard();

        while ((winner = game.winnerCheck()) == -1) {
            game.makeMove();
            game.incrementTurn();
            game.printBoard();
        }
        
        // Prints the Winner statement
        game.whoseTurnIsIt(winner, " is the winner!");
    }
}
