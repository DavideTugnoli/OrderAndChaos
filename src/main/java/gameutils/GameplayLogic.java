package gameutils;

import entities.Board;
import entities.CellState;
import entities.Player;
import exceptions.InvalidMoveException;
import gameutils.BoardChecker;
import gameutils.MoveParser;

public class GameplayLogic {
    private final Board board;
    private final MoveParser moveParser;
    private final BoardChecker checker;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;

    private Player winner;

    public GameplayLogic(Board board, Player player1, Player player2) {
        this.board = board;
        this.moveParser = new MoveParser(board);
        this.checker = new BoardChecker(board);
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
    }

    public void playTurn(int row, int col, CellState piece) {
        try {
            boolean moveMade = moveParser.makeMove(row, col, piece);
            if (!moveMade) {
                return;
            }
        } catch (InvalidMoveException e) {
            return;
        }

        if (checker.isOrderWinner()) {
            winner = player1;
        } else if (checker.isChaosWinner()) {
            winner = player2;
        } else {
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
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
}

