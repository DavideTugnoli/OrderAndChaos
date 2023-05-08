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
        int[] coordinatesAndPiece;

        do {
            System.out.print(prompt);
            String input = scanner.nextLine();

            coordinatesAndPiece = parseInput(input);

            if (isNotValidCoordinates(coordinatesAndPiece, min, max)) {
                System.out.printf(MessageBundle.consoleBadInputMessage(), min, max);
            }
        } while (isNotValidCoordinates(coordinatesAndPiece, min, max));

        return coordinatesAndPiece;
    }

    private int[] parseInput(String input) {
        try {
            String[] parts = input.split(",");
            if (parts.length != 3) {
                return new int[0];
            }

            int row = Integer.parseInt(parts[0].trim());
            int col = Integer.parseInt(parts[1].trim());
            CellState piece = parsePieceSelection(parts[2].trim());

            return (piece == null) ? new int[0] : new int[]{row, col, piece.ordinal()};
        } catch (NumberFormatException e) {
            return new int[0];
        }
    }

    private boolean isNotValidCoordinates(int[] coordinatesAndPiece, int min, int max) {
        if (coordinatesAndPiece.length != 3) {
            return true;
        }

        int row = coordinatesAndPiece[0];
        int col = coordinatesAndPiece[1];

        return row < min || row > max || col < min || col > max;
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
