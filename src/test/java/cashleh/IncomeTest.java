package cashleh;

import cashleh.transaction.Income;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IncomeTest {
    Income testIncome = new Income("pocket money", 200);

    @Test
    void testToString() {
        assertEquals(testIncome.toString(), "pocket money (amount: 200.0)");
    }
}
