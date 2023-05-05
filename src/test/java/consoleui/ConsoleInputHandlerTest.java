package consoleui;

import entities.CellState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleInputHandlerTest {
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

    @ParameterizedTest
    @MethodSource("provideInputForTesting")
    void testGetValidInput(String simulatedInput, int[] expected) {
        String prompt = "Enter row and column separated by a comma: ";
        int min = 1;
        int max = 10;

        simulateUserInput(simulatedInput);
        ConsoleInputHandler inputHandler = new ConsoleInputHandler();
        int[] result = inputHandler.getValidInput(prompt, min, max);

        assertArrayEquals(expected, result);
    }

    private static Stream<Arguments> provideInputForTesting() {
        return Stream.of(
                Arguments.of("5, 7", new int[]{5, 7}),
                Arguments.of("invalid\n5, 7", new int[]{5, 7}),
                Arguments.of("12, 20\n5, 7", new int[]{5, 7}),
                Arguments.of("a, b\n5, 7", new int[]{5, 7}),
                Arguments.of("5, 7, 9\n5, 7", new int[]{5, 7})
        );
    }



    // Helper methods to simulate user input and capture system out
    private void simulateUserInput(String input) {
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);
    }
}



