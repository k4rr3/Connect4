import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DirectionTest {

    @Test
    public void test_can_get_components() {
        Direction right = Direction.RIGHT;
        assertEquals(0, right.getChangeInRow());
        assertEquals(1, right.getChangeInColumn());
    }

    @Test
    public void test_invert_right() {
        Direction left = Direction.RIGHT.invert();
        assertEquals(0, left.getChangeInRow());
        assertEquals(-1, left.getChangeInColumn());
    }

    @Test
    public void test_invert_down() {
        Direction up = Direction.DOWN.invert();
        assertEquals(-1, up.getChangeInRow());
        assertEquals(0, up.getChangeInColumn());
    }
}