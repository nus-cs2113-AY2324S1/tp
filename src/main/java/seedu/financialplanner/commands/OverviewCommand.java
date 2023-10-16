package seedu.financialplanner.commands;

import seedu.financialplanner.list.*;
import seedu.financialplanner.utils.Parser;
import seedu.financialplanner.utils.Ui;

import java.util.ArrayList;

public class OverviewCommand extends AbstractCommand {
    public OverviewCommand(RawCommand rawCommand) {
        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }

    @Override
    public void execute() throws Exception {
        CashflowList list = CashflowList.INSTANCE;
        AbstractCommand watchlist = new WatchListCommand(Parser.parseRawCommand("watchlist"));
        Ui.INSTANCE.showMessage("Here is an overview of your financials:\n" +
                "Total balance: " + String.format("%.2f", Cashflow.getBalance()) + "\n" +
                "Highest income: " + getHighestIncome(list) + "\n" +
                "Highest expense: " + getHighestExpense(list) + "\n" +
                "Remaining budget for the month: " + getBudgetDesc() + "\n" +
                "Watchlist: ");
        //todo: maybe indicate if income/expense is recurring
        //todo: goal disparity
        watchlist.execute();
        //todo: add educational tip
    }

    private static String getBudgetDesc() {
        return String.format("%.2f", Budget.getCurrentBudget());
    }

    private static String getHighestIncome(CashflowList list) {
        double maxIncome = 0;
        for (Cashflow entry : list.list) {
            if (entry instanceof Income) {
                if (entry.getAmount() > maxIncome) {
                    maxIncome = entry.getAmount();
                }
            }
        }
        return String.format("%.2f", maxIncome);
    }

    private static String getHighestExpense(CashflowList list) {
        double maxExpense = 0;
        for (Cashflow entry : list.list) {
            if (entry instanceof Expense) {
                if (entry.getAmount() > maxExpense) {
                    maxExpense = entry.getAmount();
                }
            }
        }
        return String.format("%.2f", maxExpense);
    }
}
