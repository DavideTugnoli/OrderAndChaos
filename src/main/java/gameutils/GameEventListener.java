package gameutils;

import entities.Cell;
import entities.Player;

public interface GameEventListener {
    void onTurnPlayed(Cell cell);

    void onComputerTurnPlayed(Cell cell);

    void onGameOver(Player winner);

    void onTurnChanged();

}
