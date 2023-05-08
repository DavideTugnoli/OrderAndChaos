package gameutils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageBundleTest {

    @BeforeEach
    void setUp() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testGetPlayAgainInput() {
        // Test per lingua italiana
        MessageBundle.setCurrentLocale(new Locale("it"));
        String expectedIt = "Sì";
        ByteArrayInputStream inIt = new ByteArrayInputStream(expectedIt.getBytes());
        System.setIn(inIt);
        assertEquals(expectedIt, MessageBundle.getPlayAgainInput());

        // Test per lingua inglese
        MessageBundle.setCurrentLocale(Locale.ENGLISH);
        String expectedEn = "No";
        ByteArrayInputStream inEn = new ByteArrayInputStream(expectedEn.getBytes());
        System.setIn(inEn);
        assertEquals(expectedEn, MessageBundle.getPlayAgainInput());
    }

    @Test
    @DisplayName("Test getThanksMessage() with both languages")
    void testGetThanksMessage() {
        String[] expected = {"Thank you for playing! Goodbye!", "Grazie per aver giocato! Arrivederci!"};

        for (int i = 0; i < 2; i++) {
            Locale locale = i == 0 ? Locale.ENGLISH : Locale.ITALIAN;
            MessageBundle.setCurrentLocale(locale);
            assertEquals(expected[i], MessageBundle.getThanksMessage());
        }
    }

    @Test
    @DisplayName("Test getFinalBoardMessage() with both languages")
    void testGetFinalBoardMessage() {
        String[] expected = {"Final board:", "Tabellone finale:"};

        for (int i = 0; i < 2; i++) {
            Locale locale = i == 0 ? Locale.ENGLISH : Locale.ITALIAN;
            MessageBundle.setCurrentLocale(locale);
            assertEquals(expected[i], MessageBundle.getFinalBoardMessage());
        }
    }

    @Test
    @DisplayName("Test getCurrentBoardMessage() with both languages")
    void testGetCurrentBoardMessage() {
        String[] expected = {"Current board:", "Tabellone corrente:"};

        for (int i = 0; i < 2; i++) {
            Locale locale = i == 0 ? Locale.ENGLISH : Locale.ITALIAN;
            MessageBundle.setCurrentLocale(locale);
            assertEquals(expected[i], MessageBundle.getCurrentBoardMessage());
        }
    }

    @Test
    @DisplayName("Test getCurrentPlayerTurnMessage with both languages")
    void testGetCurrentPlayerTurnMessage() {
        String[] expected = {"It's player's turn.", "È il turno di player."};

        for (int i = 0; i < 2; i++) {
            Locale locale = i == 0 ? Locale.ENGLISH : Locale.ITALIAN;
            MessageBundle.setCurrentLocale(locale);
            assertEquals(expected[i], MessageBundle.getCurrentPlayerTurnMessage("player"));
        }
    }

    @Test
    @DisplayName("Test getInputPrompt() with both languages")
    void testGetInputPrompt() {
        String[] expected = {"Enter row,column: ", "Inserisci riga,colonna: "};
        for (int i = 0; i < 2; i++) {
            Locale locale = i == 0 ? Locale.ENGLISH : Locale.ITALIAN;
            MessageBundle.setCurrentLocale(locale);
            assertEquals(expected[i], MessageBundle.getInputPrompt());
        }
    }

    @Test
    @DisplayName("Test getYesInput() with both languages")
    void testGetYesInput() {
        String[] expected = {"Yes", "Si"};

        for (int i = 0; i < 2; i++) {
            Locale locale = i == 0 ? Locale.ENGLISH : Locale.ITALIAN;
            MessageBundle.setCurrentLocale(locale);
            assertEquals(expected[i], MessageBundle.getYesInput());
        }
    }

    @Test
    @DisplayName("Test getGameOverMessage() with both languages")
    void testGetGameOverMessage() {
        String[] expected = {"John won!\nDo you want to play again?", "Hai vinto John!\nVuoi fare un'altra partita?"};

        for (int i = 0; i < 2; i++) {
            Locale locale = i == 0 ? Locale.ENGLISH : Locale.ITALIAN;
            MessageBundle.setCurrentLocale(locale);
            assertEquals(expected[i], MessageBundle.getGameOverMessage("John"));
        }
    }

    @Test
    @DisplayName("Test getTurnMessage() with both languages")
    void testGetTurnMessage() {
        String[] expected = {"Player's turn: John", "Turno del giocatore: John"};

        for (int i = 0; i < 2; i++) {
            Locale locale = i == 0 ? Locale.ENGLISH : Locale.ITALIAN;
            MessageBundle.setCurrentLocale(locale);
            assertEquals(expected[i], MessageBundle.getTurnMessage("John"));
        }
    }

    @Test
    @DisplayName("Test getLanguageChoiceRequestMessage() with both languages")
    void testGetLanguageChoiceRequestMessage() {
        String[] expected = {"""
                    Choose the language:
                    1. English
                    2. Italian
                    """, """
                    Scegli la lingua:
                    1. Inglese
                    2. Italiano
                    """};

        for (int i = 0; i < 2; i++) {
            Locale locale = i == 0 ? Locale.ENGLISH : Locale.ITALIAN;
            MessageBundle.setCurrentLocale(locale);
            assertEquals(expected[i], MessageBundle.getLanguageChoiceRequestMessage());
        }
    }

    @Test
    @DisplayName("Test getGameInterfaceChoiceRequestMessage() with both languages")
    void testGetGameInterfaceChoiceRequestMessage() {
        String[] expected = {"""
                    Choose the game interface:
                    1. Console
                    2. Graphic
                    """, """
                    Scegli l'interfaccia di gioco:
                    1. Console
                    2. Grafica
                    """};

        for (int i = 0; i < 2; i++) {
            Locale locale = i == 0 ? Locale.ENGLISH : Locale.ITALIAN;
            MessageBundle.setCurrentLocale(locale);
            assertEquals(expected[i], MessageBundle.getGameInterfaceChoiceRequestMessage());
        }
    }

    @Test
    @DisplayName("Test getInvalidInterfaceChoiceMessage() with both languages")
    void testGetInvalidInterfaceChoiceMessage() {
        String[] expected = {"Invalid choice. Enter 1 for console, 2 for graphic.", "Scelta non valida. Inserisci 1 per console, 2 per grafica."};

        for (int i = 0; i < 2; i++) {
            Locale locale = i == 0 ? Locale.ENGLISH : Locale.ITALIAN;
            MessageBundle.setCurrentLocale(locale);
            assertEquals(expected[i], MessageBundle.getInvalidInterfaceChoiceMessage());
        }
    }

    @Test
    @DisplayName("Test getInvalidLanguageChoiceMessage() with both languages")
    void testGetInvalidLanguageChoiceMessage() {
        String[] expected = {"Invalid choice. Enter 1 for english, 2 for italian.", "Scelta non valida. Inserisci 1 per inglese, 2 per italiano."};

        for (int i = 0; i < 2; i++) {
            Locale locale = i == 0 ? Locale.ENGLISH : Locale.ITALIAN;
            MessageBundle.setCurrentLocale(locale);
            assertEquals(expected[i], MessageBundle.getInvalidLanguageChoiceMessage());
        }
    }

    @Test
    @DisplayName("Test getEnterIntegerMessage() with both languages")
    void testGetEnterIntegerMessage() {
        String[] expected = {"Enter an integer number.", "Inserisci un numero intero."};

        for (int i = 0; i < 2; i++) {
            Locale locale = i == 0 ? Locale.ENGLISH : Locale.ITALIAN;
            MessageBundle.setCurrentLocale(locale);
            assertEquals(expected[i], MessageBundle.getEnterIntegerMessage());
        }
    }

    @Test
    @DisplayName("Test gameMenuLabel() with both languages")
    void testGameMenuLabel() {
        String[] expected = {"Game", "Gioco"};

        for (int i = 0; i < 2; i++) {
            Locale locale = i == 0 ? Locale.ENGLISH : Locale.ITALIAN;
            MessageBundle.setCurrentLocale(locale);
            assertEquals(expected[i], MessageBundle.gameMenuLabel());
        }
    }

    @Test
    @DisplayName("Test gameMenuNewGameLabel() with both languages")
    void testGameMenuNewGameLabel() {
        String[] expected = {"New game", "Nuova partita"};

        for (int i = 0; i < 2; i++) {
            Locale locale = i == 0 ? Locale.ENGLISH : Locale.ITALIAN;
            MessageBundle.setCurrentLocale(locale);
            assertEquals(expected[i], MessageBundle.gameMenuNewGameLabel());
        }
    }

    @Test
    @DisplayName("Test helpMenuLabel() with both languages")
    void testHelpMenuLabel() {
        String[] expected = {"Help", "Aiuto"};

        for (int i = 0; i < 2; i++) {
            Locale locale = i == 0 ? Locale.ENGLISH : Locale.ITALIAN;
            MessageBundle.setCurrentLocale(locale);
            assertEquals(expected[i], MessageBundle.helpMenuLabel());
        }
    }

    @Test
    @DisplayName("Test helpMenuInstructionsLabel() with both languages")
    void testHelpMenuInstructionsLabel() {
        String[] expected = {"Instructions", "Istruzioni"};

        for (int i = 0; i < 2; i++) {
            Locale locale = i == 0 ? Locale.ENGLISH : Locale.ITALIAN;
            MessageBundle.setCurrentLocale(locale);
            assertEquals(expected[i], MessageBundle.helpMenuInstructionsLabel());
        }
    }

    @Test
    @DisplayName("Test getInstructionsDialogMessage() with both languages")
    void testGetInstructionsDialogMessage() {
        String[] expected = {"Game instructions:\n\n1. Order must form a sequence of 5 of the same symbols to win.\n2. The game is over if Order wins or Chaos prevents it.", "Istruzioni di gioco:\n\n1. Order deve formare una sequenza di 5 simboli uguali per vincere.\n2. Il gioco termina se Order vince o Chaos glielo impedisce."};

        for (int i = 0; i < 2; i++) {
            Locale locale = i == 0 ? Locale.ENGLISH : Locale.ITALIAN;
            MessageBundle.setCurrentLocale(locale);
            assertEquals(expected[i], MessageBundle.getInstructionsDialogMessage());
        }
    }

    @Test
    @DisplayName("Test getOrderPlayerName() with both languages")
    void testGetOrderPlayerName() {
        String[] expected = {"Enter Order player name: ", "Inserisci il nome di Order: "};

        for (int i = 0; i < 2; i++) {
            Locale locale = i == 0 ? Locale.ENGLISH : Locale.ITALIAN;
            MessageBundle.setCurrentLocale(locale);
            assertEquals(expected[i], MessageBundle.getOrderPlayerName());
        }
    }

    @Test
    @DisplayName("Test getChaosPlayerName() with both languages")
    void testGetChaosPlayerName() {
        String[] expected = {"Enter Chaos player name: ", "Inserisci il nome di Chaos: "};

        for (int i = 0; i < 2; i++) {
            Locale locale = i == 0 ? Locale.ENGLISH : Locale.ITALIAN;
            MessageBundle.setCurrentLocale(locale);
            assertEquals(expected[i], MessageBundle.getChaosPlayerName());
        }
    }

    @Test
    @DisplayName("Test consoleBadInputMessage() with both languages")
    void testConsoleBadInputMessage() {
        String[] expected = {
                "Invalid input. Please enter two numbers separated by a comma, each from %d to %d, followed by the symbol (X or O) also separated by a comma.%n",
                "Input non valido. Inserisci due numeri separati da una virgola, ciascuno da %d a %d, seguiti dal simbolo (X o O) anch'esso separato da una virgola.%n"
        };

        for (int i = 0; i < 2; i++) {
            Locale locale = i == 0 ? Locale.ENGLISH : Locale.ITALIAN;
            MessageBundle.setCurrentLocale(locale);
            assertEquals(expected[i], MessageBundle.consoleBadInputMessage());
        }
    }

    @Test
    @DisplayName("Test badSelectionConsolePeaceMessage() with both languages")
    void testBadSelectionConsolePeaceMessage() {
        String[] expected = {"Invalid input. Please enter O or X.", "Input non valido. Inserisci O o X."};

        for (int i = 0; i < 2; i++) {
            Locale locale = i == 0 ? Locale.ENGLISH : Locale.ITALIAN;
            MessageBundle.setCurrentLocale(locale);
            assertEquals(expected[i], MessageBundle.badSelectionConsolePeaceMessage());
        }
    }

    @Test
    @DisplayName("Test gameMenuLabel() with default language")
    void testGameMenuLabelDefault() {
        MessageBundle.setCurrentLocale(Locale.ENGLISH);
        assertEquals("Game", MessageBundle.gameMenuLabel());
    }

    @Test
    @DisplayName("Test getComputerTurnMessage() with both languages")
    void testGetComputerTurnMessage() {
        String[] expected = {"Computer's turn...", "Turno del computer..."};

        for (int i = 0; i < 2; i++) {
            Locale locale = i == 0 ? Locale.ENGLISH : new Locale("it", "IT");
            MessageBundle.setCurrentLocale(locale);
            assertEquals(expected[i], MessageBundle.getComputerTurnMessage());
        }
    }

    @Test
    @DisplayName("Test gameMenuExitLabel() with both languages")
    void testGameMenuExitLabel() {
        String[] expected = {"Exit", "Esci"};

        for (int i = 0; i < 2; i++) {
            Locale locale = i == 0 ? Locale.ENGLISH : new Locale("it", "IT");
            MessageBundle.setCurrentLocale(locale);
            assertEquals(expected[i], MessageBundle.gameMenuExitLabel());
        }
    }

    @Test
    @DisplayName("Test gameMenuSwitchModeLabel() with both languages")
    void testGameMenuSwitchModeLabel() {
        String[] expected = {"Switch mode", "Cambia modalit\u00E0"};

        for (int i = 0; i < 2; i++) {
            Locale locale = i == 0 ? Locale.ENGLISH : new Locale("it", "IT");
            MessageBundle.setCurrentLocale(locale);
            assertEquals(expected[i], MessageBundle.gameMenuSwitchModeLabel());
        }
    }

    @Test
    @DisplayName("Test getEnterPlayerNamesMessage() with both languages")
    void testGetEnterPlayerNamesMessage() {
        String[] expected = {"Enter player names", "Inserisci i nomi dei giocatori"};

        for (int i = 0; i < 2; i++) {
            Locale locale = i == 0 ? Locale.ENGLISH : new Locale("it", "IT");
            MessageBundle.setCurrentLocale(locale);
            assertEquals(expected[i], MessageBundle.getEnterPlayerNamesMessage());
        }
    }

}
