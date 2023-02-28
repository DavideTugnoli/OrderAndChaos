package game;

import annotations.Generated;
import consoleui.ConsoleInputHandler;
import consoleui.GameplayConsole;
import consoleui.GameConsoleSetup;
import consoleui.MessagePrinter;
import entities.Board;
import entities.Player;

import java.util.List;
import java.util.Scanner;

@Generated
public class Game {
    public static void main(String[] args) {
        System.out.println("Scegli l'interfaccia di gioco: 1 per console, 2 per grafica");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice == 1) {
            MessagePrinter.printInstructions();
            Board board = new Board();
            List<Player> players = GameConsoleSetup.setupPlayers();
            GameplayConsole gameplayConsole = new GameplayConsole(board, new ConsoleInputHandler(), players.get(0), players.get(1));
            gameplayConsole.play();
        } else if (choice == 2) {
            //GameGUI();
        } else {
            System.out.println("Scelta non valida");
        }
    }
}
