package edu.colostate.cs.cs414.method_men.jungle;

public class Main {
    public static void main(String args[]) {
        Game game = new Game();
        int winner;
        game.printBoard();

        while ((winner = game.winnerCheck()) == -1) {
            System.out.println("'white' @ top && 'black' @ bottom");
            game.makeMove();
            game.incrementTurn();
            game.printBoard();

        }

        Player w = game.getPlayer(winner);
        System.out.println("'white' @ top && 'black' @ bottom");
        System.out.println("Player " + w.getColor() + " is the winner!");
    }
}
