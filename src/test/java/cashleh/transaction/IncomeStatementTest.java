package cashleh.transaction;

import cashleh.exceptions.CashLehMissingTransactionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IncomeStatementTest {
    IncomeStatement testStatement = new IncomeStatement();
    Income testIncome = new Income("pocket money", 200);

    @Test
    void getIncome() throws CashLehMissingTransactionException {
        testStatement.addIncome(testIncome);
        assertEquals(testStatement.getIncome(0), testIncome);
    }

    @Test
    void getNumberOfIncomes() {
        assertEquals(testStatement.getNumberOfIncomes(), 0);
        testStatement.addIncome(testIncome);
        assertEquals(testStatement.getNumberOfIncomes(), 1);
        testStatement.addIncome(testIncome);
        assertEquals(testStatement.getNumberOfIncomes(), 2);
    }

    @Test
    void getSumOfIncomes() {
        testStatement.addIncome(testIncome);
        assertEquals(testStatement.getSumOfIncomes(), 200);
        testStatement.addIncome(testIncome);
        assertEquals(testStatement.getSumOfIncomes(), 400);
    }

    @Test
    void testToString() {
        testStatement.addIncome(testIncome);
        testStatement.addIncome(testIncome);
        System.out.println(testStatement);
        assertEquals(testStatement.toString(), "pocket money (amount: 200.0)"
                + "\npocket money (amount: 200.0)");
    }
}
