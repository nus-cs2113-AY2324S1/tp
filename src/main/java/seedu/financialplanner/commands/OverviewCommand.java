package seedu.financialplanner.commands;

import seedu.financialplanner.list.Budget;
import seedu.financialplanner.list.Cashflow;
import seedu.financialplanner.list.CashflowList;
import seedu.financialplanner.list.Income;
import seedu.financialplanner.list.Expense;
import seedu.financialplanner.utils.Ui;

import java.text.DecimalFormat;
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
        CashflowList list = CashflowList.getInstance();
        Ui.getInstance().showMessage("Here is an overview of your financials:\n" +
                "Total balance: " + formatDoubleToString(Cashflow.getBalance()) + "\n" +
                "Highest income: " + getHighestIncome(list) + "\n" +
                "Highest expense: " + getHighestExpense(list) + "\n" +
                "Remaining budget for the month: " + getBudgetDesc());
        //todo: indicate description of income/expense
        //todo: goal disparity
        //todo: add educational tip
    }

    private static String getBudgetDesc() {
        return Budget.getCurrentBudgetString();
    }

    private static String getHighestIncome(CashflowList list) {
        double maxIncome = 0;
        for (Cashflow entry : list.list) {
            if (entry instanceof Income && entry.getAmount() > maxIncome) {
                maxIncome = entry.getAmount();
            }
        }
        return formatDoubleToString(maxIncome);
    }

    private static String getHighestExpense(CashflowList list) {
        double maxExpense = 0;
        for (Cashflow entry : list.list) {
            if (entry instanceof Expense && entry.getAmount() > maxExpense) {
                maxExpense = entry.getAmount();
            }
        }
        return formatDoubleToString(maxExpense);
    }

    private static String formatDoubleToString(double amount) {
        DecimalFormat decimalFormat = new DecimalFormat("####0.00");

        return decimalFormat.format(Cashflow.round(amount, 2));
    }
}
