package seedu.nuscents.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.nuscents.data.TransactionList;
import seedu.nuscents.data.transaction.Allowance;
import seedu.nuscents.data.transaction.AllowanceCategory;
import seedu.nuscents.data.transaction.Expense;
import seedu.nuscents.data.transaction.ExpenseCategory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ViewCommandTest {
    private ViewCommand viewCommand;
    private TransactionList transactionList;
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
    public void execute_viewTransaction_success() {
        Date date = new Date(123, Calendar.NOVEMBER, 30);
        Expense testExpense = new Expense(20.0f, date, "Lunch", "Pasta",
                ExpenseCategory.FOOD);
        Allowance testAllowance = new Allowance(100.0f, date, "Pocket money", "November",
                AllowanceCategory.ALLOWANCE);
        transactionList.addTransaction(testExpense);
        transactionList.addTransaction(testAllowance);
        viewCommand = new ViewCommand(1);
        viewCommand.execute(transactionList);
        String expectedOutput =
                "---------------------------------------------------------------------------------------------\n"
                + "Following are details of the transaction:\n"
                + "TYPE: EXPENSE\n"
                + "DATE: 30 November, 2023\n"
                + "AMOUNT: 20.0\n"
                + "DESCRIPTION: Lunch\n"
                + "NOTE: Pasta\n"
                + "CATEGORY: FOOD\n"
                + "---------------------------------------------------------------------------------------------";
        String actualOutput = outContent.toString().replaceAll("\\r\\n", "\n").trim();
        assertTrue(actualOutput.contains(expectedOutput));
    }
}
