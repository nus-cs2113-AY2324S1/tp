package cashleh.transaction;

import cashleh.exceptions.CashLehMissingTransactionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.OptionalDouble;

import static cashleh.transaction.ExpenseCategories.ExpenseCategory.FOOD_DRINK;
import static cashleh.transaction.IncomeCategories.IncomeCategory.LOTTERY_GAMBLING;
import static cashleh.transaction.IncomeCategories.IncomeCategory.SALARY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FinancialStatementTest {
    IncomeStatement incomeStatement;
    ExpenseStatement expenseStatement;
    FinancialStatement financialStatement;
    Income testIncome;
    Expense testExpense;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        // Initialize IncomeStatement, ExpenseStatement and FinancialStatement
        incomeStatement = new IncomeStatement();
        expenseStatement = new ExpenseStatement();
        testIncome = new Income("salary", 1000, LocalDate.now(), SALARY);
        testExpense = new Expense("lunch", 20, LocalDate.now(), FOOD_DRINK);
        incomeStatement.addIncome(testIncome);
        expenseStatement.addExpense(testExpense);
        financialStatement = new FinancialStatement(incomeStatement, expenseStatement);
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void getSize() {
        assertEquals(financialStatement.getSize(), 2);
    }

    @Test
    void getNetCash() {
        assertEquals(financialStatement.getNetCash(), 980);
    }

    @Test
    void findTransaction_transactionIsNotFound_throwsException() {
        assertThrows(CashLehMissingTransactionException.class,
                () -> financialStatement.findTransaction(
                        "black jack", OptionalDouble.of(5000), LocalDate.now(), LOTTERY_GAMBLING
                )) ;
    }
}
