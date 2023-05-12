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
        for (int i = 0; i < board.getSize(); i++) {
            if (checkRow(i) || checkCol(i)) {
                return true;
            }
        }
        return checkMainDiagonal() || checkSecondaryDiagonal() || checkMinorDiagonals();
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

    private boolean checkMinorDiagonals() {
        for (int row = 0; row < board.getSize() - MIN_LINE_LENGTH; row++) {
            for (int col = 0; col < board.getSize() - MIN_LINE_LENGTH; col++) {
                if (checkLine(board.getMinorDiagonal(row, col, false)) ||
                        checkLine(board.getMinorDiagonal(row + MIN_LINE_LENGTH, col, true))) {
                    return true;
                }
            }
        }
        return false;
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

    private boolean isSequenceFound(int sequenceLength, CellState[] cells, int i) {
        for (int j = 1; j < sequenceLength; j++) {
            if (cells[i] != cells[i + j] || cells[i] == CellState.EMPTY) {
                return false;
            }
        }
        return true;
    }

    private CellSequence handleSequenceFound(int sequenceLength, CellState[] cells, int i, int startRow, int startCol, int rowIncrement, int colIncrement) {
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

    private CellSequence findTwoInMinorDiagonals() {
        CellSequence result;
        for (int row = 0; row < board.getSize() - MIN_LINE_LENGTH; row++) {
            for (int col = 0; col < board.getSize() - MIN_LINE_LENGTH; col++) {
                result = findSequenceInLine(2, board.getMinorDiagonal(row, col, false), row, col, 1, 1);
                if (result != null) {
                    return result;
                }
                result = findSequenceInLine(2, board.getMinorDiagonal(row + MIN_LINE_LENGTH, col, true), row + MIN_LINE_LENGTH, col, -1, 1);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    private CellSequence findThreeInMinorDiagonals() {
        CellSequence result;
        for (int row = 0; row < board.getSize() - MIN_LINE_LENGTH; row++) {
            for (int col = 0; col < board.getSize() - MIN_LINE_LENGTH; col++) {
                result = findSequenceInLine(3, board.getMinorDiagonal(row, col, false), row, col, 1, 1);
                if (result != null) {
                    return result;
                }
                result = findSequenceInLine(3, board.getMinorDiagonal(row + MIN_LINE_LENGTH, col, true), row + MIN_LINE_LENGTH, col, -1, 1);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }


    private CellSequence findFourInMinorDiagonals() {
        CellSequence result;
        for (int row = 0; row < board.getSize() - MIN_LINE_LENGTH; row++) {
            for (int col = 0; col < board.getSize() - MIN_LINE_LENGTH; col++) {
                result = findSequenceInLine(4, board.getMinorDiagonal(row, col, false), row, col, 1, 1);
                if (result != null) {
                    return result;
                }
                result = findSequenceInLine(4, board.getMinorDiagonal(row + MIN_LINE_LENGTH, col, true), row + MIN_LINE_LENGTH, col, -1, 1);
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
        if (sequenceLength == 2) {
            result = findTwoInMinorDiagonals();
        } else if (sequenceLength == 3) {
            result = findThreeInMinorDiagonals();
        } else if (sequenceLength == 4) {
            result = findFourInMinorDiagonals();
        }

        return result;
    }


}