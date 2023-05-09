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
import java.awt.GridBagConstraints;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


@Generated
public class GraphicUi extends JFrame implements GameEventListener {
    private static final Logger LOGGER = Logger.getLogger(GraphicUi.class.getName());
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
        iconO = loadScaledImageIcon("/graphicui/images/O.png");
        iconX = loadScaledImageIcon("/graphicui/images/X.png");
        turnLabel = createTurnLabel();
        getContentPane().add(turnLabel, BorderLayout.NORTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLayeredPane gameLayeredPane = new JLayeredPane();
        gameLayeredPane.setPreferredSize(new Dimension(600, 600));
        getContentPane().add(gameLayeredPane);

        JPanel gamePanel = new JPanel(new GridBagLayout());
        gamePanel.setBounds(0, 0, 600, 600);
        gameLayeredPane.add(gamePanel, JLayeredPane.DEFAULT_LAYER);

        cellButtons = new JButton[6][6];
        createCellButtons(gamePanel);
        createMenu();
        setWindowProperties();
        setApplicationIcon();
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
        if (winner.getRole() == PlayerRole.ORDER) {
            playSound("/graphicui/sounds/success_fanfare.mp3");
        } else {
            playSound("/graphicui/sounds/success_harmony.mp3");
        }
        showGameOverDialog();
    }

    @Override
    public void onTurnChanged() {
        String currentPlayerName = gameplayLogic.getCurrentPlayerName();
        if (gameplayLogic.getCurrentPlayer() instanceof ComputerPlayer) {
            turnLabel.setText(MessageBundle.getComputerTurnMessage());
            setUserInputAllowed(false);
            isWaitingForComputer = true;
        } else {
            turnLabel.setText(MessageBundle.getTurnMessage(currentPlayerName));
        }
    }

    @Override
    public void onComputerTurnPlayed(Cell cell) {
        updateBoardUI(cell);
        setUserInputAllowed(true);
        isWaitingForComputer = false;
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

        Player newPlayer1 = gameplayLogic.getPlayer1();
        Player newPlayer2 = gameplayLogic.getPlayer2();

        if (gameplayLogic.isSinglePlayer()) {
            java.util.List<Player> players = GameGraphicSetup.setupPlayers(false);
            newPlayer1 = new HumanPlayer(players.get(0).getName(), newPlayer1.getRole());
            newPlayer2 = new HumanPlayer(players.get(1).getName(), newPlayer2.getRole());
        } else {
            java.util.List<Player> players = GameGraphicSetup.setupPlayers(true);
            newPlayer1 = new HumanPlayer(players.get(0).getName(), newPlayer1.getRole());
            newPlayer2 = new ComputerPlayer("Chaos", newPlayer2.getRole());
        }

        GraphicUi newGame = new GraphicUi();
        GameplayLogic newGameplayLogic = new GameplayLogic(new Board(), newPlayer1, newPlayer2, newGame);
        newGame.setGameplayLogic(newGameplayLogic);
        newGame.setLocation(windowLocation);
        newGame.setVisible(true);

        this.dispose();
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
        ImageIcon instructionsImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/graphicui/images/mouse-instructions.png")));
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
        JLabel newTurnLabel = new JLabel();
        newTurnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        newTurnLabel.setFont(new Font("Arial", Font.BOLD, 16));
        // Inizializza il testo del turno con una stringa vuota o un messaggio di attesa
        newTurnLabel.setText("");
        return newTurnLabel;
    }

    private void updateTurnLabel() {
        String currentPlayerName = gameplayLogic.getCurrentPlayerName();
        turnLabel.setText(MessageBundle.getTurnMessage(currentPlayerName));
    }

    private void createCellButtons(JPanel gamePanel) {
        final int BUTTON_SIZE = 100;
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

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
                gridBagConstraints.gridx = j;
                gridBagConstraints.gridy = i;
                gamePanel.add(cellButton, gridBagConstraints);
            }
        }
    }

    private void handleButtonClick(CellButton cellButton, MouseEvent e) {
        if (isWaitingForComputer || !userInputAllowed) {
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

        // Riproduci il suono qui
        playSound("/graphicui/sounds/cartoon_jump.mp3");

        gameplayLogic.playTurn(new Cell(row, col, piece));
    }

    private ImageIcon loadScaledImageIcon(String imagePath) {
        URL imageUrl = getClass().getResource(imagePath);
        if (imageUrl == null) {
            return null;
        }
        ImageIcon originalIcon = new ImageIcon(imageUrl);
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
    }

    private void updateButtonIcon(JButton button, ImageIcon icon) {
        button.setIcon(icon);
        button.setDisabledIcon(icon);
    }

    private void disableButton(JButton button) {
        button.setEnabled(false);
    }

    private void setWindowProperties() {
        int menuHeight = getJMenuBar().getPreferredSize().height;
        int turnLabelHeight = turnLabel.getPreferredSize().height;

        int windowWidth = 600;
        int windowHeight = 600 + menuHeight + turnLabelHeight + 50;

        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("mac")) {
            setSize(windowWidth, windowHeight);
        } else if (os.contains("win")) {
            setSize(windowWidth + 14, windowHeight + 7); // offset per Windows
        } else {
            setSize(windowWidth, windowHeight);
        }

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void showGameOverDialog() {
        if (gameplayLogic.getWinner() != null) {
            if (JOptionPane.showConfirmDialog(GraphicUi.this, MessageBundle.getGameOverMessage(gameplayLogic.getWinner().getName()), "Game Over", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                startNewGame();
            } else {
                dispose();
                System.exit(0);
            }
        }
    }

    public void setGameplayLogic(GameplayLogic gameplayLogic) {
        this.gameplayLogic = new GameplayLogic(gameplayLogic.getBoard(), gameplayLogic.getPlayer1(), gameplayLogic.getPlayer2(), this);
        // Aggiorna il testo del turno dopo aver inizializzato gameplayLogic
        updateTurnLabel();
    }

    private void setApplicationIcon() {
        URL iconURL = getClass().getResource("/graphicui/images/game_icon.png");
        if (iconURL != null) {
            ImageIcon icon = new ImageIcon(iconURL);
            Image image = icon.getImage();
            setIconImage(image);

            // Impostare l'icona sulla barra delle applicazioni
            if (Taskbar.isTaskbarSupported()) {
                Taskbar taskbar = Taskbar.getTaskbar();
                try {
                    taskbar.setIconImage(image);
                } catch (UnsupportedOperationException e) {
                    LOGGER.severe("The os does not support: 'taskbar.setIconImage'");
                } catch (SecurityException e) {
                    LOGGER.severe("There was a security exception for: 'taskbar.setIconImage'");
                }
            }
        }
    }

    private void playSound(String soundFilePath) {
        try {
            InputStream fis = getClass().getResourceAsStream(soundFilePath);
            if (fis != null) {
                javazoom.jl.player.Player playMP3 = new javazoom.jl.player.Player(fis);
                new Thread(() -> {
                    try {
                        playMP3.play();
                    } catch (Exception e) {
                        LOGGER.log(Level.SEVERE, "Error playing sound: " + soundFilePath, e);
                    }
                }).start();
            } else {
                LOGGER.log(Level.SEVERE, "Input stream is null for sound file: " + soundFilePath);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error playing sound: " + soundFilePath, e);
        }
    }

}