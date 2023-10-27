package cashleh.transaction;

import cashleh.exceptions.CashLehMissingTransactionException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseStatementTest {
    ExpenseStatement testExpenseStatement = new ExpenseStatement();
    Expense testExpense = new Expense("milk tea", 2.50);
    Expense testExpense2 = new Expense("CS2113 textbook", 10);
    Expense testExpense3 = new Expense("bus fare", 1.80, LocalDate.of(2023, 9, 20));

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    String getCurrentDateString() {
        return LocalDate.now().format(formatter);
    }

    @Test
    void testGetExpense() throws CashLehMissingTransactionException {
        testExpenseStatement.addExpense(testExpense);
        assertEquals(testExpenseStatement.getExpense(0), testExpense);
    }
    @Test
    void testGetNumberOfExpenses() {
        assertEquals(testExpenseStatement.getNumberOfExpenses(), 0);
        testExpenseStatement.addExpense(testExpense);
        assertEquals(testExpenseStatement.getNumberOfExpenses(), 1);
        testExpenseStatement.addExpense(testExpense2);
        assertEquals(testExpenseStatement.getNumberOfExpenses(), 2);
    }
    @Test
    void testGetTotalExpenseAmount() {
        testExpenseStatement.addExpense(testExpense);
        assertEquals(testExpenseStatement.getTotalExpenseAmount(), 2.5);
        testExpenseStatement.addExpense(testExpense2);
        assertEquals(testExpenseStatement.getTotalExpenseAmount(), 12.5);
    }
    @Test
    void testToString() {
        testExpenseStatement.addExpense(testExpense);
        System.out.println(testExpenseStatement);
        String expectedString = "Expense: milk tea (Amount: 2.5, Date: " + getCurrentDateString() + ")";
        assertEquals(testExpenseStatement.toString(), expectedString);
        testExpenseStatement.addExpense(testExpense2);
        System.out.println(testExpenseStatement);
        String expectedString2 = "Expense: milk tea (Amount: 2.5, Date: " + getCurrentDateString() + ")\n" +
                "Expense: CS2113 textbook (Amount: 10.0, Date: " + getCurrentDateString() + ")";
        assertEquals(testExpenseStatement.toString(), expectedString2);
        testExpenseStatement.addExpense(testExpense3);
        System.out.println(testExpenseStatement);
        String expectedString3 = "Expense: milk tea (Amount: 2.5, Date: " + getCurrentDateString() + ")\n" +
                "Expense: CS2113 textbook (Amount: 10.0, Date: " + getCurrentDateString() + ")\n" +
                "Expense: bus fare (Amount: 1.8, Date: 20/09/2023)";
        assertEquals(testExpenseStatement.toString(), expectedString3);
    }

}

