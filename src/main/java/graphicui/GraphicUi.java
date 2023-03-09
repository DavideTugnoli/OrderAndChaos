package graphicui;

import annotations.Generated;
import entities.Board;
import entities.CellState;
import gameutils.GameplayLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Generated
public class GraphicUi extends JFrame {
    private final transient GameplayLogic gameplayLogic;
    private final JButton[][] cellButtons;

    public GraphicUi(GameplayLogic gameplayLogic) {
        super("Order and Chaos");
        this.gameplayLogic = gameplayLogic;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel gamePanel = new JPanel(new GridLayout(6, 6));
        cellButtons = new JButton[6][6];
        createCellButtons(gamePanel);
        setWindowProperties(gamePanel);
    }

    private void createCellButtons(JPanel gamePanel) {
        final int BUTTON_SIZE = 100;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                JButton cellButton = new JButton();
                cellButton.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
                cellButton.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mousePressed(MouseEvent e) {
                        handleButtonClick(cellButton, e);
                    }
                });
                cellButtons[i][j] = cellButton;
                gamePanel.add(cellButton);
            }
        }
    }

    private void handleButtonClick(JButton clickedCellButton, MouseEvent e) {
        final ImageIcon iconO = new ImageIcon("src/main/java/graphicui/images/O.png");
        final ImageIcon iconX = new ImageIcon("src/main/java/graphicui/images/X.png");
        boolean isLeftClick = SwingUtilities.isLeftMouseButton(e);

        if (isLeftClick && !clickedCellButton.isEnabled()) {
            return;
        }

        ImageIcon icon = null;
        CellState piece = null;

        switch (e.getButton()) {
            case MouseEvent.BUTTON1 -> {
                icon = new ImageIcon(iconO.getImage().getScaledInstance(clickedCellButton.getWidth(), clickedCellButton.getHeight(), Image.SCALE_SMOOTH));
                piece = CellState.O;
            }
            case MouseEvent.BUTTON3 -> {
                if (clickedCellButton.isEnabled()) {
                    icon = new ImageIcon(iconX.getImage().getScaledInstance(clickedCellButton.getWidth(), clickedCellButton.getHeight(), Image.SCALE_SMOOTH));
                    piece = CellState.X;
                }
            }
            default -> {
            }
        }

        updateButtonIcon(clickedCellButton, icon);
        disableButton(clickedCellButton);

        int[] coords = getRowAndColForButton(clickedCellButton);
        int row = coords[0];
        int col = coords[1];

        gameplayLogic.playTurn(row, col, piece);

        if (gameplayLogic.isGameOver()) {
            showGameOverDialog();
        }
    }

    private void updateButtonIcon(JButton button, ImageIcon icon) {
        button.setIcon(icon);
        button.setDisabledIcon(icon);
    }

    private void disableButton(JButton button) {
        button.setEnabled(false);
    }


    private int[] getRowAndColForButton(JButton button) {
        int[] rowAndCol = new int[2];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (cellButtons[i][j] == button) {
                    rowAndCol[0] = i;
                    rowAndCol[1] = j;
                    return rowAndCol;
                }
            }
        }
        return new int[0];
    }

    private void setWindowProperties(JPanel gamePanel) {
        setSize(500, 500);
        setResizable(false);
        getContentPane().add(gamePanel);
        pack();
        setVisible(true);
    }

    private void showGameOverDialog() {
        if (gameplayLogic.getWinner() != null) {
            if (JOptionPane.showConfirmDialog(GraphicUi.this, "Vuoi fare un'altra partita?", "Hai vinto " + gameplayLogic.getWinner().getName() + "!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                dispose();
                new GraphicUi(new GameplayLogic(new Board(), gameplayLogic.getPlayer1(), gameplayLogic.getPlayer2())).setVisible(true);
            } else {
                dispose();
            }
        }
    }
}
