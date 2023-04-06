package consoleui;

import annotations.Generated;
import entities.*;
import gameutils.GameplayLogic;
import gameutils.MessageBundle;

@Generated
public class ConsoleUi {
    private final GameplayLogic gameplayLogic;
    private final BoardPrinter printer;
    private final ConsoleInputHandler consoleInputHandler;

    public ConsoleUi(GameplayLogic gameplayLogic, ConsoleInputHandler consoleInputHandler) {
        this.gameplayLogic = gameplayLogic;
        this.printer = new BoardPrinter();
        this.consoleInputHandler = consoleInputHandler;
    }

    public void play() {
        System.out.println(MessageBundle.getWelcomeMessage());

        while (!gameplayLogic.isGameOver()) {
            printCurrentBoard();
            printCurrentPlayerTurn();
            executeTurn();
        }

        endGame();
    }

    private void printCurrentBoard() {
        System.out.println(MessageBundle.getCurrentBoardMessage());
        printer.printBoard(gameplayLogic.getBoard());
    }

    private void printCurrentPlayerTurn() {
        System.out.println(MessageBundle.getCurrentPlayerTurnMessage(gameplayLogic.getCurrentPlayerName()));
    }

    private void executeTurn() {
        int[] input = consoleInputHandler.getValidInput(MessageBundle.getInputPrompt(), 1, gameplayLogic.getBoard().getSize());
        int row = input[0] - 1;
        int col = input[1] - 1;

        CellState piece = consoleInputHandler.getPieceSelection();
        gameplayLogic.playTurn(new Cell(row, col, piece)); // TO-DO
    }

    private void endGame() {
        printFinalBoard();
        handlePostGame();
    }

    private void printFinalBoard() {
        System.out.println(MessageBundle.getFinalBoardMessage());
        printer.printBoard(gameplayLogic.getBoard());
    }

    private void handlePostGame() {
        Player winner = gameplayLogic.getWinner();
        if (winner != null) {
            System.out.println(MessageBundle.getWinnerMessage(winner));
        }

        String input = MessageBundle.getPlayAgainInput();
        if (input.equalsIgnoreCase(MessageBundle.getYesInput())) {
            restartGame();
        } else {
            System.out.println(MessageBundle.getThanksMessage());
            consoleInputHandler.close();
        }
    }

    private void restartGame() {
        Board newBoard = new Board();
        GameplayLogic newGameplayLogic = new GameplayLogic(newBoard, gameplayLogic.getPlayer1(), gameplayLogic.getPlayer2());
        ConsoleUi newConsoleUi = new ConsoleUi(newGameplayLogic, consoleInputHandler);
        newConsoleUi.play();
    }
}
