package cashleh.transaction;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IncomeTest {
    Income testIncome = new Income("pocket money", 200);
    Income testIncome2 = new Income("salary", 8000, LocalDate.of(2023, 9, 20));

    @Test
    void testToStringWithoutDate() {
        String expectedString = "pocket money (amount: 200.0, date: " + LocalDate.now() + ")";
        assertEquals(testIncome.toString(), expectedString);
    }

    @Test
    void testToStringWithDate() {
        String expectedString = "salary (amount: 8000.0, date: 2023-09-20)";
        assertEquals(testIncome2.toString(), expectedString);
    }
}
