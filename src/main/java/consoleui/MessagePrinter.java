package consoleui;

import entities.Player;
public class MessagePrinter {
    public static void printWelcome() {
        System.out.println("******************************************************");
        System.out.println("*                                                    *");
        System.out.println("*            WELCOME TO ORDER AND CHAOS              *");
        System.out.println("*                                                    *");
        System.out.println("******************************************************");
        System.out.println();
    }

    public static void printOrder() {
        System.out.println("******************************************************");
        System.out.println("*                                                    *");
        System.out.println("*                       ORDER                        *");
        System.out.println("*                                                    *");
        System.out.println("******************************************************");
        System.out.println();
    }

    public static void printChaos() {
        System.out.println("******************************************************");
        System.out.println("*                                                    *");
        System.out.println("*                       CHAOS                        *");
        System.out.println("*                                                    *");
        System.out.println("******************************************************");
        System.out.println();
    }

    public static void printWinner(Player player) {
        System.out.println("******************************************************");
        System.out.println("*                                                    *");
        System.out.println("*                 " + player.getName().toUpperCase() + " WINS!                      *");
        System.out.println("*                                                    *");
        System.out.println("******************************************************");
        System.out.println();
    }
}
