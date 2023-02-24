package entities;

import java.util.Objects;

public class Cell {
    private final int row;
    private final int col;
    private CellState state;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.state = CellState.EMPTY;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = Objects.requireNonNullElse(state, CellState.EMPTY);
    }

    @Override
    public String toString() {
        return state.toString();
    }
}