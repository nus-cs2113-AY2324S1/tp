package seedu.duke.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * JUnit test class for Parser.
 * This class tests if the commands are parsed correctly.
 * It also tests if the correct exceptions are thrown when the command is invalid.
 */
public class ParserTest {

    @Test
    public void testParse_exitCommand_parsedCorrectly() {
        assertEquals("exit", Parser.parse("exit"));
    }

    @Test
    public void testParse_addExpenseCommand_parsedCorrectly() {
        assertEquals("add_expense",
                Parser.parse("add Expense /de lunch /amt 5.00 /cat food /date 12/Oct/2023"));
    }

    @Test
    public void testParse_addIncomeCommand_parsedCorrectly() {
        assertEquals("add_income",
                Parser.parse("add Income /de salary /amt 5000.00 /date 12/Oct/2023"));
    }

    @Test
    public void testParse_listExpenseCommand_parsedCorrectly() {
        assertEquals("list_expense", Parser.parse("list Expense"));
    }

    @Test
    public void testParse_listIncomeCommand_parsedCorrectly() {
        assertEquals("list_income", Parser.parse("list Income"));
    }

    @Test
    public void testParse_deleteExpenseCommand_parsedCorrectly() {
        assertEquals("delete_expense", Parser.parse("delete Expense /i 1"));
    }

    @Test
    public void testParse_deleteIncomeCommand_parsedCorrectly() {
        assertEquals("delete_income", Parser.parse("delete Income /i 1"));
    }

    @Test
    public void testParse_findCommand_parsedCorrectly() {
        assertEquals("find", Parser.parse("find /t expense /cat food /de lunch /date 12/Oct/2023"));
    }

    @Test
    public void testParse_helpCommand_parsedCorrectly() {
        assertEquals("help", Parser.parse("help"));
    }

    @Test
    public void testParse_invalidCommand_parsedCorrectly() {
        assertEquals("invalid", Parser.parse("invalid"));
    }

    @Test
    public void testParse_listCommand_parsedCorrectly() {
        assertEquals("list", Parser.parse("list"));
    }

    @Test
    public void testParse_balanceCommand_parsedCorrectly() {
        assertEquals("balance", Parser.parse("balance"));
    }

    @Test
    public void testParse_clearIncomesCommand_parsedCorrectly() {
        assertEquals("clear_incomes", Parser.parse("clear incomes"));
    }

    @Test
    public void testParse_clearExpensesCommand_parsedCorrectly() {
        assertEquals("clear_expenses", Parser.parse("clear expenses"));
    }

    @Test
    public void testParse_clearAllCommand_parsedCorrectly() {
        assertEquals("clear_all", Parser.parse("clear all"));
    }

    @Test
    public void testParse_editIncomeCommand_parsedCorrectly() {
        assertEquals("edit_income", Parser.parse("edit income /i 1 /de salary /amt 5000.00 /date 12/Oct/2023"));
    }

    @Test
    public void testParse_editExpenseCommand_parsedCorrectly() {
        assertEquals("edit_expense", Parser.parse("edit expense /i 1 /de lunch /amt 5.00 /cat food /date 12/Oct/2023"));
    }
}
