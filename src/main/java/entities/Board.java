package entities;
import exceptions.InvalidMoveException;
public class Board {
    private final Cell[][] gameBoard;
    private final int size;
    private int remainingCells;

    public Board() {
        this(6);
    }

    public Board(int size) {
        this.size = size;
        gameBoard = new Cell[size][size];
        remainingCells = size * size;
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

    public void makeMove(int row, int col, CellState state) throws InvalidMoveException {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new InvalidMoveException("Cell coordinates are out of bounds");
        }
        if (gameBoard[row][col].getState() != CellState.EMPTY) {
            throw new InvalidMoveException("Cell is already occupied");
        }
        gameBoard[row][col].setState(state);
        remainingCells--;
    }

    public boolean isFull() {
        return remainingCells == 0;
    }

    public CellState[] getRow(int row) {
        CellState[] result = new CellState[size];
        for (int i = 0; i < size; i++) {
            result[i] = gameBoard[row][i].getState();
        }
        return result;
    }

    public CellState[] getCol(int col) {
        CellState[] result = new CellState[size];
        for (int i = 0; i < size; i++) {
            result[i] = gameBoard[i][col].getState();
        }
        return result;
    }

    public CellState[] getMainDiagonal() {
        CellState[] result = new CellState[size];
        for (int i = 0; i < size; i++) {
            result[i] = gameBoard[i][i].getState();
        }
        return result;
    }


    public CellState[] getSecondaryDiagonal() {
        CellState[] result = new CellState[size];
        for (int i = 0; i < size; i++) {
            result[i] = gameBoard[i][size - i - 1].getState();
        }
        return result;
    }

    /* public CellState getCellState(int row, int col) {
        return gameBoard[row][col].getState();
    }
    */
}