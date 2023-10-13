package seedu.financialplanner.list;

import org.junit.jupiter.api.Test;
import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FinancialListTest {
    private FinancialList testList = new FinancialList();
    private DecimalFormat decimalFormat = new DecimalFormat("####0.00");

    @Test
    void testAddIncomeAndExpense() {

        testList.addIncome(15, "work", 30);
        Cashflow testIncome = testList.list.get(0);
        double roundedValue = testList.round(testIncome.value, 2);
        double roundedBalance = testList.round(Cashflow.balance, 2);
        assertTrue(testIncome instanceof Income);
        assertEquals("15.00", decimalFormat.format(roundedValue));
        assertEquals("work", testIncome.type);
        assertEquals(30, testIncome.recur);
        assertEquals("15.00", decimalFormat.format(roundedBalance));

        testList.addIncome(15.999, "rate of returns", 0);
        testIncome = testList.list.get(1);
        roundedValue = testList.round(testIncome.value, 2);
        roundedBalance = testList.round(Cashflow.balance, 2);
        assertTrue(testIncome instanceof Income);
        assertEquals("16.00", decimalFormat.format(roundedValue));
        assertEquals("rate of returns", testIncome.type);
        assertEquals(0, testIncome.recur);
        assertEquals("31.00", decimalFormat.format(roundedBalance));

        testList.addExpense(10, "lunch", 0);
        Cashflow testExpense = testList.list.get(2);
        roundedValue = testList.round(testExpense.value, 2);
        roundedBalance = testList.round(Cashflow.balance, 2);
        assertTrue(testExpense instanceof Expense);
        assertEquals("10.00", decimalFormat.format(roundedValue));
        assertEquals("lunch", testExpense.type);
        assertEquals(0, testExpense.recur);
        assertEquals("21.00", decimalFormat.format(roundedBalance));

        testList.addExpense(19.999, "Apple Music", 30);
        testExpense = testList.list.get(3);
        roundedValue = testList.round(testExpense.value, 2);
        roundedBalance = testList.round(Cashflow.balance, 2);
        assertTrue(testExpense instanceof Expense);
        assertEquals("20.00", decimalFormat.format(roundedValue));
        assertEquals("Apple Music", testExpense.type);
        assertEquals(30, testExpense.recur);
        assertEquals("1.00", decimalFormat.format(roundedBalance));
    }
}
