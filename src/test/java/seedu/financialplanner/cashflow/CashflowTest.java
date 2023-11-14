package seedu.financialplanner.cashflow;

import org.junit.jupiter.api.Test;
import seedu.financialplanner.enumerations.ExpenseType;
import seedu.financialplanner.enumerations.IncomeType;
import seedu.financialplanner.exceptions.FinancialPlannerException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class CashflowTest {
    protected CashflowList cashflowList = CashflowList.getInstance();


    @Test
    void round() {
        double testValue = Cashflow.round(123.555, 2);
        assertEquals(123.56, testValue);
        testValue = Cashflow.round(123.554, 2);
        assertEquals(123.55, testValue);
    }

    @Test
    void getIncomeBalance() {
        cashflowList.list.clear();
        Cashflow.incomeBalance = 0.00;
        cashflowList.addIncome(123.12, IncomeType.SALARY, 0 , null);
        cashflowList.addIncome(321.21, IncomeType.SALARY, 0, null);
        assertEquals(444.33, Cashflow.round(Cashflow.getIncomeBalance(), 2));
    }

    @Test
    void getExpenseBalance() {
        cashflowList.list.clear();
        Cashflow.expenseBalance = 0.00;
        cashflowList.addExpense(123.12, ExpenseType.OTHERS, 0 ,null);
        cashflowList.addExpense(321.21, ExpenseType.OTHERS, 0, null);
        assertEquals(444.33, Cashflow.round(Cashflow.getExpenseBalance(), 2));
    }

    @Test
    void testFinancialPlannerException() {
        try {
            Cashflow testIncome = new Income(9999999999999.99, IncomeType.SALARY, 0, null);
            fail();
        } catch (FinancialPlannerException e) {
            assertEquals("Balance exceeded maximum value this program can hold." +
                    " Please add a different income.", e.getMessage());
        }
        try {
            Cashflow testExpense = new Expense(9999999999999.99, ExpenseType.OTHERS, 0, null);
            fail();
        } catch (FinancialPlannerException e) {
            assertEquals("Balance exceeded minimum value this program can hold." +
                    " Please add a different expense.", e.getMessage());
        }
    }
}
