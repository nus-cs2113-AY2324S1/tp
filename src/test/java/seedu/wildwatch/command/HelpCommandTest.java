package seedu.wildwatch.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HelpCommandTest {
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream mockOutput = new ByteArrayOutputStream();

    @BeforeEach
    void redirectSystemOut() {
        System.setOut(new PrintStream(mockOutput));
    }

    @AfterEach
    void restoreSystemOut() {
        System.setOut(originalOut);
    }

    @Test
    void testExecute() {
        HelpCommand helpCommand = new HelpCommand("");
        helpCommand.execute();

        String expectedMessage = HelpCommand.getHelpPage();

        // Normalize the line separators in the actual and expected output to ignore differences
        String actualOutput = mockOutput.toString().replace(System.lineSeparator(), "\n");
        String expectedOutput = expectedMessage.replace(System.lineSeparator(), "\n");

        // Assert: Verify the printed output contains the expected message.
        assertTrue(actualOutput.contains(expectedOutput));
    }
}
