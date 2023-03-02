package consoleui;


import java.io.ByteArrayInputStream;
import java.util.List;

import org.junit.jupiter.api.Test;
import entities.Player;
import entities.PlayerRole;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameConsoleSetupTest {

    @Test
    void testSetupPlayersWithValidInput() {
        // Simulate user input
        String input = "Alice\nBob\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Execute the method under test
        List<Player> players = GameConsoleSetup.setupPlayers();

        // Verify the results
        assertEquals(2, players.size());
        assertEquals("Alice", players.get(0).getName());
        assertEquals(PlayerRole.ORDER, players.get(0).getRole());
        assertEquals("Bob", players.get(1).getName());
        assertEquals(PlayerRole.CHAOS, players.get(1).getRole());
    }

    @ParameterizedTest
    @CsvSource({
            ",Bob",
            "Alice,"
    })
    void testSetupPlayersWithInvalidInput(String player1Name, String player2Name) {
        // Simulate user input
        String input = (player1Name == null ? "" : player1Name) + "\n" + (player2Name == null ? "" : player2Name) + "\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Execute the method under test
        List<Player> players = GameConsoleSetup.setupPlayers();

        // Verify the results
        assertEquals(2, players.size());

        assertEquals(player1Name == null || player1Name.isEmpty() ? "Order Player" : player1Name, players.get(0).getName());
        assertEquals(PlayerRole.ORDER, players.get(0).getRole());

        assertEquals(player2Name == null || player2Name.isEmpty() ? "Chaos Player" : player2Name, players.get(1).getName());
        assertEquals(PlayerRole.CHAOS, players.get(1).getRole());
    }
}

