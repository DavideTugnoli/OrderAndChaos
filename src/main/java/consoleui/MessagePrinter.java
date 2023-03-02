package consoleui;

import annotations.Generated;
import entities.Player;
public class MessagePrinter {

    @Generated
    public static void printWelcome() {
        System.out.println("******************************************************");
        System.out.println("*                                                    *");
        System.out.println("*            WELCOME TO ORDER AND CHAOS              *");
        System.out.println("*                                                    *");
        System.out.println("******************************************************");
        System.out.println();
    }

    public static void printWinner(Player player) {
        System.out.println("************************************************************************************************************");
        System.out.println("                                                    ");
        System.out.println("                 " + player.getName().toUpperCase() + " WINS!                      ");
        System.out.println("                                                      ");
        System.out.println("************************************************************************************************************");
        System.out.println();
    }

    @Generated
    public static void printInstructions() {
        System.out.println("****************************************************************************************************************");
        System.out.println("*                                                                                                              *");
        System.out.println("*            Order aims to get five similar pieces in a row, vertically, horizontally or diagonally.           *");
        System.out.println("*            Chaos aims to fill the board without completing a row of five similar pieces.                     *");
        System.out.println("*            It is played in turns.                                                                            *");
        System.out.println("*            Good luck!                                                                                        *");
        System.out.println("*                                                                                                              *");
        System.out.println("****************************************************************************************************************");
        System.out.println();
    }
}
