package graphicui;

import annotations.Generated;
import entities.Player;
import entities.PlayerRole;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Generated
public class GameGraphicSetup {

    public static List<Player> setupPlayers() {
        List<Player> players = new ArrayList<>();
        Player[] selectedPlayers = showPlayerNamesDialog();
        if (selectedPlayers == null) {
            System.exit(0); // Terminates the program when the cancel button is clicked
        } else {
            players.addAll(Arrays.asList(selectedPlayers));
        }
        return players;
    }

    private static Player[] showPlayerNamesDialog() {
        JPanel panel = createPlayerNamesPanel();
        int result = JOptionPane.showConfirmDialog(null, panel, "Enter Player Names", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) {
            return null; // Handle cancel button click
        }
        String orderPlayerName = getPlayerName(panel, 0);
        String chaosPlayerName = getPlayerName(panel, 1);
        if (orderPlayerName.isEmpty()) {
            orderPlayerName = "Order Player";
        }
        if (chaosPlayerName.isEmpty()) {
            chaosPlayerName = "Chaos Player";
        }
        Player player1 = new Player(orderPlayerName, PlayerRole.ORDER);
        Player player2 = new Player(chaosPlayerName, PlayerRole.CHAOS);
        return new Player[]{player1, player2};
    }

    private static JPanel createPlayerNamesPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JTextField orderPlayerNameField = new JTextField();
        JTextField chaosPlayerNameField = new JTextField();
        panel.add(new JLabel("Order Player Name:"));
        panel.add(orderPlayerNameField);
        panel.add(new JLabel("Chaos Player Name:"));
        panel.add(chaosPlayerNameField);
        return panel;
    }

    private static String getPlayerName(JPanel panel, int index) {
        JTextField playerNameField = (JTextField) panel.getComponent(index * 2 + 1);
        return playerNameField.getText().trim();
    }
}
