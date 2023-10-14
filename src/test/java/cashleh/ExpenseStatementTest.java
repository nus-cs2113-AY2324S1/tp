package cashleh;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseStatementTest {
    ExpenseStatement testExpenseStatement = new ExpenseStatement();
    Expense testExpense = new Expense("milk tea", 2.50);
    Expense testExpense2 = new Expense("CS2113 textbook", 10);

    @Test
    void getNumberOfEntries() {
        assertEquals(testExpenseStatement.getNumberOfExpenses(), 0);
        testExpenseStatement.addExpense(testExpense);
        assertEquals(testExpenseStatement.getNumberOfExpenses(), 1);
        testExpenseStatement.addExpense(testExpense2);
        assertEquals(testExpenseStatement.getNumberOfExpenses(), 2);
    }

    @Test
    void testToString() {
        testExpenseStatement.addExpense(testExpense);
        System.out.println(testExpenseStatement);
        assertEquals(testExpenseStatement.toString(), "\t+ milk tea (amount: 2.50)");
        testExpenseStatement.addExpense(testExpense2);
        System.out.println(testExpenseStatement);
        assertEquals(testExpenseStatement.toString(), "\t+ milk tea (amount: 2.50)\n\t"
                + "CS2113 textbook (amount: 10)");
    }

}

