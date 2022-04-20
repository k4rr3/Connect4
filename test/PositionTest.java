import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    @Test
    public void can_get_row_and_column() {
        Position position = new Position(2, 3);
        assertEquals(2, position.getRow());
        assertEquals(3, position.getColumn());
    }

    @Test
    public void equal_positions() {
        Position pos1 = new Position(2, 3);
        Position pos2 = new Position(2, 3);
        assertTrue(pos1.isEqualTo(pos2));
    }

    @Test
    public void non_equal_positions1() {
        Position pos1 = new Position(2, 3);
        Position pos2 = new Position(1, 3);
        assertFalse(pos1.isEqualTo(pos2));
        assertFalse(pos2.isEqualTo(pos1));
    }

    @Test
    public void non_equal_positions2() {
        Position pos1 = new Position(2, 3);
        Position pos2 = new Position(2, 2);
        assertFalse(pos1.isEqualTo(pos2));
        assertFalse(pos2.isEqualTo(pos1));
    }

    @Test
    public void move() {
        Position position = new Position(2, 3);
        Position moved = position.move(Direction.DOWN);
        assertTrue(moved.isEqualTo(new Position(3, 3)));
    }

    @Test
    public void same_pos_path_length() {
        Position pos = new Position(2, 4);
        assertEquals(1, Position.pathLength(pos, pos));
    }

    @Test
    public void horizontal_path_length() {
        Position pos1 = new Position(4, 2);
        Position pos2 = new Position(4, 5);
        assertEquals(4, Position.pathLength(pos1, pos2));
        assertEquals(4, Position.pathLength(pos2, pos1));
    }

    @Test
    public void vertical_path_length() {
        Position pos1 = new Position(2, 4);
        Position pos2 = new Position(5, 4);
        assertEquals(4, Position.pathLength(pos1, pos2));
        assertEquals(4, Position.pathLength(pos2, pos1));
    }

    @Test
    public void main_diagonal_path_length() {
        Position pos1 = new Position(2, 4);
        Position pos2 = new Position(7, 9);
        assertEquals(6, Position.pathLength(pos1, pos2));
        assertEquals(6, Position.pathLength(pos2, pos1));
    }

    @Test
    public void contra_diagonal_path_length() {
        Position pos1 = new Position(7, 4);
        Position pos2 = new Position(3, 8);
        assertEquals(5, Position.pathLength(pos1, pos2));
        assertEquals(5, Position.pathLength(pos2, pos1));
    }
}