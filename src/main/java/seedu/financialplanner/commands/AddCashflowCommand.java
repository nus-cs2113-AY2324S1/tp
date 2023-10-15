package seedu.financialplanner.commands;

import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.list.CashflowList;
import seedu.financialplanner.utils.Ui;

public class AddCashflowCommand extends Command{
    private static final String INCOME = "income";
    private static final String EXPENSE = "expense";
    protected String cashflowType;
    protected double amount;
    protected String type;
    protected int recur;

    public AddCashflowCommand(String cashflowType, double amount, String type, int recur) {
        this.cashflowType = cashflowType;
        this.amount = amount;
        this.type = type;
        this.recur = recur;
    }

    @Override
    public void execute(Ui ui, CashflowList list, WatchList watchList) {
        switch (cashflowType) {
        case INCOME:
            list.addIncome(amount, type, recur);
            break;
        case EXPENSE:
            list.addExpense(amount, type, recur);
            break;
        default:
            ui.showMessage("Unidentified entry.");
            break;
        }
    }
}
