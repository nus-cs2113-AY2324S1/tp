package seedu.financialplanner.cashflow;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import seedu.financialplanner.commands.AddCashflowCommand;
import seedu.financialplanner.utils.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestMethodOrder(OrderAnnotation.class)
public class BudgetTest {
    @Test
    @Order(1)
    public void testSetBudget() {
        Budget.deleteBudget();
        assertFalse(Budget.hasBudget());
        Budget.setBudget(500);
        assertTrue(Budget.hasBudget());
        assertEquals(500, Budget.getInitialBudget());
        assertEquals(500, Budget.getCurrentBudget());
    }

    @Test
    @Order(2)
    public void testNewExpense() {
        AddCashflowCommand testExpense = new AddCashflowCommand(Parser.parseRawCommand("add expense /a 50 /t dining"));
        testExpense.execute();
        assertEquals(450, Budget.getCurrentBudget());
    }

    @Test
    @Order(3)
    public void testUpdateBudget() {
        Budget.updateBudget(300);
        assertEquals(300, Budget.getInitialBudget());
        assertEquals(250, Budget.getCurrentBudget());
        Budget.updateBudget(1000);
        assertEquals(1000, Budget.getInitialBudget());
        assertEquals(950, Budget.getCurrentBudget());
    }

    @Test
    @Order(4)
    public void testSetInitialBudget() {
        Budget.setInitialBudget(1500);
        assertEquals(1500, Budget.getInitialBudget());
    }

    @Test
    @Order(5)
    public void testUpdateCurrentBudget() {
        Budget.updateCurrentBudget(50);
        assertEquals(1000, Budget.getCurrentBudget());
    }

    @Test
    @Order(6)
    public void testResetBudget() {
        Budget.resetBudget();
        assertEquals(1500, Budget.getInitialBudget());
        assertEquals(1500, Budget.getCurrentBudget());
    }

    @Test
    @Order(7)
    public void testDeleteBudget() {
        Budget.deleteBudget();
        assertEquals(0, Budget.getInitialBudget());
        assertEquals(0, Budget.getCurrentBudget());
        assertFalse(Budget.hasBudget());
    }

    @Test
    @Order(8)
    public void testLoadBudget() {
        Budget.load(100, 100);
        assertEquals(100, Budget.getInitialBudget());
        assertEquals(100, Budget.getCurrentBudget());
        Budget.deleteBudget();
    }
}
