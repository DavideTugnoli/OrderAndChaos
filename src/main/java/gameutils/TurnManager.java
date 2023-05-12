package gameutils;

import entities.ComputerPlayer;
import entities.Player;
import entities.PlayerRole;

public class TurnManager {
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;

    public TurnManager(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        determineFirstPlayer();
    }

    private void determineFirstPlayer() {
        if (player1.getRole() == PlayerRole.ORDER) {
            this.currentPlayer = player1;
        } else {
            this.currentPlayer = player2;
        }
    }

    public void nextPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isComputerPlayer() {
        return getCurrentPlayer() instanceof ComputerPlayer;
    }
}

