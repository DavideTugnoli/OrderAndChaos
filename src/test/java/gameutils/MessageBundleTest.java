package gameutils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageBundleTest {
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    // Questo test simula l'input dell'utente "yes" e verifica che il metodo getPlayAgainInput() restituisca effettivamente la stringa "yes".
    @Test
    void testGetPlayAgainInput() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("yes\n".getBytes());
        System.setIn(in);

        assertEquals("yes", MessageBundle.getPlayAgainInput());

        System.setIn(sysInBackup);
    }

    @Test
     void testGetInputPrompt() {
        MessageBundle.setCurrentLocale(Locale.ITALIAN);
        assertEquals("Inserisci riga,colonna: ", MessageBundle.getInputPrompt());
        MessageBundle.setCurrentLocale(Locale.ENGLISH);
        assertEquals("Enter row,column: ", MessageBundle.getInputPrompt());
    }

    @Test
    void testGetYesInput() {
        MessageBundle.setCurrentLocale(Locale.ENGLISH);
        assertEquals("Yes", MessageBundle.getYesInput());
        MessageBundle.setCurrentLocale(Locale.ITALIAN);
        assertEquals("Sì", MessageBundle.getYesInput());
    }

    @Test
    void testGetGameOverMessage() {
        MessageBundle.setCurrentLocale(Locale.ENGLISH);
        String winnerName = "John";
        String expectedOutput = "Do you want to play again?\nJohn won!";
        String actualOutput = MessageBundle.getGameOverMessage(winnerName);
        assertEquals(expectedOutput, actualOutput);

        MessageBundle.setCurrentLocale(Locale.ITALIAN);
        expectedOutput = "Vuoi fare un'altra partita?\nHai vinto John!";
        actualOutput = MessageBundle.getGameOverMessage(winnerName);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testGetTurnMessage() {
        String playerName = "John";
        MessageBundle.setCurrentLocale(Locale.ENGLISH);
        String expectedOutput = "Player's turn: John";
        String output = MessageBundle.getTurnMessage(playerName);
        assertEquals(expectedOutput, output);

        MessageBundle.setCurrentLocale(Locale.ITALIAN);
        expectedOutput = "Turno del giocatore: John";
        output = MessageBundle.getTurnMessage(playerName);
        assertEquals(expectedOutput, output);
    }

    @Test
    @DisplayName("Test gameMenuLabel() with default language")
    void testGameMenuLabelDefault() {
        MessageBundle.setCurrentLocale(Locale.ENGLISH);
        assertEquals("Game", MessageBundle.gameMenuLabel());
    }

    @Test
    @DisplayName("Test gameMenuLabel() with Italian language")
    void testGameMenuLabelItalian() {
        MessageBundle.setCurrentLocale(Locale.ITALIAN);
        assertEquals("Gioco", MessageBundle.gameMenuLabel());
    }

    @Test
    @DisplayName("Test gameMenuNewGameLabel() with default language")
    void testGameMenuNewGameLabelDefault() {
        MessageBundle.setCurrentLocale(Locale.ENGLISH);
        assertEquals("New game", MessageBundle.gameMenuNewGameLabel());
    }

    @Test
    @DisplayName("Test gameMenuNewGameLabel() with Italian language")
    void testGameMenuNewGameLabelItalian() {
        MessageBundle.setCurrentLocale(Locale.ITALIAN);
        assertEquals("Nuova partita", MessageBundle.gameMenuNewGameLabel());
    }

    @Test
    @DisplayName("Test helpMenuLabel() with default language")
    void testHelpMenuLabelDefault() {
        MessageBundle.setCurrentLocale(Locale.ENGLISH);
        assertEquals("Help", MessageBundle.helpMenuLabel());
    }

    @Test
    @DisplayName("Test helpMenuLabel() with Italian language")
    void testHelpMenuLabelEnglish() {
        MessageBundle.setCurrentLocale(Locale.ITALIAN);
        assertEquals("Aiuto", MessageBundle.helpMenuLabel());
    }

    @Test
    @DisplayName("Test helpMenuInstructionsLabel() with default language")
    void testHelpMenuInstructionsLabelDefault() {
        MessageBundle.setCurrentLocale(Locale.ENGLISH);
        assertEquals("Instructions", MessageBundle.helpMenuInstructionsLabel());
    }

    @Test
    @DisplayName("Test helpMenuInstructionsLabel() with Italian language")
    void testHelpMenuInstructionsLabelItalian() {
        MessageBundle.setCurrentLocale(Locale.ITALIAN);
        assertEquals("Istruzioni", MessageBundle.helpMenuInstructionsLabel());
    }

    @Test
    @DisplayName("Test getInstructionsDialogMessage() with default language")
    void testGetInstructionsDialogMessageDefault() {
        MessageBundle.setCurrentLocale(Locale.ENGLISH);
        String expected = "Game instructions:\n\n1. Order must form a sequence of 5 of the same symbols to win.\n2. The game is over if Order wins or Chaos prevents it.";
        assertEquals(expected, MessageBundle.getInstructionsDialogMessage());
    }

    @Test
    @DisplayName("Test getInstructionsDialogMessage() with Italian language")
    void testGetInstructionsDialogMessageItalian() {
        MessageBundle.setCurrentLocale(Locale.ITALIAN);
        String expected = "Istruzioni di gioco:\n\n1. Order deve formare una sequenza di 5 simboli uguali per vincere.\n2. Il gioco termina se Order vince o Chaos glielo impedisce.";
        assertEquals(expected, MessageBundle.getInstructionsDialogMessage());
    }

}
