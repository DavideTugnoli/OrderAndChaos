package gameutils;

import org.junit.jupiter.api.Test;
import entities.Board;
import entities.CellState;
import exceptions.InvalidMoveException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class BoardCheckerTest {
    private BoardChecker checker;
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(6);
        checker = new BoardChecker(board);
    }

    @Test
    void testIsOrderWinnerHorizontal() throws InvalidMoveException {
        board.makeMove(0, 0, CellState.X);
        board.makeMove(0, 1, CellState.X);
        board.makeMove(0, 2, CellState.X);
        board.makeMove(0, 3, CellState.X);
        board.makeMove(0, 4, CellState.X);

        assertTrue(checker.isOrderWinner());
    }

    @Test
    void testIsOrderWinnerVertical() throws InvalidMoveException {
        board.makeMove(0, 0, CellState.X);
        board.makeMove(1, 0, CellState.X);
        board.makeMove(2, 0, CellState.X);
        board.makeMove(3, 0, CellState.X);
        board.makeMove(4, 0, CellState.X);

        assertTrue(checker.isOrderWinner());
    }

    @Test
    void testIsOrderWinnerDiagonal() throws InvalidMoveException {
        board.makeMove(0, 0, CellState.X);
        board.makeMove(1, 1, CellState.X);
        board.makeMove(2, 2, CellState.X);
        board.makeMove(3, 3, CellState.X);
        board.makeMove(4, 4, CellState.X);

        assertTrue(checker.isOrderWinner());
    }

    @Test
    void testIsNotChaosWinner() throws InvalidMoveException {
        board.makeMove(0, 0, CellState.X);
        board.makeMove(0, 1, CellState.X);
        board.makeMove(0, 2, CellState.X);
        board.makeMove(0, 3, CellState.X);
        board.makeMove(0, 4, CellState.X);
        board.makeMove(0, 5, CellState.X);

        assertFalse(checker.isChaosWinner());
    }

    @Test
    void isNotChaosWinner() throws InvalidMoveException {
        board.makeMove(0, 0, CellState.X);
        board.makeMove(0, 1, CellState.X);
        board.makeMove(0, 2, CellState.X);
        board.makeMove(0, 3, CellState.X);
        board.makeMove(0, 4, CellState.X);

        assertFalse(checker.isChaosWinner());
    }

    @Test
    void testIsOrderWinnerInRow() throws InvalidMoveException {
        board.makeMove(0, 0, CellState.X);
        board.makeMove(0, 1, CellState.X);
        board.makeMove(0, 2, CellState.X);
        board.makeMove(0, 3, CellState.X);
        board.makeMove(0, 4, CellState.X);

        assertTrue(checker.isOrderWinner());
    }

    @Test
    void testIsOrderWinnerInCol() throws InvalidMoveException {
        board.makeMove(0, 0, CellState.X);
        board.makeMove(1, 0, CellState.X);
        board.makeMove(2, 0, CellState.X);
        board.makeMove(3, 0, CellState.X);
        board.makeMove(4, 0, CellState.X);

        assertTrue(checker.isOrderWinner());
    }

    @Test
    void testIsOrderWinnerInDiagonal() throws InvalidMoveException {
        board.makeMove(0, 0, CellState.X);
        board.makeMove(1, 1, CellState.X);
        board.makeMove(2, 2, CellState.X);
        board.makeMove(3, 3, CellState.X);
        board.makeMove(4, 4, CellState.X);

        assertTrue(checker.isOrderWinner());
    }

    @Test
    void testIsOrderWinnerInAntiDiagonal() throws InvalidMoveException {
        board.makeMove(0, 5, CellState.X);
        board.makeMove(1, 4, CellState.X);
        board.makeMove(2, 3, CellState.X);
        board.makeMove(3, 2, CellState.X);
        board.makeMove(4, 1, CellState.X);

        assertTrue(checker.isOrderWinner());
    }

    @Test
    void testIsChaosWinner() throws InvalidMoveException {
        board.makeMove(0, 0, CellState.X);
        board.makeMove(0, 1, CellState.O);
        board.makeMove(0, 2, CellState.X);
        board.makeMove(0, 3, CellState.O);
        board.makeMove(0, 4, CellState.X);
        board.makeMove(0, 5, CellState.X);
        board.makeMove(1, 0, CellState.X);
        board.makeMove(1, 1, CellState.O);
        board.makeMove(1, 2, CellState.X);
        board.makeMove(1, 3, CellState.O);
        board.makeMove(1, 4, CellState.X);
        board.makeMove(1, 5, CellState.O);
        board.makeMove(2, 0, CellState.O);
        board.makeMove(2, 1, CellState.X);
        board.makeMove(2, 2, CellState.X);
        board.makeMove(2, 3, CellState.X);
        board.makeMove(2, 4, CellState.X);
        board.makeMove(2, 5, CellState.O);
        board.makeMove(3, 0, CellState.X);
        board.makeMove(3, 1, CellState.O);
        board.makeMove(3, 2, CellState.X);
        board.makeMove(3, 3, CellState.O);
        board.makeMove(3, 4, CellState.O);
        board.makeMove(3, 5, CellState.O);
        board.makeMove(4, 0, CellState.X);
        board.makeMove(4, 1, CellState.O);
        board.makeMove(4, 2, CellState.O);
        board.makeMove(4, 3, CellState.O);
        board.makeMove(4, 4, CellState.X);
        board.makeMove(4, 5, CellState.O);
        board.makeMove(5, 0, CellState.X);
        board.makeMove(5, 1, CellState.O);
        board.makeMove(5, 2, CellState.X);
        board.makeMove(5, 3, CellState.O);
        board.makeMove(5, 4, CellState.X);
        board.makeMove(5, 5, CellState.X);
        // The board should now be full
        assertTrue(board.isFull());
       /* BoardPrinter printer = new BoardPrinter();
        printer.printBoard(board);*/
        assertTrue(checker.isChaosWinner());
    }
}
