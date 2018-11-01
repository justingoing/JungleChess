package edu.colostate.cs.cs414.method_men.jungle.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;

    @Test
    void playerConstructor() {
        player = new Player("Red");
        assertTrue(player.color=="Red");
    }

    @Test
    void playerConstructor2() {
        player = new Player("Red");
        assertTrue(player.pieces!=null);
    }


    @Test
    void getValidPiecesCount() {
        player = new Player("Red");
        assertTrue(player.getValidPiecesCount()==8);
    }

    @Test
    void getValidPieces() {
        player = new Player("Red");
        assertTrue(player.getValidPieces().length==8);
    }

    @Test
    void retrievePieceByLocation() {
        player = new Player("Red");
        assertThrows(NullPointerException.class, () -> {
            player.retrievePieceByLocation(0,0);
        });
    }

    @Test
    void retrievePieceByLocation2() {
       Game game = new Game();
       assertTrue(game.getPlayer(1).retrievePieceByLocation(0,0)==null);
    }

    @Test
    void retrievePieceByLocation3() {
        Game game = new Game();
        assertTrue(game.getPlayer(1).retrievePieceByLocation(8,0)!=null);
    }

    @Test
    void getPiece() {
        player = new Player("Red");
        assertTrue(player.getPiece(1)!=null);
    }

    @Test
    void getColor() {
        player = new Player("Red");
        assertTrue(player.getColor()=="Red");
    }

    @Test
    void isCaptured() {
        player = new Player("Red");
        player.isCaptured(1);
        assertTrue(player.getValidPieces().length==7);
    }
}
