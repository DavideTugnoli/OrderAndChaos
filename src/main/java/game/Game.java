package game;

import annotations.Generated;
import consoleui.*;
import entities.Board;
import entities.Player;
import gameutils.GameplayLogic;

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
            GameplayLogic gameplayLogic = new GameplayLogic(board, players.get(0), players.get(1));
            Consoleui consoleui = new Consoleui(gameplayLogic, new ConsoleInputHandler());
            consoleui.play();
        } else if (choice == 2) {
            //GameGUI();
        } else {
            System.out.println("Scelta non valida");
        }
    }
}
