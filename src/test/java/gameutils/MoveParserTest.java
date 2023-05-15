package gameutils;

import entities.Board;
import entities.Cell;
import entities.CellState;
import exceptions.InvalidMoveException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveParserTest {

    private Board board;
    private MoveParser moveParser;

    @BeforeEach
    void setUp() {
        board = new Board();
        moveParser = new MoveParser(board);
    }

    @Test
    void testValidMoveCell() {
        assertDoesNotThrow(() -> {
            moveParser.makeMove(new Cell(0, 0, CellState.X));
            assertEquals(CellState.X, board.getCellState(0, 0));
        });

        assertDoesNotThrow(() -> {
            moveParser.makeMove(new Cell(1, 1, CellState.O));
            assertEquals(CellState.O, board.getCellState(1, 1));
        });

        assertDoesNotThrow(() -> {
            moveParser.makeMove(new Cell(5, 5, CellState.X));
            assertEquals(CellState.X, board.getCellState(5, 5));
        });
    }

    @Test
    void testInvalidMoveOccupiedCellCell() throws InvalidMoveException {
        moveParser.makeMove(new Cell(0, 0, CellState.X)); // Occupying the cell
        assertThrows(InvalidMoveException.class, () -> moveParser.makeMove(new Cell(0, 0, CellState.O)));
        assertEquals(CellState.X, board.getCell(0, 0).getState());
    }

    @Test
    void testInvalidMoveOutOfBoundsCell() {
        assertThrows(InvalidMoveException.class, () -> moveParser.makeMove(new Cell(6, 6, CellState.X)));
        assertThrows(InvalidMoveException.class, () -> moveParser.makeMove(new Cell(-1, 0, CellState.X)));
        assertThrows(InvalidMoveException.class, () -> moveParser.makeMove(new Cell(0, -1, CellState.X)));
        assertThrows(InvalidMoveException.class, () -> moveParser.makeMove(new Cell(-1, -1, CellState.X)));
    }

    @Test
    void testInvalidMoveDuplicateCell() throws InvalidMoveException {
        moveParser.makeMove(new Cell(0, 0, CellState.X)); // Occupying the cell
        assertThrows(InvalidMoveException.class, () -> moveParser.makeMove(new Cell(0, 0, CellState.X)));
        assertEquals(CellState.X, board.getCell(0, 0).getState());
    }

}