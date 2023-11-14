package seedu.nuscents.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.nuscents.data.TransactionList;
import seedu.nuscents.ui.Messages;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HelpCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void execute_helpCommand_success() {
        HelpCommand helpCommand = new HelpCommand();
        TransactionList transactionList = new TransactionList();
        helpCommand.execute(transactionList);
        String expectedOutput = Messages.HELP_MENU;
        String actualOutput = outContent.toString();
        assertTrue(actualOutput.contains(expectedOutput));
    }
}
