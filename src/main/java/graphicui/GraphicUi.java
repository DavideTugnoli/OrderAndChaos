package graphicui;

import annotations.Generated;
import gameutils.MessagePrinter;
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
    private final JLabel turnLabel;

    public GraphicUi(GameplayLogic gameplayLogic) {
        super("Order and Chaos");
        this.gameplayLogic = gameplayLogic;
        turnLabel = createTurnLabel();
        getContentPane().add(turnLabel, BorderLayout.NORTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel gamePanel = new JPanel(new GridLayout(6, 6));
        cellButtons = new JButton[6][6];
        createCellButtons(gamePanel);
        setWindowProperties(gamePanel);
    }

    private JLabel createTurnLabel() {
        JLabel turnLabel = new JLabel();
        turnLabel.setHorizontalAlignment(JLabel.CENTER);
        turnLabel.setFont(new Font("Arial", Font.BOLD, 16));
        String currentPlayerName = gameplayLogic.getCurrentPlayer().getName();
        turnLabel.setText(MessagePrinter.getTurnMessage(currentPlayerName));
        return turnLabel;
    }

    private void updateTurnLabel() {
        String currentPlayerName = gameplayLogic.getCurrentPlayerName();
        turnLabel.setText(MessagePrinter.getTurnMessage(currentPlayerName));
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

    private void handleButtonClick(JButton cellButton, MouseEvent e) {
        final ImageIcon iconO = new ImageIcon("src/main/java/graphicui/images/O.png");
        final ImageIcon iconX = new ImageIcon("src/main/java/graphicui/images/X.png");
        boolean isLeftClick = SwingUtilities.isLeftMouseButton(e);
        boolean isRightClick = SwingUtilities.isRightMouseButton(e);

        if (isLeftClick && !cellButton.isEnabled()) {
            return;
        }

        if (isLeftClick) {
            ImageIcon icon = new ImageIcon(iconO.getImage().getScaledInstance(cellButton.getWidth(), cellButton.getHeight(), Image.SCALE_SMOOTH));
            updateButtonIcon(cellButton, icon);
        } else if (isRightClick) {
            if (cellButton.isEnabled()) {
                ImageIcon icon = new ImageIcon(iconX.getImage().getScaledInstance(cellButton.getWidth(), cellButton.getHeight(), Image.SCALE_SMOOTH));
                updateButtonIcon(cellButton, icon);
            }
        }

        disableButton(cellButton);

        int[] coords = getRowAndColForButton(cellButton);
        int row = coords[0];
        int col = coords[1];

        CellState piece = isLeftClick ? CellState.O : CellState.X;
        gameplayLogic.playTurn(row, col, piece);
        updateTurnLabel();

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
            if (JOptionPane.showConfirmDialog(GraphicUi.this, MessagePrinter.getGameOverMessage(gameplayLogic.getWinner().getName()), "Game Over", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                dispose();
                new GraphicUi(new GameplayLogic(new Board(), gameplayLogic.getPlayer1(), gameplayLogic.getPlayer2())).setVisible(true);
            } else {
                dispose();
            }
        }
    }

}
