package consoleui;

import entities.Player;
import entities.PlayerRole;

import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

public class GameConsoleSetup {
    public static List<Player> setupPlayers() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Order player name: ");
        String name1 = scanner.nextLine();
        if(name1.isEmpty()) {
            name1 = "Order Player";
        }
        Player player1 = new Player(name1, PlayerRole.ORDER);

        System.out.print("Enter Chaos player name: ");
        String name2 = scanner.nextLine();
        if(name2.isEmpty()) {
            name2 = "Chaos Player";
        }
        Player player2 = new Player(name2, PlayerRole.CHAOS);

        return Arrays.asList(player1, player2);
    }
}
