package cashleh;

import cashleh.Income;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class IncomeTest {
    Income testIncome = new Income(200, "pocket money",
            LocalDate.of(2023, 10, 10));

    @Test
    void isOneTimeIncome() {
        assertEquals(testIncome.isOneTimeIncome(), "One time");
        testIncome.setOneTimeIncome(false);
        assertEquals(testIncome.isOneTimeIncome(), "Recurring");
    }

    @Test
    void testToString() {
        assertEquals(testIncome.toString(), "	+ S$ 200: pocket money (date: 2023-10-10, One time)");
    }
}