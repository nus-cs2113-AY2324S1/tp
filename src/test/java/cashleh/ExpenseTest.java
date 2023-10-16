package cashleh;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseTest {
    Expense testExpense = new Expense("milk tea", 2.50);

    @Test
    void testGetTotalExpense() {
        assertEquals(Expense.getTotalExpense(), 2.50);
    }

    @Test
    void testToString() {
        assertEquals(testExpense.toString(), "milk tea (amount: 2.50)");
    }
}

