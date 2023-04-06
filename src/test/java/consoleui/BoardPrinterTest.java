package consoleui;

import entities.Board;
import entities.Cell;
import exceptions.InvalidMoveException;
import entities.CellState;
import gameutils.MoveParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
class BoardPrinterTest {

    private BoardPrinter printer;
    private Board board;
    private ByteArrayOutputStream outContent;

    private MoveParser moveParser;

    @BeforeEach
    void setUp() {
        board = new Board(6);
        printer = new BoardPrinter();
        moveParser = new MoveParser(board);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testPrintEmptyBoard() {
        printer.printBoard(board);
        String expectedOutput = """
                   -------------------------
                 1 |   |   |   |   |   |   |
                   -------------------------
                 2 |   |   |   |   |   |   |
                   -------------------------
                 3 |   |   |   |   |   |   |
                   -------------------------
                 4 |   |   |   |   |   |   |
                   -------------------------
                 5 |   |   |   |   |   |   |
                   -------------------------
                 6 |   |   |   |   |   |   |
                   -------------------------
                     1   2   3   4   5   6\s
                """;
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testPrintBoardWithOneMove() throws InvalidMoveException {
        moveParser.makeMove(new Cell(0, 0, CellState.X));
        printer.printBoard(board);
        String expectedOutput =
                """
                           -------------------------
                         1 | X |   |   |   |   |   |
                           -------------------------
                         2 |   |   |   |   |   |   |
                           -------------------------
                         3 |   |   |   |   |   |   |
                           -------------------------
                         4 |   |   |   |   |   |   |
                           -------------------------
                         5 |   |   |   |   |   |   |
                           -------------------------
                         6 |   |   |   |   |   |   |
                           -------------------------
                             1   2   3   4   5   6\s
                        """;
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testPrintBoardWithTwoMoves() throws InvalidMoveException {
        moveParser.makeMove(new Cell(0, 0, CellState.X));
        moveParser.makeMove(new Cell(0, 1, CellState.O));
        printer.printBoard(board);
        String expectedOutput =
                """
                           -------------------------
                         1 | X | O |   |   |   |   |
                           -------------------------
                         2 |   |   |   |   |   |   |
                           -------------------------
                         3 |   |   |   |   |   |   |
                           -------------------------
                         4 |   |   |   |   |   |   |
                           -------------------------
                         5 |   |   |   |   |   |   |
                           -------------------------
                         6 |   |   |   |   |   |   |
                           -------------------------
                             1   2   3   4   5   6\s
                        """;
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testPrintBoardWithMoves() {
        try {
            moveParser.makeMove(new Cell(0, 0, CellState.X));
            moveParser.makeMove(new Cell(1, 1, CellState.O));
            moveParser.makeMove(new Cell(2, 2, CellState.X));
            moveParser.makeMove(new Cell(3, 3, CellState.O));
            moveParser.makeMove(new Cell(4, 4, CellState.X));
            moveParser.makeMove(new Cell(5, 5, CellState.O));
            moveParser.makeMove(new Cell(5, 4, CellState.X));
            moveParser.makeMove(new Cell(4, 5, CellState.O));
            moveParser.makeMove(new Cell(0, 1, CellState.X));
            moveParser.makeMove(new Cell(0, 2, CellState.O));
            moveParser.makeMove(new Cell(0, 3, CellState.X));
            moveParser.makeMove(new Cell(0, 4, CellState.O));
            moveParser.makeMove(new Cell(0, 5, CellState.X));
            moveParser.makeMove(new Cell(2, 0, CellState.O));
            moveParser.makeMove(new Cell(3, 0, CellState.X));
            moveParser.makeMove(new Cell(4, 0, CellState.O));
            moveParser.makeMove(new Cell(5, 0, CellState.X));
            moveParser.makeMove(new Cell(2, 5, CellState.O));
            moveParser.makeMove(new Cell(3, 4, CellState.X));
            moveParser.makeMove(new Cell(4, 3, CellState.O));
            moveParser.makeMove(new Cell(5, 2, CellState.X));
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        }
        printer.printBoard(board);
        String expectedOutput =
                """
                           -------------------------
                         1 | X | X | O | X | O | X |
                           -------------------------
                         2 |   | O |   |   |   |   |
                           -------------------------
                         3 | O |   | X |   |   | O |
                           -------------------------
                         4 | X |   |   | O | X |   |
                           -------------------------
                         5 | O |   |   | O | X | O |
                           -------------------------
                         6 | X |   | X |   | X | O |
                           -------------------------
                             1   2   3   4   5   6\s
                        """;
        assertEquals(expectedOutput, outContent.toString());
    }

}
