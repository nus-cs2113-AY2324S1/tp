package cashleh;

import cashleh.parser.Parser;
import org.junit.jupiter.api.Test;

import cashleh.commands.AddExpense;
import cashleh.commands.AddIncome;
import cashleh.commands.DeleteExpense;
import cashleh.commands.DeleteIncome;
import cashleh.commands.Exit;
import cashleh.commands.ViewExpenses;
import cashleh.commands.ViewIncomes;
import cashleh.transaction.ExpenseStatement;
import cashleh.transaction.IncomeStatement;
import exceptions.CashLehException;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParserTest {
    Parser parser = new Parser(new ExpenseStatement(), new IncomeStatement());

    @Test
    public void parserUnknownCommandTest() {
        String inputString = "test";
        try {
            assertNull(parser.parse(inputString));
        } catch (CashLehException e) {
            assertEquals("Aiyoh! Your input blur like sotong... Clean your input for CashLeh!", e.getMessage());
        }
    }

    @Test
    public void parserAddIncomeTest() throws CashLehException {
        String inputString = "addIncome pocket money /amt 200";
        assertInstanceOf(AddIncome.class, parser.parse(inputString));
    }

    @Test
    public void parserDeleteIncomeTest() throws CashLehException {
        String inputString = "deleteIncome 1";
        assertInstanceOf(DeleteIncome.class, parser.parse(inputString));
    }

    @Test
    public void parserViewIncomesTest() throws CashLehException {
        String inputString = "viewIncomes";
        assertInstanceOf(ViewIncomes.class, parser.parse(inputString));
    }

    @Test
    public void parserAddExpenseTest() throws CashLehException {
        String inputString = "addExpense food /amt 10";
        assertInstanceOf(AddExpense.class, parser.parse(inputString));
    }

    @Test
    public void parserDeleteExpenseTest() throws CashLehException {
        String inputString = "deleteExpense 1";
        assertInstanceOf(DeleteExpense.class, parser.parse(inputString));
    }

    @Test
    public void parserViewExpensesTest() throws CashLehException {
        String inputString = "viewExpenses";
        assertInstanceOf(ViewExpenses.class, parser.parse(inputString));
    }

    @Test
    public void parserExitTest() throws CashLehException {
        String inputString = "exit";
        assertInstanceOf(Exit.class, parser.parse(inputString));
    }
}
