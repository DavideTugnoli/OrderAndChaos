package consoleui;

import entities.*;
import gameutils.MessageBundle;

import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

public class GameConsoleSetup {

    private GameConsoleSetup() {
        // This class should not be instantiated
        throw new UnsupportedOperationException();
    }

    public static List<Player> setupPlayers(boolean singlePlayer) {
        Scanner scanner = new Scanner(System.in);

        System.out.print(MessageBundle.getOrderPlayerName());
        String name1 = scanner.nextLine().trim();
        if (name1.isEmpty()) {
            name1 = "Order";
        }
        Player player1 = new HumanPlayer(name1, PlayerRole.ORDER);

        if (singlePlayer) {
            Player player2 = new ComputerPlayer("Computer", PlayerRole.CHAOS);
            return Arrays.asList(player1, player2);
        } else {
            System.out.print(MessageBundle.getChaosPlayerName());
            String name2 = scanner.nextLine().trim();
            if (name2.isEmpty()) {
                name2 = "Chaos";
            }
            Player player2 = new HumanPlayer(name2, PlayerRole.CHAOS);
            return Arrays.asList(player1, player2);
        }
    }
}
