package seedu.financialplanner.storage;

import org.junit.jupiter.api.Test;
import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.list.Expense;
import seedu.financialplanner.list.FinancialList;
import seedu.financialplanner.list.Income;
import seedu.financialplanner.utils.Ui;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    void loadValidData() throws FinancialPlannerException {
        Storage storage = new Storage();
        FinancialList test = new FinancialList();
        storage.load(test, new Ui(), "src/test/data/ValidData.txt");
        FinancialList expected = getTestData();
        assertEquals(expected.getList(), test.getList());
    }

    private FinancialList getTestData() {
        FinancialList list = new FinancialList();
        list.load(new Income(123.12, "allowance", 0));
        list.load(new Expense(100, "daily necessities", 30));
        return list;
    }
}
