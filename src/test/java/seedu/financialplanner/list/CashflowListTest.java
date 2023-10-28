package seedu.financialplanner.list;

import org.junit.jupiter.api.Test;
import seedu.financialplanner.enumerations.CashflowCategory;
import seedu.financialplanner.enumerations.ExpenseType;
import seedu.financialplanner.enumerations.IncomeType;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CashflowListTest {
    private CashflowList testList = CashflowList.getInstance();
    private DecimalFormat decimalFormat = new DecimalFormat("####0.00");

    @Test
    void testAddIncomeAndExpense() {
        testList.list.clear();

        Cashflow.balance = 0;
        testList.addIncome(15, IncomeType.SALARY, 30, "part time job");
        Cashflow testIncome = testList.list.get(0);
        double roundedValue = Cashflow.round(testIncome.amount, 2);
        double roundedBalance = Cashflow.round(Cashflow.balance, 2);
        assertTrue(testIncome instanceof Income);
        assertEquals("15.00", decimalFormat.format(roundedValue));
        assertEquals(IncomeType.SALARY, testIncome.getIncomeType());
        assertEquals(30, testIncome.recur);
        assertEquals("15.00", decimalFormat.format(roundedBalance));
        assertEquals("part time job", testIncome.description);

        testList.addIncome(15.999, IncomeType.INVESTMENTS, 0, "AAPL");
        testIncome = testList.list.get(1);
        roundedValue = Cashflow.round(testIncome.amount, 2);
        roundedBalance = Cashflow.round(Cashflow.balance, 2);
        assertTrue(testIncome instanceof Income);
        assertEquals("16.00", decimalFormat.format(roundedValue));
        assertEquals(IncomeType.INVESTMENTS, testIncome.getIncomeType());
        assertEquals(0, testIncome.recur);
        assertEquals("31.00", decimalFormat.format(roundedBalance));
        assertEquals("AAPL", testIncome.description);

        testList.addExpense(10, ExpenseType.DINING, 0, "double mcspicy");
        Cashflow testExpense = testList.list.get(2);
        roundedValue = Cashflow.round(testExpense.amount, 2);
        roundedBalance = Cashflow.round(Cashflow.balance, 2);
        assertTrue(testExpense instanceof Expense);
        assertEquals("10.00", decimalFormat.format(roundedValue));
        assertEquals(ExpenseType.DINING, testExpense.getExpenseType());
        assertEquals(0, testExpense.recur);
        assertEquals("21.00", decimalFormat.format(roundedBalance));
        assertEquals("double mcspicy", testExpense.description);

        testList.addExpense(19.999, ExpenseType.ENTERTAINMENT, 30, "netflix");
        testExpense = testList.list.get(3);
        roundedValue = Cashflow.round(testExpense.amount, 2);
        roundedBalance = Cashflow.round(Cashflow.balance, 2);
        assertTrue(testExpense instanceof Expense);
        assertEquals("20.00", decimalFormat.format(roundedValue));
        assertEquals(ExpenseType.ENTERTAINMENT, testExpense.getExpenseType());
        assertEquals(30, testExpense.recur);
        assertEquals("1.00", decimalFormat.format(roundedBalance));
        assertEquals("netflix", testExpense.description);
    }

    @Test
    void testDeleteIncomeAndExpense() {
        testList.deleteCashflow(CashflowCategory.INCOME, 2);
        assertEquals(3, testList.list.size());
        double roundedBalance = Cashflow.round(Cashflow.balance, 2);
        assertEquals("-15.00", decimalFormat.format(roundedBalance));

        testList.delete(1);
        assertEquals(2, testList.list.size());
        roundedBalance = Cashflow.round(Cashflow.balance, 2);
        assertEquals("-30.00", decimalFormat.format(roundedBalance));

        testList.deleteCashflow(CashflowCategory.EXPENSE, 2);
        assertEquals(1, testList.list.size());
        roundedBalance = Cashflow.round(Cashflow.balance, 2);
        assertEquals("-10.00", decimalFormat.format(roundedBalance));

        testList.delete(1);
        assertEquals(0, testList.list.size());
        roundedBalance = Cashflow.round(Cashflow.balance, 2);
        assertEquals("0.00", decimalFormat.format(roundedBalance));
    }
}
