package entities;

import gameutils.MoveParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import exceptions.InvalidMoveException;

import java.util.List;

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
            moveParser.makeMove(new Cell(0, 0, CellState.X));
            assertEquals(CellState.X, board.getCell(0, 0).getState());
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        }
    }


    @Test
    void testInvalidMove() {
        try {
            moveParser.makeMove(new Cell(0, 0, CellState.X));
            assertThrows(InvalidMoveException.class, () -> moveParser.makeMove(new Cell(0, 0, CellState.O)));
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testOccupiedCellMove() {
        try {
            moveParser.makeMove(new Cell(0, 0, CellState.X));
            assertThrows(InvalidMoveException.class, () -> moveParser.makeMove(new Cell(0, 0, CellState.O)));
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        }
    }


    @Test
    void testOutOfBoundsMove() {
        assertThrows(InvalidMoveException.class, () -> moveParser.makeMove(new Cell(-1, 0, CellState.X)));
        assertThrows(InvalidMoveException.class, () -> moveParser.makeMove(new Cell(0, -1, CellState.X)));
        assertThrows(InvalidMoveException.class, () -> moveParser.makeMove(new Cell(board.getSize(), 0, CellState.X)));
        assertThrows(InvalidMoveException.class, () -> moveParser.makeMove(new Cell(0, board.getSize(), CellState.X)));
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
        moveParser.makeMove(new Cell(0, 0, CellState.X));
        assertFalse(board.isCellEmpty(0, 0));
    }

    @Test
    void testConstructor() {
        assertEquals(6, board.getSize());
    }

    @Test
    void testGetMinorDiagonalDescending() {
        try {
            moveParser.makeMove(new Cell(1, 1, CellState.O));
            moveParser.makeMove(new Cell(2, 2, CellState.O));
            moveParser.makeMove(new Cell(3, 3, CellState.O));
            moveParser.makeMove(new Cell(4, 4, CellState.O));
            moveParser.makeMove(new Cell(5, 5, CellState.O));

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
            moveParser.makeMove(new Cell(4, 1, CellState.X));
            moveParser.makeMove(new Cell(3, 2, CellState.X));
            moveParser.makeMove(new Cell(2, 3, CellState.X));
            moveParser.makeMove(new Cell(1, 4, CellState.X));
            moveParser.makeMove(new Cell(0, 5, CellState.X));

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
            moveParser.makeMove(new Cell(0, 0, CellState.X));
            moveParser.makeMove(new Cell(0, 1, CellState.O));
            moveParser.makeMove(new Cell(0, 2, CellState.X));

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
            moveParser.makeMove(new Cell(0, 0, CellState.X));
            moveParser.makeMove(new Cell(1, 0, CellState.O));
            moveParser.makeMove(new Cell(2, 0, CellState.X));

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
            moveParser.makeMove(new Cell(0, 0, CellState.X));
            moveParser.makeMove(new Cell(1, 1, CellState.O));
            moveParser.makeMove(new Cell(2, 2, CellState.X));

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
            moveParser.makeMove(new Cell(0, 5, CellState.X));
            moveParser.makeMove(new Cell(1, 4, CellState.O));
            moveParser.makeMove(new Cell(2, 3, CellState.X));

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

    @Test
    void testGetAvailableCells() {
        try {
            moveParser.makeMove(new Cell(0, 0, CellState.X));
            moveParser.makeMove(new Cell(1, 1, CellState.O));

            List<Cell> availableCells = board.getAvailableCells();
            assertEquals((board.getSize() * board.getSize()) - 2, availableCells.size());
            assertFalse(availableCells.contains(new Cell(0, 0)));
            assertFalse(availableCells.contains(new Cell(1, 1)));
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSetCellState() {
        board.setCellState(0, 0, CellState.X);
        assertEquals(CellState.X, board.getCellState(0, 0));
    }

    @Test
    void testIsCellEmpty() {
        assertTrue(board.isCellEmpty(0, 0));
        board.setCellState(0, 0, CellState.O);
        assertFalse(board.isCellEmpty(0, 0));
    }

    @Test
    void testInitializeBoard() {
        Board newBoard = new Board(3);
        for (int row = 0; row < newBoard.getSize(); row++) {
            for (int col = 0; col < newBoard.getSize(); col++) {
                assertEquals(CellState.EMPTY, newBoard.getCellState(row, col));
            }
        }
    }

    @Test
    void testGetCellOutOfBounds() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> board.getCell(-1, 0));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> board.getCell(0, -1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> board.getCell(board.getSize(), 0));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> board.getCell(0, board.getSize()));
    }

    @Test
    void testSetCellStateOutOfBounds() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> board.setCellState(-1, 0, CellState.X));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> board.setCellState(0, -1, CellState.X));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> board.setCellState(board.getSize(), 0, CellState.X));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> board.setCellState(0, board.getSize(), CellState.X));
    }

    @Test
    void testNotFull() {
        assertFalse(board.isFull());
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                if (row == board.getSize() - 1 && col == board.getSize() - 1) {
                    break;
                }
                board.setCellState(row, col, CellState.X);
            }
        }
        assertFalse(board.isFull());
    }

}