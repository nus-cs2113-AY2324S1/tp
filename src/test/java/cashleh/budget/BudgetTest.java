package cashleh.budget;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class BudgetTest {
    Budget budget = new Budget(30);

    @Test
    void getBudget() {
        assertEquals(budget.getBudget(), 30);
    }

    @Test
    void isActive_budgetIsNotActive_returnTrue() {
        assertFalse(budget.isActive());
    }

    @Test
    void isActive_budgetIsActive_returnFalse() {
        budget.setActive(true);
        assertTrue(budget.isActive());
    }

    @Test
    void testToString() {
        assertEquals(budget.toString(), "You have set a budget of: 30.0");
    }
}
