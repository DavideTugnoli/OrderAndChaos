package gameutils;

import entities.Board;
import entities.CellSequence;
import entities.CellState;

public class BoardChecker {

    private final Board board;
    private static final int MIN_LINE_LENGTH = 4;

    public BoardChecker(Board board) {
        this.board = board;
    }

    public boolean isOrderWinner() {
        CellSequence sequence = findSequenceInBoard(5);
        return sequence != null;
    }

    public boolean isChaosWinner() {
        return board.isFull() && !isOrderWinner();
    }

    public boolean isGameOver() {
        return isOrderWinner() || isChaosWinner();
    }

    private boolean isSequenceFound(int sequenceLength, CellState[] cells, int i) {
        for (int j = 1; j < sequenceLength; j++) {
            if (cells[i] != cells[i + j] || cells[i] == CellState.EMPTY) {
                return false;
            }
        }
        return true;
    }

    private CellSequence handleSequenceFound(int sequenceLength, CellState[] cells, int i, int startRow, int startCol, int rowIncrement, int colIncrement) {
        // if the sequence is a max length size, the last cell is not empty
        if (sequenceLength == board.getSize() - 1) {
            return new CellSequence(board.getCell(startRow, startCol), cells[i]);
        }
        int row, col;
        if (i == 0) {
            row = startRow + rowIncrement * (i + sequenceLength);
            col = startCol + colIncrement * (i + sequenceLength);
        } else if (i == cells.length - sequenceLength) {
            row = startRow + rowIncrement * (i - 1);
            col = startCol + colIncrement * (i - 1);
        } else {
            row = startRow + rowIncrement * (i - 1);
            col = startCol + colIncrement * (i - 1);
            if (board.isCellEmpty(row, col)) {
                return new CellSequence(board.getCell(row, col), cells[i]);
            }
            row = startRow + rowIncrement * (i + sequenceLength);
            col = startCol + colIncrement * (i + sequenceLength);
        }
        if (board.isCellEmpty(row, col)) {
            return new CellSequence(board.getCell(row, col), cells[i]);
        }
        return null;
    }

    private CellSequence findSequenceInLine(int sequenceLength, CellState[] cells, int startRow, int startCol, int rowIncrement, int colIncrement) {
        for (int i = 0; i < cells.length - sequenceLength + 1; i++) {
            if (isSequenceFound(sequenceLength, cells, i)) {
                CellSequence cellSequence = handleSequenceFound(sequenceLength, cells, i, startRow, startCol, rowIncrement, colIncrement);
                if (cellSequence != null) {
                    return cellSequence;
                }
            }
        }
        return null;
    }

    private CellSequence findSequenceInMinorDiagonals(int sequenceLength) {
        CellSequence result;
        for (int row = 0; row < board.getSize() - MIN_LINE_LENGTH; row++) {
            for (int col = 0; col < board.getSize() - MIN_LINE_LENGTH; col++) {
                result = findSequenceInLine(sequenceLength, board.getMinorDiagonal(row, col, false), row, col, 1, 1);
                if (result != null) {
                    return result;
                }
                result = findSequenceInLine(sequenceLength, board.getMinorDiagonal(row + MIN_LINE_LENGTH, col, true), row + MIN_LINE_LENGTH, col, -1, 1);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }


    public CellSequence findSequenceInBoard(int sequenceLength) {
        CellSequence result;

        // Check rows
        for (int i = 0; i < board.getSize(); i++) {
            result = findSequenceInLine(sequenceLength, board.getRow(i), i, 0, 0, 1);
            if (result != null) {
                return result;
            }
        }

        // Check columns
        for (int i = 0; i < board.getSize(); i++) {
            result = findSequenceInLine(sequenceLength, board.getCol(i), 0, i, 1, 0);
            if (result != null) {
                return result;
            }
        }

        // Check main diagonal
        result = findSequenceInLine(sequenceLength, board.getMainDiagonal(), 0, 0, 1, 1);
        if (result != null) {
            return result;
        }

        // Check secondary diagonal
        result = findSequenceInLine(sequenceLength, board.getSecondaryDiagonal(), 0, board.getSize() - 1, 1, -1);
        if (result != null) {
            return result;
        }

        // Check minor diagonals
        result = findSequenceInMinorDiagonals(sequenceLength);
        return result;
    }

}