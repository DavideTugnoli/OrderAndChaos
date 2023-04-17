package gameutils;

import entities.Cell;
import entities.Player;

public class GameEventListenerStub implements GameEventListener {
    @Override
    public void onGameStarted() {
    }

    @Override
    public void onTurnPlayed(Cell cell) {
    }

    @Override
    public void onComputerTurnPlayed(Cell cell) {
    }

    @Override
    public void onGameOver(Player winner) {
    }

    @Override
    public void onTurnChanged() {
    }

}
