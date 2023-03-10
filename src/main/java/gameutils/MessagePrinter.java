package gameutils;

import annotations.Generated;
import entities.Player;

import java.util.Locale;
import java.util.Scanner;

public class MessagePrinter {

    private static Locale currentLocale = Locale.ENGLISH;

    public static void setCurrentLocale(Locale locale) {
        currentLocale = locale;
    }

    @Generated
    public static void printWelcome() {
        if (currentLocale.getLanguage().equals("it")) {
            System.out.println("******************************************************");
            System.out.println("*                                                    *");
            System.out.println("*              BENVENUTO IN ORDINE E CAOS            *");
            System.out.println("*                                                    *");
            System.out.println("******************************************************");
        } else {
            System.out.println("******************************************************");
            System.out.println("*                                                    *");
            System.out.println("*            WELCOME TO ORDER AND CHAOS              *");
            System.out.println("*                                                    *");
            System.out.println("******************************************************");
        }
        System.out.println();
    }

    @Generated
    public static void printWinner(Player player) {
        if (currentLocale.getLanguage().equals("it")) {
            System.out.println("************************************************************************************************************");
            System.out.println("                                                    ");
            System.out.println("                 " + player.getName().toUpperCase() + " VINCE!                      ");
            System.out.println("                                                      ");
            System.out.println("************************************************************************************************************");
        } else {
            System.out.println("************************************************************************************************************");
            System.out.println("                                                    ");
            System.out.println("                 " + player.getName().toUpperCase() + " WINS!                      ");
            System.out.println("                                                      ");
            System.out.println("************************************************************************************************************");
        }
        System.out.println();
    }

    public static String getPlayAgainInput() {
        Scanner scanner = new Scanner(System.in);
        if (currentLocale.getLanguage().equals("it")) {
            System.out.println("Vuoi giocare di nuovo? Scrivi 'Sì' o 'No'");
        } else {
            System.out.println("Do you want to play again? Write 'Yes' or 'No'");
        }
        return scanner.nextLine();
    }

    @Generated
    public static void printThanksMessage() {
        if (currentLocale.getLanguage().equals("it")) {
            System.out.println("Grazie per aver giocato! Arrivederci!");
        } else {
            System.out.println("Thank you for playing! Goodbye!");
        }
    }

    @Generated
    public static void getFinalBoardMessage() {
        if (currentLocale.getLanguage().equals("it")) {
            System.out.println("Tabellone finale:");
        } else {
            System.out.println("Final board:");
        }
    }

    @Generated
    public static void getCurrentBoardMessage() {
        if (currentLocale.getLanguage().equals("it")) {
            System.out.println("Tabellone corrente:");
        } else {
            System.out.println("Current board:");
        }
    }

    @Generated
    public static void getCurrentPlayerTurnMessage(String playerName) {
        if (currentLocale.getLanguage().equals("it")) {
            System.out.println("È il turno di " + playerName + ".");
        } else {
            System.out.println("It's " + playerName + "'s turn.");
        }
    }

    public static String getInputPrompt() {
        if (currentLocale.getLanguage().equals("it")) {
            return "Inserisci riga,colonna: ";
        }
        return "Enter row,column: ";
    }

    public static String getYesInput() {
        if (currentLocale.getLanguage().equals("it")) {
            return "Sì";
        }
        return "Yes";
    }

    @Generated
    public static void printInstructions() {
        if (currentLocale.getLanguage().equals("it")) {
            System.out.println("****************************************************************************************************************");
            System.out.println("*                                                                                                              *");
            System.out.println("*            L'obiettivo di Ordine è di completare una fila di cinque pedine dello stesso colore,                *");
            System.out.println("*            sia in orizzontale, sia in verticale, sia in diagonale.                                            *");
            System.out.println("*            L'obiettivo di Caos è di riempire il tabellone senza completare una fila di cinque pedine.         *");
            System.out.println("*            Il gioco si svolge a turni.                                                                       *");
            System.out.println("*            Buona fortuna!                                                                                    *");
            System.out.println("*                                                                                                              *");
            System.out.println("****************************************************************************************************************");
        } else {
            System.out.println("****************************************************************************************************************");
            System.out.println("*                                                                                                              *");
            System.out.println("*            Order aims to get five similar pieces in a row, vertically, horizontally or diagonally.           *");
            System.out.println("*            Chaos aims to fill the board without completing a row of five similar pieces.                     *");
            System.out.println("*            It is played in turns.                                                                            *");
            System.out.println("*            Good luck!                                                                                        *");
            System.out.println("*                                                                                                              *");
            System.out.println("****************************************************************************************************************");
        }
        System.out.println();
    }

    public static String getGameOverMessage(String winnerName) {
        if (currentLocale.getLanguage().equals("it")) {
            return "Vuoi fare un'altra partita?\nHai vinto " + winnerName + "!";
        } else {
            return "Do you want to play again?\n" + winnerName + " won!";
        }
    }

    public static String getTurnMessage(String playerName) {
        if (currentLocale.getLanguage().equals("it")) {
            return String.format("Turno del giocatore: %s", playerName);
        } else {
            return String.format("Player's turn: %s", playerName);
        }
    }

    @Generated
    public static void getInvalidChoiceMessage() {
        if (currentLocale.getLanguage().equals("it")) {
            System.out.println("Scelta non valida.");
        } else {
            System.out.println("Invalid choice.");
        }
    }

    @Generated
    public static void printLanguageChoiceRequest() {
        if (currentLocale.getLanguage().equals("it")) {
            System.out.println("Scegli la lingua:");
            System.out.println("1. Inglese");
            System.out.println("2. Italiano");
        } else {
            System.out.println("Choose the language:");
            System.out.println("1. English");
            System.out.println("2. Italian");
        }
    }

    @Generated
    public static void printGameInterfaceChoiceRequest() {
        if (currentLocale.getLanguage().equals("it")) {
            System.out.println("Scegli l'interfaccia di gioco:");
            System.out.println("1. Console");
            System.out.println("2. Grafica");
        } else {
            System.out.println("Choose the game interface:");
            System.out.println("1. Console");
            System.out.println("2. Graphic");
        }
    }

    @Generated
    public static void printInvalidChoiceMessage() {
        if (currentLocale.getLanguage().equals("it")) {
            System.out.println("Scelta non valida. Inserisci 1 per console, 2 per grafica.");
        } else {
            System.out.println("Invalid choice. Enter 1 for console, 2 for graphic.");
        }
    }

    @Generated
    public static void printEnterIntegerMessage() {
        if (currentLocale.getLanguage().equals("it")) {
            System.out.println("Inserisci un numero intero.");
        } else {
            System.out.println("Enter an integer number.");
        }
    }

}
