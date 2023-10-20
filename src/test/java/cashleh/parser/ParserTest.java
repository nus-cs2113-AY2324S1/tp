package cashleh.parser;

import cashleh.budget.Budget;
import cashleh.budget.BudgetHandler;
import cashleh.exceptions.CashLehParsingException;
import cashleh.transaction.FinancialStatement;
import cashleh.transaction.ExpenseStatement;
import cashleh.transaction.IncomeStatement;
import org.junit.jupiter.api.Test;

import cashleh.commands.AddExpense;
import cashleh.commands.AddIncome;
import cashleh.commands.DeleteExpense;
import cashleh.commands.DeleteIncome;
import cashleh.commands.Exit;
import cashleh.commands.ViewExpenses;
import cashleh.commands.ViewIncomes;
import cashleh.exceptions.CashLehException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ParserTest {
    ExpenseStatement expenseStatement = new ExpenseStatement();
    IncomeStatement incomeStatement = new IncomeStatement();
    BudgetHandler budgetHandler = new BudgetHandler(new FinancialStatement(incomeStatement, expenseStatement)
            , new Budget(10));
    Parser parser = new Parser(expenseStatement, incomeStatement, budgetHandler);

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
