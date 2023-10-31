package seedu.financialplanner.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import seedu.financialplanner.enumerations.ExpenseType;
import seedu.financialplanner.enumerations.IncomeType;
import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.cashflow.Expense;
import seedu.financialplanner.cashflow.CashflowList;
import seedu.financialplanner.cashflow.Income;
import seedu.financialplanner.utils.Ui;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {
    @TempDir
    public static Path testFolder;
    protected CashflowList cashflowList = CashflowList.getInstance();
    protected Ui ui = Ui.getInstance();
    protected Storage storage = Storage.getInstance();

    @Test
    public void loadValidData() throws FinancialPlannerException {
        cashflowList.list.clear();
        storage.load("src/test/testData/ValidData.txt", LocalDate.now());
        String actual = cashflowList.getList();
        cashflowList.list.clear();
        getTestData();
        String expected = cashflowList.getList();
        assertEquals(expected, actual);
    }

    @Test
    public void loadInvalidData_userInputNo() {
        cashflowList.list.clear();
        ByteArrayInputStream in = new ByteArrayInputStream("n".getBytes());
        ui.setScanner(new Scanner(in));
        assertThrows(FinancialPlannerException.class,
                () -> storage.load("src/test/testData/InvalidData.txt", LocalDate.now()));
    }

    @Test
    public void saveValidData() throws FinancialPlannerException, IOException {
        cashflowList.list.clear();
        getTestData();
        storage.save(String.valueOf(testFolder.resolve("temp.txt")));
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        String filePath = String.valueOf(testFolder.resolve("ValidDataCopy.txt"));
        getTestValidData(filePath, date);

        assertEquals(Files.readAllLines(Path.of(filePath)),
                Files.readAllLines(testFolder.resolve("temp.txt")));
    }

    @Test
    public void saveNonExistentFile() {
        getTestData();
        assertThrows(FinancialPlannerException.class, () -> storage.save(""));
    }

    private void getTestData() {
        cashflowList.load(new Income(123.12, IncomeType.ALLOWANCE, 0, null));
        cashflowList.load(new Expense(100, ExpenseType.SHOPPING, 0, "shopee"));
    }

    private static void getTestValidData(String filePath, String date) throws IOException {
        try {
            Files.copy(Paths.get("src/test/testData/ValidData.txt"), Path.of(filePath));
            FileWriter fw = new FileWriter(filePath, true);
            fw.append(" ").append(date);
            fw.close();
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}
