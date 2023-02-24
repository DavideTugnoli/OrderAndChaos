package entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import exceptions.InvalidMoveException;

class BoardTest {
    @Test
    public void testGetCell() {
        Board board = new Board();
        Cell cell = board.getCell(0, 0);
        assertNotNull(cell);
        assertEquals(CellState.EMPTY, cell.getState());
    }

    // verifica che tutte le celle della tavola siano vuote quando la tavola viene inizializzata.
    @Test
    void testEmptyBoard() {
        Board board = new Board();
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                assertTrue(board.isCellEmpty(row, col));
            }
        }
    }
    @Test
    void testMakeMove() throws InvalidMoveException {
        Board board = new Board();
        board.makeMove(0, 0, CellState.X);
        assertEquals(CellState.X, board.getCell(0, 0).getState());
    }

    @Test
    void testInvalidMove() throws InvalidMoveException {
        Board board = new Board();
        board.makeMove(0, 0, CellState.X);
        assertThrows(InvalidMoveException.class, () -> board.makeMove(0, 0, CellState.O));
    }

    @Test
    void testOutOfBoundsMove() {
        Board board = new Board();
        assertThrows(InvalidMoveException.class, () -> board.makeMove(-1, 0, CellState.X));
        assertThrows(InvalidMoveException.class, () -> board.makeMove(0, -1, CellState.X));
        assertThrows(InvalidMoveException.class, () -> board.makeMove(board.getSize(), 0, CellState.X));
        assertThrows(InvalidMoveException.class, () -> board.makeMove(0, board.getSize(), CellState.X));
    }

    @Test
    void testBoardSize() {
        int expectedSize = 6;
        Board board = new Board(expectedSize);
        assertEquals(expectedSize, board.getSize());
    }

    @Test
    void testMakeMoveNotEmpty() throws InvalidMoveException {
        Board board = new Board();
        board.makeMove(0, 0, CellState.X);
        assertFalse(board.isCellEmpty(0, 0));
    }

    @Test
    public void testConstructor() {
        Board board = new Board();
        assertEquals(6, board.getSize());
    }


}