package seedu.financialplanner.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.list.Expense;
import seedu.financialplanner.list.CashflowList;
import seedu.financialplanner.list.Income;
import seedu.financialplanner.utils.Ui;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {
    @TempDir
    public static Path testFolder;

    @Test
    public void loadValidData() throws FinancialPlannerException {
        Storage storage = Storage.INSTANCE;
        CashflowList cashflowList = CashflowList.INSTANCE;
        cashflowList.list.clear();
        storage.load(cashflowList, Ui.INSTANCE, "src/test/testData/ValidData.txt");
        String actual = cashflowList.getList();
        cashflowList.list.clear();
        getTestData();
        String expected = cashflowList.getList();
        assertEquals(expected, actual);
    }

    @Test
    public void loadInvalidData_userInputNo() {
        Storage storage = Storage.INSTANCE;
        CashflowList test = CashflowList.INSTANCE;
        test.list.clear();
        ByteArrayInputStream in = new ByteArrayInputStream("n".getBytes());
        Ui.INSTANCE.setScanner(new Scanner(in));
        assertThrows(FinancialPlannerException.class,
                () -> storage.load(test, Ui.INSTANCE, "src/test/testData/InvalidData.txt"));
    }

    @Test
    public void saveValidData() throws FinancialPlannerException, IOException {
        CashflowList.INSTANCE.list.clear();
        getTestData();
        Storage storage = Storage.INSTANCE;
        storage.save(CashflowList.INSTANCE, String.valueOf(testFolder.resolve("temp.txt")));
        assertEquals(Files.readAllLines(Path.of("src/test/testData/ValidData.txt")),
                Files.readAllLines(testFolder.resolve("temp.txt")));
    }

    @Test
    public void saveNonExistentFile() {
        getTestData();
        Storage storage = Storage.INSTANCE;
        assertThrows(FinancialPlannerException.class, () -> storage.save(CashflowList.INSTANCE, ""));
    }

    private void getTestData() {
        CashflowList cashflowList = CashflowList.INSTANCE;
        cashflowList.load(new Income(123.12, "allowance", 0));
        cashflowList.load(new Expense(100, "daily necessities", 30));
    }
}
