package seedu.financialplanner.commands;

import org.junit.jupiter.api.Test;
import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.list.CashflowList;
import seedu.financialplanner.utils.Parser;
import seedu.financialplanner.utils.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntryTest {
    private Ui ui = Ui.INSTANCE;
    private CashflowList cashflowList = CashflowList.INSTANCE;
    private WatchList watchList = WatchList.INSTANCE;

    @Test
    void testExecute() {
        CashflowList.INSTANCE.list.clear();

        EntryCommand testEntry = new EntryCommand(Parser.parseRawCommand("add income /a 300 /t work /r 30"));
        testEntry.execute();
        assertEquals(300, testEntry.amount);
        assertEquals("work", testEntry.type);
        assertEquals(30, testEntry.recur);

        testEntry = new EntryCommand(Parser.parseRawCommand("add expense /a 15 /t double mcspicy"));
        testEntry.execute();
        assertEquals(15, testEntry.amount);
        assertEquals("double mcspicy", testEntry.type);
        assertEquals(0, testEntry.recur);
    }
}
