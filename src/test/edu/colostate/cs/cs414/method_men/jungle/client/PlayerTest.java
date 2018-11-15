package edu.colostate.cs.cs414.method_men.jungle.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;

    @Test
    void playerConstructor() {
        player = new Player("red");
        assertEquals(player.color,"red");
    }

    @Test
    void playerConstructor2() {
        player = new Player("red");
        assertTrue(player.pieces!=null);
    }


    @Test
    void getValidPiecesCount() {
        player = new Player("red");
        assertEquals(player.getValidPiecesCount(),8);
    }

    @Test
    void getValidPieces() {
        player = new Player("red");
        assertEquals(player.getValidPieces().length,8);
    }

    @Test
    void retrievePieceByLocation() {
        player = new Player("purple");
        assertThrows(NullPointerException.class, () -> {
            player.retrievePieceByLocation(0,0);
        });
    }

    @Test
    void retrievePieceByLocation2() {
       Game game = new Game();
       assertEquals(game.getPlayer(1).retrievePieceByLocation(0,0),null);
    }

    @Test
    void retrievePieceByLocation3() {
        Game game = new Game();
        assertTrue(game.getPlayer(1).retrievePieceByLocation(8,0)!=null);
    }

    @Test
    void getPiece() {
        player = new Player("red");
        assertTrue(player.getPiece(1)!=null);
    }

    @Test
    void getColor() {
        player = new Player("red");
        assertEquals(player.getColor(),"red");
    }

    @Test
    void isCaptured() {
        player = new Player("red");
        player.isCaptured(1);
        assertEquals(player.getValidPieces().length,7);
    }
}
