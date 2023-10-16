package seedu.financialplanner.commands;

import seedu.financialplanner.enumerations.CashflowCategory;
import seedu.financialplanner.list.Budget;
import seedu.financialplanner.list.Cashflow;
import seedu.financialplanner.list.CashflowList;
import seedu.financialplanner.utils.Ui;

import java.util.ArrayList;

public class AddCashflowCommand extends AbstractCommand {

    protected double amount;
    protected CashflowCategory category;
    protected String type;
    protected int recur = 0;

    public AddCashflowCommand(RawCommand rawCommand) throws IllegalArgumentException {
        String typeString = String.join(" ", rawCommand.args);
        try {
            category = CashflowCategory.valueOf(typeString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Entry must be either income or expense");
        }

        if (!rawCommand.extraArgs.containsKey("a")) {
            throw new IllegalArgumentException("Entry must have an amount");
        }
        try {
            amount = Double.parseDouble(rawCommand.extraArgs.get("a"));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Amount must be a number");
        }
        rawCommand.extraArgs.remove("a");

        if (!rawCommand.extraArgs.containsKey("t")) {
            throw new IllegalArgumentException("Entry must have a type");
        }
        type = rawCommand.extraArgs.get("t");
        rawCommand.extraArgs.remove("t");

        if (rawCommand.extraArgs.containsKey("r")) {
            try {
                recur = Integer.parseInt(rawCommand.extraArgs.get("r"));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Recurrence must be an integer");
            }
            rawCommand.extraArgs.remove("r");
        }
        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }

    @Override
    public void execute() {
        switch (category) {
        case INCOME:
            CashflowList.INSTANCE.addIncome(amount, type, recur);
            break;
        case EXPENSE:
            CashflowList list = CashflowList.INSTANCE;
            list.addExpense(amount, type, recur);
            if (Budget.hasBudget()) {
                deductFromBudget(list.list.get(list.list.size() - 1));
            }
            break;
        default:
            Ui.INSTANCE.showMessage("Unidentified entry.");
            break;
        }
    }

    private static void deductFromBudget(Cashflow entry) {
        double expenseAmount = entry.getAmount();
        Budget.deduct(expenseAmount);
        String message = "";
        if (Budget.getCurrentBudget() <= 0) {
            message += "You have exceeded your current budget by: ";
        } else if (Budget.getCurrentBudget() > 0) {
            message += "Your remaining budget for the month is: ";
        }
        message += Budget.getCurrentBudgetString();
        Ui.INSTANCE.showMessage(message);
    }
}
