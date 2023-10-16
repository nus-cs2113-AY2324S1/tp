package seedu.financialplanner.list;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import seedu.financialplanner.commands.EntryCommand;
import seedu.financialplanner.utils.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestMethodOrder(OrderAnnotation.class)
public class BudgetTest {
    @Test
    @Order(1)
    public void testSetBudget() {
        assertFalse(Budget.hasBudget());
        Budget.setBudget(500);
        assertTrue(Budget.hasBudget());
        assertEquals(500, Budget.getInitialBudget());
        assertEquals(500, Budget.getCurrentBudget());
    }

    @Test
    @Order(2)
    public void testNewExpense() {
        EntryCommand testExpense = new EntryCommand(Parser.parseRawCommand("add expense /a 50 /t food"));
        testExpense.execute();
        assertEquals(450, Budget.getCurrentBudget());
    }

    @Test
    @Order(3)
    public void testUpdateBudget() {
        Budget.updateBudget(1000);
        assertEquals(1000, Budget.getInitialBudget());
        assertEquals(950, Budget.getCurrentBudget());
    }
}
