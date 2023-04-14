package graphicui;

import annotations.Generated;
import entities.*;
import gameutils.MessageBundle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Generated
public class GameGraphicSetup {

    public static List<Player> setupPlayers(boolean singlePlayer) {
        List<Player> players = new ArrayList<>();
        Player[] selectedPlayers = showPlayerNamesDialog(singlePlayer);
        if (selectedPlayers == null) {
            System.exit(0); // Terminates the program when the cancel button is clicked
        } else {
            players.addAll(Arrays.asList(selectedPlayers));
        }
        return players;
    }

    private static Player[] showPlayerNamesDialog(boolean singlePlayer) {
        JPanel panel = createPlayerNamesPanel(singlePlayer);
        int result = JOptionPane.showConfirmDialog(null, panel, "Enter player names", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) {
            return null; // Handle cancel button click
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
        JPanel panel = new JPanel(new GridLayout(singlePlayer ? 1 : 2, 2));
        JTextField orderPlayerNameField = new JTextField();
        panel.add(new JLabel(MessageBundle.getOrderPlayerName()));
        panel.add(orderPlayerNameField);
        if (!singlePlayer) {
            JTextField chaosPlayerNameField = new JTextField();
            panel.add(new JLabel(MessageBundle.getChaosPlayerName()));
            panel.add(chaosPlayerNameField);
        }

        return panel;
    }

    private static String getPlayerName(JPanel panel, int playerIndex) {
        JTextField playerNameField = (JTextField) panel.getComponent(playerIndex * 2 + 1
        );
        return playerNameField.getText().trim();
    }
}
