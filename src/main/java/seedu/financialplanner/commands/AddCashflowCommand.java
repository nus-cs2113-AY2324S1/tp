package seedu.financialplanner.commands;

import seedu.financialplanner.enumerations.CashflowCategory;
import seedu.financialplanner.list.Budget;
import seedu.financialplanner.list.Cashflow;
import seedu.financialplanner.list.CashflowList;
import seedu.financialplanner.utils.Ui;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AddCashflowCommand extends AbstractCommand {

    private static Logger logger = Logger.getLogger("Financial Planner Logger");
    protected double amount;
    protected CashflowCategory category;
    protected String type;
    protected int recur = 0;
    protected CashflowList cashflowList = CashflowList.getInstance();

    public AddCashflowCommand(RawCommand rawCommand) throws IllegalArgumentException {
        String typeString = String.join(" ", rawCommand.args);
        try {
            logger.log(Level.INFO, "Parsing CashflowCategory");
            category = CashflowCategory.valueOf(typeString.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, "Invalid arguments for CashflowCategory");
            throw new IllegalArgumentException("Entry must be either income or expense");
        }

        if (!rawCommand.extraArgs.containsKey("a")) {
            logger.log(Level.WARNING, "Missing arguments for amount");
            throw new IllegalArgumentException("Entry must have an amount");
        }
        try {
            logger.log(Level.INFO, "Parsing amount as double");
            amount = Double.parseDouble(rawCommand.extraArgs.get("a"));
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, "Invalid arguments for amount");
            throw new IllegalArgumentException("Amount must be a number");
        }
        if (amount < 0) {
            logger.log(Level.WARNING, "Invalid value for amount");
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        rawCommand.extraArgs.remove("a");

        if (!rawCommand.extraArgs.containsKey("t")) {
            logger.log(Level.WARNING, "Missing arguments for type");
            throw new IllegalArgumentException("Entry must have a type");
        }
        type = rawCommand.extraArgs.get("t");
        if (type.isBlank()) {
            logger.log(Level.WARNING, "Invalid arguments for type");
            throw new IllegalArgumentException("Type cannot be left empty");
        }
        rawCommand.extraArgs.remove("t");

        if (rawCommand.extraArgs.containsKey("r")) {
            try {
                logger.log(Level.INFO, "Parsing recur as integer");
                recur = Integer.parseInt(rawCommand.extraArgs.get("r"));
            } catch (IllegalArgumentException e) {
                logger.log(Level.WARNING, "Invalid arguments for recur");
                throw new IllegalArgumentException("Recurrence must be an integer");
            }
            rawCommand.extraArgs.remove("r");
        }
        if (recur < 0) {
            logger.log(Level.WARNING, "Invalid value for recur");
            throw new IllegalArgumentException("Recurring value cannot be negative");
        }

        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            logger.log(Level.WARNING, "Invalid extra arguments found");
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
            cashflowList.addIncome(amount, type, recur);
            break;
        case EXPENSE:
            cashflowList.addExpense(amount, type, recur);
            if (Budget.hasBudget()) {
                deductFromBudget(cashflowList.list.get(cashflowList.list.size() - 1));
            }
            break;
        default:
            logger.log(Level.SEVERE, "Unreachable default case reached");
            Ui.INSTANCE.showMessage("Unidentified entry.");
            break;
        }
    }

    private static void deductFromBudget(Cashflow entry) {
        double expenseAmount = entry.getAmount();
        Budget.deduct(expenseAmount);
        Ui.INSTANCE.printBudgetAfterDeduction();
    }
}
