package seedu.financialplanner.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.list.Expense;
import seedu.financialplanner.list.FinancialList;
import seedu.financialplanner.list.Income;
import seedu.financialplanner.utils.Ui;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {
    @TempDir
    public static Path testFolder;

    @Test
    public void loadValidData() throws FinancialPlannerException {
        Storage storage = new Storage();
        FinancialList test = new FinancialList();
        storage.load(test, new Ui(), "src/test/data/ValidData.txt");
        FinancialList expected = getTestData();
        assertEquals(expected.getList(), test.getList());
    }

    @Test
    public void loadInvalidData_userInputNo() {
        Storage storage = new Storage();
        FinancialList test = new FinancialList();
        ByteArrayInputStream in = new ByteArrayInputStream("n".getBytes());
        System.setIn(in);
        assertThrows(FinancialPlannerException.class,
                () -> storage.load(test, new Ui(), "src/test/data/InvalidData.txt"));
    }

    @Test
    public void saveValidData() throws FinancialPlannerException, IOException {
        FinancialList expected = getTestData();
        Storage storage = new Storage();
        storage.save(expected, String.valueOf(testFolder.resolve("temp.txt")));
        assertEquals(Files.readAllLines(Path.of("src/test/data/ValidData.txt")),
                Files.readAllLines(testFolder.resolve("temp.txt")));
    }

    @Test
    public void saveNonExistentFile() {
        FinancialList expected = getTestData();
        Storage storage = new Storage();
        assertThrows(FinancialPlannerException.class, () -> storage.save(expected, ""));
    }

    private FinancialList getTestData() {
        FinancialList list = new FinancialList();
        list.load(new Income(123.12, "allowance", 0));
        list.load(new Expense(100, "daily necessities", 30));
        return list;
    }
}
