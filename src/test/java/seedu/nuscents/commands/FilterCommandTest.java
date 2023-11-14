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

public class FilterCommandTest {
    private TransactionList transactionList;
    private FilterCommand filterCommand;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        transactionList = new TransactionList();
        transactionList.clearTransactionList();
        Date date = new Date(123, Calendar.NOVEMBER, 30);
        Expense testExpense1 = new Expense(20.0f, date, "Lunch", "Pasta",
                ExpenseCategory.FOOD);
        Expense testExpense2 = new Expense(500.0f, date, "Hostel fee", "December",
                ExpenseCategory.RENT);
        Allowance testAllowance1 = new Allowance(100.0f, date, "Pocket money", "November",
                AllowanceCategory.ALLOWANCE);
        transactionList.addTransaction(testExpense1);
        transactionList.addTransaction(testExpense2);
        transactionList.addTransaction(testAllowance1);
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void execute_filterExpenseFood_success() {
        filterCommand = new FilterCommand(ExpenseCategory.FOOD);
        filterCommand.execute(transactionList);
        String expectedOutput =
                "---------------------------------------------------------------------------------------------\n"
                + "Filtered transactions in the category FOOD:\n"
                + "---------------------------------------------------------------------------------------------\n"
                + "S/N    TYPE        AMOUNT   DATE                DESCRIPTION      NOTE        CATEGORY \n"
                + "---------------------------------------------------------------------------------------------\n"
                + "1      Expense     $20.00   30 November, 2023   Lunch            Pasta       FOOD  \n"
                + "---------------------------------------------------------------------------------------------\n"
                + "Total amount for FOOD = 20.00\n"
                + "---------------------------------------------------------------------------------------------";
        String actualOutput = outContent.toString().replaceAll("\\r\\n", "\n").trim();
        assertTrue(actualOutput.contains(expectedOutput));
    }

    @Test
    public void execute_filterExpenseTransportation_noTransactionFound() {
        filterCommand = new FilterCommand(ExpenseCategory.TRANSPORTATION);
        filterCommand.execute(transactionList);
        String expectedOutput = "No transactions found in the category TRANSPORTATION";
        String actualOutput = outContent.toString().replaceAll("\\r\\n", "\n").trim();
        assertTrue(actualOutput.contains(expectedOutput));
    }
}
