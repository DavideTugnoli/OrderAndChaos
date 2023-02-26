package consolui;

import consoleui.BoardPrinter;
import entities.Board;
import exceptions.InvalidMoveException;
import entities.CellState;
import org.junit.jupiter.api.Test;

public class BoardPrinterTest {
    @Test
    void testPrintEmptyBoard() {
        Board board = new Board(6);
        BoardPrinter printer = new BoardPrinter();
        printer.printBoard(board);
    }

    @Test
    void testPrintBoardWithOneMove() throws InvalidMoveException {
        Board board = new Board(6);
        board.makeMove(0, 0, CellState.X);
        BoardPrinter printer = new BoardPrinter();
        printer.printBoard(board);
    }

    @Test
    void testPrintBoardWithTwoMoves() throws InvalidMoveException {
        Board board = new Board(6);
        board.makeMove(0, 0, CellState.X);
        board.makeMove(0, 1, CellState.O);
        BoardPrinter printer = new BoardPrinter();
        printer.printBoard(board);
    }

    @Test
    void testPrintBoardWithMoves() throws InvalidMoveException {
        Board board = new Board(6);
        board.makeMove(0, 0, CellState.X);
        board.makeMove(1, 1, CellState.O);
        board.makeMove(2, 2, CellState.X);
        board.makeMove(3, 3, CellState.O);
        board.makeMove(4, 4, CellState.X);
        board.makeMove(5, 5, CellState.O);
        board.makeMove(5, 4, CellState.X);
        board.makeMove(4, 5, CellState.O);
        board.makeMove(0, 1, CellState.X);
        board.makeMove(0, 2, CellState.O);
        board.makeMove(0, 3, CellState.X);
        board.makeMove(0, 4, CellState.O);
        board.makeMove(0, 5, CellState.X);
        board.makeMove(2, 0, CellState.O);
        board.makeMove(3, 0, CellState.X);
        board.makeMove(4, 0, CellState.O);
        board.makeMove(5, 0, CellState.X);
        board.makeMove(2, 5, CellState.O);
        board.makeMove(3, 4, CellState.X);
        board.makeMove(4, 3, CellState.O);
        board.makeMove(5, 2, CellState.X);

        BoardPrinter printer = new BoardPrinter();
        printer.printBoard(board);
    }

}
