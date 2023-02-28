package consoleui;

import entities.CellState;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleInputHandlerTest {
    @Test
    void getValidInput_returnsValidInput() {
        String input = "1,2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();

        int[] actual = consoleInputHandler.getValidInput("Enter two numbers separated by a comma, each from 1 to 10: ", 1, 10);

        int[] expected = {1, 2};
        assertArrayEquals(expected, actual);
    }

    @Test
    void getPieceSelection_returnsO() {
        String input = "O\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();

        CellState actual = consoleInputHandler.getPieceSelection();

        assertEquals(CellState.O, actual);
    }

    @Test
    void getPieceSelection_returnsX() {
        String input = "X\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();

        CellState actual = consoleInputHandler.getPieceSelection();

        assertEquals(CellState.X, actual);
    }


    @Test
    void getPieceSelection_promptsUserUntilValidInputIsGiven() {
        String input = "invalid\nO\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();

        CellState actual = consoleInputHandler.getPieceSelection();

        assertEquals(CellState.O, actual);
    }

    @Test
    void testClose() {
        InputStream initialIn = System.in;
        ByteArrayInputStream emptyInput = new ByteArrayInputStream("".getBytes());
        System.setIn(emptyInput);
        ConsoleInputHandler inputHandler = new ConsoleInputHandler();
        inputHandler.close();
        assertFalse(new Scanner(System.in).hasNext());
        System.setIn(initialIn);
    }
}



