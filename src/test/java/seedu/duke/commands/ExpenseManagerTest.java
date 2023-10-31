package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.financialrecords.Expense;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.assertNotNull;
public class ExpenseManagerTest {
    private ExpenseManager expenseManager;

    @BeforeEach
    public void setUp() throws KaChinnnngException {
        // Initialize the expense manager
        expenseManager = new ExpenseManager("/cat food /type lunch /de chicken rice " +
                "/date 01/01/2001 /amt 100");

    }

    @Test
    public void testConstructor_objectCreated() {
        assertNotNull(expenseManager);
    }

    @Test
    public void testExecute_getNewExpense() throws KaChinnnngException{
        expenseManager.execute();
        Expense expense = expenseManager.getNewExpense();

        assertNotNull(expense);
    }

    @Test
    public void testExecute_missingFields_exceptionThrown() {
        ExpenseManager expenseManagerMissingFields = new ExpenseManager("/type lunch /de chicken rice " +
                "/date 01/01/2001 /amt 100");

        assertThrows(KaChinnnngException.class, expenseManagerMissingFields::execute);
    }

    @Test
    public void testExecute_extraFields_exceptionThrown() {
        ExpenseManager expenseManagerExtraFields = new ExpenseManager("/cat food /type lunch " +
                "/de chicken rice /date 01/01/2001 /amt 100 /extra extra");

        assertThrows(KaChinnnngException.class, expenseManagerExtraFields::execute);
    }

    @Test
    public void testExecute_wrongOrder_exceptionThrown() {
        ExpenseManager expenseManagerWrongOrder = new ExpenseManager("/type lunch /cat food " +
                "/de chicken rice /date 01/01/2001 /amt 100");
        assertThrows(KaChinnnngException.class, expenseManagerWrongOrder::execute);
    }

    @Test
    public void testExecute_upperAndLowerCase_correctParsing() {
        ExpenseManager expenseManagerUpperLowerCase = new ExpenseManager("/cat fOoD /type lUNcH " +
                "/de chicken rice /date 01/01/2001 /amt 100");
        assertNotNull(expenseManagerUpperLowerCase);
    }

    @Test
    public void testExecute_invalidDate_exceptionThrown() {
        ExpenseManager expenseManagerInvalidDate = new ExpenseManager("/cat food /type lunch " +
                "/de chicken rice /date 13/13/2000 /amt 100");
        assertThrows(KaChinnnngException.class, expenseManagerInvalidDate::execute);
    }

    @Test
    public void testExecute_incorrectDateFormat_exceptionThrown() {
        ExpenseManager expenseManagerIncorrectDateFormat = new ExpenseManager("/cat food /type lunch" +
                "/de chicken rice /date 01-01-2003 /amt 100");
        assertThrows(KaChinnnngException.class, expenseManagerIncorrectDateFormat::execute);
    }

    @Test
    public void testExecute_incorrectAmountFormat_exceptionThrown() {
        ExpenseManager expenseManagerIncorrectAmountFormat = new ExpenseManager("/cat food /type lunch" +
                "/de chicken rice /date 01/01/2003 /amt one hundred");
        assertThrows(KaChinnnngException.class, expenseManagerIncorrectAmountFormat::execute);
    }
}
