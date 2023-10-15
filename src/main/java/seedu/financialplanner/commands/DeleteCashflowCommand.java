package seedu.financialplanner.commands;

import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.list.CashflowList;
import seedu.financialplanner.utils.Ui;

public class DeleteCashflowCommand extends Command{
    protected String cashflowType;
    protected int index;

    public DeleteCashflowCommand(String cashflowType, int index) {
        this.cashflowType = cashflowType;
        this.index = index;
    }

    public DeleteCashflowCommand(int index) {
        this.cashflowType = "empty";
        this.index = index;
    }

    @Override
    public void execute(Ui ui, CashflowList cashflowList, WatchList watchList) {
        if (cashflowType.contains("empty")) {
            cashflowList.delete(index);
        } else {
            cashflowList.deleteCashflow(cashflowType, index);
        }
    }
}
