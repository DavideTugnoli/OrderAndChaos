package consolui;

import consoleui.BoardPrinter;
import entities.Board;
import exceptions.InvalidMoveException;
import entities.CellState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
 class BoardPrinterTest {

     private BoardPrinter printer;
     private Board board;
     private ByteArrayOutputStream outContent;

     @BeforeEach
     void setUp() {
         board = new Board(6);
         printer = new BoardPrinter();
         outContent = new ByteArrayOutputStream();
         System.setOut(new PrintStream(outContent));
     }

    @Test
    void testPrintEmptyBoard() {
        printer.printBoard(board);
        String expectedOutput = "   -------------------------\n" +
                                " 1 |   |   |   |   |   |   |\n" +
                                "   -------------------------\n" +
                                " 2 |   |   |   |   |   |   |\n" +
                                "   -------------------------\n" +
                                " 3 |   |   |   |   |   |   |\n" +
                                "   -------------------------\n" +
                                " 4 |   |   |   |   |   |   |\n" +
                                "   -------------------------\n" +
                                " 5 |   |   |   |   |   |   |\n" +
                                "   -------------------------\n" +
                                " 6 |   |   |   |   |   |   |\n" +
                                "   -------------------------\n" +
                                "     1   2   3   4   5   6 \n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testPrintBoardWithOneMove() throws InvalidMoveException {
        board.makeMove(0, 0, CellState.X);
        printer.printBoard(board);
        String expectedOutput =
                "   -------------------------\n" +
                " 1 | X |   |   |   |   |   |\n" +
                "   -------------------------\n" +
                " 2 |   |   |   |   |   |   |\n" +
                "   -------------------------\n" +
                " 3 |   |   |   |   |   |   |\n" +
                "   -------------------------\n" +
                " 4 |   |   |   |   |   |   |\n" +
                "   -------------------------\n" +
                " 5 |   |   |   |   |   |   |\n" +
                "   -------------------------\n" +
                " 6 |   |   |   |   |   |   |\n" +
                "   -------------------------\n" +
                "     1   2   3   4   5   6 \n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testPrintBoardWithTwoMoves() throws InvalidMoveException {
        board.makeMove(0, 0, CellState.X);
        board.makeMove(0, 1, CellState.O);
        printer.printBoard(board);
        String expectedOutput =
                "   -------------------------\n" +
                " 1 | X | O |   |   |   |   |\n" +
                "   -------------------------\n" +
                " 2 |   |   |   |   |   |   |\n" +
                "   -------------------------\n" +
                " 3 |   |   |   |   |   |   |\n" +
                "   -------------------------\n" +
                " 4 |   |   |   |   |   |   |\n" +
                "   -------------------------\n" +
                " 5 |   |   |   |   |   |   |\n" +
                "   -------------------------\n" +
                " 6 |   |   |   |   |   |   |\n" +
                "   -------------------------\n" +
                "     1   2   3   4   5   6 \n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testPrintBoardWithMoves() throws InvalidMoveException {
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
        printer.printBoard(board);
        String expectedOutput =
                "   -------------------------\n" +
                " 1 | X | X | O | X | O | X |\n" +
                "   -------------------------\n" +
                " 2 |   | O |   |   |   |   |\n" +
                "   -------------------------\n" +
                " 3 | O |   | X |   |   | O |\n" +
                "   -------------------------\n" +
                " 4 | X |   |   | O | X |   |\n" +
                "   -------------------------\n" +
                " 5 | O |   |   | O | X | O |\n" +
                "   -------------------------\n" +
                " 6 | X |   | X |   | X | O |\n" +
                "   -------------------------\n" +
                "     1   2   3   4   5   6 \n";
        assertEquals(expectedOutput, outContent.toString());
    }

}
