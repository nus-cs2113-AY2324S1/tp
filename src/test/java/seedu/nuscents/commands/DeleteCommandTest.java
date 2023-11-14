package seedu.nuscents.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.nuscents.data.TransactionList;
import seedu.nuscents.data.transaction.Expense;
import seedu.nuscents.data.transaction.ExpenseCategory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    private TransactionList transactionList;
    private Expense testExpense;
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
    public void execute_transactionDeleted_success() {
        transactionList.clearTransactionList();
        Date date = new Date(123, Calendar.NOVEMBER, 30);
        testExpense = new Expense(20.0f, date, "Lunch", "Pasta", ExpenseCategory.FOOD);
        transactionList.addTransaction(testExpense);
        DeleteCommand deleteCommand = new DeleteCommand(1);
        // Before executing, the transaction list should have one transaction
        assertEquals(1, TransactionList.getTransactionCount());
        deleteCommand.execute(transactionList);
        // After executing, the transaction list should be empty
        assertEquals(0, TransactionList.getTransactionCount());
    }
}
