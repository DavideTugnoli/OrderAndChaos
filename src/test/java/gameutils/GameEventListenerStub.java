package gameutils;

import entities.Cell;
import entities.Player;

public class GameEventListenerStub implements GameEventListener {
    private boolean gameOverCalled = false;
    private boolean onTurnPlayed = false;
    private boolean onComputerTurnPlayed = false;
    private boolean onTurnChanged = false;

    @Override
    public void onTurnPlayed(Cell cell) {
        onTurnPlayed = true;
    }

    @Override
    public void onComputerTurnPlayed(Cell cell) {
        onComputerTurnPlayed = true;
    }

    @Override
    public void onGameOver(Player winner) {
        gameOverCalled = true;
    }

    @Override
    public void onTurnChanged() {
        onTurnChanged = true;
    }

    public boolean isGameOverCalled() {
        return gameOverCalled;
    }

    public boolean isOnTurnPlayedCalled() {
        return onTurnPlayed;
    }

    public boolean isOnComputerTurnPlayedCalled() {
        return onComputerTurnPlayed;
    }

    public boolean isOnTurnChangedCalled() {
        return onTurnChanged;
    }

}
