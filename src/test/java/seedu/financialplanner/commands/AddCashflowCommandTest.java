package seedu.financialplanner.commands;

import org.junit.jupiter.api.Test;
import seedu.financialplanner.cashflow.Cashflow;
import seedu.financialplanner.enumerations.ExpenseType;
import seedu.financialplanner.enumerations.IncomeType;
import seedu.financialplanner.cashflow.CashflowList;
import seedu.financialplanner.utils.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

class AddCashflowCommandTest {
    protected CashflowList cashflowList = CashflowList.getInstance();
    @Test
    void testIllegalArgumentException() {
        try {
            AddCashflowCommand testEntry = new AddCashflowCommand(Parser.parseRawCommand("add abc"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Entry must be either income or expense.", e.getMessage());
        }
        try {
            AddCashflowCommand testEntry = new AddCashflowCommand(Parser.parseRawCommand("add  income"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Entry must have an amount.", e.getMessage());
        }
        try {
            AddCashflowCommand testEntry = new AddCashflowCommand(Parser.parseRawCommand("add income  /a  abc"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Amount must be a number.", e.getMessage());
        }
        try {
            AddCashflowCommand testEntry = new AddCashflowCommand(Parser.parseRawCommand("add income /a -1"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Amount cannot be negative.", e.getMessage());
        }
        try {
            AddCashflowCommand testEntry = new AddCashflowCommand(Parser
                    .parseRawCommand("add income /a 9999999999999"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Amount exceeded maximum value this program can hold. " +
                    "Please add a different cashflow.", e.getMessage());
        }
        try {
            AddCashflowCommand testEntry = new AddCashflowCommand(Parser.parseRawCommand("add income /a 1"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Entry must have a type.", e.getMessage());
        }
        try {
            AddCashflowCommand testEntry = new AddCashflowCommand(Parser.parseRawCommand("add expense /a 1 /t  abc"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Entry must be one of the following: " +
                    "dining, entertainment, shopping, travel, insurance, necessities, others", e.getMessage());
        }
        try {
            AddCashflowCommand testEntry = new AddCashflowCommand(Parser.parseRawCommand("add income /a 1 /t  abc"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Entry must be one of the following: " +
                    "salary, investments, allowance, others", e.getMessage());
        }
        try {
            AddCashflowCommand testEntry = new AddCashflowCommand(Parser.
                    parseRawCommand("add income /a 1 /t salary /r "));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Recurrence must be an integer and be within the " +
                    "maximum value this program can hold.", e.getMessage());
        }
        try {
            AddCashflowCommand testEntry = new AddCashflowCommand(Parser.
                    parseRawCommand("add income /a 1 /t salary /r  -1"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Recurring value cannot be negative.", e.getMessage());
        }
        try {
            AddCashflowCommand testEntry = new AddCashflowCommand(Parser.
                    parseRawCommand("add income /a 1 /t salary /r 1 /d "));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Description cannot be left empty.", e.getMessage());
        }
        try {
            AddCashflowCommand testEntry = new AddCashflowCommand(Parser.
                    parseRawCommand("add income /a 1 /t salary /r 1 /d 1  /x"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Unknown extra argument: x", e.getMessage());
        }
    }
    @Test
    void testExecute() {
        cashflowList.list.clear();

        AddCashflowCommand testEntry = new AddCashflowCommand(Parser
                .parseRawCommand("add income /a 300 /t salary"));
        testEntry.execute();
        assertEquals(1,cashflowList.list.size());
        Cashflow testIncome = cashflowList.list.get(0);
        assertEquals(300, testIncome.getAmount());
        assertEquals(IncomeType.SALARY, testIncome.getIncomeType());
        assertEquals(0, testIncome.getRecur());
        assertNull(testIncome.getDescription());

        testEntry = new AddCashflowCommand(Parser
                .parseRawCommand("add income /a  300 /t  salary /r  30 /d  abc"));
        testEntry.execute();
        assertEquals(2,cashflowList.list.size());
        testIncome = cashflowList.list.get(1);
        assertEquals(300, testIncome.getAmount());
        assertEquals(IncomeType.SALARY, testIncome.getIncomeType());
        assertEquals(30, testIncome.getRecur());
        assertEquals("abc", testIncome.getDescription());

        testEntry = new AddCashflowCommand(Parser.parseRawCommand("add expense /a 15 /t dining"));
        testEntry.execute();
        assertEquals(3,cashflowList.list.size());
        Cashflow testExpense = cashflowList.list.get(2);
        assertEquals(15, testExpense.getAmount());
        assertEquals(ExpenseType.DINING, testExpense.getExpenseType());
        assertEquals(0, testExpense.getRecur());
        assertNull(testExpense.getDescription());

        testEntry = new AddCashflowCommand(Parser.parseRawCommand("add expense /a  15 /t  dining /r  30 /d  abc"));
        testEntry.execute();
        assertEquals(4,cashflowList.list.size());
        testExpense = cashflowList.list.get(3);
        assertEquals(15, testExpense.getAmount());
        assertEquals(ExpenseType.DINING, testExpense.getExpenseType());
        assertEquals(30, testExpense.getRecur());
        assertEquals("abc", testExpense.getDescription());
    }
}
