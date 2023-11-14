package seedu.financialplanner.commands;

import seedu.financialplanner.commands.utils.Command;
import seedu.financialplanner.commands.utils.RawCommand;
import seedu.financialplanner.enumerations.CashflowCategory;
import seedu.financialplanner.enumerations.ExpenseType;
import seedu.financialplanner.enumerations.IncomeType;
import seedu.financialplanner.cashflow.Budget;
import seedu.financialplanner.cashflow.Cashflow;
import seedu.financialplanner.cashflow.CashflowList;
import seedu.financialplanner.utils.Ui;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the command to add a cashflow.
 */
@SuppressWarnings("unused")
public class AddCashflowCommand extends Command {
    public static final String NAME = "add";

    public static final String USAGE =
            "add income </a AMOUNT> </t TYPE> [/r DAYS] [/d DESCRIPTION]" + "\n" +
            "add expense </a AMOUNT> </t TYPE> [/r DAYS] [/d DESCRIPTION]";

    public static final String EXAMPLE =
            "add income /a 5000 /t salary /r 30 /d work" + "\n" +
            "add expense /a 300 /t necessities /r 30 /d groceries";


    protected static Ui ui = Ui.getInstance();
    private static Logger logger = Logger.getLogger("Financial Planner Logger");
    protected double amount;
    protected CashflowCategory category;
    protected ExpenseType expenseType;
    protected IncomeType incomeType;
    protected int recur = 0;
    protected String description = null;
    protected CashflowList cashflowList = CashflowList.getInstance();
    protected final double MAX_AMOUNT = 999999999999.99;

    /**
     * Constructor for the command to add a cashflow.
     *
     * @param rawCommand The input from the user.
     * @throws IllegalArgumentException if erroneous inputs are detected.
     */
    public AddCashflowCommand(RawCommand rawCommand) throws IllegalArgumentException {
        String categoryString = String.join(" ", rawCommand.args).trim();
        try {
            logger.log(Level.INFO, "Parsing CashflowCategory");
            category = CashflowCategory.valueOf(categoryString.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, "Invalid arguments for CashflowCategory");
            throw new IllegalArgumentException("Entry must be either income or expense.");
        }

        if (!rawCommand.extraArgs.containsKey("a")) {
            logger.log(Level.WARNING, "Missing arguments for amount");
            throw new IllegalArgumentException("Entry must have an amount.");
        }
        try {
            logger.log(Level.INFO, "Parsing amount as double");
            amount = Double.parseDouble(rawCommand.extraArgs.get("a").trim());
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, "Invalid arguments for amount");
            throw new IllegalArgumentException("Amount must be a number.");
        }
        if (amount < 0) {
            logger.log(Level.WARNING, "Invalid value for amount");
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
        if (amount > MAX_AMOUNT) {
            logger.log(Level.WARNING, "Maximum value for amount exceeded.");
            throw new IllegalArgumentException("Amount exceeded maximum value this program can hold. " +
                    "Please add a different cashflow.");
        }
        rawCommand.extraArgs.remove("a");

        if (!rawCommand.extraArgs.containsKey("t")) {
            logger.log(Level.WARNING, "Missing arguments for type");
            throw new IllegalArgumentException("Entry must have a type.");
        }
        String stringType = rawCommand.extraArgs.get("t").trim();
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
                recur = Integer.parseInt(rawCommand.extraArgs.get("r").trim());
            } catch (IllegalArgumentException e) {
                logger.log(Level.WARNING, "Invalid arguments for recur");
                throw new IllegalArgumentException("Recurrence must be an integer and be within the " +
                        "maximum value this program can hold.");
            }
            rawCommand.extraArgs.remove("r");
        }
        if (recur < 0) {
            logger.log(Level.WARNING, "Invalid value for recur");
            throw new IllegalArgumentException("Recurring value cannot be negative.");
        }

        if (rawCommand.extraArgs.containsKey("d")) {
            logger.log(Level.INFO, "Getting description of cashflow");
            String line = rawCommand.extraArgs.get("d");
            if (line.isBlank()) {
                logger.log(Level.WARNING, "Empty description");
                throw new IllegalArgumentException("Description cannot be left empty.");
            }
            description = line.trim();
        }
        rawCommand.extraArgs.remove("d");

        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            logger.log(Level.WARNING, "Invalid extra arguments found");
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }

    /**
     * Executes the command to add a cashflow.
     */
    @Override
    public void execute() {
        assert category.equals(CashflowCategory.INCOME) || category.equals(CashflowCategory.EXPENSE)
                || category.equals(CashflowCategory.RECURRING);
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
