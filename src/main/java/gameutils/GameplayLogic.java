package gameutils;

import entities.*;
import exceptions.InvalidMoveException;

public class GameplayLogic {
    private final Board board;
    private final MoveParser moveParser;
    private final BoardChecker checker;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;

    private Player winner;

    private final GameEventListener gameEventListener;

    public GameplayLogic(Board board, Player player1, Player player2, GameEventListener gameEventListener) {
        this.board = board;
        this.moveParser = new MoveParser(board);
        this.checker = new BoardChecker(board);
        this.gameEventListener = gameEventListener;
        this.player1 = player1;
        this.player2 = player2;
        determineFirstPlayer();
    }

    private void determineFirstPlayer() {
        if (player1.getRole() == PlayerRole.ORDER) {
            this.currentPlayer = player1;
        } else {
            this.currentPlayer = player2;
        }
    }

    public void playTurn(Cell cell) {
        if (isGameOver()) {
            return;
        }
        try {
            moveParser.makeMove(cell);
        } catch (InvalidMoveException e) {
            return;
        }

        updateGameState(cell);

        if (currentPlayer instanceof ComputerPlayer) {
            playComputerTurn();
        }
    }


    private void nextPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    private void playComputerTurn() {
        if (getCurrentPlayer() instanceof ComputerPlayer computerPlayer) {
            Cell computerMove = computerPlayer.makeMove(getBoard());
            playTurn(computerMove);
        }
    }

    private void updateGameState(Cell cell) {
        if (checker.isOrderWinner()) {
            winner = player1;
            gameEventListener.onGameOver(winner);
        } else if (checker.isChaosWinner()) {
            winner = player2;
            gameEventListener.onGameOver(winner);
        } else {
            if (currentPlayer instanceof HumanPlayer) {
                nextPlayer();
                gameEventListener.onTurnPlayed(cell);
            } else if (currentPlayer instanceof ComputerPlayer) {
                gameEventListener.onComputerTurnPlayed(cell);
                nextPlayer();
            }
        }
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

