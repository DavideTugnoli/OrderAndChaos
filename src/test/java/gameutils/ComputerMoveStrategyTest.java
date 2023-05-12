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
    void testMakeComputerMoveWithExistingSequences() {
        // Initialize the board such that we have a sequence of 3 cells of type 'X' and a sequence of 2 cells of type 'O'
        // This setup should give preference to the sequence of 'X' when selecting the best move
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
    void testMakeComputerMoveWithNoAvailableSequences() {
        // Initialize the board such that we have no sequence of cells
        // This setup should result in a random cell
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                board.setCellState(i, j, CellState.EMPTY);
            }
        }

        Cell chosenCell = computerMoveStrategy.makeComputerMove(board);

        assertNotNull(chosenCell);
        // Can't assert on the state of the cell as it is chosen randomly
    }
}
