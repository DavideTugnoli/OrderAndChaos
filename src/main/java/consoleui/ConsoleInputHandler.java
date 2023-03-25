package consoleui;

import entities.CellState;
import gameutils.MessagePrinter;

import java.util.Scanner;

public class ConsoleInputHandler {
    private final Scanner scanner;

    public ConsoleInputHandler() {
        scanner = new Scanner(System.in);
    }

    public int[] getValidInput(String prompt, int min, int max) {
        int[] coordinates;

        do {
            System.out.print(prompt);
            String input = scanner.nextLine();

            coordinates = parseInput(input);

            System.out.printf(MessagePrinter.inputConsoleOutOfBoundMessage(), min, max);
        } while (coordinates == null || !isValidCoordinates(coordinates, min, max));

        return coordinates;
    }

    private int[] parseInput(String input) {
        try {
            String[] parts = input.split(",");
            int row = Integer.parseInt(parts[0].trim());
            int col = Integer.parseInt(parts[1].trim());

            return new int[]{row, col};
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println(MessagePrinter.invalidConsoleInputMessage());
            return null;
        }
    }

    private boolean isValidCoordinates(int[] coordinates, int min, int max) {
        int row = coordinates[0];
        int col = coordinates[1];

        return row >= min && row <= max && col >= min && col <= max;
    }

    public CellState getPieceSelection() {
        while (true) {
            System.out.print(MessagePrinter.selectConsolePeaceMessage());
            String line = scanner.nextLine();

            CellState piece = parsePieceSelection(line);

            if (piece != null) {
                return piece;
            } else {
                System.out.println(MessagePrinter.badSelectionConsolePeaceMessage());
            }
        }
    }

    private CellState parsePieceSelection(String input) {
        if (input.equalsIgnoreCase("O")) {
            return CellState.O;
        } else if (input.equalsIgnoreCase("X")) {
            return CellState.X;
        }

        return null;
    }

    public void close() {
        scanner.close();
    }
}
