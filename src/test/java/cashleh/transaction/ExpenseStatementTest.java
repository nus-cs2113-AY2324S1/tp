package cashleh.transaction;

import cashleh.exceptions.CashLehMissingTransactionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseStatementTest {
    ExpenseStatement testStatement = new ExpenseStatement();
    Expense testExpense = new Expense("monthly rent", 1200);

    @Test
    void getExpense() throws CashLehMissingTransactionException {
        testStatement.addExpense(testExpense);
        assertEquals(testStatement.getExpense(0), testExpense);
    }

    @Test
    void getNumberOfExpenses() {
        assertEquals(testStatement.getNumberOfExpenses(), 0);
        testStatement.addExpense(testExpense);
        assertEquals(testStatement.getNumberOfExpenses(), 1);
        testStatement.addExpense(testExpense);
        assertEquals(testStatement.getNumberOfExpenses(), 2);
    }

    @Test
    void getSumOfExpenses() {
        testStatement.addExpense(testExpense);
        assertEquals(testStatement.getSumOfExpenses(), 1200);
        testStatement.addExpense(testExpense);
        assertEquals(testStatement.getSumOfExpenses(), 2400);
    }

    @Test
    void testToString() {
        testStatement.addExpense(testExpense);
        testStatement.addExpense(testExpense);
        System.out.println(testStatement);
        assertEquals(testStatement.toString(), "monthly rent (amount: 1200.0)"
                + "\nmonthly rent (amount: 1200.0)");
    }
}
