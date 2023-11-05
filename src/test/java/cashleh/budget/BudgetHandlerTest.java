package cashleh.budget;

import cashleh.exceptions.CashLehBudgetException;
import cashleh.exceptions.CashLehException;
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
    void printBudget_budgetIsNotActive_throwsException() throws CashLehBudgetException {
        incomeStatement.addIncome(new Income("salary", 50));
        budgetHandler.setBudgetPercentage();
        assertThrows(CashLehBudgetException.class,
                () -> budgetHandler.printBudget(),
                "Please create a new budget as you haven't set one yet or deleted the previous one.");
    }
}
