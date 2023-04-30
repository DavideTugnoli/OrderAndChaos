package gameutils;

import entities.*;
import exceptions.InvalidMoveException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameplayLogic {
    private final Board board;
    private final MoveParser moveParser;
    private final BoardChecker checker;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;

    private Player winner;

    private final GameEventListener gameEventListener;

    private final Random random;

    private final ScheduledExecutorService executorService;

    public GameplayLogic(Board board, Player player1, Player player2, GameEventListener gameEventListener) {
        this.board = board;
        this.moveParser = new MoveParser(board);
        this.checker = new BoardChecker(board);
        this.gameEventListener = gameEventListener;
        this.player1 = player1;
        this.player2 = player2;
        this.random = new Random();
        determineFirstPlayer();
        if (player1 instanceof ComputerPlayer || player2 instanceof ComputerPlayer) {
            executorService = Executors.newSingleThreadScheduledExecutor();
        } else {
            executorService = null;
        }
    }

    private void determineFirstPlayer() {
        if (player1.getRole() == PlayerRole.ORDER) {
            this.currentPlayer = player1;
        } else {
            this.currentPlayer = player2;
        }
    }

    public void playTurn(Cell cell) {
        if (isGameOver()) return;
        if (currentPlayer instanceof HumanPlayer) {
            playHumanTurn(cell);
        } else if (currentPlayer instanceof ComputerPlayer) {
            playComputerTurn();
        }
    }

    private void playHumanTurn(Cell cell) {
        try {
            moveParser.makeMove(cell);
        } catch (InvalidMoveException e) {
            return;
        }

        updateGameState(cell);
    }

    private void playComputerTurn() {
        Cell computerMove = makeComputerMove(getBoard());
        try {
            moveParser.makeMove(computerMove);
        } catch (InvalidMoveException e) {
            return;
        }
        updateGameState(computerMove);
    }

    private void nextPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    private void updateGameState(Cell cell) {
        if (currentPlayer instanceof HumanPlayer) {
            gameEventListener.onTurnPlayed(cell);
        } else if (currentPlayer instanceof ComputerPlayer) {
            gameEventListener.onComputerTurnPlayed(cell);
        }
        nextPlayer();
        gameEventListener.onTurnChanged();
        checkWinner();
        if (!isGameOver() && currentPlayer instanceof ComputerPlayer) {
            // Ritardo di 500 millisecondi (0,5 secondi) prima di far giocare il computer
            executorService.schedule(this::playComputerTurn, 500, TimeUnit.MILLISECONDS);
        }
    }

    private void checkWinner() {
        if (checker.isOrderWinner()) {
            winner = player1;
            gameEventListener.onGameOver(winner);
        } else if (checker.isChaosWinner()) {
            winner = player2;
            gameEventListener.onGameOver(winner);
        }
    }

    private Cell makeComputerMove(Board board) {
        List<Cell> availableCells = getAvailableCells(board);
        Cell chosenCell = availableCells.get(random.nextInt(availableCells.size()));

        CellSequence sequence = selectBestMove();
        if (sequence != null) {
            return new Cell(sequence.lastCell().getRow(), sequence.lastCell().getCol(), getOppoCellState(sequence.sequenceState()));
        }

        CellState chosenState = random.nextBoolean() ? CellState.X : CellState.O;
        return new Cell(chosenCell.getRow(), chosenCell.getCol(), chosenState);
    }

    private CellSequence selectBestMove() {
        CellSequence fourSequence = checker.findFourInSequence();
        if (fourSequence != null) {
            System.out.println("Blocking four in a row");
            return fourSequence;
        }

        CellSequence threeSequence = checker.findThreeInSequence();
        if (threeSequence != null) {
            System.out.println("Blocking three in a row");
            return threeSequence;
        }

        CellSequence twoSequence = checker.findTwoInSequence();
        if (twoSequence != null) {
            System.out.println("Creating two in a row");
            return twoSequence;
        }

        return null;
    }

    private CellState getOppoCellState(CellState state) {
        return state == CellState.X ? CellState.O : CellState.X;
    }

    private List<Cell> getAvailableCells(Board board) {
        List<Cell> availableCells = new ArrayList<>();
        int size = board.getSize();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board.isCellEmpty(row, col)) {
                    availableCells.add(new Cell(row, col));
                }
            }
        }

        return availableCells;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public String getCurrentPlayerName() {
        return currentPlayer.getName();
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
        return getCurrentPlayer() instanceof ComputerPlayer;
    }

    public boolean isSinglePlayer() {
        return player1 instanceof ComputerPlayer || player2 instanceof ComputerPlayer;
    }

}

