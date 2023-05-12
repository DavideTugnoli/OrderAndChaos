package gameutils;

import entities.*;
import exceptions.InvalidMoveException;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameplayLogic {
    private static final Logger LOGGER = Logger.getLogger(GameplayLogic.class.getName());
    private final Board board;
    private final MoveParser moveParser;
    private final BoardChecker checker;
    private final Player player1;
    private final Player player2;
    private Player winner;
    private final GameEventListener gameEventListener;
    private final ScheduledExecutorService executorService;
    private final ComputerMoveStrategy computerMoveStrategy;
    private final TurnManager turnManager;

    public GameplayLogic(Board board, Player player1, Player player2, GameEventListener gameEventListener) {
        this.board = board;
        this.moveParser = new MoveParser(board);
        this.checker = new BoardChecker(board);
        this.gameEventListener = gameEventListener;
        this.player1 = player1;
        this.player2 = player2;
        this.turnManager = new TurnManager(player1, player2);
        if (isSinglePlayer()) {
            executorService = Executors.newSingleThreadScheduledExecutor();
            computerMoveStrategy = new ComputerMoveStrategy(board);
        } else {
            executorService = null;
            computerMoveStrategy = null;
        }
    }

    public void executeTurn(Cell cell) {
        if (isGameOver()) return;
        Player currentPlayer = turnManager.getCurrentPlayer();
        if (currentPlayer instanceof ComputerPlayer) {
            Cell computerMove = computerMoveStrategy.makeComputerMove(getBoard());
            executeTurn(computerMove, currentPlayer);
        } else {
            executeTurn(cell, currentPlayer);
        }
    }

    private void executeTurn(Cell cell, Player player) {
        try {
            moveParser.makeMove(cell);
        } catch (InvalidMoveException e) {
            LOGGER.log(Level.SEVERE, "Invalid move", e);
            return;
        }

        updateGameState(cell, player);
    }


    private void updateGameState(Cell cell, Player player) {
        if (player instanceof HumanPlayer) {
            gameEventListener.onTurnPlayed(cell);
        } else if (player instanceof ComputerPlayer) {
            gameEventListener.onComputerTurnPlayed(cell);
        }
        turnManager.nextPlayer();
        gameEventListener.onTurnChanged();
        checkWinner();
        if (!isGameOver() && turnManager.isComputerPlayer()) {
            executorService.schedule(() -> executeTurn(computerMoveStrategy.makeComputerMove(getBoard()), turnManager.getCurrentPlayer()), 500, TimeUnit.MILLISECONDS);
        }
    }

    private void checkWinner() {
        if (checker.isOrderWinner()) {
            winner = player1;
            gameEventListener.onGameOver(winner);
            shutdown();
        } else if (checker.isChaosWinner()) {
            winner = player2;
            gameEventListener.onGameOver(winner);
            shutdown();
        }
    }

    private void shutdown() {
        if (executorService != null) {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                    if (!executorService.awaitTermination(60, TimeUnit.SECONDS))
                        LOGGER.log(Level.SEVERE, "Pool did not terminate");
                }
            } catch (InterruptedException ie) {
                executorService.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    public Player getCurrentPlayer() {
        return turnManager.getCurrentPlayer();
    }

    public String getCurrentPlayerName() {
        return turnManager.getCurrentPlayer().getName();
    }

    public boolean isGameOver() {
        return checker.isGameOver();
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getWinner() {
        return winner;
    }

    public boolean isComputerPlayer() {
        return turnManager.isComputerPlayer();
    }

    public boolean isSinglePlayer() {
        return player1 instanceof ComputerPlayer || player2 instanceof ComputerPlayer;
    }

}

