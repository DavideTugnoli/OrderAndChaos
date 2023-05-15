package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class CellTest {
    private Cell cell;

    @BeforeEach
    void setUp() {
        cell = new Cell(0, 0);
    }
    @Test
    void testNewCellIsEmpty() {
        assertSame(CellState.EMPTY, cell.getState());
    }

    @Test
    void testSetState() {
        cell.setState(CellState.X);
        assertSame(CellState.X, cell.getState());
    }

    @Test
    void testSetStateInvalidValue() {
        CellState originalState = cell.getState();
        cell.setState(null);
        assertEquals(originalState, cell.getState());
    }

    @Test
    void testToString() {
        cell.setState(CellState.X);
        assertEquals("X", cell.toString());
    }


    @Test
    void testGetRow() {
        assertEquals(0, cell.getRow());
    }

    @Test
    void testGetCol() {
        assertEquals(0, cell.getCol());
    }

    @Test
    void testToStringEmpty() {
        cell.setState(CellState.EMPTY);
        assertEquals("EMPTY", cell.toString());
    }

    @Test
    void testToStringO() {
        cell.setState(CellState.O);
        assertEquals("O", cell.toString());
    }

    @Test
    void testToStringX() {
        cell.setState(CellState.X);
        assertEquals("X", cell.toString());
    }

    @Test
    void testConstructorWithState() {
        Cell newCell = new Cell(1, 1, CellState.O);
        assertEquals(1, newCell.getRow());
        assertEquals(1, newCell.getCol());
        assertEquals(CellState.O, newCell.getState());
    }

    @Test
    void testConstructorWithNullState() {
        Cell newCell = new Cell(1, 1, null);
        assertEquals(1, newCell.getRow());
        assertEquals(1, newCell.getCol());
        assertEquals(CellState.EMPTY, newCell.getState());
    }


}
