package consoleui;

import annotations.Generated;
import entities.*;
import gameutils.GameplayLogic;

import java.util.Scanner;

@Generated
public class Consoleui {
    private final GameplayLogic gameplayLogic;
    private final BoardPrinter printer;
    private final ConsoleInputHandler consoleInputHandler;

    private static final String THANKS_MESSAGE = "Thanks for playing!";
    private static final String INPUT_PROMPT = "Enter row,column: ";

    public Consoleui(GameplayLogic gameplayLogic, ConsoleInputHandler consoleInputHandler) {
        this.gameplayLogic = gameplayLogic;
        this.printer = new BoardPrinter();
        this.consoleInputHandler = consoleInputHandler;
    }

    public void play() {
        MessagePrinter.printWelcome();

        while (!gameplayLogic.isGameOver()) {
            System.out.println("Current board:");
            printer.printBoard(gameplayLogic.getBoard());

            System.out.println("It's " + gameplayLogic.getCurrentPlayer().getName() + "'s turn.");
            int[] input = consoleInputHandler.getValidInput(INPUT_PROMPT, 1, gameplayLogic.getBoard().getSize());
            int row = input[0] - 1;
            int col = input[1] - 1;

            CellState piece = consoleInputHandler.getPieceSelection();
            gameplayLogic.playTurn(row, col, piece);
        }

        endGame();
    }

    private void endGame() {
        System.out.println("Final board:");
        printer.printBoard(gameplayLogic.getBoard());

        Player winner = null;
        if (gameplayLogic.getWinner() != null) {
            winner = gameplayLogic.getWinner();
        }

        if (winner != null) {
            MessagePrinter.printWinner(winner);
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to replay? Write 'Yes' or 'No'");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("Yes")) {
            restartGame();
        } else {
            System.out.println(THANKS_MESSAGE);
            consoleInputHandler.close();
        }
    }

    private void restartGame() {
        Board newBoard = new Board();
        GameplayLogic newGameplayLogic = new GameplayLogic(newBoard, gameplayLogic.getPlayer1(), gameplayLogic.getPlayer2());
        Consoleui newConsoleui = new Consoleui(newGameplayLogic, consoleInputHandler);
        newConsoleui.play();
    }
}

