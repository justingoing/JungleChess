package client.edu.colostate.cs.cs414.method_men.jungle;

import java.util.Scanner;

class Tile {
    private char attribute;

    public Tile(char attribute) {
        this.attribute = attribute;
    }

    public char getAttribute() {
        return attribute;
    }
}

// TODO: Feel free to  change the way the board appears
class Open extends Tile {
    public Open() {
        super('.');
    }
}

class Trap extends Tile {
    public Trap() {
        super('T');
    }
}

class Jump extends Tile {
    public Jump() {
        super('.');
    }
}

class River extends Tile {
    public River() {
        super('~');
    }
}

class Den extends Tile {
    public Den() {
        super('D');
    }
}

class Board {
    private final int height = 9;
    private final int width = 7;
    Tile[][] board;

    public Board() {
        board = new Tile[height][width];
        makeBoard();
    }

    public boolean isDen(int i, int j) {
        if (j == 3) {
            if (i == 0 || i == 8) {
                return true;
            }
        }
        return false;
    }

    public boolean isTrap(int i, int j) {
        if ((j == 2 || j == 4) && (i == 0 || i == 8)) {
            return true;
        } else if ((j == 3) && (i == 1 || i == 7)) {
            return true;
        }
        return false;
    }

    public boolean isRiver(int i, int j) {
        if (i >= 3 && i <= 5) {
            if (j == 1 || j == 2 || j == 4 || j == 5) {
                return true;
            }
        }
        return false;
    }

    public boolean isJump(int i, int j) {
        //if there is a River Tile above, below, to left, or to right
        if (isRiver(i+1, j) || isRiver(i-1, j) || isRiver(i, j+1) || isRiver(i, j-1)) {
            return true;
        }
        //if (i == 2 || i == 6) {
        //	if (j >= 1 && j <= 2) {
        //		return true;
        //    } else if (j >= 4 && j <= 5) {
        //		return true;
        //    }
        //} else if (i >= 3 && i <= 5) {
        //	if (j == 0 || j == 3 || j == 6) {
        //		return true;
        //	}
        //}
        return false;
    }

    public Tile makeInstance(int i, int j) {
        if (isDen(i, j)) {
            return new Den();
        } else if (isTrap(i, j)) {
            return new Trap();
        } else if (isRiver(i, j)) {
            return new River();
        } else if (isJump(i, j)) {
            return new Jump();
        } else {
            return new Open();
        }
    }

    public void makeBoard() {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                board[i][j] = makeInstance(i, j);
            }
        }
    }

    public void placePieces(char[][] draw, Player[] players) {
        for (Player player : players) {
            for (Piece piece : player.getPieces()) {
                draw[piece.getRow()][piece.getCol()] = (char) (piece.getRank() + '0');
            }
        }
    }

    //For crude implementation ONLY
    public void printBoard(Player[] players) {
        char[][] draw = new char[height][width];
        placePieces(draw, players);

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (draw[i][j] == '\0') {
                    draw[i][j] = board[i][j].getAttribute();
                }
            }
        }

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (j != width - 1) {
                    System.out.print(draw[i][j] + " ");
                } else {
                    System.out.println(draw[i][j]);
                }
            }
        }
    }
}


class Piece {
    private String name;
    private int rank;
    String color;
    private int row;
    private int col;

    public Piece(String name, int rank, String color) {
        this.name = name;
        this.rank = rank;
        this.color = color;
    }

