package cashleh.transaction;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseTest {
    Expense testExpense = new Expense("monthly rent", 1200);

    @Test
    void testToString() {
        assertEquals(testExpense.toString(), "monthly rent (amount: 1200.0)");
    }
}
