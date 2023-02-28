package game;

import consoleui.ConsoleInputHandler;
import consoleui.GameplayConsole;
import consoleui.GameConsoleSetup;
import entities.Board;
import entities.Player;

import java.util.List;

public class Game {
        public static void main(String[] args) {
            Board board = new Board();
            List<Player> players = GameConsoleSetup.setupPlayers();
            GameplayConsole gameplayConsole = new GameplayConsole(board, new ConsoleInputHandler(), players.get(0), players.get(1));
            gameplayConsole.play();
        }
}
