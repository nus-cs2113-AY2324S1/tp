package seedu.nuscents.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.nuscents.data.TransactionList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BudgetCommandTest {
    private TransactionList transactionList;
    private BudgetCommand budgetCommand;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        transactionList = new TransactionList();
        transactionList.clearTransactionList();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void execute_budgetAdded_success() {
        float budget = 200.0f;
        budgetCommand = new BudgetCommand(budget);
        assertDoesNotThrow(() -> budgetCommand.execute(transactionList));
        assertEquals(transactionList.getBudget(), budget);
    }
}
