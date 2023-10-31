package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.financialrecords.Expense;
import seedu.duke.financialrecords.Income;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the {@link ClearAll} class.
 * This test class provides test cases to check the deletion of records in
 * both incomes and expenses record list
 */
public class ClearIncomesTest {
    private ArrayList<Income> incomes;
    private ArrayList<Expense> expenses;

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    @BeforeEach
    public void setup() throws KaChinnnngException {
        incomes = new ArrayList<>();

        incomes.add(new Income("Salary", LocalDate.of(2023, 10, 10), 5000.0));
        incomes.add(new Income("Bonus", LocalDate.of(2023, 10, 15), 1000.0));
    }

    /**
     * Test the {@link ClearIncomes#clearAllIncomes()} method with incomes.
     * This test case checks if the clear incomes function correctly delete all the record in the income list.
     */
    @Test
    public void testClearIncome_withIncomes() {
        ArrayList<Income> testIncome = new ArrayList<>(incomes);
        ClearIncomes clearTestIncome = new ClearIncomes(testIncome);
        clearTestIncome.clearAllIncomes();
        assertEquals(0, testIncome.size());
    }


    /**
     * Test the {@link ClearIncomes#clearAllIncomes()} method with no records.
     * This test case checks if there is an empty list, whether clear income function will still process correctly
     */
    @Test
    public void testNoIncomes() {
        ArrayList<Income> testIncome = new ArrayList<>();
        ClearIncomes clearTestIncome = new ClearIncomes(testIncome);
        clearTestIncome.clearAllIncomes();
        assertEquals(0, testIncome.size());
    }

}
