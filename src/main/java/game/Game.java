package game;

import annotations.Generated;
import consoleui.*;
import entities.Board;
import entities.Player;
import gameutils.GameplayLogic;
import gameutils.MessageBundle;
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
        setLocaleFromUserChoice(scanner);
        Board board = new Board();
        int interfaceChoice = getGameInterfaceChoice(scanner);
        boolean singlePlayer = isSinglePlayerGame(scanner);

        switch (interfaceChoice) {
            case CONSOLE_CHOICE -> startGameWithConsoleInterface(board, singlePlayer);
            case GRAPHIC_CHOICE -> startGameWithGraphicInterface(board, singlePlayer);
        }
    }


    private static boolean isSinglePlayerGame(Scanner scanner) {
        int choice;

        while (true) {
           // System.out.println(MessageBundle.getGameModeChoiceRequestMessage());
            System.out.println("1. Single Player \n2. Multiplayer");
            try {
                choice = scanner.nextInt();
                if (choice == 1 || choice == 2) {
                    break;
                }
               // System.out.println(MessageBundle.getInvalidGameModeChoiceMessage());
                System.out.println("Invalid Choice");
            } catch (InputMismatchException e) {
                System.out.println(MessageBundle.getEnterIntegerMessage());
                scanner.nextLine();
            }
        }

        return choice == 1;
    }


    private static int getGameInterfaceChoice(Scanner scanner) {
        int choice;

        while (true) {
            System.out.println(MessageBundle.getGameInterfaceChoiceRequestMessage());
            try {
                choice = scanner.nextInt();
                if (choice == CONSOLE_CHOICE || choice == GRAPHIC_CHOICE) {
                    break;
                }
                System.out.println(MessageBundle.getInvalidInterfaceChoiceMessage());
            } catch (InputMismatchException e) {
                System.out.println(MessageBundle.getEnterIntegerMessage());
                scanner.nextLine();
            }
        }

        return choice;
    }

    private static void setLocaleFromUserChoice(Scanner scanner) {
        String languageChoice;
        System.out.println(MessageBundle.getLanguageChoiceRequestMessage());

        while (true) {
            languageChoice = scanner.nextLine();
            if (languageChoice.equals("1") || languageChoice.equals("2")) {
                break;
            }
            System.out.println(MessageBundle.getInvalidLanguageChoiceMessage());
        }

        setCurrentLocale(languageChoice);
    }

    private static void setCurrentLocale(String languageChoice) {
        Locale locale = Locale.ENGLISH;

        switch (languageChoice) {
            case "1" -> {
            }
            case "2" -> locale = Locale.ITALIAN;
        }

        MessageBundle.setCurrentLocale(locale);
    }

    private static void startGameWithConsoleInterface(Board board, boolean singlePlayer) {
        System.out.println(MessageBundle.getWelcomeMessage());
        System.out.println(MessageBundle.getInstructionsMessage());
        List<Player> players = GameConsoleSetup.setupPlayers(singlePlayer);
        ConsoleUi consoleui = new ConsoleUi(new ConsoleInputHandler());
        GameplayLogic gameplayLogic = new GameplayLogic(board, players.get(0), players.get(1), consoleui);
        consoleui.setGameplayLogic(gameplayLogic);
        consoleui.play();
    }

    private static void startGameWithGraphicInterface(Board board, boolean singlePlayer) {
        List<Player> players = GameGraphicSetup.setupPlayers(singlePlayer);
        GraphicUi graphicUi = new GraphicUi();
        GameplayLogic gameplayLogic = new GameplayLogic(board, players.get(0), players.get(1), graphicUi);
        graphicUi.setGameplayLogic(gameplayLogic);
        graphicUi.setVisible(true);
    }

}