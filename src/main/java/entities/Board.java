package entities;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final Cell[][] gameBoard;
    private final int size;

    public Board() {
        this(6);
    }

    public Board(int size) {
        this.size = size;
        gameBoard = new Cell[size][size];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                gameBoard[row][col] = new Cell(row, col);
            }
        }
    }

    public boolean isCellEmpty(int row, int col) {
        return gameBoard[row][col].getState() == CellState.EMPTY;
    }

    public Cell getCell(int row, int col) {
        return gameBoard[row][col];
    }

    public int getSize() {
        return size;
    }

    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (gameBoard[i][j].getState() == CellState.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public CellState[] getRow(int row) {
        return extractArray(row, 0, 0, 1);
    }

    public CellState[] getCol(int col) {
        return extractArray(0, col, 1, 0);
    }

    public CellState[] getMainDiagonal() {
        return extractArray(0, 0, 1, 1);
    }

    public CellState[] getSecondaryDiagonal() {
        return extractArray(0, size - 1, 1, -1);
    }

    public CellState[] getMinorDiagonal(int row, int col, boolean ascending) {
        int rowIncrement = ascending ? -1 : 1;
        int maxLength = ascending ? Math.min(row + 1, getSize() - col) : Math.min(getSize() - row, getSize() - col);
        int length = 0;

        for (int i = 0; i < maxLength && row + rowIncrement * i >= 0 && row + rowIncrement * i < getSize() && col + i < getSize(); i++) {
            length++;
        }

        return extractArray(row, col, rowIncrement, 1, length);
    }

    private CellState[] extractArray(int row, int col, int rowIncrement, int colIncrement) {
        return extractArray(row, col, rowIncrement, colIncrement, size);
    }

    private CellState[] extractArray(int row, int col, int rowIncrement, int colIncrement, int length) {
        CellState[] result = new CellState[length];
        for (int i = 0; i < length; i++) {
            result[i] = gameBoard[row][col].getState();
            row += rowIncrement;
            col += colIncrement;
        }
        return result;
    }

    public List<Cell> getAvailableCells() {
        List<Cell> availableCells = new ArrayList<>();
        int size = this.getSize();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (this.isCellEmpty(row, col)) {
                    availableCells.add(new Cell(row, col));
                }
            }
        }

        return availableCells;
    }

    public CellState getCellState(int row, int col) {
        return gameBoard[row][col].getState();
    }

    public void setCellState(int row, int col, CellState state) {
        gameBoard[row][col].setState(state);
    }
}
