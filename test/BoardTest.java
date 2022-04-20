import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void test_empty_board() {
        Board board = new Board(3, 4);
        String expected =
                """
                ····
                ····
                ····
                """;
        assertEquals(expected, board.toString());
    }

    @Test
    public void test_roundtrip() {
        Board board = new Board(3, 4);
        String expected =
                """
                ·1··
                22··
                112·
                """;
        board.loadBoard(expected);
        assertEquals(expected, board.toString());
    }

    @Test
    public void test_canPlay() {
        Board board = new Board(3, 4);
        String initial =
                """
                ·1··
                22··
                112·
                """;
        board.loadBoard(initial);

        assertFalse(board.canPlayColumn(-1));

        assertTrue(board.canPlayColumn(0));
        assertFalse(board.canPlayColumn(1));
        assertTrue(board.canPlayColumn(2));
        assertTrue(board.canPlayColumn(3));

        assertFalse(board.canPlayColumn(4));

        assertTrue(board.hasValidMoves());
    }

    @Test
    public void test_lastEmptyRow() {
        Board board = new Board(3, 4);
        String initial =
                """
                ·1··
                22··
                112·
                """;
        board.loadBoard(initial);

        assertEquals(-1, board.lastEmptyRow(-1));

        assertEquals(0, board.lastEmptyRow(0));
        assertEquals(-1, board.lastEmptyRow(1));
        assertEquals(1, board.lastEmptyRow(2));
        assertEquals(2, board.lastEmptyRow(3));

        assertEquals(-1, board.lastEmptyRow(4));
    }

    @Test
    public void test_invalid_play() {
        Board board = new Board(3, 4);
        String initial =
                """
                ·1··
                22··
                112·
                """;
        board.loadBoard(initial);

        assertNull(board.play(1, Player.ONE));
    }

    @Test
    public void test_valid_play() {
        Board board = new Board(3, 4);
        String initial =
                """
                ·1··
                22··
                112·
                """;
        board.loadBoard(initial);

        Position expectedPos = new Position(1, 2);
        String expectedBoard =
                """
                ·1··
                221·
                112·
                """;

        Position position = board.play(2, Player.ONE);

        assertTrue(expectedPos.isEqualTo(position));
        assertEquals(expectedBoard, board.toString());
    }

    @Test
    public void test_sequence_valid_plays() {
        Board board = new Board(3, 4);
        String initial =
                """
                ·1··
                22··
                112·
                """;
        board.loadBoard(initial);

        String expectedBoard =
                """
                2112
                2211
                1122
                """;

        board.play(2, Player.ONE);
        board.play(3, Player.TWO);
        board.play(3, Player.ONE);
        board.play(0, Player.TWO);
        board.play(2, Player.ONE);
        board.play(3, Player.TWO);

        assertEquals(expectedBoard, board.toString());
        assertFalse(board.hasValidMoves());
    }

    @Test
    public void test_max_connected() {
        Board board = new Board(3, 4);
        String initial =
                """
                2112
                1211
                1112
                """;
        board.loadBoard(initial);

        assertEquals(2, board.maxConnected(new Position(0, 0)));
        assertEquals(2, board.maxConnected(new Position(0, 1)));
        assertEquals(3, board.maxConnected(new Position(0, 2)));
        assertEquals(1, board.maxConnected(new Position(0, 3)));

        assertEquals(2, board.maxConnected(new Position(1, 0)));
        assertEquals(2, board.maxConnected(new Position(1, 1)));
        assertEquals(3, board.maxConnected(new Position(1, 2)));
        assertEquals(2, board.maxConnected(new Position(1, 3)));

        assertEquals(3, board.maxConnected(new Position(2, 0)));
        assertEquals(3, board.maxConnected(new Position(2, 1)));
        assertEquals(3, board.maxConnected(new Position(2, 2)));
        assertEquals(1, board.maxConnected(new Position(2, 3)));
    }
    @Test
    public void test_sequence_max_connected() {
        Board board = new Board(3, 4);
        String initial =
                """
                ·1··
                22··
                112·
                """;
        board.loadBoard(initial);

        Position pos1 = board.play(3, Player.ONE);
        assertEquals(1, board.maxConnected(pos1));

        Position pos2 = board.play(0, Player.TWO);
        assertEquals(3, board.maxConnected(pos2));

        Position pos3 = board.play(2, Player.ONE);
        assertEquals(3, board.maxConnected(pos3));
    }
}