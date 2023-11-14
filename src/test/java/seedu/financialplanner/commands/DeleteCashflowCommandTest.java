package seedu.financialplanner.commands;

import org.junit.jupiter.api.Test;
import seedu.financialplanner.cashflow.Cashflow;
import seedu.financialplanner.cashflow.CashflowList;
import seedu.financialplanner.enumerations.ExpenseType;
import seedu.financialplanner.enumerations.IncomeType;
import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.utils.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

class DeleteCashflowCommandTest {
    protected CashflowList cashflowList = CashflowList.getInstance();

    @Test
    void testIllegalArgumentException() {
        try {
            DeleteCashflowCommand testEntry = new DeleteCashflowCommand(Parser.parseRawCommand("delete"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Incorrect arguments.", e.getMessage());
        }
        try {
            DeleteCashflowCommand testEntry = new DeleteCashflowCommand(Parser.parseRawCommand("delete a"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Index must be an integer and be within the " +
                    "maximum value this program can hold.", e.getMessage());
        }
        try {
            DeleteCashflowCommand testEntry = new DeleteCashflowCommand(Parser.parseRawCommand("delete 0"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Index must be within the list.", e.getMessage());
        }
        try {
            DeleteCashflowCommand testEntry = new DeleteCashflowCommand(Parser.parseRawCommand("delete 1 /r 3"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Arguments after /r should be left empty.", e.getMessage());
        }
        try {
            DeleteCashflowCommand testEntry = new DeleteCashflowCommand(Parser.parseRawCommand("delete 1 /a"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Unknown extra argument: a", e.getMessage());
        }
        try {
            DeleteCashflowCommand testEntry = new DeleteCashflowCommand(Parser.parseRawCommand("delete abc 1"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Entry must be either income, expense or recurring.", e.getMessage());
        }
    }

    @Test
    void testExecute() throws FinancialPlannerException {
        cashflowList.list.clear();

        try {
            DeleteCashflowCommand testEntry = new DeleteCashflowCommand(Parser.parseRawCommand("delete 2 /r"));
            testEntry.execute();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Index must be within the list.", e.getMessage());
        }
        try {
            DeleteCashflowCommand testEntry = new DeleteCashflowCommand(Parser.parseRawCommand("delete 2"));
            testEntry.execute();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Index must be within the list.", e.getMessage());
        }
        try {
            DeleteCashflowCommand testEntry = new DeleteCashflowCommand(Parser.parseRawCommand("delete income 2 /r"));
            testEntry.execute();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Index must be within the list.", e.getMessage());
        }
        try {
            DeleteCashflowCommand testEntry = new DeleteCashflowCommand(Parser.parseRawCommand("delete income 2"));
            testEntry.execute();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Index must be within the list.", e.getMessage());
        }

        cashflowList.addIncome(1, IncomeType.SALARY, 10, "work");
        cashflowList.addExpense(1, ExpenseType.ENTERTAINMENT, 10, "Apple Music");
        cashflowList.addIncome(1, IncomeType.SALARY, 10, "work");
        cashflowList.addExpense(1, ExpenseType.ENTERTAINMENT, 10, "Apple Music");

        DeleteCashflowCommand testEntry = new DeleteCashflowCommand(Parser.parseRawCommand("delete  recurring  3  /r"));
        testEntry.execute();
        Cashflow testIncome = cashflowList.list.get(2);
        assertEquals(0, testIncome.getRecur());
        assertNull(testIncome.getDate());

        testEntry = new DeleteCashflowCommand(Parser.parseRawCommand("delete  recurring  3"));
        testEntry.execute();
        assertEquals(3, cashflowList.list.size());

        testEntry = new DeleteCashflowCommand(Parser.parseRawCommand("delete  income  1  /r"));
        testEntry.execute();
        testIncome = cashflowList.list.get(0);
        assertEquals(0, testIncome.getRecur());
        assertNull(testIncome.getDate());

        testEntry = new DeleteCashflowCommand(Parser.parseRawCommand("delete  income  1"));
        testEntry.execute();
        assertEquals(2, cashflowList.list.size());

        testEntry = new DeleteCashflowCommand(Parser.parseRawCommand("delete  expense  1  /r"));
        testEntry.execute();
        Cashflow testExpense = cashflowList.list.get(1);
        assertEquals(0, testExpense.getRecur());
        assertNull(testExpense.getDate());

        testEntry = new DeleteCashflowCommand(Parser.parseRawCommand("delete  expense  1"));
        testEntry.execute();
        assertEquals(1, cashflowList.list.size());
    }
}
