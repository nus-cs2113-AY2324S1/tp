package seedu.wildwatch.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.wildwatch.command.AddCommand;
import seedu.wildwatch.command.DeleteCommand;
import seedu.wildwatch.command.EditCommand;
import seedu.wildwatch.command.FindCommand;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmptyDescriptionPrinterTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream mockOutput = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(mockOutput));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    private String getOutput() {
        return mockOutput.toString().trim();
    }

    @Test
    public void emptyDescriptionMessagePrinter_nullDescription_printsGeneralError() {
        EmptyDescriptionPrinter.emptyDescriptionMessagePrinter(null);
        assertEquals("OOPS!!! The description cannot be empty. :-(", getOutput(),
                "General Empty Input Message not working...");
    }

    @Test
    public void emptyDescriptionMessagePrinter_addCommandDescription_printsAddCommandError() {
        EmptyDescriptionPrinter.emptyDescriptionMessagePrinter(AddCommand.COMMAND_WORD);
        assertEquals("OOPS!!! The description of an add command cannot be empty. :-(", getOutput(),
                "Empty Add Command Input Message not working...");
    }

    // Similar tests for DeleteCommand, EditCommand, and FindCommand follow...
    @Test
    public void emptyDescriptionMessagePrinter_deleteCommandDescription_printsDeleteCommandError() {
        EmptyDescriptionPrinter.emptyDescriptionMessagePrinter(DeleteCommand.COMMAND_WORD);
        assertEquals("OOPS!!! The description of a delete command cannot be empty. :-(", getOutput(),
                "Empty Delete Command Input Message not working...");
    }

    @Test
    public void emptyDescriptionMessagePrinter_editCommandDescription_printsEditCommandError() {
        EmptyDescriptionPrinter.emptyDescriptionMessagePrinter(EditCommand.COMMAND_WORD);
        assertEquals("OOPS!!! The description of an edit command cannot be empty. :-(", getOutput(),
                "Empty Edit Command Input Message not working...");
    }

    @Test
    public void emptyDescriptionMessagePrinter_findCommandDescription_printsFindCommandError() {
        EmptyDescriptionPrinter.emptyDescriptionMessagePrinter(FindCommand.COMMAND_WORD);
        assertEquals("OOPS!!! The description of a find command cannot be empty. :-(", getOutput(),
                "Empty Find Command Input Message not working...");
    }

    @Test
    public void emptyDescriptionMessagePrinter_otherDescription_printsGeneralError() {
        EmptyDescriptionPrinter.emptyDescriptionMessagePrinter("SomeOtherCommand");
        assertEquals("OOPS!!! The description cannot be empty. :-(", getOutput(),
                "Empty Other Command Input Message not working...");
    }
}
