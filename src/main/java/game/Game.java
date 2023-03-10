package game;

import annotations.Generated;
import consoleui.*;
import entities.Board;
import entities.Player;
import gameutils.GameplayLogic;
import gameutils.MessagePrinter;
import graphicui.GameGraphicSetup;
import graphicui.GraphicUi;


import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Generated
public class Game {
    private static final int CONSOLE_CHOICE = 1;
    private static final int GRAPHIC_CHOICE = 2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MessagePrinter.printLanguageChoiceRequest();
        String languageChoice = getLanguageChoice(scanner);
        setCurrentLocale(languageChoice);
        Board board = new Board();
        int interfaceChoice = getGameInterfaceChoice(scanner);

        switch (interfaceChoice) {
            case CONSOLE_CHOICE -> startGameWithConsoleInterface(board);
            case GRAPHIC_CHOICE -> startGameWithGraphicInterface(board);
            default -> MessagePrinter.getInvalidChoiceMessage();
        }
    }

    private static int getGameInterfaceChoice(Scanner scanner) {
        int choice;

        while (true) {
            MessagePrinter.printGameInterfaceChoiceRequest();
            try {
                choice = scanner.nextInt();
                if (choice == CONSOLE_CHOICE || choice == GRAPHIC_CHOICE) {
                    break;
                }
                MessagePrinter.printInvalidChoiceMessage();
            } catch (InputMismatchException e) {
                MessagePrinter.printEnterIntegerMessage();
                scanner.nextLine();
            }
        }

        return choice;
    }

    private static String getLanguageChoice(Scanner scanner) {
        String language;

        while (true) {
            language = scanner.nextLine();
            if (language.equals("1") || language.equals("2")) {
                break;
            }
            MessagePrinter.printInvalidChoiceMessage();
        }

        return language;
    }

    private static void setCurrentLocale(String languageChoice) {
        if (languageChoice.equals("1")) {
            MessagePrinter.setCurrentLocale(Locale.ENGLISH);
        } else if (languageChoice.equals("2")) {
            MessagePrinter.setCurrentLocale(Locale.ITALIAN);
        }
    }

    private static void startGameWithConsoleInterface(Board board) {
        MessagePrinter.printInstructions();
        List<Player> players = GameConsoleSetup.setupPlayers();
        GameplayLogic gameplayLogic = new GameplayLogic(board, players.get(0), players.get(1));
        ConsoleUi consoleui = new ConsoleUi(gameplayLogic, new ConsoleInputHandler());
        consoleui.play();
    }

    private static void startGameWithGraphicInterface(Board board) {
        List<Player> players = GameGraphicSetup.setupPlayers();
        GameplayLogic gameplayLogic = new GameplayLogic(board, players.get(0), players.get(1));
        GraphicUi graphicUi = new GraphicUi(gameplayLogic);
        graphicUi.setVisible(true);
    }
}

