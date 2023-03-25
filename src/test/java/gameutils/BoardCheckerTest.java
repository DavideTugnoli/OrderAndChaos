package gameutils;

import org.junit.jupiter.api.Test;
import entities.Board;
import entities.CellState;
import exceptions.InvalidMoveException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

class BoardCheckerTest {
    private BoardChecker checker;
    private Board board;

    private MoveParser moveParser;

    @BeforeEach
    void setUp() {
        board = new Board(6);
        checker = new BoardChecker(board);
        moveParser = new MoveParser(board);
    }

    @Test
    void testIsOrderWinnerHorizontal() {
        try {
            moveParser.makeMove(0, 0, CellState.X);
            moveParser.makeMove(0, 1, CellState.X);
            moveParser.makeMove(0, 2, CellState.X);
            moveParser.makeMove(0, 3, CellState.X);
            moveParser.makeMove(0, 4, CellState.X);
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        }
        assertTrue(checker.isOrderWinner());
    }

    @Test
    void testIsOrderWinnerVertical() {
        try {
            moveParser.makeMove(0, 0, CellState.X);
            moveParser.makeMove(1, 0, CellState.X);
            moveParser.makeMove(2, 0, CellState.X);
            moveParser.makeMove(3, 0, CellState.X);
            moveParser.makeMove(4, 0, CellState.X);
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        }
        assertTrue(checker.isOrderWinner());
    }

    @Test
    void testIsOrderWinnerDiagonal() {
        try {
            moveParser.makeMove(0, 0, CellState.X);
            moveParser.makeMove(1, 1, CellState.X);
            moveParser.makeMove(2, 2, CellState.X);
            moveParser.makeMove(3, 3, CellState.X);
            moveParser.makeMove(4, 4, CellState.X);
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        }

        assertTrue(checker.isOrderWinner());
    }

    @Test
    void testIsNotChaosWinner() {
        try {
            moveParser.makeMove(0, 0, CellState.X);
            moveParser.makeMove(0, 1, CellState.X);
            moveParser.makeMove(0, 2, CellState.X);
            moveParser.makeMove(0, 3, CellState.X);
            moveParser.makeMove(0, 4, CellState.X);
            moveParser.makeMove(0, 5, CellState.X);
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        }
        assertFalse(checker.isChaosWinner());
    }

    @Test
    void isNotChaosWinner() {
        try {
            moveParser.makeMove(0, 0, CellState.X);
            moveParser.makeMove(0, 1, CellState.X);
            moveParser.makeMove(0, 2, CellState.X);
            moveParser.makeMove(0, 3, CellState.X);
            moveParser.makeMove(0, 4, CellState.X);
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        }
        assertFalse(checker.isChaosWinner());
    }

    @Test
    void testIsOrderWinnerInRow() {
        try {
            moveParser.makeMove(0, 0, CellState.X);
            moveParser.makeMove(0, 1, CellState.X);
            moveParser.makeMove(0, 2, CellState.X);
            moveParser.makeMove(0, 3, CellState.X);
            moveParser.makeMove(0, 4, CellState.X);
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        }
        assertTrue(checker.isOrderWinner());
    }

    @Test
    void testIsOrderWinnerInCol() {
        try {
            moveParser.makeMove(0, 0, CellState.X);
            moveParser.makeMove(1, 0, CellState.X);
            moveParser.makeMove(2, 0, CellState.X);
            moveParser.makeMove(3, 0, CellState.X);
            moveParser.makeMove(4, 0, CellState.X);
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        }
        assertTrue(checker.isOrderWinner());
    }

    @Test
    void testIsOrderWinnerInDiagonal() {
        try {
            moveParser.makeMove(0, 0, CellState.X);
            moveParser.makeMove(1, 1, CellState.X);
            moveParser.makeMove(2, 2, CellState.X);
            moveParser.makeMove(3, 3, CellState.X);
            moveParser.makeMove(4, 4, CellState.X);
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        }
        assertTrue(checker.isOrderWinner());
    }

    @Test
    void testIsOrderWinnerInAntiDiagonal() {
        try {
            moveParser.makeMove(0, 5, CellState.X);
            moveParser.makeMove(1, 4, CellState.X);
            moveParser.makeMove(2, 3, CellState.X);
            moveParser.makeMove(3, 2, CellState.X);
            moveParser.makeMove(4, 1, CellState.X);
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        }
        assertTrue(checker.isOrderWinner());
    }

    @Test
    void testIsChaosWinner() {
        fillBoardWithMoves();
        assertTrue(board.isFull());
        assertTrue(checker.isChaosWinner());
    }

    @Test
    void testIsGameOverWhenBoardIsFull() {
        // Riempie il tabellone con i movimenti di entrambi i giocatori
        fillBoardWithMoves();
        assertTrue(checker.isGameOver());
    }

    public void fillBoardWithMoves() {
        try {
            moveParser.makeMove(0, 0, CellState.X);
            moveParser.makeMove(0, 1, CellState.O);
            moveParser.makeMove(0, 2, CellState.X);
            moveParser.makeMove(0, 3, CellState.O);
            moveParser.makeMove(0, 4, CellState.X);
            moveParser.makeMove(0, 5, CellState.X);
            moveParser.makeMove(1, 0, CellState.X);
            moveParser.makeMove(1, 1, CellState.O);
            moveParser.makeMove(1, 2, CellState.X);
            moveParser.makeMove(1, 3, CellState.O);
            moveParser.makeMove(1, 4, CellState.X);
            moveParser.makeMove(1, 5, CellState.O);
            moveParser.makeMove(2, 0, CellState.O);
            moveParser.makeMove(2, 1, CellState.X);
            moveParser.makeMove(2, 2, CellState.X);
            moveParser.makeMove(2, 3, CellState.X);
            moveParser.makeMove(2, 4, CellState.X);
            moveParser.makeMove(2, 5, CellState.O);
            moveParser.makeMove(3, 0, CellState.X);
            moveParser.makeMove(3, 1, CellState.O);
            moveParser.makeMove(3, 2, CellState.X);
            moveParser.makeMove(3, 3, CellState.O);
            moveParser.makeMove(3, 4, CellState.O);
            moveParser.makeMove(3, 5, CellState.O);
            moveParser.makeMove(4, 0, CellState.X);
            moveParser.makeMove(4, 1, CellState.O);
            moveParser.makeMove(4, 2, CellState.O);
            moveParser.makeMove(4, 3, CellState.O);
            moveParser.makeMove(4, 4, CellState.X);
            moveParser.makeMove(4, 5, CellState.O);
            moveParser.makeMove(5, 0, CellState.X);
            moveParser.makeMove(5, 1, CellState.O);
            moveParser.makeMove(5, 2, CellState.X);
            moveParser.makeMove(5, 3, CellState.O);
            moveParser.makeMove(5, 4, CellState.X);
            moveParser.makeMove(5, 5, CellState.X);
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        }
    }

}

