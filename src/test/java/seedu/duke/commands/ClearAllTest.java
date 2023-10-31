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
public class ClearAllTest {
    private ArrayList<Income> incomes;
    private ArrayList<Expense> expenses;

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    @BeforeEach
    public void setup() throws KaChinnnngException {
        incomes = new ArrayList<>();
        expenses = new ArrayList<>();

        incomes.add(new Income("Salary", LocalDate.of(2023, 10, 10), 5000.0));
        incomes.add(new Income("Bonus", LocalDate.of(2023, 10, 15), 1000.0));

        expenses.add(new Expense("Rent", LocalDate.of(2023, 10, 5), 2000.0));
        expenses.add(new Expense("Groceries", LocalDate.of(2023, 10, 7), 100.0));
    }

    /**
     * Test the {@link ClearAll#clearAllIncomeAndExpense()} method with both incomes and expenses.
     * This test case checks if the clear all function correctly delete all the record in the income and expenses list.
     */
    @Test
    public void testClearAll_withIncomesAndExpenses() {
        ArrayList<Income> testIncomes = new ArrayList<>(incomes);
        ArrayList<Expense> testExpenses = new ArrayList<>(expenses);
        ClearAll clearAll = new ClearAll(testIncomes, testExpenses);
        clearAll.clearAllIncomeAndExpense();
        assertEquals(0, testIncomes.size());
        assertEquals(0, testExpenses.size());
    }

    /**
     * Test the {@link ClearAll#clearAllIncomeAndExpense()} method with only incomes but not expenses.
     * This test case checks if the clear all function correctly
     * delete all the record in the both list without error.
     */
    @Test
    public void testClearAll_withIncomesNoExpenses() {
        ArrayList<Income> testIncomes = new ArrayList<>(incomes);
        ArrayList<Expense> testExpenses = new ArrayList<>();
        ClearAll clearAll = new ClearAll(testIncomes, testExpenses);
        clearAll.clearAllIncomeAndExpense();
        assertEquals(0, testIncomes.size());
        assertEquals(0, testExpenses.size());
    }

    /**
     * Test the {@link ClearAll#clearAllIncomeAndExpense()} method with only expenses but no incomes.
     * This test case checks if the clear all function correctly
     * delete all the record in the both list without error.
     */
    @Test
    public void testClearAll_withExpensesNoIncomes() {
        ArrayList<Income> testIncomes = new ArrayList<>();
        ArrayList<Expense> testExpenses = new ArrayList<>(expenses);
        ClearAll clearAll = new ClearAll(testIncomes, testExpenses);
        clearAll.clearAllIncomeAndExpense();
        assertEquals(0, testIncomes.size());
        assertEquals(0, testExpenses.size());
    }

    /**
     * Test the {@link ClearAll#clearAllIncomeAndExpense()} method with no records.
     * This test case checks if there is an empty list, whether clear income function will still process correctly
     */
    @Test
    public void testNoIncomesAndExpenses() {
        ArrayList<Income> testIncomes = new ArrayList<>();
        ArrayList<Expense> testExpenses = new ArrayList<>();
        ClearAll clearAll = new ClearAll(testIncomes, testExpenses);
        clearAll.clearAllIncomeAndExpense();
        assertEquals(0, testIncomes.size());
        assertEquals(0, testExpenses.size());
    }
}
