package seedu.financialplanner.commands;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.cashflow.Budget;
import seedu.financialplanner.cashflow.Cashflow;
import seedu.financialplanner.utils.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

    @Test
    @Order(3)
    public void testResetBudget() throws FinancialPlannerException {
        BudgetCommand testBudget = new BudgetCommand(Parser.parseRawCommand("budget reset"));
        AddCashflowCommand testExpense = new AddCashflowCommand(Parser.parseRawCommand("add expense /a 50 /t dining"));
        testExpense.execute();
        testBudget.execute();
        assertEquals(1500, Budget.getInitialBudget());
        assertEquals(1500, Budget.getCurrentBudget());
        Budget.deduct(50);
        Cashflow.setBalance(1000);
        BudgetCommand testBudgetExceedBalance = new BudgetCommand(Parser.parseRawCommand("budget reset"));
        testBudgetExceedBalance.execute();
        assertEquals(1000, Budget.getInitialBudget());
        assertEquals(1000, Budget.getCurrentBudget());
    }

    @Test
    @Order(4)
    public void testDeleteBudget() throws FinancialPlannerException {
        BudgetCommand testBudget = new BudgetCommand(Parser.parseRawCommand("budget delete"));
        testBudget.execute();
        assertFalse(Budget.hasBudget());
    }

    @Test
    @Order(5)
    public void testInvalidCommandFormat_throwsException() throws FinancialPlannerException {
        try {
            BudgetCommand testExtraArgument = new BudgetCommand(Parser.parseRawCommand("budget" +
                    " set /b 500 /t sdf"));
        } catch (IllegalArgumentException e) {
            assertEquals("Unknown extra argument: t", e.getMessage());
        }
        try {
            BudgetCommand testInvalidCommand = new BudgetCommand(Parser.parseRawCommand("budget random /b 5"));
        } catch (FinancialPlannerException e) {
            assertEquals("Budget command must be one of the following: set, update, " +
                    "delete, reset, view.", e.getMessage());
        }

        Budget.setBudget(5);
        try {
            BudgetCommand testSetAndHasBudget = new BudgetCommand(Parser.parseRawCommand("budget set /b 55"));
        } catch (FinancialPlannerException e) {
            assertEquals("There is an existing budget, did you mean update?", e.getMessage());
        }
        Budget.deleteBudget();

        try {
            BudgetCommand testUpdateAndNoBudget = new BudgetCommand(Parser.parseRawCommand("budget update " +
                    "/b 500"));
        } catch (FinancialPlannerException e) {
            assertEquals("There is no budget set yet, did you mean set?", e.getMessage());
        }

        try {
            BudgetCommand testMissingArgument = new BudgetCommand(Parser.parseRawCommand("budget set"));
        } catch (IllegalArgumentException e) {
            assertEquals("Missing /b argument.", e.getMessage());
        }
    }

    @Test
    @Order(6)
    public void testInvalidBudget_throwsException() throws FinancialPlannerException {
        try {
            BudgetCommand testStringBudget = new BudgetCommand(Parser.parseRawCommand("budget set /b f"));
        } catch (IllegalArgumentException e) {
            assertEquals("Budget must be a number.", e.getMessage());
        }
        try {
            BudgetCommand testNegativeBudget = new BudgetCommand(Parser.parseRawCommand("budget set /b -5"));
        } catch (FinancialPlannerException e) {
            assertEquals("Budget should be greater than 0.", e.getMessage());
        }
        Cashflow.clearBalance();
        try {
            BudgetCommand testBudgetExceedBalance = new BudgetCommand(Parser.parseRawCommand("budget set /b 500"));
        } catch (FinancialPlannerException e) {
            assertEquals("Budget should be lower than total balance.", e.getMessage());
        }
    }
}
