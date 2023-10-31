package seedu.financialplanner.commands;

import seedu.financialplanner.cashflow.Budget;
import seedu.financialplanner.cashflow.Cashflow;
import seedu.financialplanner.cashflow.CashflowList;
import seedu.financialplanner.cashflow.Income;
import seedu.financialplanner.cashflow.Expense;
import seedu.financialplanner.reminder.Reminder;
import seedu.financialplanner.reminder.ReminderList;
import seedu.financialplanner.utils.Ui;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OverviewCommand extends Command {
    private static final CashflowList cashflowList = CashflowList.getInstance();

    public OverviewCommand(RawCommand rawCommand) {
        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }

    @Override
    public void execute() throws Exception {
        String balance = getBalance();
        String highestIncome = getHighestIncome();
        String highestExpense = getHighestExpense();
        String budget = getBudgetDesc();
        String reminders = getReminders();

        Ui.getInstance().printOverview(balance, highestIncome, highestExpense, budget, reminders);

        //todo: goal disparity
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
        StringBuilder reminders = new StringBuilder();
        int count = 1;
        for (Reminder reminder : reminderList.list) {
            reminders.append(count).append(". ").append(reminder.toString()).append("\n");
            count++;
        }

        return reminders.toString();
    }

    private String getBalance() {
        return formatDoubleToString(Cashflow.getBalance());
    }
}
