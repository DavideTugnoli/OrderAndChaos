package graphicui;

import annotations.Generated;

import javax.swing.JButton;
@Generated
public class CellButton extends JButton {
    private final int row;
    private final int col;

    public CellButton(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}

