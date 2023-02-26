package entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerRoleTest {
    @Test
    void testOrderRole() {
        PlayerRole order = PlayerRole.ORDER;
        assertEquals("ORDER", order.toString());
    }

    @Test
    void testChaosRole() {
        PlayerRole chaos = PlayerRole.CHAOS;
        assertEquals("CHAOS", chaos.toString());
    }

}
