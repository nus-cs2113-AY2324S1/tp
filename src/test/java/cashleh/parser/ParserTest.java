package cashleh.parser;

import exceptions.CashLehParsingException;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ParserTest {
    Parser parser = new Parser(new ExpenseStatement(), new IncomeStatement());

    @Test
    public void parserUnknownCommandTest() {
        String inputString = "test";
        assertThrows(CashLehParsingException.class, () -> parser.parse(inputString));
    }

    @Test
    public void parserAddIncomeTest() throws CashLehException {
        String inputString = "addIncome pocket money /amt 200 /date 01/01/2020";
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
        String inputString = "addExpense food /amt 10 /date 01/01/2020";
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
