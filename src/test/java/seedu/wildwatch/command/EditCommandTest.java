//@@woodenclock
package seedu.wildwatch.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.wildwatch.entry.Entry;
import seedu.wildwatch.entry.EntryList;
import seedu.wildwatch.exception.InvalidInputException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EditCommandTest {
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream mockOutput = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        // Set up the EntryList with one entry for editing
        System.setOut(new PrintStream(mockOutput));
        EntryList.clearEntryList();
        Entry initialEntry = new Entry("01-01-2021", "Lion", "Leo", "The leader of the pride");
        EntryList.addEntry(initialEntry);
    }

    @AfterEach
    void tearDown() {
        EntryList.clearEntryList();
        System.setOut(originalOut);
    }

    @Test
    void execute_validEditChangesEntry() {
        // Simulate input for editing the first entry's date
        String input = "edit I/1 D/02-02-2021";
        LocalDate thisDate = LocalDate.parse("2021-02-02");
        EditCommand editCommand = new EditCommand(input);
        System.out.println("Array size is: " + EntryList.getArraySize());

        assertDoesNotThrow(() -> editCommand.execute());
        Entry editedEntry = EntryList.getEntry(0);

        assertEquals(thisDate, editedEntry.getDate());
    }

    @Test
    void execute_invalidIndex_throwsInvalidInputException() {
        String input = "edit I/-1 D/02-02-2021"; // Index -1 does not exist
        EditCommand editCommand = new EditCommand(input);

        assertThrows(InvalidInputException.class, () -> editCommand.execute());
    }

    @Test
    void execute_invalidInputFormat_throwsInvalidInputException() {
        // Simulate input with invalid format (missing index)
        String input = "edit D/02-02-2021";
        EditCommand editCommand = new EditCommand(input);

        assertThrows(InvalidInputException.class, () -> editCommand.execute());
    }
}
