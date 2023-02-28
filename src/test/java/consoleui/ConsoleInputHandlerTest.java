package consoleui;

import entities.CellState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleInputHandlerTest {

    @Test
    void testGetValidInputValid() {
        String prompt = "Enter row and column separated by a comma: ";
        int min = 1;
        int max = 10;
        int[] expected = {5, 7};
        simulateUserInput("5, 7");
        ConsoleInputHandler inputHandler = new ConsoleInputHandler();
        int[] result = inputHandler.getValidInput(prompt, min, max);
        assertArrayEquals(expected, result);
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

    @ParameterizedTest
    @ValueSource(strings = {"O", "X"})
    void testGetPieceSelectionValid(String input) {
        simulateUserInput(input);
        ConsoleInputHandler inputHandler = new ConsoleInputHandler();
        CellState result = inputHandler.getPieceSelection();
        assertEquals(input.equalsIgnoreCase("O") ? CellState.O : CellState.X, result);
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



    // Helper methods to simulate user input and capture system out
    private void simulateUserInput(String input) {
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);
    }

    private String systemOut() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        return outContent.toString();
    }

}



