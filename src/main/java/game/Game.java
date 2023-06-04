package game;

import annotations.Generated;
import consoleui.*;
import entities.Board;
import entities.Player;
import gameutils.GameplayLogic;
import gameutils.MessageBundle;
import graphicui.GameGraphicPlayerSetup;
import graphicui.GraphicUi;

import java.util.List;
import java.util.Locale;

@Generated
public class Game {
    private static final String CONSOLE_ARG = "--console";
    private static final String GRAPHIC_ARG = "--graphic";
    private static final String SINGLE_PLAYER_ARG = "--single-player";
    private static final String MULTI_PLAYER_ARG = "--multi-player";
    private static final String ENGLISH_ARG = "--english";
    private static final String ITALIAN_ARG = "--italian";

    public static void main(String[] args) {
        boolean consoleMode = false;
        boolean singlePlayer = false;
        Locale locale = Locale.ENGLISH;

        for (String arg : args) {
            switch (arg) {
                case CONSOLE_ARG -> consoleMode = true;
                case GRAPHIC_ARG -> consoleMode = false;
                case SINGLE_PLAYER_ARG -> singlePlayer = true;
                case MULTI_PLAYER_ARG -> singlePlayer = false;
                case ENGLISH_ARG -> locale = Locale.ENGLISH;
                case ITALIAN_ARG -> locale = Locale.ITALIAN;
                default -> System.out.println("Unrecognized argument: " + arg);
            }
        }

        MessageBundle.setCurrentLocale(locale);
        Board board = new Board();

        if (consoleMode) {
            startGameWithConsoleInterface(board, singlePlayer);
        } else {
            startGameWithGraphicInterface(board, singlePlayer);
        }
    }

    private static void startGameWithConsoleInterface(Board board, boolean singlePlayer) {
        System.out.println(MessageBundle.getWelcomeMessage());
        System.out.println(MessageBundle.getInstructionsMessage());
        List<Player> players = GameConsolePlayerSetup.setupPlayers(singlePlayer);
        ConsoleUi consoleUi = new ConsoleUi(new ConsoleInputHandler());
        GameplayLogic gameplayLogic = new GameplayLogic(board, players.get(0), players.get(1), consoleUi);
        consoleUi.setGameplayLogic(gameplayLogic);
        consoleUi.play();
    }

    private static void startGameWithGraphicInterface(Board board, boolean singlePlayer) {
        GraphicUi graphicUi = new GraphicUi(singlePlayer);
        List<Player> players = GameGraphicPlayerSetup.setupPlayers(singlePlayer);
        GameplayLogic gameplayLogic = new GameplayLogic(board, players.get(0), players.get(1), graphicUi);
        graphicUi.setGameplayLogic(gameplayLogic);
        graphicUi.setVisible(true);
    }

}
