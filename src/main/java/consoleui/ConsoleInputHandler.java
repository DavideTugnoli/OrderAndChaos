package consoleui;

import entities.CellState;
import gameutils.MessageBundle;

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

            if (coordinates.length == 0 || isNotValidCoordinates(coordinates, min, max)) {
                System.out.printf(MessageBundle.inputConsoleOutOfBoundMessage(), min, max);
            }
        } while (coordinates.length == 0 || isNotValidCoordinates(coordinates, min, max));

        return coordinates;
    }

    private int[] parseInput(String input) {
        try {
            String[] parts = input.split(",");
            if (parts.length != 2) {
                return new int[0]; // input non valido, ci sono meno o più di due parti
            }
            int row = Integer.parseInt(parts[0].trim());
            int col = Integer.parseInt(parts[1].trim());
            return new int[]{row, col};
        } catch (NumberFormatException e) {
            return new int[0]; // input non valido, una delle parti non è un intero
        }
    }

    private boolean isNotValidCoordinates(int[] coordinates, int min, int max) {
        if (coordinates.length != 2) {
            return true;  // array non valido, non contiene due elementi
        }

        int row = coordinates[0];
        int col = coordinates[1];

        return row < min || row > max || col < min || col > max;
    }

    public CellState getPieceSelection() {
        while (true) {
            System.out.print(MessageBundle.selectConsolePeaceMessage());
            String line = scanner.nextLine();

            CellState piece = parsePieceSelection(line);

            if (piece != null) {
                return piece;
            } else {
                System.out.println(MessageBundle.badSelectionConsolePeaceMessage());
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
