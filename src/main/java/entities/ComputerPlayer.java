package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Player {
    private final Random random;

    public ComputerPlayer(String name, PlayerRole role) {
        super(name, role);
        random = new Random();
    }

    public Cell makeMove(Board board) {
        List<Cell> availableCells = getAvailableCells(board);
        Cell chosenCell = availableCells.get(random.nextInt(availableCells.size()));
        CellState chosenState = random.nextBoolean() ? CellState.X : CellState.O;
        return new Cell(chosenCell.getRow(), chosenCell.getCol(), chosenState);
    }

    private List<Cell> getAvailableCells(Board board) {
        List<Cell> availableCells = new ArrayList<>();
        int size = board.getSize();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board.isCellEmpty(row, col)) {
                    availableCells.add(new Cell(row, col));
                }
            }
        }

        return availableCells;
    }
}
