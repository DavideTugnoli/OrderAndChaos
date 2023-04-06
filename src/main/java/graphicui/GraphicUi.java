package graphicui;

import annotations.Generated;
import entities.Cell;
import gameutils.MessageBundle;
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

    private final ImageIcon iconO;
    private final ImageIcon iconX;

    public GraphicUi(GameplayLogic gameplayLogic) {
        super("Order and Chaos");
        this.gameplayLogic = gameplayLogic;
        iconO = loadScaledImageIcon("src/main/java/graphicui/images/O.png");
        iconX = loadScaledImageIcon("src/main/java/graphicui/images/X.png");
        turnLabel = createTurnLabel();
        getContentPane().add(turnLabel, BorderLayout.NORTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel gamePanel = new JPanel(new GridLayout(6, 6));
        cellButtons = new JButton[6][6];
        createCellButtons(gamePanel);
        createMenu();
        setWindowProperties(gamePanel);
    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createGameMenu());
        menuBar.add(createHelpMenu());
        setJMenuBar(menuBar);
    }

    private JMenu createGameMenu() {
        JMenu gameMenu = new JMenu(MessageBundle.gameMenuLabel());
        JMenuItem newGameItem = new JMenuItem(MessageBundle.gameMenuNewGameLabel());
        newGameItem.addActionListener(e -> startNewGame());
        gameMenu.add(newGameItem);
        return gameMenu;
    }

    private JMenu createHelpMenu() {
        JMenu helpMenu = new JMenu(MessageBundle.helpMenuLabel());
        JMenuItem instructionsItem = new JMenuItem(MessageBundle.helpMenuInstructionsLabel());
        instructionsItem.addActionListener(e -> showInstructionsDialog());
        helpMenu.add(instructionsItem);
        return helpMenu;
    }

    private void startNewGame() {
        Point windowLocation = getLocation();
        dispose();
        GraphicUi newGame = new GraphicUi(new GameplayLogic(new Board(), gameplayLogic.getPlayer1(), gameplayLogic.getPlayer2()));
        newGame.setLocation(windowLocation);
        newGame.setVisible(true);
    }

    private void showInstructionsDialog() {
        // Create the dialog window
        JDialog instructionsDialog = new JDialog(this, MessageBundle.helpMenuInstructionsLabel(), true);
        instructionsDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // Create a panel with a border and white background to hold the text and image
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.WHITE);

        // Add the text from MessagePrinter.getInstructionsDialogMessage() to the panel
        JTextArea textArea = new JTextArea(MessageBundle.getInstructionsDialogMessage());
        textArea.setEditable(false);
        panel.add(textArea, BorderLayout.NORTH);

        // Load the instructions image and add it to the panel
        ImageIcon instructionsImage = new ImageIcon("src/main/java/graphicui/images/mouse-instructions.png");
        JLabel instructionsLabel = new JLabel(instructionsImage);
        panel.add(instructionsLabel, BorderLayout.CENTER);

        // Add the panel to the dialog
        instructionsDialog.add(panel);

        // Set the size and position of the dialog, and show it
        instructionsDialog.pack();
        instructionsDialog.setLocationRelativeTo(this);
        instructionsDialog.setVisible(true);
    }

    private JLabel createTurnLabel() {
        JLabel turnLabel = new JLabel();
        turnLabel.setHorizontalAlignment(JLabel.CENTER);
        turnLabel.setFont(new Font("Arial", Font.BOLD, 16));
        String currentPlayerName = gameplayLogic.getCurrentPlayer().getName();
        turnLabel.setText(MessageBundle.getTurnMessage(currentPlayerName));
        return turnLabel;
    }

    private void updateTurnLabel() {
        String currentPlayerName = gameplayLogic.getCurrentPlayerName();
        turnLabel.setText(MessageBundle.getTurnMessage(currentPlayerName));
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
        boolean isLeftClick = SwingUtilities.isLeftMouseButton(e);
        boolean isRightClick = SwingUtilities.isRightMouseButton(e);

        if (isLeftClick && !cellButton.isEnabled()) {
            return;
        }

        if (isLeftClick) {
            updateButtonIcon(cellButton, iconO);
        } else if (isRightClick) {
            if (cellButton.isEnabled()) {
                updateButtonIcon(cellButton, iconX);
            }
        }

        disableButton(cellButton);

        int[] coords = getRowAndColForButton(cellButton);
        int row = coords[0];
        int col = coords[1];

        CellState piece = isLeftClick ? CellState.O : CellState.X;
        gameplayLogic.playTurn(new Cell(row, col, piece));
        updateTurnLabel();

        if (gameplayLogic.isGameOver()) {
            showGameOverDialog();
        }
    }

    private ImageIcon loadScaledImageIcon(String imagePath) {
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
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
            if (JOptionPane.showConfirmDialog(GraphicUi.this, MessageBundle.getGameOverMessage(gameplayLogic.getWinner().getName()), "Game Over", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                startNewGame();
            } else {
                dispose();
            }
        }
    }

}
