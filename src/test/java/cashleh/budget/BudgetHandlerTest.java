package cashleh.budget;

import cashleh.exceptions.CashLehBudgetException;
import cashleh.exceptions.CashLehException;
import cashleh.transaction.Expense;
import cashleh.transaction.Income;
import cashleh.transaction.ExpenseStatement;
import cashleh.transaction.IncomeStatement;
import cashleh.transaction.FinancialStatement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;

class BudgetHandlerTest {
    Budget budget;
    IncomeStatement incomeStatement;
    ExpenseStatement expenseStatement;
    FinancialStatement financialStatement;
    BudgetHandler budgetHandler;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @BeforeEach
    void setUp() {
        // Initialize IncomeStatement, ExpenseStatement and FinancialStatement
        incomeStatement = new IncomeStatement();
        expenseStatement = new ExpenseStatement();
        financialStatement = new FinancialStatement(incomeStatement, expenseStatement);

        // Initialize a new Budget and BudgetHandler
        budget =  new Budget(30);
        budgetHandler = new BudgetHandler(financialStatement, budget);
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void setBudget_budgetIsValid() throws CashLehBudgetException {
        budget = new Budget(10);
        budgetHandler.setBudget(budget);
        assertEquals(budgetHandler.getBudget(), budget);
    }

    @Test
    void setBudget_budgetIsNotValid() throws CashLehBudgetException {
        budget = new Budget(0);
        assertThrows(CashLehException.class,
                () -> budgetHandler.setBudget(budget));
    }

    @Test
    void getBudget_budgetIsValid() throws CashLehBudgetException {
        assertEquals(budgetHandler.getBudget(), budget);
        budget = new Budget(2);
        budgetHandler.setBudget(budget);
        assertEquals(budgetHandler.getBudget(), budget);
    }

    @Test
    void deleteBudget() {
        budgetHandler.deleteBudget();
        assertFalse(budget.isActive());
    }

    @Test
    void printBasicWarning_budgetIsNotOnTrack_throwsException() throws CashLehBudgetException {
        incomeStatement.addIncome(new Income("salary", 3));
        budgetHandler.setBudgetPercentage();
        budget.setActive(true);
        assertThrows(CashLehException.class,
                () -> budgetHandler.printBasicWarning());
        expenseStatement.addExpense(new Expense("rent", 1));
        budgetHandler.setBudgetPercentage();
        assertThrows(CashLehException.class,
                () -> budgetHandler.printBasicWarning());
    }

    @Test
    void printSeriousWarning_budgetHasBeenMaxedOut_throwsException() throws CashLehBudgetException {
        expenseStatement.addExpense(new Expense("rent", 10));
        budgetHandler.setBudgetPercentage();
        budget.setActive(true);
        assertThrows(CashLehException.class,
                () -> budgetHandler.printSeriousWarning());
        incomeStatement.addIncome(new Income("salary", 5));
        budgetHandler.setBudgetPercentage();
        assertThrows(CashLehException.class,
                () -> budgetHandler.printSeriousWarning());

    }

    /*@Test
    void printBudget_budgetIsActiveAndOnTrack() throws CashLehBudgetException {
        incomeStatement.addIncome(new Income("salary", 50));
        budgetHandler.setBudgetPercentage();
        budget.setActive(true);
        budgetHandler.printBudget();
        String expectedOutput = "\t____________________________________________________________\n"
                + "\tYou have set a budget of: 30.0\n"
                + "\tHere's a quick view of how you're doing so far:\n"
                + "\tYou have a net cash on hand of: 50.0\n"
                + "\tYou still have the following percent of your budget left:\n"
                + "\n"
                + "\t[******************************] 100.00%\n"
                + "\t____________________________________________________________\n";
        assertEquals(outputStreamCaptor.toString(), expectedOutput);
    }*/

    @Test
    void printBudget_budgetIsNotActive_throwsException() throws CashLehBudgetException {
        incomeStatement.addIncome(new Income("salary", 50));
        budgetHandler.setBudgetPercentage();
        assertThrows(CashLehBudgetException.class,
                () -> budgetHandler.printBudget(),
                "Please create a new budget as you haven't set one yet or deleted the previous one.");
    }
}
