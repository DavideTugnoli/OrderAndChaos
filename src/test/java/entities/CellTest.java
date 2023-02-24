package entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class CellTest {
    @Test
    void testNewCellIsEmpty() {
        Cell cell = new Cell(0, 0);
        assertSame(cell.getState(), CellState.EMPTY);
    }

    @Test
    void testSetState() {
        Cell cell = new Cell(0, 0);
        cell.setState(CellState.X);
        assertSame(cell.getState(), CellState.X);
    }

    @Test
    void testSetStateInvalidValue() {
        Cell cell = new Cell(0, 0);
        CellState originalState = cell.getState();
        cell.setState(null);
        assertEquals(originalState, cell.getState());
    }

    @Test
    void testToString() {
        Cell cell = new Cell(0, 0);
        cell.setState(CellState.X);
        assertEquals("X", cell.toString());
    }
}
