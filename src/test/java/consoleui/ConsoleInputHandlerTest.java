package consoleui;

import entities.CellState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleInputHandlerTest {

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
        String prompt = "Enter row, column, and piece (X or O) separated by commas: ";
        int min = 1;
        int max = 10;

        simulateUserInput(simulatedInput);
        ConsoleInputHandler inputHandler = new ConsoleInputHandler();
        int[] result = inputHandler.getValidInput(prompt, min, max);

        assertArrayEquals(expected, result);
    }

    @Test
    void testGetValidInputAfterInvalidInput() {
        String prompt = "Enter row, column, and piece (X or O) separated by commas: ";
        int min = 1;
        int max = 6;
        String simulatedInput = "7, 7, O\n5, 5, X";  // primo input non valido, secondo valido
        int[] expected = {5, 5, CellState.X.ordinal()};

        simulateUserInput(simulatedInput);
        ConsoleInputHandler inputHandler = new ConsoleInputHandler();
        int[] result = inputHandler.getValidInput(prompt, min, max);

        assertArrayEquals(expected, result);
    }

    private static Stream<Arguments> provideInputForTesting() {
        return Stream.of(
                Arguments.of("5, 7, O", new int[]{5, 7, CellState.O.ordinal()}),
                Arguments.of("invalid\n5, 7, X", new int[]{5, 7, CellState.X.ordinal()}),
                Arguments.of("12, 20, O\n5, 7, O", new int[]{5, 7, CellState.O.ordinal()}),
                Arguments.of("a, b, X\n5, 7, X", new int[]{5, 7, CellState.X.ordinal()}),
                Arguments.of("5, 7, 9, O\n5, 7, O", new int[]{5, 7, CellState.O.ordinal()}),
                Arguments.of("5, 7, A\n5, 7, O", new int[]{5, 7, CellState.O.ordinal()}),
                Arguments.of("  \n5, 7, O", new int[]{5, 7, CellState.O.ordinal()}),
                Arguments.of("5 ,  7 ,  X", new int[]{5, 7, CellState.X.ordinal()}),
                Arguments.of("11, 7, O\n5, 7, O", new int[]{5, 7, CellState.O.ordinal()}),
                Arguments.of("5, 0, X\n5, 7, X", new int[]{5, 7, CellState.X.ordinal()}),
                Arguments.of("5, 7, x", new int[]{5, 7, CellState.X.ordinal()}),
                Arguments.of("5, 7, o", new int[]{5, 7, CellState.O.ordinal()})
        );
    }

    // Helper methods to simulate user input and capture system out
    private void simulateUserInput(String input) {
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);
    }
}