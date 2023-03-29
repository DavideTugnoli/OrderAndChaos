package gameutils;

import entities.Board;
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
    void testValidMove() {
        assertDoesNotThrow(() -> {
            moveParser.makeMove(0, 0, CellState.X);
            assertEquals(CellState.X, board.getCellState(0, 0));
        });

        assertDoesNotThrow(() -> {
            moveParser.makeMove(1, 1, CellState.O);
            assertEquals(CellState.O, board.getCellState(1, 1));
        });

        assertDoesNotThrow(() -> {
            moveParser.makeMove(5, 5, CellState.X);
            assertEquals(CellState.X, board.getCellState(5, 5));
        });
    }

    @Test
    void testInvalidMoveOccupiedCell() throws InvalidMoveException {
        moveParser.makeMove(0, 0, CellState.X); // Occupying the cell
        assertThrows(InvalidMoveException.class, () -> moveParser.makeMove(0, 0, CellState.O));
        assertEquals(CellState.X, board.getCell(0, 0).getState());
    }

    @Test
    void testInvalidMoveOutOfBounds() {
        assertThrows(InvalidMoveException.class, () -> moveParser.makeMove(6, 6, CellState.X));
        assertThrows(InvalidMoveException.class, () -> moveParser.makeMove(-1, 0, CellState.X));
        assertThrows(InvalidMoveException.class, () -> moveParser.makeMove(0, -1, CellState.X));
        assertThrows(InvalidMoveException.class, () -> moveParser.makeMove(-1, -1, CellState.X));
    }

}