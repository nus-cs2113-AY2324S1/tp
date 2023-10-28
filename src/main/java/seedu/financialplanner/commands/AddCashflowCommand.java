package seedu.financialplanner.commands;

import seedu.financialplanner.enumerations.CashflowCategory;
import seedu.financialplanner.enumerations.ExpenseType;
import seedu.financialplanner.enumerations.IncomeType;
import seedu.financialplanner.list.Budget;
import seedu.financialplanner.list.Cashflow;
import seedu.financialplanner.list.CashflowList;
import seedu.financialplanner.utils.Ui;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AddCashflowCommand extends Command {
    protected static Ui ui = Ui.getInstance();
    private static Logger logger = Logger.getLogger("Financial Planner Logger");
    protected double amount;
    protected CashflowCategory category;
    protected ExpenseType expenseType;
    protected IncomeType incomeType;
    protected int recur = 0;
    protected String description = null;
    protected CashflowList cashflowList = CashflowList.getInstance();

    public AddCashflowCommand(RawCommand rawCommand) throws IllegalArgumentException {
        String categoryString = String.join(" ", rawCommand.args);
        try {
            logger.log(Level.INFO, "Parsing CashflowCategory");
            category = CashflowCategory.valueOf(categoryString.toUpperCase());
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
        String stringType = rawCommand.extraArgs.get("t");
        if (category.equals(CashflowCategory.EXPENSE)) {
            try {
                logger.log(Level.INFO, "Parsing ExpenseType");
                expenseType = ExpenseType.valueOf(stringType.toUpperCase());
            } catch (IllegalArgumentException e) {
                logger.log(Level.WARNING, "Invalid arguments for ExpenseType");
                throw new IllegalArgumentException("Entry must be one of the following: " +
                        "dining, entertainment, shopping, travel, insurance, necessities, others");
            }
        } else if (category.equals(CashflowCategory.INCOME)) {
            try {
                logger.log(Level.INFO, "Parsing IncomeType");
                incomeType = IncomeType.valueOf(stringType.toUpperCase());
            } catch (IllegalArgumentException e) {
                logger.log(Level.WARNING, "Invalid arguments for IncomeType");
                throw new IllegalArgumentException("Entry must be one of the following: " +
                        "salary, investments, allowance, others");
            }
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

        if (rawCommand.extraArgs.containsKey("d")) {
            description = rawCommand.extraArgs.get("d");
        }
        rawCommand.extraArgs.remove("d");

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
        if (category.equals(CashflowCategory.EXPENSE)) {
            assert expenseType.equals(ExpenseType.DINING) || expenseType.equals(ExpenseType.ENTERTAINMENT)
                    || expenseType.equals(ExpenseType.SHOPPING) || expenseType.equals(ExpenseType.TRAVEL)
                    || expenseType.equals(ExpenseType.INSURANCE) || expenseType.equals(ExpenseType.OTHERS)
                    || expenseType.equals(ExpenseType.NECESSITIES);
        } else if (category.equals(CashflowCategory.INCOME)) {
            assert incomeType.equals(IncomeType.SALARY) || incomeType.equals(IncomeType.INVESTMENTS)
                    || incomeType.equals(IncomeType.ALLOWANCE) || incomeType.equals(IncomeType.OTHERS);
        }

        switch (category) {
        case INCOME:
            cashflowList.addIncome(amount, incomeType, recur, description);
            break;
        case EXPENSE:
            cashflowList.addExpense(amount, expenseType, recur, description);
            if (Budget.hasBudget()) {
                deductFromBudget(cashflowList.list.get(cashflowList.list.size() - 1));
            }
            break;
        default:
            logger.log(Level.SEVERE, "Unreachable default case reached");
            ui.showMessage("Unidentified entry.");
            break;
        }
    }

    private static void deductFromBudget(Cashflow entry) {
        double expenseAmount = entry.getAmount();
        Budget.deduct(expenseAmount);
        ui.printBudgetAfterDeduction();
    }
}
