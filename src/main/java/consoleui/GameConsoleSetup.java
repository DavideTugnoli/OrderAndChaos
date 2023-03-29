package consoleui;

import entities.Player;
import entities.PlayerRole;
import gameutils.MessageBundle;

import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

public class GameConsoleSetup {
    public static List<Player> setupPlayers() {
        Scanner scanner = new Scanner(System.in);

        System.out.print(MessageBundle.getOrderPlayerName());
        String name1 = scanner.nextLine().trim();
        if (name1.isEmpty()) {
            name1 = "Order";
        }
        Player player1 = new Player(name1, PlayerRole.ORDER);

        System.out.print(MessageBundle.getChaosPlayerName());
        String name2 = scanner.nextLine().trim();
        if (name2.isEmpty()) {
            name2 = "Chaos";
        }
        Player player2 = new Player(name2, PlayerRole.CHAOS);

        return Arrays.asList(player1, player2);
    }
}