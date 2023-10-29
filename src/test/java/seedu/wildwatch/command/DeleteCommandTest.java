package seedu.wildwatch.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

import static seedu.wildwatch.entry.EntryList.addEntry;
import static seedu.wildwatch.entry.EntryList.clearEntry;
import seedu.wildwatch.entry.Entry;
import seedu.wildwatch.entry.EntryList;

public class DeleteCommandTest {
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream mockOutput = new ByteArrayOutputStream();
    private InputStream originalIn;
    private ByteArrayInputStream mockInput;

    @BeforeEach
    void redirectSystemOut() {
        System.setOut(new PrintStream(mockOutput));
        originalIn = System.in;
    }

    @AfterEach
    void restoreSystemOut() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void testExecuteConfirmation() {
        clearEntry();
        addEntry(new Entry("28-10-23", "Lion", "Simba", "This is Simba."));

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
        clearEntry();
        addEntry(new Entry("28-10-23", "Lion", "Simba", "This is Simba."));

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
        clearEntry();
        addEntry(new Entry("09-11-23", "Lion", "Simba", "This is Simba"));

        DeleteCommand deleteCommand = new DeleteCommand(2);
        deleteCommand.execute();

        int entryListSizeAfter = EntryList.getArraySize();

        assertEquals(1,entryListSizeAfter,"Entry count should remain the same due to deletion of an invalid index");

    }
}