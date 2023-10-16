package seedu.financialplanner.list;

import org.junit.jupiter.api.Test;
import seedu.financialplanner.enumerations.CashflowCategory;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CashflowListTest {
    private CashflowList testList = CashflowList.INSTANCE;
    private DecimalFormat decimalFormat = new DecimalFormat("####0.00");

    @Test
    void testAddIncomeAndExpense() {
        CashflowList.INSTANCE.list.clear();

        Cashflow.balance = 0;
        testList.addIncome(15, "work", 30);
        Cashflow testIncome = testList.list.get(0);
        double roundedValue = Cashflow.round(testIncome.amount, 2);
        double roundedBalance = Cashflow.round(Cashflow.balance, 2);
        assertTrue(testIncome instanceof Income);
        assertEquals("15.00", decimalFormat.format(roundedValue));
        assertEquals("work", testIncome.type);
        assertEquals(30, testIncome.recur);
        assertEquals("15.00", decimalFormat.format(roundedBalance));

        testList.addIncome(15.999, "rate of returns", 0);
        testIncome = testList.list.get(1);
        roundedValue = Cashflow.round(testIncome.amount, 2);
        roundedBalance = Cashflow.round(Cashflow.balance, 2);
        assertTrue(testIncome instanceof Income);
        assertEquals("16.00", decimalFormat.format(roundedValue));
        assertEquals("rate of returns", testIncome.type);
        assertEquals(0, testIncome.recur);
        assertEquals("31.00", decimalFormat.format(roundedBalance));

        testList.addExpense(10, "lunch", 0);
        Cashflow testExpense = testList.list.get(2);
        roundedValue = Cashflow.round(testExpense.amount, 2);
        roundedBalance = Cashflow.round(Cashflow.balance, 2);
        assertTrue(testExpense instanceof Expense);
        assertEquals("10.00", decimalFormat.format(roundedValue));
        assertEquals("lunch", testExpense.type);
        assertEquals(0, testExpense.recur);
        assertEquals("21.00", decimalFormat.format(roundedBalance));

        testList.addExpense(19.999, "Apple Music", 30);
        testExpense = testList.list.get(3);
        roundedValue = Cashflow.round(testExpense.amount, 2);
        roundedBalance = Cashflow.round(Cashflow.balance, 2);
        assertTrue(testExpense instanceof Expense);
        assertEquals("20.00", decimalFormat.format(roundedValue));
        assertEquals("Apple Music", testExpense.type);
        assertEquals(30, testExpense.recur);
        assertEquals("1.00", decimalFormat.format(roundedBalance));
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
