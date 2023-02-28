package consoleui;

import entities.Player;
import entities.PlayerRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
                "******************************************************\n" +
                        "*                                                    *\n" +
                        "*                 JOHN WINS!                      *\n" +
                        "*                                                    *\n" +
                        "******************************************************\n" +
                        "\n";
        assertEquals(expectedOutput, outContent.toString());
    }

}
