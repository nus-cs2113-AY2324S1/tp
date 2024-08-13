package seedu.wildwatch.command;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.wildwatch.entry.EntryList.addEntry;
import static seedu.wildwatch.entry.EntryList.clearEntryList;
import seedu.wildwatch.entry.Entry;
import seedu.wildwatch.entry.EntryList;

public class DeleteCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream mockOutput = new ByteArrayOutputStream();
    private InputStream standardIn;
    private ByteArrayInputStream mockInput;

    @BeforeEach
    void redirectSystemOut() {
        System.setOut(new PrintStream(mockOutput));
        standardIn = System.in;
    }

    @AfterEach
    void restoreSystemOut() {
        System.setOut(standardOut);
        System.setIn(standardIn);
    }

    @Test
    void testExecuteConfirmation() {
        clearEntryList();
        addEntry(new Entry("28-10-2023", "Lion", "Simba", "This is Simba."));

        // Prepare user input "yes" for confirmation
        mockInput = new ByteArrayInputStream("yes\n".getBytes());
        System.setIn(mockInput);

        DeleteCommand deleteCommand = new DeleteCommand(1);
        deleteCommand.execute();

        String consoleOutput = mockOutput.toString();

        // Assert: Verify the confirmation message and that the entry is removed
        assertTrue(consoleOutput.contains("Are you sure you want to delete this entry? (yes/no):"));
        assertTrue(consoleOutput.contains("The entry has been deleted."));
    }

    @Test
    void testExecuteCancellation() {
        clearEntryList();
        addEntry(new Entry("28-10-2023", "Lion", "Simba", "This is Simba."));

        // Prepare user input "no" for cancellation
        mockInput = new ByteArrayInputStream("no\n".getBytes());
        System.setIn(mockInput);

        DeleteCommand deleteCommand = new DeleteCommand(1);
        deleteCommand.execute();

        String consoleOutput = mockOutput.toString();

        // Assert: Verify the confirmation message and that the entry is not removed
        assertTrue(consoleOutput.contains("Are you sure you want to delete this entry? (yes/no):"));
        assertTrue(consoleOutput.contains("The entry was not deleted."));
    }

    @Test
    public void testDeleteCommandWithInvalidIndex(){
        clearEntryList();
        addEntry(new Entry("09-11-2023", "Lion", "Simba", "This is Simba"));

        DeleteCommand deleteCommand = new DeleteCommand(2);
        deleteCommand.execute();

        int entryListSizeAfter = EntryList.getArraySize();

        assertEquals(1,entryListSizeAfter,"Entry count should remain the same due to deletion of an invalid index");

    }
}
