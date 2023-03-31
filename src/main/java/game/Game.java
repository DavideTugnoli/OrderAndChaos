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

        switch (interfaceChoice) {
            case CONSOLE_CHOICE -> startGameWithConsoleInterface(board);
            case GRAPHIC_CHOICE -> startGameWithGraphicInterface(board);
        }
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

    private static void startGameWithConsoleInterface(Board board) {
        System.out.println(MessageBundle.getInstructionsMessage());
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