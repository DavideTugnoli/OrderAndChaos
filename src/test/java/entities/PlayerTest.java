package entities;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlayerTest {
    @Test
    void testGetName() {
        Player player = new Player("Alice", PlayerRole.ORDER);
        assertEquals("Alice", player.getName());
    }

    @Test
    void testGetRole() {
        Player player = new Player("Bob", PlayerRole.CHAOS);
        assertEquals(PlayerRole.CHAOS, player.getRole());
    }

    @Test
    void testConstructor() {
        Player player = new Player("Charlie", PlayerRole.ORDER);
        assertNotNull(player);
        assertEquals("Charlie", player.getName());
        assertEquals(PlayerRole.ORDER, player.getRole());
    }
}
