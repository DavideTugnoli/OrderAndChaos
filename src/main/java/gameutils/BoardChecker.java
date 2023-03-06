package gameutils;

import entities.Board;
import entities.CellState;

public class BoardChecker {

    private final Board board;

    public BoardChecker(Board board) {
        this.board = board;
    }

    public boolean isOrderWinner() {
        for (int i = 0; i < board.getSize(); i++) {
            if (checkRow(i) || checkCol(i) || checkMainDiagonal() || checkSecondaryDiagonal()) {
                return true;
            }
        }
        return false;
    }

    public boolean isChaosWinner() {
        return board.isFull() && !isOrderWinner();
    }

    private boolean checkRow(int row) {
        CellState[] cells = board.getRow(row);
        return checkLine(cells);
    }

    private boolean checkCol(int col) {
        CellState[] cells = board.getCol(col);
        return checkLine(cells);
    }

    private boolean checkMainDiagonal() {
        CellState[] cells = board.getMainDiagonal();
        return checkLine(cells);
    }

    private boolean checkSecondaryDiagonal() {
        CellState[] cells = board.getSecondaryDiagonal();
        return checkLine(cells);
    }

    private boolean checkLine(CellState[] cells) {
        int count = 0;
        CellState currentCell = null;
        for (CellState cell : cells) {
            if (cell == CellState.EMPTY) {
                count = 0;
                currentCell = null;
            } else if (cell == currentCell) {
                count++;
                if (count == 5) {
                    return true;
                }
            } else {
                count = 1;
                currentCell = cell;
            }
        }
        return false;
    }

    public boolean isGameOver() {
        return isOrderWinner() || isChaosWinner();
    }
}