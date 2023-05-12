package gameutils;

import entities.*;

import java.util.List;
import java.util.Random;

public class ComputerMoveStrategy {
    private final BoardChecker checker;
    private final Random random;

    public ComputerMoveStrategy(Board board) {
        this.checker = new BoardChecker(board);
        this.random = new Random();
    }

    public Cell makeComputerMove(Board board) {
        List<Cell> availableCells = board.getAvailableCells();
        Cell chosenCell = availableCells.get(random.nextInt(availableCells.size()));

        CellSequence sequence = selectBestMove();
        if (sequence != null) {
            return new Cell(sequence.lastCell().getRow(), sequence.lastCell().getCol(), getOppositeCellState(sequence.sequenceState()));
        }

        CellState chosenState = random.nextBoolean() ? CellState.X : CellState.O;
        return new Cell(chosenCell.getRow(), chosenCell.getCol(), chosenState);
    }

    private CellSequence selectBestMove() {
        CellSequence fourSequence = checker.findSequenceInBoard(4);
        if (fourSequence != null) {
            return fourSequence;
        }

        CellSequence threeSequence = checker.findSequenceInBoard(3);
        if (threeSequence != null) {
            return threeSequence;
        }

        return checker.findSequenceInBoard(2);
    }

    private CellState getOppositeCellState(CellState state) {
        return state == CellState.X ? CellState.O : CellState.X;
    }
}
