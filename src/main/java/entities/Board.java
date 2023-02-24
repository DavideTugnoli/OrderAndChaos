package entities;
import exceptions.InvalidMoveException;
public class Board {
    private final Cell[][] gameBoard;
    private final int size;
    public Board() {
        this(6);
    }

    public Board(int size) {
        this.size = size;
        gameBoard = new Cell[size][size];
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
    }
}