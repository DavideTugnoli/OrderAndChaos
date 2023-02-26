package consoleui;

import entities.CellState;
import entities.Board;

public class BoardPrinter {

    private static final String HORIZONTAL_LINE = "----";
    private static final String VERTICAL_LINE = "|";
    private static final String EMPTY_CELL = "   ";

    public void printBoard(Board board) {
        int size = board.getSize();
        printHorizontalLine(size);
        for (int i = 0; i < size; i++) {
            System.out.print(" " + (i + 1) + " ");
            for (int j = 0; j < size; j++) {
                System.out.print(VERTICAL_LINE);
                CellState state = board.getCell(i, j).getState();
                if (state == CellState.EMPTY) {
                    System.out.print(EMPTY_CELL);
                } else {
                    System.out.print(" " + state + " ");
                }
            }
            System.out.println(VERTICAL_LINE);
            printHorizontalLine(size);
        }
        System.out.print("   ");
        for (int i = 0; i < size; i++) {
            System.out.print("  " + (i + 1) + " ");
        }
        System.out.println();
    }

    private void printHorizontalLine(int size) {
        System.out.print("   ");
        for (int j = 0; j < size; j++) {
            System.out.print(HORIZONTAL_LINE);
        }
        System.out.println("-");
    }

}
