package cashleh;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IncomeStatementTest {
    IncomeStatement testStatement = new IncomeStatement();
    Income testIncome = new Income(200, "pocket money",
            LocalDate.of(2023, 10, 10));
    @Test
    void getNumberOfEntries() {
        assertEquals(testStatement.getNumberOfEntries(), 0);
        testStatement.add(testIncome);
        assertEquals(testStatement.getNumberOfEntries(), 1);
        testStatement.add(testIncome);
        assertEquals(testStatement.getNumberOfEntries(), 2);
    }

    @Test
    void getSumOfEntries() {
        testStatement.add(testIncome);
        assertEquals(testStatement.getSumOfEntries(), 200);
        testStatement.add(testIncome);
        assertEquals(testStatement.getSumOfEntries(), 400);
    }

    @Test
    void testToString() {
        testStatement.add(testIncome);
        testStatement.add(testIncome);
        System.out.println(testStatement);
        assertEquals(testStatement.toString(), "\t+ S$ 200: pocket money (date: 2023-10-10, One time)\n\t"
                + "+ S$ 200: pocket money (date: 2023-10-10, One time)");
    }
}
