package seedu.financialplanner.commands;

import org.junit.jupiter.api.Test;
import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.list.FinancialList;
import seedu.financialplanner.utils.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntryTest {
    private Ui ui = new Ui();
    private FinancialList financialList = new FinancialList();
    private WatchList watchList = new WatchList();

    @Test
    void testExecute() {
        Entry testEntry = new Entry("income a/300 t/work r/30");
        testEntry.execute(ui, financialList, watchList);
        assertEquals(300, testEntry.value);
        assertEquals("work", testEntry.type);
        assertEquals(30, testEntry.recur);

        testEntry = new Entry("expense a/15 t/double mcspicy");
        testEntry.execute(ui, financialList, watchList);
        assertEquals(15, testEntry.value);
        assertEquals("double mcspicy", testEntry.type);
        assertEquals(0, testEntry.recur);
        financialList.list.clear();
    }
}
