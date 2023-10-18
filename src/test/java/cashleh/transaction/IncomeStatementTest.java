package cashleh.transaction;


import cashleh.exceptions.CashLehMissingTransactionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IncomeStatementTest {
    IncomeStatement testStatement = new IncomeStatement();
    Income testIncome = new Income("pocket money", 200);

    @Test
    void getNumberOfEntries() {
        assertEquals(testStatement.getNumberOfEntries(), 0);
        testStatement.addIncome(testIncome);
        assertEquals(testStatement.getNumberOfEntries(), 1);
        testStatement.addIncome(testIncome);
        assertEquals(testStatement.getNumberOfEntries(), 2);
    }

    @Test
    void getSumOfEntries() {
        testStatement.addIncome(testIncome);
        assertEquals(testStatement.getTotalIncomeAmount(), 200);
        testStatement.addIncome(testIncome);
        assertEquals(testStatement.getTotalIncomeAmount(), 400);
    }

    @Test
    void getIncome() throws CashLehMissingTransactionException {
        testStatement.addIncome(testIncome);
        assertEquals(testStatement.getIncome(0), testIncome);
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
