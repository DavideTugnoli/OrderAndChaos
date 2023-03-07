package game;

import annotations.Generated;
import consoleui.*;
import entities.Board;
import entities.Player;
import gameutils.GameplayLogic;
import graphicui.GameGraphicSetup;
import graphicui.GraphicUi;


import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Generated
public class Game {
    private static final int CONSOLE_CHOICE = 1;
    private static final int GRAPHIC_CHOICE = 2;
    public static void main(String[] args) {
        System.out.println("Scegli l'interfaccia di gioco: 1 per console, 2 per grafica");
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        int choice;

        while (true) {
            try {
                choice = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Inserisci un numero intero");
                scanner.nextLine();
            }
        }

        if (choice == CONSOLE_CHOICE) {
            MessagePrinter.printInstructions();
            List<Player> players = GameConsoleSetup.setupPlayers();
            GameplayLogic gameplayLogic = new GameplayLogic(board, players.get(0), players.get(1));
            ConsoleUi consoleui = new ConsoleUi(gameplayLogic, new ConsoleInputHandler());
            consoleui.play();
        } else if (choice == GRAPHIC_CHOICE) {
            //GameGUI();
            List<Player> players = GameGraphicSetup.setupPlayers();
            GameplayLogic gameplayLogic = new GameplayLogic(board, players.get(0), players.get(1));
            GraphicUi graphicUi = new GraphicUi(gameplayLogic);
            graphicUi.setVisible(true);
        } else {
            System.out.println("Scelta non valida");
        }
    }
}
