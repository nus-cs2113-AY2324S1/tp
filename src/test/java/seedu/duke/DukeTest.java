package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.commands.KaChinnnngException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DukeTest {

    Duke duke;

    @BeforeEach
    void setUp() {
        duke = new Duke();
    }

    @Test
    void loadData_noFileExists_createsFile() {
        File file = new File("KaChinnnngggg.txt");
        if (file.exists()) {
            boolean isDeleted = file.delete();
            assertTrue(isDeleted, "File should have been deleted before the test.");
        }
        duke.loadData();
        assertTrue(file.exists(), "File should have been created");
    }

    @Test
    void loadData_fileExists_loadsData() {
        File file = new File("KaChinnnngggg.txt");
        if (file.exists()) {
            boolean isDeleted = file.delete();
            assertTrue(isDeleted, "File should have been deleted before the test.");
        }
        duke.loadData();
        assertTrue(file.exists(), "File should have been created");
    }

    @Test
    void executeCommand_validCommand_noExceptionThrown() {
        assertThrows(KaChinnnngException.class, () ->
                duke.executeCommand("income /de Salary /date 12/10/2023 /amt 5000.00"));
    }

    @Test
    void executeCommand_invalidCommand_exceptionThrown() {
        assertThrows(KaChinnnngException.class, () ->
                duke.executeCommand("income /de Salary /date 12/10/2023 /amt 5000.00"));
    }

    @Test
    void executeCommand_addIncome_increasesSizeOfIncomeList() throws KaChinnnngException {
        int initialSize = duke.getIncomesSize();
        duke.executeCommand("add income /de Bonus /date 01/10/2023 /amt 1000.00");
        assertEquals(initialSize + 1, duke.getIncomesSize());
    }

    @Test
    void addIncome_invalidIncome_exception() {
        assertThrows(KaChinnnngException.class, () ->
                duke.executeCommand("add income /de Bonus /date 01/10/2024 /amt 1000.00"));
    }

    @Test
    void executeCommand_addExpense_increasesSizeOfExpenseList() throws KaChinnnngException {
        int initialSize = duke.getExpensesSize();
        duke.executeCommand("add expense /cat food /type lunch" +
                " /de chicken sandwich /date 01/10/2023 /amt 10.00");
        assertEquals(initialSize + 1, duke.getExpensesSize());
    }

    @Test
    void addExpense_invalidExpense_exception() {
        assertThrows(KaChinnnngException.class, () -> duke.executeCommand("add expense /cat food /type lunch" +
                " /de chicken sandwich /date 01/10/2024 /amt 10.00"));
    }

    @Test
    void executeCommand_deleteIncome_decreasesSizeOfIncomeList() throws KaChinnnngException {
        duke.executeCommand("add income /de Bonus /date 03/10/2023 /amt 1000.00");
        int sizeAfterAddition = duke.getIncomesSize();
        duke.executeCommand("delete income 1");  // Assuming index 1 exists for this test
        assertEquals(sizeAfterAddition - 1, duke.getIncomesSize());
    }

    @Test
    void executeCommand_deleteExpense_decreasesSizeOfExpenseList() throws KaChinnnngException {
        duke.executeCommand("add expense /cat food /type lunch" +
                " /de chicken sandwich /date 03/10/2023 /amt 10.00");
        int sizeAfterAddition = duke.getExpensesSize();
        duke.executeCommand("delete expense 1");  // Assuming index 1 exists for this test
        assertEquals(sizeAfterAddition - 1, duke.getExpensesSize());
    }

    @Test
    void executeCommand_listIncome_correctOutput() throws KaChinnnngException {
        // Backup the original system out
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream captureStream = new PrintStream(outputStream);
        System.setOut(captureStream);

        // Capture add income output
        duke.executeCommand("add income /de Bonus /date 03/10/2023 /amt 1000.00");
        String addIncomeOutput = outputStream.toString().trim();
        String expectedAddIncomeOutput =
                "____________________________________________________________" + System.lineSeparator()
                        + "Got it. I've added this income:" + System.lineSeparator() +
                        "Income: Bonus | Date: 03/Oct/2023 | Amount: $1000.00" + System.lineSeparator() +
                        "____________________________________________________________";
        assertEquals(expectedAddIncomeOutput, addIncomeOutput);

        // Clear the outputStream
        outputStream.reset();

        // Capture list income output
        duke.executeCommand("list income");
        String listIncomeOutput = outputStream.toString().trim();
        String expectedListIncomeOutput =
                "____________________________________________________________" + System.lineSeparator() +
                        "Here are your incomes:" + System.lineSeparator() +
                        "1. Income: Bonus | Date: 03/Oct/2023 | Amount: $1000.00" + System.lineSeparator() +
                        "____________________________________________________________";
        assertEquals(expectedListIncomeOutput, listIncomeOutput);

        // Reset the system out
        System.setOut(originalOut);
    }

    @Test
    void executeCommand_listExpense_correctOutput() throws KaChinnnngException {
        // Backup the original system out
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream captureStream = new PrintStream(outputStream);
        System.setOut(captureStream);

        // Capture add expense output
        duke.executeCommand("add expense /cat food /type lunch /de chicken sandwich /date 01/10/2023 /amt 10.00");
        String addExpenseOutput = outputStream.toString().trim();
        String expectedAddExpenseOutput =
                "____________________________________________________________" + System.lineSeparator() +
                        "Got it. I've added this expense:" + System.lineSeparator() +
                        "Food Expense (LUNCH): chicken sandwich | Date: 01/Oct/2023 | Amount: $10.00" +
                         System.lineSeparator() +
                        "____________________________________________________________";
        assertEquals(expectedAddExpenseOutput, addExpenseOutput);

        // Clear the outputStream
        outputStream.reset();

        // Capture list expense output
        duke.executeCommand("list expense");
        String listExpenseOutput = outputStream.toString().trim();
        String expectedListExpenseOutput =
                "____________________________________________________________" + System.lineSeparator() +
                        "Here are your expenses:" + System.lineSeparator() +
                        "1. Food Expense (LUNCH): chicken sandwich | Date: 01/Oct/2023 | Amount: $10.00"
                        + System.lineSeparator() +
                        "____________________________________________________________";
        assertEquals(expectedListExpenseOutput, listExpenseOutput);

        // Reset the system out
        System.setOut(originalOut);
    }


    @Test
    void executeCommand_listAll_correctOutput() throws KaChinnnngException {
        // Backup the original system out
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream captureStream = new PrintStream(outputStream);
        System.setOut(captureStream);

        // Assuming the list starts empty, and we add an income and an expense
        duke.executeCommand("add income /de Bonus /date 03/10/2023 /amt 1000.00");
        duke.executeCommand("add expense /cat food /type lunch /de chicken sandwich /date 01/10/2023 /amt 10.00");

        // Clear the outputStream to only capture the list-all output
        outputStream.reset();

        // Capture list-all output
        duke.executeCommand("list");
        String listAllOutput = outputStream.toString().trim();

        String expectedListAllOutput =
                "____________________________________________________________" + System.lineSeparator() +
                        "Here are your incomes:" + System.lineSeparator() +
                        "1. Income: Bonus | Date: 03/Oct/2023 | Amount: $1000.00" + System.lineSeparator() +
                        "Total income is: $1000.00." + System.lineSeparator() + System.lineSeparator() +
                        "Here are your expenses:" + System.lineSeparator() +
                        "1. Food Expense (LUNCH): chicken sandwich | Date: 01/Oct/2023 | Amount: $10.00" +
                        System.lineSeparator() +
                        "Total expenses is: $10.00." + System.lineSeparator() + System.lineSeparator() +
                        "Total balance is: $990.00." + System.lineSeparator() +
                        "____________________________________________________________";
        expectedListAllOutput = expectedListAllOutput.trim();
        assertEquals(expectedListAllOutput, listAllOutput);

        // Restore the original system out
        System.setOut(originalOut);
    }

    @Test
    void executeCommand_help_correctOutput() throws KaChinnnngException {
        // Backup the original system out
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream captureStream = new PrintStream(outputStream);
        System.setOut(captureStream);

        // Capture help output
        duke.executeCommand("help");
        String helpOutput = outputStream.toString().trim();

        // The expected output should represent whatever the help command outputs in your program
        String expectedHelpOutput = "____________________________________________________________"
                + System.lineSeparator() +
                "You can access our user guide by " +
                "https://docs.google.com/document/d/1BOz_v4eYQ8y7Dje6Jm6nqymi9jmrsb9MAohLCL_sLvI/edit?usp=sharing" +
                System.lineSeparator() +
                System.lineSeparator() +
                "Functions and their format:" + System.lineSeparator() +
                System.lineSeparator() +
                "Adding an entry: add" + System.lineSeparator() +
                "Format:" + System.lineSeparator() +
                "Add expense /category /description /value" + System.lineSeparator() +
                "Add income /description /value" + System.lineSeparator() +
                System.lineSeparator() +
                "Listing all entries: list" + System.lineSeparator() +
                "Format:" + System.lineSeparator() +
                "list" + System.lineSeparator() +
                "list income" + System.lineSeparator() +
                "list expense" + System.lineSeparator() +
                System.lineSeparator() +
                "Deleting an entry: delete" + System.lineSeparator() +
                "Format:" + System.lineSeparator() +
                "delete income [index_pos]" + System.lineSeparator() +
                "delete expense [index_pos]" + System.lineSeparator()
                + System.lineSeparator() +
                "Check balance of income: balance" + System.lineSeparator() +
                "Format:" + System.lineSeparator() +
                "balance" + System.lineSeparator()
                + System.lineSeparator() +
                "Exiting the program: exit" + System.lineSeparator() +
                "Format:" + System.lineSeparator() +
                "exit" + System.lineSeparator() +
                "____________________________________________________________";
        assertEquals(expectedHelpOutput, helpOutput);
        // Restore the original System.out
        System.setOut(originalOut);
    }

    @Test
    void executeCommand_balance_correctOutput() throws KaChinnnngException {
        // Backup the original system out
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream captureStream = new PrintStream(outputStream);
        System.setOut(captureStream);

        // Assuming the list starts empty, and you add an income and an expense
        duke.executeCommand("add income /de Bonus /date 03/10/2023 /amt 1000.00");
        duke.executeCommand("add expense /cat food /type lunch " +
                "/de chicken sandwich /date 01/10/2023 /amt 10.00");

        // Clear the outputStream to only capture the balance output
        outputStream.reset();

        // Capture balance output
        duke.executeCommand("balance");
        String balanceOutput = outputStream.toString().trim();

        // The expected output based on the given additions
        String expectedBalanceOutput =
                "____________________________________________________________" + System.lineSeparator() +
                        "Balance: 990.0" + System.lineSeparator() +
                        "____________________________________________________________";

        assertEquals(expectedBalanceOutput, balanceOutput);

        // Restore the original System.out
        System.setOut(originalOut);
    }
}
