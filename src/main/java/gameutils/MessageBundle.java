package gameutils;

import annotations.Generated;
import entities.Player;

import java.util.Locale;
import java.util.Scanner;

public class MessageBundle {

    private static Locale currentLocale = Locale.ENGLISH;

    public static void setCurrentLocale(Locale locale) {
        currentLocale = locale;
    }

    @Generated
    public static String getWelcomeMessage() {
        String welcomeMessage;
        if (currentLocale.getLanguage().equals("it")) {
            welcomeMessage = """
                    ******************************************************
                    *                                                    *
                    *              BENVENUTO IN ORDINE E CAOS            *
                    *                                                    *
                    ******************************************************
                    """;
        } else {
            welcomeMessage = """
                    ******************************************************
                    *                                                    *
                    *            WELCOME TO ORDER AND CHAOS              *
                    *                                                    *
                    ******************************************************
                    """;
        }
        welcomeMessage += "\n";
        return welcomeMessage;
    }

    @Generated
    public static String getWinnerMessage(Player player) {
        String winnerMessage;
        if (currentLocale.getLanguage().equals("it")) {
            winnerMessage = "************************************************************************************************************\n" +
                    "                                                    \n" +
                    "                 " + player.getName().toUpperCase() + " VINCE!                      \n" +
                    "                                                      \n" +
                    "************************************************************************************************************\n";
        } else {
            winnerMessage = "************************************************************************************************************\n" +
                    "                                                    \n" +
                    "                 " + player.getName().toUpperCase() + " WINS!                      \n" +
                    "                                                      \n" +
                    "************************************************************************************************************\n";
        }
        winnerMessage += "\n";
        return winnerMessage;
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

    public static String getThanksMessage() {
        String thanksMessage;
        if (currentLocale.getLanguage().equals("it")) {
            thanksMessage = "Grazie per aver giocato! Arrivederci!";
        } else {
            thanksMessage = "Thank you for playing! Goodbye!";
        }
        return thanksMessage;
    }

    public static String getFinalBoardMessage() {
        String finalBoardMessage;
        if (currentLocale.getLanguage().equals("it")) {
            finalBoardMessage = "Tabellone finale:";
        } else {
            finalBoardMessage = "Final board:";
        }
        return finalBoardMessage;
    }

    public static String getCurrentBoardMessage() {
        String currentBoardMessage;
        if (currentLocale.getLanguage().equals("it")) {
            currentBoardMessage = "Tabellone corrente:";
        } else {
            currentBoardMessage = "Current board:";
        }
        return currentBoardMessage;
    }

    public static String getCurrentPlayerTurnMessage(String playerName) {
        String currentPlayerTurnMessage;
        if (currentLocale.getLanguage().equals("it")) {
            currentPlayerTurnMessage = "È il turno di " + playerName + ".";
        } else {
            currentPlayerTurnMessage = "It's " + playerName + "'s turn.";
        }
        return currentPlayerTurnMessage;
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
    public static String getInstructionsMessage() {
        String instructionsMessage;
        if (currentLocale.getLanguage().equals("it")) {
            instructionsMessage = """
                    ****************************************************************************************************************
                    *                                                                                                              *
                    *            L'obiettivo di Ordine è di completare una fila di cinque pedine dello stesso colore,                *
                    *            sia in orizzontale, sia in verticale, sia in diagonale.                                            *
                    *            L'obiettivo di Caos è di riempire il tabellone senza completare una fila di cinque pedine.         *
                    *            Il gioco si svolge a turni.                                                                       *
                    *            Buona fortuna!                                                                                    *
                    *                                                                                                              *
                    ****************************************************************************************************************
                    """;
        } else {
            instructionsMessage = """
                    ****************************************************************************************************************
                    *                                                                                                              *
                    *            Order aims to get five similar pieces in a row, vertically, horizontally or diagonally.           *
                    *            Chaos aims to fill the board without completing a row of five similar pieces.                     *
                    *            It is played in turns.                                                                            *
                    *            Good luck!                                                                                        *
                    *                                                                                                              *
                    ****************************************************************************************************************
                    """;
        }
        instructionsMessage += "\n";
        return instructionsMessage;
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

    public static String getLanguageChoiceRequestMessage() {
        String languageChoiceRequestMessage;
        if (currentLocale.getLanguage().equals("it")) {
            languageChoiceRequestMessage = """
                    Scegli la lingua:
                    1. Inglese
                    2. Italiano
                    """;
        } else {
            languageChoiceRequestMessage = """
                    Choose the language:
                    1. English
                    2. Italian
                    """;
        }
        return languageChoiceRequestMessage;
    }

    public static String getGameInterfaceChoiceRequestMessage() {
        String gameInterfaceChoiceRequestMessage;
        if (currentLocale.getLanguage().equals("it")) {
            gameInterfaceChoiceRequestMessage = """
                    Scegli l'interfaccia di gioco:
                    1. Console
                    2. Grafica
                    """;
        } else {
            gameInterfaceChoiceRequestMessage = """
                    Choose the game interface:
                    1. Console
                    2. Graphic
                    """;
        }
        return gameInterfaceChoiceRequestMessage;
    }

    public static String getInvalidInterfaceChoiceMessage() {
        String invalidChoiceMessage;
        if (currentLocale.getLanguage().equals("it")) {
            invalidChoiceMessage = "Scelta non valida. Inserisci 1 per console, 2 per grafica.";
        } else {
            invalidChoiceMessage = "Invalid choice. Enter 1 for console, 2 for graphic.";
        }
        return invalidChoiceMessage;
    }

    public static String getInvalidLanguageChoiceMessage() {
        String invalidChoiceMessage;
        if (currentLocale.getLanguage().equals("it")) {
            invalidChoiceMessage = "Scelta non valida. Inserisci 1 per inglese, 2 per italiano.";
        } else {
            invalidChoiceMessage = "Invalid choice. Enter 1 for english, 2 for italian.";
        }
        return invalidChoiceMessage;
    }

    public static String getEnterIntegerMessage() {
        String enterIntegerMessage;
        if (currentLocale.getLanguage().equals("it")) {
            enterIntegerMessage = "Inserisci un numero intero.";
        } else {
            enterIntegerMessage = "Enter an integer number.";
        }
        return enterIntegerMessage;
    }

    public static String gameMenuLabel() {
        if (currentLocale.getLanguage().equals("it")) {
            return "Gioco";
        } else {
            return "Game";
        }
    }

    public static String gameMenuNewGameLabel() {
        if (currentLocale.getLanguage().equals("it")) {
            return "Nuova partita";
        } else {
            return "New game";
        }
    }

    public static String helpMenuLabel() {
        if (currentLocale.getLanguage().equals("it")) {
            return "Aiuto";
        } else {
            return "Help";
        }
    }

    public static String helpMenuInstructionsLabel() {
        if (currentLocale.getLanguage().equals("it")) {
            return "Istruzioni";
        } else {
            return "Instructions";
        }
    }

    public static String getInstructionsDialogMessage() {
        if (currentLocale.getLanguage().equals("it")) {
            return "Istruzioni di gioco:\n\n1. Order deve formare una sequenza di 5 simboli uguali per vincere.\n2. Il gioco termina se Order vince o Chaos glielo impedisce.";
        } else {
            return "Game instructions:\n\n1. Order must form a sequence of 5 of the same symbols to win.\n2. The game is over if Order wins or Chaos prevents it.";
        }
    }

    public static String getOrderPlayerName() {
        if (currentLocale.getLanguage().equals("it")) {
            return "Inserisci il nome di Order: ";
        } else {
            return "Enter Order player name: ";
        }
    }

    public static String getChaosPlayerName() {
        if (currentLocale.getLanguage().equals("it")) {
            return "Inserisci il nome di Chaos: ";
        } else {
            return "Enter Chaos player name: ";
        }
    }

    public static String inputConsoleOutOfBoundMessage() {
        if (currentLocale.getLanguage().equals("it")) {
            return "Input non valido. Inserisci due numeri separati da una virgola, ciascuno da %d a %d.%n";
        } else {
            return "Invalid input. Please enter two numbers separated by a comma, each from %d to %d.%n";
        }
    }

    public static String selectConsolePeaceMessage() {
        if (currentLocale.getLanguage().equals("it")) {
            return "Scegli il pezzo (O o X): ";
        } else {
            return "Enter piece (O or X): ";
        }
    }

    public static String badSelectionConsolePeaceMessage() {
        if (currentLocale.getLanguage().equals("it")) {
            return "Input non valido. Inserisci O o X.";
        } else {
            return "Invalid input. Please enter O or X.";
        }
    }

}
