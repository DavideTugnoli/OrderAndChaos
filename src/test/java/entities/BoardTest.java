package entities;

import gameutils.MoveParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import exceptions.InvalidMoveException;

class BoardTest {
    private Board board;
    private MoveParser moveParser;

    @BeforeEach
    void setUp() {
        board = new Board();
        moveParser = new MoveParser(board);
    }

    @Test
    void testGetCell() {
        Cell cell = board.getCell(0, 0);
        assertNotNull(cell);
        assertEquals(CellState.EMPTY, cell.getState());
    }

    // verifica che tutte le celle della tavola siano vuote quando la tavola viene inizializzata.
    @Test
    void testEmptyBoard() {
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                assertTrue(board.isCellEmpty(row, col));
            }
        }
    }
    @Test
    void testMakeMove() {
        try {
            moveParser.makeMove(0, 0, CellState.X);
            assertEquals(CellState.X, board.getCell(0, 0).getState());
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        }
    }


    @Test
    void testInvalidMove() {
        try {
            moveParser.makeMove(0, 0, CellState.X);
            assertThrows(InvalidMoveException.class, () -> moveParser.makeMove(0, 0, CellState.O));
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testOccupiedCellMove() {
        try {
            moveParser.makeMove(0, 0, CellState.X);
            assertThrows(InvalidMoveException.class, () -> moveParser.makeMove(0, 0, CellState.O));
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        }
    }



    @Test
    void testOutOfBoundsMove() {
        assertThrows(InvalidMoveException.class, () -> moveParser.makeMove(-1, 0, CellState.X));
        assertThrows(InvalidMoveException.class, () -> moveParser.makeMove(0, -1, CellState.X));
        assertThrows(InvalidMoveException.class, () -> moveParser.makeMove(board.getSize(), 0, CellState.X));
        assertThrows(InvalidMoveException.class, () -> moveParser.makeMove(0, board.getSize(), CellState.X));
    }


    @Test
    void testBoardSize() {
        int expectedSize = 7;
        Board board = new Board(expectedSize);
        assertEquals(expectedSize, board.getSize());
    }

    @Test
    void testBoardSizeWithoutParameter() {
        int expectedSize = 6;
        Board board = new Board();
        assertEquals(expectedSize, board.getSize());
    }

    @Test
    void testMakeMoveNotEmpty() throws InvalidMoveException {
        moveParser.makeMove(0, 0, CellState.X);
        assertFalse(board.isCellEmpty(0, 0));
    }

    @Test
    void testConstructor() {
        assertEquals(6, board.getSize());
    }

    @Test
    void testGetMinorDiagonalDescending() {
        try {
            moveParser.makeMove(1, 1, CellState.O);
            moveParser.makeMove(2, 2, CellState.O);
            moveParser.makeMove(3, 3, CellState.O);
            moveParser.makeMove(4, 4, CellState.O);
            moveParser.makeMove(5, 5, CellState.O);

            CellState[] expected = {CellState.O, CellState.O, CellState.O, CellState.O, CellState.O};
            CellState[] diagonal = board.getMinorDiagonal(1, 1, false);
            assertArrayEquals(expected, diagonal);
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetMinorDiagonalAscending() {
        try {
            moveParser.makeMove(4, 1, CellState.X);
            moveParser.makeMove(3, 2, CellState.X);
            moveParser.makeMove(2, 3, CellState.X);
            moveParser.makeMove(1, 4, CellState.X);
            moveParser.makeMove(0, 5, CellState.X);

            CellState[] expected = {CellState.X, CellState.X, CellState.X, CellState.X, CellState.X};
            CellState[] diagonal = board.getMinorDiagonal(4, 1, true);
            assertArrayEquals(expected, diagonal);
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetRow() {
        try {
            moveParser.makeMove(0, 0, CellState.X);
            moveParser.makeMove(0, 1, CellState.O);
            moveParser.makeMove(0, 2, CellState.X);

            CellState[] expected = {CellState.X, CellState.O, CellState.X, CellState.EMPTY, CellState.EMPTY, CellState.EMPTY};
            CellState[] row = board.getRow(0);
            assertArrayEquals(expected, row);
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetCol() {
        try {
            moveParser.makeMove(0, 0, CellState.X);
            moveParser.makeMove(1, 0, CellState.O);
            moveParser.makeMove(2, 0, CellState.X);

            CellState[] expected = {CellState.X, CellState.O, CellState.X, CellState.EMPTY, CellState.EMPTY, CellState.EMPTY};
            CellState[] col = board.getCol(0);
            assertArrayEquals(expected, col);
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetMainDiagonal() {
        try {
            moveParser.makeMove(0, 0, CellState.X);
            moveParser.makeMove(1, 1, CellState.O);
            moveParser.makeMove(2, 2, CellState.X);

            CellState[] expected = {CellState.X, CellState.O, CellState.X, CellState.EMPTY, CellState.EMPTY, CellState.EMPTY};
            CellState[] diagonal = board.getMainDiagonal();
            assertArrayEquals(expected, diagonal);
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetSecondaryDiagonal() {
        try {
            moveParser.makeMove(0, 5, CellState.X);
            moveParser.makeMove(1, 4, CellState.O);
            moveParser.makeMove(2, 3, CellState.X);

            CellState[] expected = {CellState.X, CellState.O, CellState.X, CellState.EMPTY, CellState.EMPTY, CellState.EMPTY};
            CellState[] diagonal = board.getSecondaryDiagonal();
            assertArrayEquals(expected, diagonal);
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testIsFull() {
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                try {
                    board.setCellState(row, col, CellState.X);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        assertTrue(board.isFull());
    }


}