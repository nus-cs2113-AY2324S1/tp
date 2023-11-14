package seedu.nuscents.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import seedu.nuscents.data.TransactionList;
import seedu.nuscents.data.transaction.Expense;
import seedu.nuscents.data.transaction.ExpenseCategory;
import seedu.nuscents.data.transaction.Transaction;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {
    private TransactionList transactionList;
    private Expense testExpense;
    private AddCommand addCommand;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        transactionList = new TransactionList();
        transactionList.clearTransactionList();
        Date date = new Date(123, Calendar.NOVEMBER, 30);
        // Create a test transaction to be used in the tests
        testExpense = new Expense(20.0f, date, "Lunch", "Pasta", ExpenseCategory.FOOD);
        addCommand = new AddCommand(testExpense);
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void execute_transactionAdded_success() {
        // Before executing, the transaction list should be empty
        assertEquals(0, TransactionList.getTransactionCount());
        addCommand.execute(transactionList);
        // After executing, the transaction list should have one entry
        assertEquals(1, TransactionList.getTransactionCount());
        // Verify that the transaction is the one we added
        Transaction addedEntry = transactionList.getTransactions().get(0);
        assertEquals(testExpense, addedEntry);
    }
}
