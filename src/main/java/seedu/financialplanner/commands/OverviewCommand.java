package seedu.financialplanner.commands;

import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.list.Cashflow;
import seedu.financialplanner.list.Expense;
import seedu.financialplanner.list.FinancialList;
import seedu.financialplanner.list.Income;
import seedu.financialplanner.utils.Ui;

public class OverviewCommand extends Command {
    @Override
    public void execute(Ui ui, FinancialList financialList, WatchList watchList) throws FinancialPlannerException {
        Command watchlist = new WatchListCommand();
        ui.showMessage("Here is an overview of your financials:\n" +
                "Total balance: " + String.format("%.2f", Cashflow.getBalance()) + "\n" +
                "Highest income: " + String.format("%.2f", getHighestIncome(financialList)) + "\n" +
                "Highest expense: " + String.format("%.2f", getHighestExpense(financialList)) + "\n" +
                "Watchlist: "); //todo: maybe indicate if income/expense is recurring
        //todo: add budget and goal disparity
        watchlist.execute(ui, financialList, watchList);
        //todo: add visualisation
    }

    private double getHighestIncome(FinancialList list) {
        double maxIncome = 0;
        for (Cashflow entry : list.list) {
            if (entry instanceof Income) {
                if (entry.getValue() > maxIncome) {
                    maxIncome = entry.getValue();
                }
            }
        }
        return maxIncome;
    }

    private double getHighestExpense(FinancialList list) {
        double maxExpense = 0;
        for (Cashflow entry : list.list) {
            if (entry instanceof Expense) {
                if (entry.getValue() > maxExpense) {
                    maxExpense = entry.getValue();
                }
            }
        }
        return maxExpense;
    }
}
