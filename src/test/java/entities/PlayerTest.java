package entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PlayerTest {
    @Test
    void testConstructor() {
        Player player1 = new Player("Alice", PlayerRole.ORDER);
        assertNotNull(player1);
        assertEquals("Alice", player1.getName());
        assertEquals(PlayerRole.ORDER, player1.getRole());

        Player player2 = new Player("Bob", PlayerRole.CHAOS);
        assertNotNull(player2);
        assertEquals("Bob", player2.getName());
        assertEquals(PlayerRole.CHAOS, player2.getRole());
    }
}
