package seedu.wildwatch.command;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import seedu.wildwatch.entry.Entry;
import seedu.wildwatch.entry.EntryList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for AddCommand.
 */
public class AddCommandTest {
    private Entry testEntry;
    private AddCommand thisAddCommand;
    private final ByteArrayOutputStream mockOutput = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(mockOutput));

        // Reset the EntryList to ensure it's empty before each test
        EntryList.clearEntryList();

        // Create a test entry to be used in the tests
        testEntry = new Entry("02-03-2023", "Annam Leaf Turtle", "Ariel", "Injured left flipper");

        // Create the AddCommand with the test entry
        thisAddCommand = new AddCommand(testEntry);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);

        // Clear the EntryList after each test to avoid side effects on other tests
        EntryList.clearEntryList();
    }

    @Test
    public void execute_entryAddedToList_success() {
        // Before executing, the entry list should be empty
        assertEquals(0, EntryList.getArraySize());

        // Execute the command
        thisAddCommand.execute();

        // After executing, the entry list should have one entry
        assertEquals(1, EntryList.getArraySize());

        // Verify that the entry is the one we added
        Entry addedEntry = EntryList.getEntry(0);
        assertEquals(testEntry, addedEntry);
    }

    @Test
    public void equals_sameEntry_true() {
        // Verify that an AddCommand is equal to itself
        assertTrue(thisAddCommand.equals(thisAddCommand));

        // Create another AddCommand with the same entry
        AddCommand otherAddCommand = new AddCommand(testEntry);
        assertTrue(thisAddCommand.equals(otherAddCommand));
    }
}
