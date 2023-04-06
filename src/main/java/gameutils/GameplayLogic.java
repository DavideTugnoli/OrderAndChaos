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

    public GameplayLogic(Board board, Player player1, Player player2) {
        this.board = board;
        this.moveParser = new MoveParser(board);
        this.checker = new BoardChecker(board);
        this.player1 = player1;
        this.player2 = player2;
        if(player1.getRole() == PlayerRole.ORDER) {
            this.currentPlayer = player1;
        } else {
            this.currentPlayer = player2;
        }
    }

    public void playTurn(Cell cell) {
        try {
            moveParser.makeMove(cell);
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
}

