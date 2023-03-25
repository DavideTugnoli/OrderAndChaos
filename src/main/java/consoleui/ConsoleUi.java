package consoleui;

import annotations.Generated;
import entities.*;
import gameutils.GameplayLogic;
import gameutils.MessagePrinter;

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
        MessagePrinter.printWelcome();

        while (!gameplayLogic.isGameOver()) {
            printCurrentBoard();
            printCurrentPlayerTurn();
            executeTurn();
        }

        endGame();
    }

    private void printCurrentBoard() {
        MessagePrinter.getCurrentBoardMessage();
        printer.printBoard(gameplayLogic.getBoard());
    }

    private void printCurrentPlayerTurn() {
        MessagePrinter.getCurrentPlayerTurnMessage(gameplayLogic.getCurrentPlayerName());
    }

    private void executeTurn() {
        int[] input = consoleInputHandler.getValidInput(MessagePrinter.getInputPrompt(), 1, gameplayLogic.getBoard().getSize());
        int row = input[0] - 1;
        int col = input[1] - 1;

        CellState piece = consoleInputHandler.getPieceSelection();
        gameplayLogic.playTurn(row, col, piece);
    }

    private void endGame() {
        printFinalBoard();
        handlePostGame();
    }

    private void printFinalBoard() {
        MessagePrinter.getFinalBoardMessage();
        printer.printBoard(gameplayLogic.getBoard());
    }

    private void handlePostGame() {
        Player winner = gameplayLogic.getWinner();
        if (winner != null) {
            MessagePrinter.printWinner(winner);
        }

        String input = MessagePrinter.getPlayAgainInput();
        if (input.equalsIgnoreCase(MessagePrinter.getYesInput())) {
            restartGame();
        } else {
            MessagePrinter.printThanksMessage();
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
