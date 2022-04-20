import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void test_initial_player_is_player1() {
        Game game = new Game(3, 4, 3);

        String expected =
                """
                ····
                ····
                ····
                """;

        assertEquals(Status.ONE_PLAYS, game.getStatus());
        assertEquals(expected, game.toString());
        assertFalse(game.isFinished());
    }

    @Test
    public void test_second_player_is_player2() {
        Game game = new Game(3, 4, 3);

        String expected =
                """
                ····
                ····
                ·1··
                """;

        game.play(1);

        assertEquals(Status.TWO_PLAYS, game.getStatus());
        assertEquals(expected, game.toString());
        assertFalse(game.isFinished());
    }

    @Test
    public void test_third_player_is_player1() {
        Game game = new Game(3, 4, 3);

        String expected =
                """
                ····
                ····
                ·1·2
                """;

        game.play(1);
        game.play(3);

        assertEquals(Status.ONE_PLAYS, game.getStatus());
        assertEquals(expected, game.toString());
        assertFalse(game.isFinished());
    }

    @Test
    public void test_canPlay() {
        Game game = new Game(3, 4, 3);

        String initial =
                """
                212·
                1211
                1212
                """;

        game.loadGame(initial);

        assertFalse(game.canPlayColumn(0));
        assertTrue(game.canPlayColumn(3));
    }

    @Test
    public void test_player1_moves_and_wins() {
        Game game = new Game(3, 4, 3);

        String initial =
                """
                ····
                ·12·
                1212
                """;

        String expected =
                """
                ··1·
                ·12·
                1212
                """;

        game.loadGame(initial);
        game.play(2);

        assertEquals(Status.ONE_HAS_WON, game.getStatus());
        assertEquals(expected, game.toString());
        assertTrue(game.isFinished());
    }


    @Test
    public void test_player2_moves_and_wins() {
        Game game = new Game(3, 4, 3);

        String initial =
                """
                ····
                ·211
                1212
                """;

        String expected =
                """
                ·2··
                ·211
                1212
                """;

        game.loadGame(initial);
        game.play(1);

        assertEquals(Status.TWO_HAS_WON, game.getStatus());
        assertEquals(expected, game.toString());
        assertTrue(game.isFinished());
    }

    @Test
    public void test_draw() {
        Game game = new Game(3, 4, 3);

        String initial =
                """
                212·
                1211
                1212
                """;

        String expected =
                """
                2122
                1211
                1212
                """;

        game.loadGame(initial);
        game.play(3);

        assertEquals(Status.DRAW, game.getStatus());
        assertEquals(expected, game.toString());
        assertTrue(game.isFinished());
    }
}