    public void setLocation(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int[] getLocation() {
        int[] loc = new int[2];
        loc[0] = this.row;
        loc[1] = this.col;
        return loc;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public String getName() {
        return this.name;
    }

    public int getRank() {
        return this.rank;
    }
}

class Rat extends Piece {
    public Rat(String color) {
        super("Rat", 1, color);
        if (color.equals("white")) {
            setLocation(2, 0);
        } else if (color.equals("black")) {
            setLocation(6, 6);
        }
    }
}

class Cat extends Piece {
    public Cat(String color) {
        super("Cat", 2, color);
        if (color.equals("white")) {
            setLocation(1, 5);
        } else if (color.equals("black")) {
            setLocation(7, 1);
        }
    }
}

class Wolf extends Piece {
    public Wolf(String color) {
        super("Wolf", 3, color);
        if (color.equals("white")) {
            setLocation(2, 4);
        } else if (color.equals("black")) {
            setLocation(6, 2);
        }
    }
}

class Dog extends Piece {
    public Dog(String color) {
        super("Dog", 4, color);
        if (color.equals("white")) {
            setLocation(1, 1);
        } else if (color.equals("black")) {
            setLocation(7, 5);
        }
    }
}

class Leopard extends Piece {
    public Leopard(String color) {
        super("Leopard", 5, color);
        if (color.equals("white")) {
            setLocation(2, 2);
        } else if (color.equals("black")) {
            setLocation(6, 4);
        }
    }
}

class Tiger extends Piece {
    public Tiger(String color) {
        super("Tiger", 6, color);
        if (color.equals("white")) {
            setLocation(0, 6);
        } else if (color.equals("black")) {
            setLocation(8, 0);
        }
    }
}

class Lion extends Piece {
    public Lion(String color) {
        super("Lion", 7, color);
        if (color.equals("white")) {
            setLocation(0, 0);
        } else if (color.equals("black")) {
            setLocation(8, 6);
        }
    }
}

class Elephant extends Piece {
    public Elephant(String color) {
        super("Elephant", 8, color);
        if (color.equals("white")) {
            setLocation(2, 6);
        } else if (color.equals("black")) {
            setLocation(6, 0);
        }
    }
}


class Player {
    String color;
    Piece[] pieces;

    public Player(String color) {
        this.color = color;
        pieces = new Piece[8];
        makePieceInstances();
    }

    public void makePieceInstances() {
        pieces[0] = new Rat(color);
        pieces[1] = new Cat(color);
        pieces[2] = new Wolf(color);
        pieces[3] = new Dog(color);
        pieces[4] = new Leopard(color);
        pieces[5] = new Tiger(color);
        pieces[6] = new Lion(color);
        pieces[7] = new Elephant(color);
    }

    public Piece[] getPieces() {
        return this.pieces;
    }

    public Piece getPiece(int pieceRank) {
        return this.pieces[pieceRank];
    }

    public String getColor() {
        return this.color;
    }
}


class Game {
    private Player[] players;
    private int turn;
    private Board board;

    public Game () {
        players = new Player[2];
        players[0] = new Player("white");
        players[1] = new Player("black");
        turn = 0; // white makes first move
        board = new Board();
    }

    //For crude implementation ONLY
    public void printBoard() {
        this.board.printBoard(players);
    }

    //For crude implementation ONLY
    public int[] getDirection(Piece p, char direction) {
        int[] rc = new int[2];
        rc[0] = p.getRow();
        rc[1] = p.getCol();
        System.out.println("from (" + rc[0] + "," + rc[1] + ")");

        switch (direction) {
            case 'd': ++rc[0]; // TODO: doesn't work
            case 'u': --rc[0];
            case 'r': ++rc[1]; // TODO: doesn't work
            case 'l': --rc[1];
            default: System.out.println("** " + direction + " is not a direction **");
        }
        System.out.println("to (" + rc[0] + "," + rc[1] + ")");
        return rc;
    }

    //For crude implementation ONLY
    public void makeMove() {
        Scanner sc = new Scanner(System.in);
        int[] nextLocation;
        int pieceRank;
        Piece piece;
        boolean isValid;

        // no need to validate the inputs, as we'll be upgrading this with mouse clicks
        do {
            System.out.println("\tPlayer " + players[turn].getColor() + "'s turn");
            System.out.println("What Piece number do you choose? ");
            System.out.println("A piece can be selected by it's rank. '1' for Rat, '2' for Cat, etc");
            pieceRank = sc.nextInt();
            piece = players[turn].getPiece(pieceRank - 1);

            System.out.println("Which direction do you want to move " + piece.getName() + "? ");
            System.out.println("Directions can be 'u', 'd', 'l', or 'r'");
            char direction = Character.toLowerCase(sc.next().charAt(0));
            nextLocation = getDirection(piece, direction);
            isValid = verifyValid(piece, nextLocation[0], nextLocation[1]);
        } while (!isValid);

        //move piece
        players[turn].getPiece(pieceRank - 1).setLocation(nextLocation[0], nextLocation[1]);

        // TODO: if you capture an enemy piece
//        if (you captured an enemy piece) {
//            Player e = players[(turn + 1) % 2];
//            e.setPiece(e.getPiece(nextLocation)) = null;
//        }
        System.out.println("Moved piece " + piece.getName() + " to (" + piece.getRow() + "," + piece.getCol() + ")\n");
    }

    public Player getPlayer(int whichPlayer) {
        return players[whichPlayer];
    }

    public void incrementTurn() {
        turn = (turn + 1) % 2;
    }

    public boolean verifyValid(Piece p, int i, int j) {
        // TODO: all following comments
//        check Tile for river
//        check piece for rank
//        check piece for 'special rules'
//        check Tile for friendly piece = false
//        check Tile for enemy piece (isTrap==F && (p.rank < ePiece.rank))
        return true;
    }

    public boolean isDen(int whichPlayer, int[] location) {
        if (whichPlayer == 0) { //white
            if (location[0] == 0 && location[1] == 3) {
                return true;
            }
        } else {
            if (location[0] == 8 && location[1] == 3) {
                return true;
            }
        }
        return false;
    }

    public int winnerCheck() {
        int count;
        // iterate through both players
        for (int i = 0; i < 2; ++i) {
            count = 0;
            // iterate through the players' pieces
            for (Piece piece : players[i].getPieces()) {
                // if one is at the enemy den
                if (isDen(i, piece.getLocation())) {
                    return i;
                }
                // if piece hasn't been captured
                if (piece != null) {
                    ++count;
                }
            }
            // if this player doesn't have any remaining Pieces
            if (count == 0) {
                return (i + 1) % 2; // the other Player is the winner
            }
        }

        return -1; // no winner
    }
}



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

        Player w = game.getPlayer(winner);
        System.out.println("Player " + w.getColor() + "is the winner!");
    }
}
