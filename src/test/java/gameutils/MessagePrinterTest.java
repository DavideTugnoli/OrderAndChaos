package gameutils;

import entities.Player;
import entities.PlayerRole;
import org.junit.jupiter.api.BeforeEach;
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
        // Set the current locale to Italian
        MessagePrinter.setCurrentLocale(Locale.ITALIAN);

        // Test that the prompt is correctly printed in Italian
        assertEquals("Inserisci riga,colonna: ", MessagePrinter.getInputPrompt());

        // Set the current locale to English
        MessagePrinter.setCurrentLocale(Locale.ENGLISH);

        // Test that the prompt is correctly printed in English
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


}
