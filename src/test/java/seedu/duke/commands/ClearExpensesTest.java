package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.financialrecords.Expense;

import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * This class contains JUnit tests for the DeleteExpenseCommand class.
 */
public class ClearExpensesTest {
    private ArrayList<Expense> expenses;

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    @BeforeEach
    public void setup() throws KaChinnnngException {
        expenses = new ArrayList<>();

        expenses.add(new Expense("Rent", LocalDate.of(2023, 10, 5), 2000.0));
        expenses.add(new Expense("Groceries", LocalDate.of(2023, 10, 7), 100.0));
    }

    /**
     * Test the {@link ClearExpenses#clearAllExpenses()} method with expenses.
     * This test case checks if the clear incomes function correctly delete all the record in the income list.
     */
    @Test
    public void testClearExpenses_withExpenses() {
        ArrayList<Expense> testExpenses = new ArrayList<>(expenses);
        ClearExpenses clearExpenses = new ClearExpenses(testExpenses);
        clearExpenses.clearAllExpenses();
        assertEquals(0, testExpenses.size());
    }


    /**
     * Test the {@link ClearExpenses#clearAllExpenses()} method with no records.
     * This test case checks if there is an empty list, whether clear income function will still process correctly
     */
    @Test
    public void testNoExpenses() throws KaChinnnngException{
        ArrayList<Expense> testExpenses = new ArrayList<>();
        ClearExpenses clearExpenses = new ClearExpenses(testExpenses);
        clearExpenses.clearAllExpenses();
        assertEquals(0, testExpenses.size());
    }

}