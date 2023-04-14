package graphicui;

import annotations.Generated;
import entities.*;
import gameutils.GameEventListener;
import gameutils.MessageBundle;
import gameutils.GameplayLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


@Generated
public class GraphicUi extends JFrame implements GameEventListener {
    private transient GameplayLogic gameplayLogic;
    private final JButton[][] cellButtons;
    private final JLabel turnLabel;

    private final ImageIcon iconO;
    private final ImageIcon iconX;

    private boolean isWaitingForComputer;

    private boolean userInputAllowed = true;

    private final OverlayPanel overlayPanel;


    public GraphicUi() {
        super("Order and Chaos");
        iconO = loadScaledImageIcon("src/main/java/graphicui/images/O.png");
        iconX = loadScaledImageIcon("src/main/java/graphicui/images/X.png");
        turnLabel = createTurnLabel();
        getContentPane().add(turnLabel, BorderLayout.NORTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLayeredPane gameLayeredPane = new JLayeredPane();
        gameLayeredPane.setPreferredSize(new Dimension(600, 600));
        getContentPane().add(gameLayeredPane);

        JPanel gamePanel = new JPanel(new GridLayout(6, 6));
        gamePanel.setBounds(0, 0, 600, 600);
        gameLayeredPane.add(gamePanel, JLayeredPane.DEFAULT_LAYER);

        cellButtons = new JButton[6][6];
        createCellButtons(gamePanel);
        createMenu();
        setWindowProperties();
        overlayPanel = new OverlayPanel();
        overlayPanel.setBounds(0, 0, 600, 600);
        gameLayeredPane.add(overlayPanel, JLayeredPane.POPUP_LAYER);
        setUserInputAllowed(true);
    }

    @Override
    public void onGameStarted() {
        // Aggiorna l'interfaccia Swing per mostrare il messaggio di benvenuto (se necessario)
    }

    @Override
    public void onTurnPlayed(Cell cell) {
        updateBoardUI(cell);
    }

    @Override
    public void onGameOver(Player winner) {
        showGameOverDialog();
    }

    @Override
    public void onComputerTurnPlayed(Cell cell) {
        // delay of 1 second
        Timer timer = new Timer(1000, e -> {
            updateBoardUI(cell);
            setUserInputAllowed(true);
            isWaitingForComputer = false;
        });
        timer.setRepeats(false);
        timer.start();

        turnLabel.setText("Turno del computer...");
        setUserInputAllowed(false);
        isWaitingForComputer = true;
    }

    private void showOverlay(boolean show) {
        SwingUtilities.invokeLater(() -> {
            overlayPanel.setVisible(show);
            overlayPanel.setBounds(0, 0, this.getContentPane().getWidth(), this.getContentPane().getHeight());
        });
    }

    private void setUserInputAllowed(boolean allowed) {
        userInputAllowed = allowed;
        showOverlay(!allowed);
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

        JMenuItem changeModeItem = new JMenuItem(MessageBundle.gameMenuSwitchModeLabel());
        changeModeItem.addActionListener(e -> switchGameMode());
        gameMenu.add(changeModeItem);

        JMenuItem exitItem = new JMenuItem(MessageBundle.gameMenuExitLabel());
        exitItem.addActionListener(e -> System.exit(0));
        gameMenu.add(exitItem);

        return gameMenu;
    }

    private void switchGameMode() {
        Point windowLocation = getLocation();
        dispose();

        Player newPlayer1 = gameplayLogic.getPlayer1();
        Player newPlayer2 = gameplayLogic.getPlayer2();

        if (gameplayLogic.isSinglePlayer()) {
            java.util.List<Player> players = GameGraphicSetup.setupPlayers(false);
            newPlayer1 = new HumanPlayer(players.get(0).getName(), newPlayer1.getRole());
            newPlayer2 = new HumanPlayer(players.get(1).getName(), newPlayer2.getRole());
        } else {
            java.util.List<Player> players = GameGraphicSetup.setupPlayers(true);
            newPlayer1 = new HumanPlayer(players.get(0).getName(), newPlayer1.getRole());
            newPlayer2 = new ComputerPlayer(newPlayer2.getName(), newPlayer2.getRole());
        }

        GraphicUi newGame = new GraphicUi();
        GameplayLogic newGameplayLogic = new GameplayLogic(new Board(), newPlayer1, newPlayer2, newGame);
        newGame.setGameplayLogic(newGameplayLogic);
        newGame.setLocation(windowLocation);
        newGame.setVisible(true);
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
        GraphicUi newGame = new GraphicUi();
        GameplayLogic newGameplayLogic = new GameplayLogic(new Board(), gameplayLogic.getPlayer1(), gameplayLogic.getPlayer2(), newGame);
        newGame.setGameplayLogic(newGameplayLogic);
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
        // Inizializza il testo del turno con una stringa vuota o un messaggio di attesa
        turnLabel.setText("");
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
                CellButton cellButton = new CellButton(i, j);
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

    private void handleButtonClick(CellButton cellButton, MouseEvent e) {
        if (isWaitingForComputer || !userInputAllowed ) {
            return;
        }

        if (!cellButton.isEnabled()) {
            return;
        }

        boolean isLeftClick = SwingUtilities.isLeftMouseButton(e);

        int row = cellButton.getRow();
        int col = cellButton.getCol();

        CellState piece = isLeftClick ? CellState.O : CellState.X;

        ImageIcon icon = piece == CellState.O ? iconO : iconX;
        updateButtonIcon(cellButton, icon);
        disableButton(cellButton);

        gameplayLogic.playTurn(new Cell(row, col, piece));
    }

    private ImageIcon loadScaledImageIcon(String imagePath) {
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    private void updateBoardUI(Cell cell) {
        int row = cell.getRow();
        int col = cell.getCol();
        CellState piece = cell.getState();
        JButton cellButton = cellButtons[row][col];

        ImageIcon icon = piece == CellState.O ? iconO : iconX;
        updateButtonIcon(cellButton, icon);

        disableButton(cellButton);
        updateTurnLabel();
    }

    private void updateButtonIcon(JButton button, ImageIcon icon) {
        button.setIcon(icon);
        button.setDisabledIcon(icon);
    }

    private void disableButton(JButton button) {
        button.setEnabled(false);
    }

    private void setWindowProperties() {
        setSize(600, 600);
        setResizable(false);
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

    public void setGameplayLogic(GameplayLogic gameplayLogic) {
        this.gameplayLogic = new GameplayLogic(gameplayLogic.getBoard(), gameplayLogic.getPlayer1(), gameplayLogic.getPlayer2(), this);
        // Aggiorna il testo del turno dopo aver inizializzato gameplayLogic
        updateTurnLabel();
    }

}