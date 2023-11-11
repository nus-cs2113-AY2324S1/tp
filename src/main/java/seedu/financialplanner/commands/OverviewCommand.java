package seedu.financialplanner.commands;

import seedu.financialplanner.cashflow.Budget;
import seedu.financialplanner.cashflow.Cashflow;
import seedu.financialplanner.cashflow.CashflowList;
import seedu.financialplanner.cashflow.Income;
import seedu.financialplanner.cashflow.Expense;
import seedu.financialplanner.commands.utils.Command;
import seedu.financialplanner.commands.utils.RawCommand;
import seedu.financialplanner.goal.WishList;
import seedu.financialplanner.reminder.ReminderList;
import seedu.financialplanner.utils.Ui;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Represents a command to display overview of user's financials.
 */
@SuppressWarnings("unused")
public class OverviewCommand extends Command {
    public static final String NAME = "overview";

    public static final String USAGE =
            "overview";

    public static final String EXAMPLE =
            "overview";
    private static final CashflowList cashflowList = CashflowList.getInstance();

    public OverviewCommand(RawCommand rawCommand) {
        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }

    /**
     * Executes the command and displays an overview of the user's financials.
     */
    @Override
    public void execute() {
        String balance = getBalance();
        String highestIncome = getHighestIncome();
        String highestExpense = getHighestExpense();
        String budget = getBudgetDesc();
        String reminders = getReminders();
        String wishlist = getWishlist();

        Ui.getInstance().printOverview(balance, highestIncome, highestExpense, budget, reminders, wishlist);
    }

    private String getBudgetDesc() {
        return Budget.getCurrentBudgetString();
    }

    private String getHighestIncome() {
        double maxIncome = 0;
        String incomeType = "";
        for (Cashflow entry : cashflowList.list) {
            if (entry instanceof Income && entry.getAmount() > maxIncome) {
                maxIncome = entry.getAmount();
                incomeType = entry.capitalize(entry.getIncomeType().
                        toString().toLowerCase()); // Capitalise the first letter
            }
        }

        if (incomeType.isEmpty()) {
            return "No income added yet.";
        }

        return formatDoubleToString(maxIncome) + "    Category: " + incomeType;
    }

    private String getHighestExpense() {
        double maxExpense = 0;
        String expenseType = "";
        for (Cashflow entry : cashflowList.list) {
            if (entry instanceof Expense && entry.getAmount() > maxExpense) {
                maxExpense = entry.getAmount();
                expenseType = entry.capitalize(entry.getExpenseType().
                        toString().toLowerCase()); // Capitalise the first letter
            }
        }

        if (expenseType.isEmpty()) {
            return "No expense added yet.";
        }

        return formatDoubleToString(maxExpense) + "    Category: " + expenseType;
    }

    private String formatDoubleToString(double amount) {
        DecimalFormat decimalFormat = new DecimalFormat("####0.00");

        return decimalFormat.format(Cashflow.round(amount, 2));
    }

    private String getReminders() {
        ReminderList reminderList = ReminderList.getInstance();
        if (reminderList.list.isEmpty()) {
            return "No reminders added yet.";
        }
        return reminderList.toString();
    }

    private String getWishlist() {
        WishList wishList = WishList.getInstance();
        if (wishList.list.isEmpty()) {
            return "No goals added yet.";
        }
        return wishList.toString();
    }

    private String getBalance() {
        return formatDoubleToString(Cashflow.getBalance());
    }
}
