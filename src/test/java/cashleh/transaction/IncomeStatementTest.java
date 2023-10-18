package cashleh.transaction;


import cashleh.exceptions.CashLehMissingTransactionException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IncomeStatementTest {
    IncomeStatement testStatement = new IncomeStatement();
    Income testIncome = new Income("pocket money", 200);
    Income testIncome2 = new Income("salary", 8000, LocalDate.of(2023, 10, 1));

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
        String expectedString = "pocket money (amount: 200.0, date: " + LocalDate.now() + ")\n"
                + "pocket money (amount: 200.0, date: " + LocalDate.now() + ")";
        assertEquals(testStatement.toString(), expectedString);
        testStatement.addIncome(testIncome2);
        System.out.println(testStatement);
        String expectedString2 = "pocket money (amount: 200.0, date: " + LocalDate.now() + ")\n" +
                "pocket money (amount: 200.0, date: " + LocalDate.now() + ")\n" +
                "salary (amount: 8000.0, date: 2023-10-01)";
        assertEquals(testStatement.toString(), expectedString2);
    }
}
