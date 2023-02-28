package consoleui;

import entities.CellState;

import java.util.Scanner;

public class ConsoleInputHandler {
    private final Scanner scanner;

    public ConsoleInputHandler() {
        scanner = new Scanner(System.in);
    }

    public int[] getValidInput(String prompt, int min, int max) {
        int row;
        int col;

        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();

            try {
                String[] parts = input.split(",");
                row = Integer.parseInt(parts[0].trim());
                col = Integer.parseInt(parts[1].trim());
                if (row < min || row > max || col < min || col > max) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Invalid input. Please enter two numbers separated by a comma, each from %d to %d.%n", min, max);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid input. Please enter two numbers separated by a comma.");
            }
        }

        return new int[]{row, col};
    }

    public CellState getPieceSelection() {
        CellState piece = null;

        while (piece == null) {
            System.out.print("Enter piece (O or X): ");
            String line = scanner.nextLine();

            if (line.equalsIgnoreCase("O")) {
                piece = CellState.O;
            } else if (line.equalsIgnoreCase("X")) {
                piece = CellState.X;
            } else {
                System.out.println("Invalid input. Please enter O or X.");
            }
        }

        return piece;
    }

    public void close() {
        scanner.close();
    }
}
