package seedu.wildwatch.ui;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputConsoleTest {
    private final InputStream originalIn = System.in;

    @BeforeEach
    public void setUpInput() {
    }

    @AfterEach
    public void restoreSystemInput() {
        // After a test runs, restore System.in to its original source.
        System.setIn(originalIn);
    }

    private void provideInput(String data) {
        ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(mockInput);
    }

    @Test
    public void testInputRetriever_testInput_returnTrimmedInput() {
        // Given
        String expected = "test input";
        provideInput("   " + expected + "    " + "\n"); // Simulate user input that includes the newline at the end.

        // When
        String actual = InputConsole.inputRetriever();

        // Checkint whether the expected matches the actual input
        assertEquals(expected, actual, "Input should be trimmed and match expected output.");
    }
}
