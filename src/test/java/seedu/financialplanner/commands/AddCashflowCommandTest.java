package seedu.financialplanner.commands;

import org.junit.jupiter.api.Test;
import seedu.financialplanner.enumerations.ExpenseType;
import seedu.financialplanner.enumerations.IncomeType;
import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.list.CashflowList;
import seedu.financialplanner.utils.Parser;
import seedu.financialplanner.utils.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddCashflowCommandTest {
    private Ui ui = Ui.getInstance();
    private CashflowList cashflowList = CashflowList.getInstance();
    private WatchList watchList = WatchList.INSTANCE;

    @Test
    void testExecute() {
        cashflowList.list.clear();

        AddCashflowCommand testEntry = new AddCashflowCommand(Parser
                .parseRawCommand("add income /a 300 /t salary /r 30"));
        testEntry.execute();
        assertEquals(300, testEntry.amount);
        assertEquals(IncomeType.SALARY, testEntry.incomeType);
        assertEquals(30, testEntry.recur);

        testEntry = new AddCashflowCommand(Parser.parseRawCommand("add expense /a 15 /t dining"));
        testEntry.execute();
        assertEquals(15, testEntry.amount);
        assertEquals(ExpenseType.DINING, testEntry.expenseType);
        assertEquals(0, testEntry.recur);
    }
}
