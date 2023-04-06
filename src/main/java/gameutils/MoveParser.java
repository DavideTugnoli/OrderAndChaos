package gameutils;

import entities.Cell;
import entities.CellState;
import exceptions.InvalidMoveException;
import entities.Board;

public class MoveParser {
    private final Board board;

    public MoveParser(Board board) {
        this.board = board;
    }

    public void makeMove(Cell cell) throws InvalidMoveException {
        if (cell.getRow() < 0 || cell.getRow() >= board.getSize() || cell.getCol() < 0 || cell.getCol() >= board.getSize()) {
            throw new InvalidMoveException("Cell coordinates are out of bounds");
        }
        if (board.getCellState(cell.getRow(), cell.getCol()) != CellState.EMPTY) {
            throw new InvalidMoveException("Cell is already occupied");
        }
        board.setCellState(cell.getRow(), cell.getCol(), cell.getState());
    }
}