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
        int expectedSize = 6;
        Board board = new Board(expectedSize);
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

}