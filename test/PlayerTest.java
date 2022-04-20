import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void equals_and_null() {
        Player player = Player.ONE;
        assertFalse(player.isEqualTo(null));
    }

    @Test
    public void equals_different_players() {
        Player player1 = Player.ONE;
        Player player2 = Player.TWO;
        assertFalse(player1.isEqualTo(player2));
        assertFalse(player2.isEqualTo(player1));
    }

    @Test
    public void equals_same_players1() {
        Player playerA = Player.ONE;
        Player playerB = Player.ONE;
        assertTrue(playerA.isEqualTo(playerB));
    }

    @Test
    public void equals_same_players2() {
        Player playerA = Player.TWO;
        Player playerB = Player.TWO;
        assertTrue(playerA.isEqualTo(playerB));
    }

    @Test
    public void is_player() {
        assertTrue(Player.ONE.isOne());
        assertFalse(Player.TWO.isOne());

        assertFalse(Player.ONE.isTwo());
        assertTrue(Player.TWO.isTwo());
    }

    @Test
    public void to_string() {
        Player player = Player.ONE;
        assertEquals("1", player.toString());
    }

    @Test
    public void from_char() {
        Player player1 = Player.fromChar('1');
        Player player2 = Player.fromChar('2');
        Player player3 = Player.fromChar('·');
        assertTrue(player1.isOne());
        assertTrue(player2.isTwo());
        assertNull(player3);
    }
}