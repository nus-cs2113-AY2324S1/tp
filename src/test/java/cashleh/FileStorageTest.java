package cashleh;

import cashleh.exceptions.CashLehFileCorruptedException;
import cashleh.exceptions.CashLehMissingTransactionException;
import cashleh.exceptions.CashLehReadFromFileException;
import cashleh.exceptions.CashLehWriteToFileException;
import cashleh.transaction.Expense;
import cashleh.transaction.ExpenseStatement;
import cashleh.transaction.Income;
import cashleh.transaction.IncomeStatement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileStorageTest {
    private static final String TEST_USERNAME = "testUser";
    private FileStorage fileStorage;
    private IncomeStatement incomeStatement;
    private ExpenseStatement expenseStatement;

    @BeforeEach
    void setUp() {
        // Prepare a test instance of FileStorage
        fileStorage = new FileStorage(TEST_USERNAME);

        // Initialize IncomeStatement and ExpenseStatement
        incomeStatement = new IncomeStatement();
        expenseStatement = new ExpenseStatement();
    }

    @Test
    void testReadAndWriteToFile() throws CashLehMissingTransactionException, CashLehWriteToFileException,
            CashLehFileCorruptedException, CashLehReadFromFileException {
        // Read data from clean file
        fileStorage.writeToFile(incomeStatement, expenseStatement); // Clean the file
        fileStorage.readFromFile(incomeStatement, expenseStatement);

        assertEquals(0, incomeStatement.getSize());
        assertEquals(0, expenseStatement.getSize());

        // Create sample income and expense transactions
        Income income1 = new Income("Salary", 2000.0);
        Income income2 = new Income("Bonus", 500.0);
        Expense expense1 = new Expense("Groceries", 100.0);
        Expense expense2 = new Expense("Utilities", 150.0);

        // Add transactions to the statements
        incomeStatement.addIncome(income1);
        incomeStatement.addIncome(income2);
        expenseStatement.addExpense(expense1);
        expenseStatement.addExpense(expense2);

        // Write data to the file
        fileStorage.writeToFile(incomeStatement, expenseStatement);

        // Verify that the data matches what was written
        assertEquals(2, incomeStatement.getSize());
        assertEquals(2, expenseStatement.getSize());

        // Check the first income transaction
        assertEquals("Salary", incomeStatement.getIncome(0).getDescription());
        assertEquals(2000.0, incomeStatement.getIncome(0).getAmount());

        // Check the second income transaction
        assertEquals("Bonus", incomeStatement.getIncome(1).getDescription());
        assertEquals(500.0, incomeStatement.getIncome(1).getAmount());

        // Check the first expense transaction
        assertEquals("Groceries", expenseStatement.getExpense(0).getDescription());
        assertEquals(100.0, expenseStatement.getExpense(0).getAmount());

        // Check the second expense transaction
        assertEquals("Utilities", expenseStatement.getExpense(1).getDescription());
        assertEquals(150.0, expenseStatement.getExpense(1).getAmount());
    }

    @Test
    void testFileCorrupted() throws IOException {
        // Create a test file with corrupted data
        String corruptedData = "[I] Income: Salary, (Amount: 2000.0, Date: 202";
        Files.write(Path.of(fileStorage.getFilePath()), List.of(corruptedData));

        // Attempt to read from the corrupted file
        assertThrows(CashLehFileCorruptedException.class,
            () -> fileStorage.readFromFile(incomeStatement, expenseStatement));
    }
}
