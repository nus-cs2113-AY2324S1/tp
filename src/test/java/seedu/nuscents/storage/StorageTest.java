package seedu.nuscents.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.nuscents.parser.Parser.parseDate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.nuscents.data.transaction.Expense;
import seedu.nuscents.data.TransactionList;
import seedu.nuscents.data.transaction.ExpenseCategory;

public class StorageTest {
    @TempDir
    public static Path testFolder;

    private static final String TEST_DATA_FOLDER = "src/test/data/StorageFileTest";

    @Test
    public void save_validTransactionList() throws Exception {
        TransactionList transactions = getTestTransactionList();
        Storage storage = getTempStorage();
        storage.writeToFile(transactions);
        assertStorageFilesEqual(storage, getStorage("ValidData.txt"));
    }

    private void assertStorageFilesEqual(Storage sf1, Storage sf2) throws Exception {
        assertTextFilesEqual(Paths.get(sf1.getPath()), Paths.get(sf2.getPath()));
    }

    public static void assertTextFilesEqual(Path path1, Path path2) throws IOException {
        List<String> list1 = Files.readAllLines(path1, Charset.defaultCharset());
        List<String> list2 = Files.readAllLines(path2, Charset.defaultCharset());
        assertEquals(String.join("\n", list1), String.join("\n", list2));
    }

    private Storage getStorage(String fileName) throws Exception {
        return new Storage(TEST_DATA_FOLDER + "/" + fileName);
    }

    private Storage getTempStorage() throws Exception {
        return new Storage(testFolder.resolve("temp.txt").toString());
    }

    private TransactionList getTestTransactionList() throws Exception {
        TransactionList transactions = new TransactionList();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date formattedDate = parseDate("23-10-2023", formatter);
        transactions.addTransaction(new Expense(50, formattedDate, "Lunch", "Pasta",
                ExpenseCategory.FOOD));
        return transactions;
    }
}
