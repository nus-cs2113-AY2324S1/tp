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
    void printTransactions() {
        financialStatement.printTransactions();
        assertEquals(outputStreamCaptor.toString(),
                "+-------------------------------------------------------------------------------"
                        + "----------------------------------------+\n"
                        + "|                                                  Financial Statement"
                        + "                                                  |\n"
                        + "+----------+--------------+--------------------+------------------------------+"
                        + "--------------------+--------------------+\n"
                        + "|    ID    |     Type     |        Date        |         Description          |"
                        + "      Category      |       Amount       |\n"
                        + "+----------+--------------+--------------------+------------------------------+"
                        + "--------------------+--------------------+\n"
                        + "|    1     |    Income    |     2023-10-31     |            salary            |"
                        + "       SALARY       |     + $1000.0      |\n"
                        + "|    2     |   Expense    |     2023-10-31     |            lunch             |"
                        + "     FOOD_DRINK     |      - $20.0       |\n"
                        + "+------------------------------------------------------------------------------"
                        + "-----------------------------------------+\n"
                        + "| Total Income: $1000.0                                                         "
                        + "                                        |\n"
                        + "| Total Expense: $20.0                                                          "
                        + "                                        |\n"
                        + "| Net Income: $980.0                                                            "
                        + "                                        |\n"
                        + "+------------------------------------------------------------------------------"
                        + "-----------------------------------------+\n");
    }

    /*@Test
    void findTransaction_transactionIsFound_printsTransaction() throws CashLehMissingTransactionException {
        financialStatement.findTransaction("salary", OptionalDouble.of(1000), LocalDate.now(), SALARY);
        assertEquals(outputStreamCaptor.toString(),
                "\t____________________________________________________________\n"
                        + "\tHere are your corresponding transactions with <description>: "
                        + "salary ||<amount>: 1000.0 ||<date>: 2023-10-31 ||<category>: SALARY ||\n"
                        + "\t[+] Income: salary (Amount: 1000.0, Date: 31/10/2023, Category: SALARY)\n"
                        + "\t____________________________________________________________\n");
    }*/

    @Test
    void findTransaction_transactionIsNotFound_throwsException() throws CashLehMissingTransactionException {
        assertThrows(CashLehMissingTransactionException.class,
                () -> financialStatement.findTransaction(
                        "black jack", OptionalDouble.of(5000), LocalDate.now(), LOTTERY_GAMBLING
                )) ;
    }

    /*@Test
    void testToString() {
        assertEquals(financialStatement.toString(),
                "Income: salary (Amount: 1000.0, Date: 31/10/2023, Category: SALARY)\n"
                        + "Expense: lunch (Amount: 20.0, Date: 31/10/2023, Category: FOOD_DRINK)");
    }*/
}
