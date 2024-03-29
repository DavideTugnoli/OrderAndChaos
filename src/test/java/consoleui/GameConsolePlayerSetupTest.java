package consoleui;


import java.io.ByteArrayInputStream;
import java.util.List;

import org.junit.jupiter.api.Test;
import entities.Player;
import entities.PlayerRole;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameConsolePlayerSetupTest {
    @Test
    void testSetupPlayersWithValidInput() {
        // Simulate user input
        String input = "Alice\nBob\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Execute the method under test
        List<Player> players = GameConsolePlayerSetup.setupPlayers(false);

        // Verify the results
        assertEquals(2, players.size());
        assertEquals("Alice", players.get(0).getName());
        assertEquals(PlayerRole.ORDER, players.get(0).getRole());
        assertEquals("Bob", players.get(1).getName());
        assertEquals(PlayerRole.CHAOS, players.get(1).getRole());
    }

    @Test
    void testSetupPlayersWithValidInputTruncated() {
        // Simulate user input
        String input = "MassimilianoBarpFortissimo\nBob\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Execute the method under test
        List<Player> players = GameConsolePlayerSetup.setupPlayers(false);

        // Verify the results
        assertEquals(2, players.size());
        assertEquals("MassimilianoBar...", players.get(0).getName());
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
        List<Player> players = GameConsolePlayerSetup.setupPlayers(false);

        // Verify the results
        assertEquals(2, players.size());

        assertEquals(player1Name == null || player1Name.isEmpty() ? "Order" : player1Name, players.get(0).getName());
        assertEquals(PlayerRole.ORDER, players.get(0).getRole());

        assertEquals(player2Name == null || player2Name.isEmpty() ? "Chaos" : player2Name, players.get(1).getName());
        assertEquals(PlayerRole.CHAOS, players.get(1).getRole());
    }

    @Test
    void testSetupPlayersWithEmptyInput() {
        // Simulate user input
        String input = "\n\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Execute the method under test
        List<Player> players = GameConsolePlayerSetup.setupPlayers(false);

        // Verify the results
        assertEquals(2, players.size());
        assertEquals("Order", players.get(0).getName());
        assertEquals(PlayerRole.ORDER, players.get(0).getRole());
        assertEquals("Chaos", players.get(1).getName());
        assertEquals(PlayerRole.CHAOS, players.get(1).getRole());
    }

    @Test
    void testSetupSinglePlayer() {
        // Simulate user input
        String input = "Alice\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Execute the method under test
        List<Player> players = GameConsolePlayerSetup.setupPlayers(true);

        // Verify the results
        assertEquals(2, players.size());
        assertEquals("Alice", players.get(0).getName());
        assertEquals(PlayerRole.ORDER, players.get(0).getRole());
        assertEquals("Computer", players.get(1).getName());
        assertEquals(PlayerRole.CHAOS, players.get(1).getRole());
    }

}

