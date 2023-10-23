package seedu.financialplanner.commands;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.list.Budget;
import seedu.financialplanner.list.Cashflow;
import seedu.financialplanner.utils.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(OrderAnnotation.class)
public class BudgetCommandTest {
    @Test
    @Order(1)
    public void testSetBudget() throws FinancialPlannerException {
        Cashflow.setBalance(2000);
        Budget.deleteBudget();
        BudgetCommand testBudget = new BudgetCommand(Parser.parseRawCommand("budget set /b 1000"));
        testBudget.execute();
        assertEquals(1000, Budget.getInitialBudget());
        assertEquals(1000, Budget.getCurrentBudget());
        assertTrue(Budget.hasBudget());
    }

    @Test
    @Order(2)
    public void testUpdateBudget() throws FinancialPlannerException {
        BudgetCommand testBudget = new BudgetCommand(Parser.parseRawCommand("budget update /b 1500"));
        testBudget.execute();
        assertEquals(1500, Budget.getInitialBudget());
        assertEquals(1500, Budget.getCurrentBudget());
    }
}
