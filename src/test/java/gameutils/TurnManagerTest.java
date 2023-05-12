package gameutils;

import entities.ComputerPlayer;
import entities.HumanPlayer;
import entities.Player;
import entities.PlayerRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TurnManagerTest {
    private Player player1;
    private Player player2;
    private TurnManager turnManager;

    @BeforeEach
    void setUp() {
        player1 = new HumanPlayer("Player 1", PlayerRole.ORDER);
        player2 = new HumanPlayer("Player 2", PlayerRole.CHAOS);
        turnManager = new TurnManager(player1, player2);
    }

    @Test
    void testGetCurrentPlayer() {
        Player currentPlayer = turnManager.getCurrentPlayer();
        assertEquals(player1, currentPlayer);
    }

    @Test
    void testNextPlayer() {
        turnManager.nextPlayer();
        assertEquals(player2, turnManager.getCurrentPlayer());
    }

    @Test
    void testNextPlayerTwice() {
        turnManager.nextPlayer();
        turnManager.nextPlayer();
        assertEquals(player1, turnManager.getCurrentPlayer());
    }

    @Test
    void testIsComputerPlayer() {
        assertFalse(turnManager.isComputerPlayer());
    }

    @Test
    void testIsComputerPlayerWithComputerPlayer() {
        Player computerPlayer = new ComputerPlayer("Computer", PlayerRole.CHAOS);
        TurnManager turnManagerWithComputerPlayer = new TurnManager(player1, computerPlayer);
        turnManagerWithComputerPlayer.nextPlayer();
        assertTrue(turnManagerWithComputerPlayer.isComputerPlayer());
    }

    @Test
    void testDetermineFirstPlayerOrder() {
        Player playerOrder = new HumanPlayer("Order", PlayerRole.ORDER);
        Player playerChaos = new HumanPlayer("Chaos", PlayerRole.CHAOS);
        TurnManager turnManagerOrderFirst = new TurnManager(playerOrder, playerChaos);
        assertEquals(playerOrder, turnManagerOrderFirst.getCurrentPlayer());
    }

}
