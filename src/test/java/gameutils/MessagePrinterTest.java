package gameutils;

import entities.Player;
import entities.PlayerRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessagePrinterTest {
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testPrintWinner() {
        Player orderPlayer = new Player("John", PlayerRole.ORDER);
        MessagePrinter.setCurrentLocale(Locale.ENGLISH);
        MessagePrinter.printWinner(orderPlayer);
        String expectedOutput =
                """
                        ************************************************************************************************************
                                                                           \s
                                         JOHN WINS!                     \s
                                                                             \s
                        ************************************************************************************************************

                        """;
        assertEquals(expectedOutput, outContent.toString());
    }


    // Questo test simula l'input dell'utente "yes" e verifica che il metodo getPlayAgainInput() restituisca effettivamente la stringa "yes".
    @Test
    void testGetPlayAgainInput() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("yes\n".getBytes());
        System.setIn(in);

        assertEquals("yes", MessagePrinter.getPlayAgainInput());

        System.setIn(sysInBackup);
    }

    @Test
    public void testGetInputPrompt() {
        MessagePrinter.setCurrentLocale(Locale.ITALIAN);
        assertEquals("Inserisci riga,colonna: ", MessagePrinter.getInputPrompt());
        MessagePrinter.setCurrentLocale(Locale.ENGLISH);
        assertEquals("Enter row,column: ", MessagePrinter.getInputPrompt());
    }

    @Test
    void testGetYesInput() {
        MessagePrinter.setCurrentLocale(Locale.ENGLISH);
        assertEquals("Yes", MessagePrinter.getYesInput());
        MessagePrinter.setCurrentLocale(Locale.ITALIAN);
        assertEquals("Sì", MessagePrinter.getYesInput());
    }

    @Test
    void testGetGameOverMessage() {
        MessagePrinter.setCurrentLocale(Locale.ENGLISH);
        String winnerName = "John";
        String expectedOutput = "Do you want to play again?\nJohn won!";
        String actualOutput = MessagePrinter.getGameOverMessage(winnerName);
        assertEquals(expectedOutput, actualOutput);

        MessagePrinter.setCurrentLocale(Locale.ITALIAN);
        expectedOutput = "Vuoi fare un'altra partita?\nHai vinto John!";
        actualOutput = MessagePrinter.getGameOverMessage(winnerName);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testGetTurnMessage() {
        String playerName = "John";
        MessagePrinter.setCurrentLocale(Locale.ENGLISH);
        String expectedOutput = "Player's turn: John";
        String output = MessagePrinter.getTurnMessage(playerName);
        assertEquals(expectedOutput, output);

        MessagePrinter.setCurrentLocale(Locale.ITALIAN);
        expectedOutput = "Turno del giocatore: John";
        output = MessagePrinter.getTurnMessage(playerName);
        assertEquals(expectedOutput, output);
    }

    @Test
    @DisplayName("Test gameMenuLabel() with default language")
    void testGameMenuLabelDefault() {
        MessagePrinter.setCurrentLocale(Locale.ENGLISH);
        assertEquals("Game", MessagePrinter.gameMenuLabel());
    }

    @Test
    @DisplayName("Test gameMenuLabel() with Italian language")
    void testGameMenuLabelItalian() {
        MessagePrinter.setCurrentLocale(Locale.ITALIAN);
        assertEquals("Gioco", MessagePrinter.gameMenuLabel());
    }

    @Test
    @DisplayName("Test gameMenuNewGameLabel() with default language")
    void testGameMenuNewGameLabelDefault() {
        MessagePrinter.setCurrentLocale(Locale.ENGLISH);
        assertEquals("New game", MessagePrinter.gameMenuNewGameLabel());
    }

    @Test
    @DisplayName("Test gameMenuNewGameLabel() with Italian language")
    void testGameMenuNewGameLabelItalian() {
        MessagePrinter.setCurrentLocale(Locale.ITALIAN);
        assertEquals("Nuova partita", MessagePrinter.gameMenuNewGameLabel());
    }

    @Test
    @DisplayName("Test helpMenuLabel() with default language")
    void testHelpMenuLabelDefault() {
        MessagePrinter.setCurrentLocale(Locale.ENGLISH);
        assertEquals("Help", MessagePrinter.helpMenuLabel());
    }

    @Test
    @DisplayName("Test helpMenuLabel() with Italian language")
    void testHelpMenuLabelEnglish() {
        MessagePrinter.setCurrentLocale(Locale.ITALIAN);
        assertEquals("Aiuto", MessagePrinter.helpMenuLabel());
    }

    @Test
    @DisplayName("Test helpMenuInstructionsLabel() with default language")
    void testHelpMenuInstructionsLabelDefault() {
        MessagePrinter.setCurrentLocale(Locale.ENGLISH);
        assertEquals("Instructions", MessagePrinter.helpMenuInstructionsLabel());
    }

    @Test
    @DisplayName("Test helpMenuInstructionsLabel() with Italian language")
    void testHelpMenuInstructionsLabelItalian() {
        MessagePrinter.setCurrentLocale(Locale.ITALIAN);
        assertEquals("Istruzioni", MessagePrinter.helpMenuInstructionsLabel());
    }

    @Test
    @DisplayName("Test getInstructionsDialogMessage() with default language")
    void testGetInstructionsDialogMessageDefault() {
        MessagePrinter.setCurrentLocale(Locale.ENGLISH);
        String expected = "Game instructions:\n\n1. Order must form a sequence of 5 of the same symbols to win.\n2. The game is over if Order wins or Chaos prevents it.";
        assertEquals(expected, MessagePrinter.getInstructionsDialogMessage());
    }

    @Test
    @DisplayName("Test getInstructionsDialogMessage() with Italian language")
    void testGetInstructionsDialogMessageItalian() {
        MessagePrinter.setCurrentLocale(Locale.ITALIAN);
        String expected = "Istruzioni di gioco:\n\n1. Order deve formare una sequenza di 5 simboli uguali per vincere.\n2. Il gioco termina se Order vince o Chaos glielo impedisce.";
        assertEquals(expected, MessagePrinter.getInstructionsDialogMessage());
    }
}
