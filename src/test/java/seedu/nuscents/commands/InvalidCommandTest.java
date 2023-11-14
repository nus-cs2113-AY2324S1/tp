package seedu.nuscents.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.nuscents.data.TransactionList;
import seedu.nuscents.data.exception.NuscentsException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.nuscents.ui.Messages.MESSAGE_UNKNOWN;

public class InvalidCommandTest {
    private TransactionList transactionList;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        transactionList = new TransactionList();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void execute_invalidCommand_throwsNuscentsException() {
        InvalidCommand invalidCommand = new InvalidCommand();
        assertThrows(NuscentsException.class, () -> {
            invalidCommand.execute(transactionList); }, MESSAGE_UNKNOWN);
    }
}
