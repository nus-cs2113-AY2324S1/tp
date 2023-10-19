package cashleh.transaction;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseTest {
    Expense testExpense = new Expense("monthly rent", 1200);
    Expense testExpense2 = new Expense("utility bill", 10, LocalDate.of(2023, 8, 15));

    @Test
    void testToStringWithoutDate() {
        String expectedString = "monthly rent (amount: 1200.0, date: " + LocalDate.now() + ")";
        assertEquals(testExpense.toString(), expectedString);
    }

    @Test
    void testToStringWithDate() {
        String expectedString = "utility bill (amount: 10.0, date: 2023-08-15)";
        assertEquals(testExpense2.toString(), expectedString);
    }
}
