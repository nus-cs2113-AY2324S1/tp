package cashleh;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
        assertEquals(testStatement.getSumOfEntries(), 200);
        testStatement.addIncome(testIncome);
        assertEquals(testStatement.getSumOfEntries(), 400);
    }

    @Test
    void testToString() {
        testStatement.addIncome(testIncome);
        testStatement.addIncome(testIncome);
        System.out.println(testStatement);
//        assertEquals(testStatement.toString(), "\t200: pocket money (date: 2023-10-10, One time)\n\t"
//                + "+ S$ 200: pocket money (date: 2023-10-10, One time)");
    }
}
