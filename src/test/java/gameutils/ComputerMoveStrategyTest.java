package gameutils;

import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComputerMoveStrategyTest {
    private ComputerMoveStrategy computerMoveStrategy;
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        computerMoveStrategy = new ComputerMoveStrategy(board);
    }

    @Test
    void testMakeComputerMoveOnEmptyBoard() {
        Cell cell = computerMoveStrategy.makeComputerMove(board);
        assertNotNull(cell);
        assertTrue(board.isCellEmpty(cell.getRow(), cell.getCol()));
    }

    @Test
    void testMakeComputerMoveWithExistingTwoSequences() {

        board.setCellState(0, 0, CellState.X);
        board.setCellState(0, 1, CellState.X);
        board.setCellState(1, 0, CellState.O);

        Cell chosenCell = computerMoveStrategy.makeComputerMove(board);

        assertNotNull(chosenCell);
        assertEquals(CellState.O, chosenCell.getState()); // Since best sequence was 'X', chosen cell should be 'O'
    }

    @Test
    void testMakeComputerMoveWithExistingThreeSequences() {

        board.setCellState(0, 0, CellState.X);
        board.setCellState(0, 1, CellState.X);
        board.setCellState(0, 2, CellState.X);
        board.setCellState(1, 0, CellState.O);
        board.setCellState(1, 1, CellState.O);

        Cell chosenCell = computerMoveStrategy.makeComputerMove(board);

        assertNotNull(chosenCell);
        assertEquals(CellState.O, chosenCell.getState()); // Since best sequence was 'X', chosen cell should be 'O'
    }

    @Test
    void testMakeComputerMoveWithExistingFourSequences() {

        board.setCellState(0, 0, CellState.X);
        board.setCellState(0, 1, CellState.X);
        board.setCellState(0, 2, CellState.X);
        board.setCellState(0, 3, CellState.X);
        board.setCellState(1, 0, CellState.O);
        board.setCellState(1, 1, CellState.O);

        Cell chosenCell = computerMoveStrategy.makeComputerMove(board);

        assertNotNull(chosenCell);
        assertEquals(CellState.O, chosenCell.getState()); // Since best sequence was 'X', chosen cell should be 'O'
    }

    @Test
    void testMakeComputerMoveWithNoAvailableSequences() {

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                board.setCellState(i, j, CellState.EMPTY);
            }
        }

        Cell chosenCell = computerMoveStrategy.makeComputerMove(board);

        assertNotNull(chosenCell);
    }
}
