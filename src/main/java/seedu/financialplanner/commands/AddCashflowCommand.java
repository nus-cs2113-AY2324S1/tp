package seedu.financialplanner.commands;

import seedu.financialplanner.enumerations.CashflowCategory;
import seedu.financialplanner.list.Budget;
import seedu.financialplanner.list.Cashflow;
import seedu.financialplanner.list.CashflowList;
import seedu.financialplanner.utils.Ui;

import java.util.ArrayList;
import static java.lang.Math.abs;

public class AddCashflowCommand extends AbstractCommand {

    protected double amount;
    protected CashflowCategory category;
    protected String type = null;
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
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        rawCommand.extraArgs.remove("a");

        if (!rawCommand.extraArgs.containsKey("t")) {
            throw new IllegalArgumentException("Entry must have a type");
        }
        type = rawCommand.extraArgs.get("t");
        if (type.isBlank()) {
            throw new IllegalArgumentException("Type cannot be left empty");
        }
        rawCommand.extraArgs.remove("t");

        if (rawCommand.extraArgs.containsKey("r")) {
            try {
                recur = Integer.parseInt(rawCommand.extraArgs.get("r"));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Recurrence must be an integer");
            }
            rawCommand.extraArgs.remove("r");
        }
        if (recur < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }

    @Override
    public void execute() {
        assert category.equals(CashflowCategory.INCOME) || category.equals(CashflowCategory.EXPENSE);
        assert recur >= 0;
        assert amount >= 0;
        assert type != null;

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
        if (Budget.getCurrentBudget() <= 0) {
            Ui.INSTANCE.showMessage("You have exceeded your current budget by: " +
                    String.format("%.2f", abs(Budget.getCurrentBudget())));
        } else if (Budget.getCurrentBudget() > 0) {
            Ui.INSTANCE.showMessage("Your remaining budget for the month is: " +
                    String.format("%.2f", Budget.getCurrentBudget()));
        }
    }
}
