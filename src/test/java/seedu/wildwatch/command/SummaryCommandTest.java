package seedu.wildwatch.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.wildwatch.entry.Entry;
import seedu.wildwatch.entry.EntryList;
import seedu.wildwatch.exception.InvalidInputException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SummaryCommandTest {
    private Entry testEntry;
    private AddCommand thisAddCommand;
    private final ByteArrayOutputStream mockOutput = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    @BeforeEach
    public void setUp() {
        // Reset the EntryList to ensure it's empty before each test
        EntryList.clearEntryList();
        // Create a test entry to be used in the tests
        testEntry = new Entry("02-03-2023", "Annam Leaf Turtle", "Ariel", "Injured left flipper");
        new AddCommand(testEntry).execute();
        testEntry = new Entry("02-03-2023", "Annam Leaf Turtle", "Javier", "Injured left flipper");
        new AddCommand(testEntry).execute();


        // Redirect output only AFTER we have executed the two AddCommand above
        System.setOut(new PrintStream(mockOutput));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);

        // Clear the EntryList after each test to avoid side effects on other tests
        EntryList.clearEntryList();
    }
    /**
     * Test to see if "summary SPECIES" command work as expected
     */
    @Test
    public void testExecute() {
        // Before executing, the entry list size should be 2 (we added two entries beforehand)
        assertEquals(2, EntryList.getArraySize());

        // Execute the command
        try {
            new SummaryCommand("Annam Leaf Turtle").execute();
            String actualOutput = mockOutput.toString().replace(System.lineSeparator(), "\n");
            String expectedOutput = "grouped by their names".replace(System.lineSeparator(), "\n");
            assertTrue(actualOutput.contains(expectedOutput));
        } catch(InvalidInputException e) {
            fail(); // fail assertion
        }
    }

}
