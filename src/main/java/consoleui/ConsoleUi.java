package consoleui;

import annotations.JacocoExclude;
import entities.*;
import gameutils.GameEventListener;
import gameutils.GameplayLogic;
import gameutils.MessageBundle;

@JacocoExclude
public class ConsoleUi implements GameEventListener {
    private GameplayLogic gameplayLogic;
    private final BoardPrinter printer;
    private final ConsoleInputHandler consoleInputHandler;

    public ConsoleUi(ConsoleInputHandler consoleInputHandler) {
        this.printer = new BoardPrinter();
        this.consoleInputHandler = consoleInputHandler;
    }

    @Override
    public void onGameStarted() {
        printCurrentBoard();
    }

    @Override
    public void onTurnPlayed(Cell cell) {
        printCurrentBoard();
    }

    @Override
    public void onGameOver(Player winner) {
        endGame();
    }

    @Override
    public void onComputerTurnPlayed(Cell cell) {
        System.out.println(MessageBundle.getComputerTurnMessage(gameplayLogic.getPlayer2().getName(), cell.getRow() + 1, cell.getCol() + 1));
        printCurrentBoard();
    }

    @Override
    public void onTurnChanged() {
        printCurrentPlayerTurn();
    }

    public void play() {
        onGameStarted();

        while (!gameplayLogic.isGameOver()) {
            if (!gameplayLogic.isComputerPlayer()) {
                int[] input = consoleInputHandler.getValidInput(MessageBundle.getInputPrompt(), 1, gameplayLogic.getBoard().getSize());
                int row = input[0] - 1;
                int col = input[1] - 1;
                CellState piece = consoleInputHandler.getPieceSelection();
                gameplayLogic.playTurn(new Cell(row, col, piece));
            }
        }
    }

    private void printCurrentBoard() {
        System.out.println(MessageBundle.getCurrentBoardMessage());
        printer.printBoard(gameplayLogic.getBoard());
    }

    private void printCurrentPlayerTurn() {
        System.out.println(MessageBundle.getCurrentPlayerTurnMessage(gameplayLogic.getCurrentPlayerName()));
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
            System.exit(0);
        }
    }

    private void restartGame() {
        Board newBoard = new Board();
        GameplayLogic newGameplayLogic = new GameplayLogic(newBoard, gameplayLogic.getPlayer1(), gameplayLogic.getPlayer2(), this);
        this.setGameplayLogic(newGameplayLogic);
        this.play();
    }

    public void setGameplayLogic(GameplayLogic gameplayLogic) {
        this.gameplayLogic = gameplayLogic;
    }
}
