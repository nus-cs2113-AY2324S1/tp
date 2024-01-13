package seedu.wildwatch.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static seedu.wildwatch.entry.EntryList.addEntry;
import static seedu.wildwatch.entry.EntryList.clearEntryList;

import seedu.wildwatch.entry.Entry;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class ListCommandTest {
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
    void listEntry() {
        clearEntryList();
        addEntry(new Entry("28-10-2023", "Lion", "Simba", "This is Simba."));
        addEntry(new Entry("28-10-2023", "Tiger", "Tigger", "This is Tigger."));

        ListCommand.listEntry();
        String consoleOutput = mockOutput.toString();

        String expectedOutput = "1.Date: [28-10-2023] | Species: [Lion] | Name: [Simba] | Remark: [This is Simba.]"
                + "\n"
                + "2.Date: [28-10-2023] | Species: [Tiger] | Name: [Tigger] | Remark: [This is Tigger.]"
                + "\n"
                + "Now you have 2 entries in the list."
                + "\n";

        consoleOutput = consoleOutput.replace(System.lineSeparator(), "\n");

        assertEquals(expectedOutput, consoleOutput);
    }

    @Test
    void listEntryWithZeroEntry() {
        clearEntryList();
        ListCommand.listEntry();
        String consoleOutput = mockOutput.toString();
        String expectedOutput = "Now you have no entries in the list."
                + "\n";
        consoleOutput = consoleOutput.replace(System.lineSeparator(), "\n");
        assertEquals(expectedOutput, consoleOutput);
    }
}
