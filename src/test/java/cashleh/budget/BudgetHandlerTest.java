package cashleh.budget;

import cashleh.exceptions.CashLehBudgetException;
import cashleh.exceptions.CashLehException;
import cashleh.transaction.Expense;
import cashleh.transaction.Income;
import cashleh.transaction.ExpenseStatement;
import cashleh.transaction.IncomeStatement;
import cashleh.transaction.FinancialStatement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BudgetHandlerTest {
    Budget budget = new Budget(30);
    IncomeStatement incomeStatement = new IncomeStatement();
    ExpenseStatement expenseStatement = new ExpenseStatement();
    FinancialStatement fs = new FinancialStatement(incomeStatement, expenseStatement);
    BudgetHandler budgetHandler = new BudgetHandler(fs, budget);

    @Test
    void getBudget_budgetIsValid() throws CashLehBudgetException {
        assertEquals(budgetHandler.getBudget(), budget);
        budget = new Budget(2);
        budgetHandler.setBudget(budget);
        assertEquals(budgetHandler.getBudget(), budget);
    }

    @Test
    void printBasicWarning_budgetIsNotOnTrack_throwsException() throws CashLehBudgetException {
        incomeStatement.addIncome(new Income("salary", 27));
        budgetHandler.setBudgetPercentage();
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
        assertThrows(CashLehException.class,
                () -> budgetHandler.printSeriousWarning());
        incomeStatement.addIncome(new Income("salary", 5));
        budgetHandler.setBudgetPercentage();
        assertThrows(CashLehException.class,
                () -> budgetHandler.printSeriousWarning());

    }
}
