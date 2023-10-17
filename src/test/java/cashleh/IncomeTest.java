package cashleh;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IncomeTest {
    Income testIncome = new Income(200, "pocket money",
            LocalDate.of(2023, 10, 10));

    @Test
    void isOneTimeIncome() {
        System.out.println(testIncome.getTotalIncome());
        assertEquals(testIncome.isOneTimeIncome(), "One time");
        testIncome.setOneTimeIncome(false);
        assertEquals(testIncome.isOneTimeIncome(), "Recurring");
        System.out.println(testIncome.getTotalIncome());
    }

    @Test
    void testToString() {
        System.out.println(testIncome.getTotalIncome());
        assertEquals(testIncome.toString(), "\t+ S$ 200.0: pocket money (date: 2023-10-10, One time)");
        System.out.println(testIncome.getTotalIncome());
    }

    @Test
    void getDate() {
        assertEquals(testIncome.getDate(), LocalDate.of(2023, 10, 10));
    }
}
