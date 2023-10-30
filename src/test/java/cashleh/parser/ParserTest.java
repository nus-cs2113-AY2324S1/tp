package cashleh.parser;

import cashleh.budget.Budget;
import cashleh.budget.BudgetHandler;
import cashleh.exceptions.CashLehParsingException;
import cashleh.transaction.FinancialStatement;
import cashleh.transaction.ExpenseStatement;
import cashleh.transaction.IncomeStatement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ParserTest {
    ExpenseStatement expenseStatement = new ExpenseStatement();
    IncomeStatement incomeStatement = new IncomeStatement();
    BudgetHandler budgetHandler = new BudgetHandler(
        new FinancialStatement(incomeStatement, expenseStatement), new Budget(10));
    Parser parser = new Parser(expenseStatement, incomeStatement, budgetHandler);

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void parserUnknownCommandTest() {
        String inputString1 = "test";
        Exception exception1 = assertThrows(CashLehParsingException.class, () -> parser.parse(inputString1));
        assertEquals("Aiyoh! Your input blur like sotong... Clean your input for CashLeh!",
                exception1.getMessage());

        String inputString2 = "test /test test";
        Exception exception2 = assertThrows(CashLehParsingException.class, () -> parser.parse(inputString2));
        assertEquals("Aiyoh! Your input blur like sotong... Clean your input for CashLeh!",
                exception2.getMessage());
    }

    @Test
    public void parserAddIncomeTest() throws CashLehException {
        String inputString1 = "addIncome pineapple";
        Exception exception1 = assertThrows(CashLehParsingException.class, () -> parser.parse(inputString1));
        assertEquals("Aiyoh! Your input blur like sotong... You never enter /amt leh!", exception1.getMessage());

        String inputString2 = "addIncome /amt 100";
        Exception exception2 = assertThrows(CashLehParsingException.class, () -> parser.parse(inputString2));
        assertEquals("Oopsie! An income without a description is like a CashLeh "
                + "transaction without its story - not as fun!", exception2.getMessage());
                
        String inputString3 = "addIncome pocket money /amt 200";
        assertInstanceOf(AddIncome.class, parser.parse(inputString3));
        parser.parse(inputString3).execute();
        assertEquals("\t____________________________________________________________\r\n"
        + "\tThe following income was added:\r\n"
        + "\tpocket money (amount: 200.0, date: " + LocalDate.now() + ")\r\n"
        + "\t____________________________________________________________\r\n", outputStreamCaptor.toString());

        String inputString4 = "addIncome pocket money /amt notnumber";
        Exception exception4 = assertThrows(CashLehParsingException.class, () -> parser.parse(inputString4));
        assertEquals("Please enter a valid income amount!", exception4.getMessage());

        String inputString5 = "addIncome pocket money /amt 200 /date notdate";
        Exception exception5 = assertThrows(CashLehParsingException.class, () -> parser.parse(inputString5));
        assertEquals("Date format is invalid leh! Use DD/MM/YYYY can or not?", exception5.getMessage());
    }

    @Test
    public void parserDeleteIncomeTest() throws CashLehException {
        String inputString1 = "deleteIncome 1";
        assertInstanceOf(DeleteIncome.class, parser.parse(inputString1));

        String inputString2 = "deleteIncome";
        Exception exception2 = assertThrows(CashLehParsingException.class, () -> parser.parse(inputString2));
        assertEquals("Which transaction kena the chop today? Make your choice!",
                exception2.getMessage());

        String inputString3 = "deleteIncome lol";
        Exception exception3 = assertThrows(CashLehParsingException.class, () -> parser.parse(inputString3));
        assertEquals("Eh, that's not the kind of number we flaunt in CashLeh!",
                exception3.getMessage());
    }

    @Test
    public void parserViewIncomesTest() throws CashLehException {
        String inputString = "viewIncomes";
        assertInstanceOf(ViewIncomes.class, parser.parse(inputString));
    }

    @Test
    public void parserAddExpenseTest() throws CashLehException {
        String inputString1 = "addExpense food";
        Exception exception1 = assertThrows(CashLehParsingException.class, () -> parser.parse(inputString1));
        assertEquals("Aiyoh! Your input blur like sotong... You never enter /amt leh!", exception1.getMessage());

        String inputString2 = "addExpense /amt 100";
        Exception exception2 = assertThrows(CashLehParsingException.class, () -> parser.parse(inputString2));
        assertEquals("Oopsie! An expense without a description is like a CashLeh "
                + "transaction without its story - not as fun!", exception2.getMessage());

        String inputString3 = "addExpense food /amt 10";
        assertInstanceOf(AddExpense.class, parser.parse(inputString3));
        parser.parse(inputString3).execute();
        assertEquals("\t____________________________________________________________\r\n"
        + "\tThe following expense was added:\r\n"
        + "\tfood (amount: 10.0, date: " + LocalDate.now() + ")\r\n"
        + "\t____________________________________________________________\r\n", outputStreamCaptor.toString());

        String inputString4 = "addExpense food /amt notnumber";
        Exception exception4 = assertThrows(CashLehParsingException.class, () -> parser.parse(inputString4));
        assertEquals("Please enter a valid expense amount!", exception4.getMessage());

        String inputString5 = "addExpense food /amt 10 /date notdate";
        Exception exception5 = assertThrows(CashLehParsingException.class, () -> parser.parse(inputString5));
        assertEquals("Date format is invalid leh! Use DD/MM/YYYY can or not?", exception5.getMessage());
    }

    @Test
    public void parserDeleteExpenseTest() throws CashLehException {
        String inputString1 = "deleteExpense 1";
        assertInstanceOf(DeleteExpense.class, parser.parse(inputString1));

        String inputString2 = "deleteExpense";
        Exception exception2 = assertThrows(CashLehParsingException.class, () -> parser.parse(inputString2));
        assertEquals("Which transaction kena the chop today? Make your choice!",
                exception2.getMessage());

        String inputString3 = "deleteExpense lol";
        Exception exception3 = assertThrows(CashLehParsingException.class, () -> parser.parse(inputString3));
        assertEquals("Eh, that's not the kind of number we flaunt in CashLeh!",
                exception3.getMessage());
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
