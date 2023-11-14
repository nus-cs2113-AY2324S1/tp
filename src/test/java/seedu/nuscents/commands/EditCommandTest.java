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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditCommandTest {
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
    public void execute_editTransaction_success() {
        transactionList.clearTransactionList();
        Date date = new Date(123, Calendar.NOVEMBER, 30);
        Expense testExpense = new Expense(20.0f, date, "Lunch", "Pasta",
                ExpenseCategory.NO_EXPENSE_CATEGORY);
        transactionList.addTransaction(testExpense);
        Expense modifiedExpense = new Expense(20.0f, date, "Lunch", "Pasta",
                ExpenseCategory.FOOD);
        EditCommand editCommand = new EditCommand(modifiedExpense, 1);
        assertDoesNotThrow(() -> editCommand.execute(transactionList));
        assertEquals(ExpenseCategory.FOOD, transactionList.getTransactions().get(0).getCategory());
    }
}
