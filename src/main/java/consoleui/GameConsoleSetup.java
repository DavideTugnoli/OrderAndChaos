package consoleui;

import entities.Player;
import entities.PlayerRole;
import gameutils.MessagePrinter;

import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

public class GameConsoleSetup {
    private static final String DEFAULT_ORDER_PLAYER_NAME = "Order";
    private static final String DEFAULT_CHAOS_PLAYER_NAME = "Chaos";

    public static List<Player> setupPlayers() {
        try (Scanner scanner = new Scanner(System.in)) {
            String name1 = getPlayerName(scanner, MessagePrinter.getOrderPlayerName(), DEFAULT_ORDER_PLAYER_NAME);
            Player player1 = new Player(name1, PlayerRole.ORDER);

            String name2 = getPlayerName(scanner, MessagePrinter.getChaosPlayerName(), DEFAULT_CHAOS_PLAYER_NAME);
            Player player2 = new Player(name2, PlayerRole.CHAOS);

            return Arrays.asList(player1, player2);
        }
    }

    private static String getPlayerName(Scanner scanner, String prompt, String defaultName) {
        System.out.print(prompt);
        String name = scanner.nextLine().trim();
        return name.isEmpty() ? defaultName : name;
    }
}
