package cashleh;

import cashleh.transaction.Income;
import cashleh.transaction.IncomeStatement;
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
}
