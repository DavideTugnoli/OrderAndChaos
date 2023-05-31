package gameutils;

import annotations.Generated;
import entities.Player;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MessageBundle {

    private MessageBundle() {
        // This class should not be instantiated
        throw new UnsupportedOperationException();
    }

    private static ResourceBundle messages = ResourceBundle.getBundle("languages/messages", Locale.ENGLISH);

    public static void setCurrentLocale(Locale locale) {
        messages = ResourceBundle.getBundle("languages/messages", locale);
    }

    @Generated
    public static String getWelcomeMessage() {
        return messages.getString("welcome") + "\n";
    }

    @Generated
    public static String getWinnerMessage(Player player) {
        return String.format(messages.getString("winner"), player.getName().toUpperCase()) + "\n";
    }

    public static String getPlayAgainInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(messages.getString("playagain"));
        return scanner.nextLine();
    }

    public static String getThanksMessage() {
        return messages.getString("thanks");
    }

    public static String getFinalBoardMessage() {
        return messages.getString("finalboard");
    }

    public static String getCurrentBoardMessage() {
        return messages.getString("currentboard");
    }

    public static String getCurrentPlayerTurnMessage(String playerName) {
        return String.format(messages.getString("currentPlayerTurnMessage"), playerName);
    }

    public static String getInputPrompt() {
        return messages.getString("inputPrompt");
    }

    public static String getYesInput() {
        return messages.getString("yesInput");
    }

    public static String getInstructionsMessage() {
        return messages.getString("instructionsMessage");
    }

    public static String getGameOverMessage(String winnerName) {
        return String.format(messages.getString("gameOverMessage"), winnerName);
    }

    public static String getTurnMessage(String playerName) {
        return String.format(messages.getString("turnMessage"), playerName);
    }

    public static String getLanguageChoiceRequestMessage() {
        return messages.getString("languageChoiceRequestMessage");
    }

    public static String getGameInterfaceChoiceRequestMessage() {
        return messages.getString("gameInterfaceChoiceRequestMessage");
    }

    public static String getInvalidInterfaceChoiceMessage() {
        return messages.getString("invalidInterfaceChoiceMessage");
    }


    public static String getInvalidLanguageChoiceMessage() {
        return messages.getString("invalidLanguageChoiceMessage");
    }

    public static String getEnterIntegerMessage() {
        return messages.getString("enterIntegerMessage");
    }

    public static String gameMenuLabel() {
        return messages.getString("gameMenuLabel");
    }

    public static String gameMenuNewGameLabel() {
        return messages.getString("gameMenuNewGameLabel");
    }

    public static String helpMenuLabel() {
        return messages.getString("helpMenuLabel");
    }

    public static String helpMenuInstructionsLabel() {
        return messages.getString("helpMenuInstructionsLabel");
    }

    public static String getInstructionsDialogMessage() {
        return messages.getString("instructionsDialogMessage");
    }

    public static String getOrderPlayerName() {
        return messages.getString("orderPlayerName");
    }

    public static String getChaosPlayerName() {
        return messages.getString("chaosPlayerName");
    }

    public static String consoleBadInputMessage() {
        return messages.getString("consoleBadInputMessage");
    }

    public static String badSelectionConsolePeaceMessage() {
        return messages.getString("badSelectionConsolePeaceMessage");
    }

    public static String getComputerTurnMessage(String playerName, int row, int col) {
        return String.format(messages.getString("computerTurnWithPositionMessage"), playerName, row, col);
    }

    public static String gameMenuSwitchModeLabel() {
        return messages.getString("gameMenuSwitchModeLabel");
    }

    public static String gameMenuExitLabel() {
        return messages.getString("gameMenuExitLabel");
    }

    public static String getEnterPlayerNamesMessage() {
        return messages.getString("enterPlayerNamesMessage");
    }

    public static String getComputerTurnMessage() {
        return messages.getString("computerTurnMessage");
    }

}
