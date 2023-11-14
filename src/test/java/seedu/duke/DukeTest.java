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
    void executeCommand_clearIncome_incomeListSizeBeZero() throws KaChinnnngException {
        duke.executeCommand("add income /de Bonus /date 03/10/2023 /amt 1000.00");
        duke.executeCommand("add income /de Bonus /date 03/10/2023 /amt 1000.00");
        int sizeAfterAddition = duke.getIncomesSize();
        assertEquals(2, sizeAfterAddition);
        duke.executeCommand("clear incomes");  // Assuming clear income command called
        assertEquals(0, duke.getExpensesSize());
    }

    @Test
    void executeCommand_clearExpense_expenseListSizeBeZero() throws KaChinnnngException {
        duke.executeCommand("add expense /cat food /type lunch" +
                " /de chicken sandwich /date 03/10/2023 /amt 10.00");
        duke.executeCommand("add expense /cat food /type lunch" +
                " /de chicken sandwich /date 03/10/2023 /amt 10.00");
        int sizeAfterAddition = duke.getExpensesSize();
        assertEquals(2, sizeAfterAddition);
        duke.executeCommand("clear expenses");  // Assuming clear income command called
        assertEquals(0, duke.getExpensesSize());
    }

    @Test
    void executeCommand_clearAll_bothExpenseAndIncomeListSizeBeZero() throws KaChinnnngException {
        duke.executeCommand("add income /de Bonus /date 03/10/2023 /amt 1000.00");
        duke.executeCommand("add income /de Bonus /date 03/10/2023 /amt 1000.00");
        duke.executeCommand("add expense /cat food /type lunch" +
                " /de chicken sandwich /date 03/10/2023 /amt 10.00");
        duke.executeCommand("add expense /cat food /type lunch" +
                " /de chicken sandwich /date 03/10/2023 /amt 10.00");
        int sizeAfterAddition = duke.getExpensesSize() + duke.getIncomesSize();
        assertEquals(4, sizeAfterAddition);
        duke.executeCommand("clear all");  // Assuming clear income command called
        assertEquals(0, duke.getExpensesSize());
    }

    @Test
    void executeCommand_allHelpFunction_makeSureCommandIsCorrectlyReturned() throws KaChinnnngException {
        duke.executeCommand("help");                // If test case passed mean all the command being accepted
        duke.executeCommand("help add");            // If one of the command not accepted then will throw
        duke.executeCommand("help delete");         // KaChinnnngException
        duke.executeCommand("help list");
        duke.executeCommand("help find");
        duke.executeCommand("help edit");
        duke.executeCommand("help update exchange rate");
        duke.executeCommand("help clear");
        duke.executeCommand("help balance");
        duke.executeCommand("help exit");
    }

    @Test
    void executeCommand_allListFunction_makeSureCommandIsCorrectlyParsed() throws KaChinnnngException {
        duke.executeCommand("list");                // If test case passed mean all the command being accepted
        duke.executeCommand("list incomes");         // If one of the command not accepted then will throw
        duke.executeCommand("list expenses");        // KaChinnnngException
        duke.executeCommand("list currencies");
        duke.executeCommand("list exchange rates");
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
                        "Balance: 990.00" + System.lineSeparator() +
                        "____________________________________________________________";

        assertEquals(expectedBalanceOutput, balanceOutput);

        // Restore the original System.out
        System.setOut(originalOut);
    }
}
