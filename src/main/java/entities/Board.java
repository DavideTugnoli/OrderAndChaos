package entities;
public class Board {
    private final Cell[][] board;
    private final int size;
    public Board() {
        this(6);
    }

    public Board(int size) {
        this.size = size;
        board = new Cell[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = new Cell(row, col);
            }
        }
    }

    public boolean isCellEmpty(int row, int col) {
        return board[row][col].getState() == CellState.EMPTY;
    }

    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    public int getSize() {
        return size;
    }

    public void makeMove(int row, int col, CellState state) {
        board[row][col].setState(state);
    }
}