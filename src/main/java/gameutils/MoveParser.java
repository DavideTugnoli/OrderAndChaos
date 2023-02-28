package gameutils;

import entities.CellState;
import exceptions.InvalidMoveException;
import entities.Board;

public class MoveParser {
    private final Board board;

    public MoveParser(Board board) {
        this.board = board;
    }

    public boolean makeMove(int row, int col, CellState piece) throws InvalidMoveException {
        if (row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize()) {
            throw new InvalidMoveException("Cell coordinates are out of bounds");
        }
        if (board.getCellState(row, col) != CellState.EMPTY) {
            throw new InvalidMoveException("Cell is already occupied");
        }
        board.setCellState(row, col, piece);
        return true;
    }
}