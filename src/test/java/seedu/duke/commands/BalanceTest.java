package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.financialrecords.Expense;
import seedu.duke.financialrecords.Income;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the {@link Balance} class.
 * This test class provides test cases to check the calculation and reporting
 * of financial balance between income and expenses.
 */
public class BalanceTest {
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
     * Test the {@link Balance#getBalance()} method with both income and expenses.
     * This test case checks if the balance calculation correctly subtracts expenses from income.
     */
    @Test
    public void testGetBalance_withBothIncomeAndExpenses_returnsCorrectDifference() {
        Balance balance = new Balance(incomes, expenses);
        assertEquals(3900.0, balance.getBalance());
    }


    /**
     * Test the {@link Balance#getBalance()} method with no records.
     * This test case checks if the balance calculation correctly returns 0.
     */
    @Test
    public void testNoRecords() {
        Balance balance = new Balance(new ArrayList<>(), new ArrayList<>());
        assertEquals(0, balance.getBalance(),
                "Balance should be 0 when no records are present.");
    }

    /**
     * Test the {@link Balance#getBalance()} method with no expenses.
     * This test case checks if the balance calculation correctly returns a positive value equal to income.
     */
    @Test
    public void testGetBalance_withNoExpenses_returnsIncomeValue()throws KaChinnnngException {
        ArrayList<Income> incomesOnly = new ArrayList<>();
        incomesOnly.add(new Income("Salary", LocalDate.of(2023, 10, 10), 5000.0));
        Balance balance = new Balance(incomesOnly, new ArrayList<>());
        assertEquals(5000.0, balance.getBalance(),
                "Balance should equal sum of all incomes when no expenses are present.");
    }

    /**
     * Test the {@link Balance#getBalance()} method with no incomes.
     * This test case checks if the balance calculation correctly returns a negative value equal to expenses.
     */
    @Test
    public void testGetBalance_withNoIncomes_returnsNegativeExpenseValue()throws KaChinnnngException {
        ArrayList<Expense> expensesOnly = new ArrayList<>();
        expensesOnly.add(new Expense("Rent", LocalDate.of(2023, 10, 5), 2000.0));
        Balance balance = new Balance(new ArrayList<>(), expensesOnly);
        assertEquals(-2000.0, balance.getBalance(),
                "Balance should be negative sum of all expenses when no incomes are present.");
    }
}
