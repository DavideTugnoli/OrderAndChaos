package graphicui;

import annotations.JacocoExclude;
import entities.*;
import gameutils.MessageBundle;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JacocoExclude
public class GameGraphicSetup {

    private GameGraphicSetup() {
        // This class should not be instantiated
        throw new UnsupportedOperationException();
    }

    public static List<Player> setupPlayers(boolean singlePlayer) {
        ImageIcon customIcon = loadCustomIcon();
        setCustomIconForDialogs(customIcon);
        List<Player> players = new ArrayList<>();
        Player[] selectedPlayers = showPlayerNamesDialog(singlePlayer);
        if (selectedPlayers.length == 0) {
            System.exit(0); // Terminates the program when the cancel button is clicked
        } else {
            players.addAll(Arrays.asList(selectedPlayers));
        }
        return players;
    }

    private static Player[] showPlayerNamesDialog(boolean singlePlayer) {
        JPanel panel = createPlayerNamesPanel(singlePlayer);
        int result = JOptionPane.showConfirmDialog(null, panel, MessageBundle.getEnterPlayerNamesMessage(), JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) {
            return new Player[0];
        }
        String orderPlayerName = getPlayerName(panel, 0);
        if (orderPlayerName.isEmpty()) {
            orderPlayerName = "Order";
        }
        Player player1 = new HumanPlayer(orderPlayerName, PlayerRole.ORDER);

        if (singlePlayer) {
            Player player2 = new ComputerPlayer("Computer", PlayerRole.CHAOS);
            return new Player[]{player1, player2};
        } else {
            String chaosPlayerName = getPlayerName(panel, 1);
            if (chaosPlayerName.isEmpty()) {
                chaosPlayerName = "Chaos";
            }
            Player player2 = new HumanPlayer(chaosPlayerName, PlayerRole.CHAOS);
            return new Player[]{player1, player2};
        }
    }

    private static JPanel createPlayerNamesPanel(boolean singlePlayer) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);

        // Order Player Name
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(new JLabel(MessageBundle.getOrderPlayerName()), constraints);

        JTextField orderPlayerNameField = new JTextField(10);
        orderPlayerNameField.setPreferredSize(new Dimension(orderPlayerNameField.getPreferredSize().width, 30));
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(orderPlayerNameField, constraints);

        if (!singlePlayer) {
            // Chaos Player Name
            constraints.gridx = 0;
            constraints.gridy = 1;
            panel.add(new JLabel(MessageBundle.getChaosPlayerName()), constraints);

            JTextField chaosPlayerNameField = new JTextField(10);
            chaosPlayerNameField.setPreferredSize(new Dimension(chaosPlayerNameField.getPreferredSize().width, 30));
            constraints.gridx = 1;
            constraints.gridy = 1;
            panel.add(chaosPlayerNameField, constraints);
        }

        return panel;
    }

    private static String getPlayerName(JPanel panel, int playerIndex) {
        JTextField playerNameField = (JTextField) panel.getComponent(playerIndex * 2 + 1
        );
        return playerNameField.getText().trim();
    }

    public static ImageIcon loadCustomIcon() {
        URL iconURL = GameGraphicSetup.class.getResource("/graphicui/images/game_icon.png");
        if (iconURL != null) {
            ImageIcon originalIcon = new ImageIcon(iconURL);
            return resizeIcon(originalIcon); // Sostituisci 32, 32 con le dimensioni desiderate
        }
        return null;
    }

    private static void setCustomIconForDialogs(ImageIcon customIcon) {
        if (customIcon != null) {
            UIManager.put("OptionPane.informationIcon", customIcon);
            UIManager.put("OptionPane.warningIcon", customIcon);
            UIManager.put("OptionPane.errorIcon", customIcon);
            UIManager.put("OptionPane.questionIcon", customIcon);
        }
    }

    private static ImageIcon resizeIcon(ImageIcon icon) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
