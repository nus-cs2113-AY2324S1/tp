package seedu.financialplanner.cashflow;

import seedu.financialplanner.enumerations.CashflowCategory;
import seedu.financialplanner.enumerations.ExpenseType;
import seedu.financialplanner.enumerations.IncomeType;
import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.utils.Ui;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the list containing all cashflows.
 */
public class CashflowList {
    private static final Logger logger = Logger.getLogger("Financial Planner Logger");

    private static CashflowList cashflowList = null;
    public final ArrayList<Cashflow> list = new ArrayList<>();
    protected Ui ui = Ui.getInstance();
    private CashflowList() {
    }

    /**
     * Gets the single instance of CashflowList class.
     *
     * @return the CashflowList instance.
     */
    public static CashflowList getInstance() {
        if (cashflowList == null) {
            cashflowList = new CashflowList();
        }
        return cashflowList;
    }

    /**
     * Add an income to the list.
     *
     * @param value The value of the income.
     * @param type The type of the income, using the values in the enum of IncomeType.
     * @param recur The number of days before the next automatic addition of the income.
     * @param description The description of the income.
     */
    public void addIncome(double value, IncomeType type, int recur, String description) {
        try {
            logger.log(Level.INFO, "Adding income");
            int existingListSize = list.size();

            Income toAdd = new Income(value, type, recur, description);
            addToList(toAdd);
            ui.printAddedCashflow(toAdd);

            int newListSize = list.size();
            assert newListSize == existingListSize + 1;
        } catch (FinancialPlannerException e) {
            ui.showMessage(e.getMessage());
        }
    }

    private void addToList(Cashflow toAdd) {
        list.add(toAdd);
    }

    /**
     * Adds an expense to the list.
     *
     * @param value The value of the expense.
     * @param type The type of the expense, using the values in the enum of ExpenseType.
     * @param recur The number of days before the next automatic addition of the expense.
     * @param description The description of the expense.
     */
    public void addExpense(double value, ExpenseType type, int recur, String description) {
        try {
            logger.log(Level.INFO, "Adding expense");
            int existingListSize = list.size();

            Expense toAdd = new Expense(value, type, recur, description);
            addToList(toAdd);
            ui.printAddedCashflow(toAdd);

            int newListSize = list.size();
            assert newListSize == existingListSize + 1;
        } catch (FinancialPlannerException e) {
            ui.showMessage(e.getMessage());
        }
    }

    /**
     * Deletes a cashflow when its category is not specified.
     *
     * @param index The index of the cashflow as displayed to the user.
     * @return The value of the cashflow to be removed.
     */
    public double deleteCashflowWithoutCategory(int index) {
        int existingListSize = list.size();
        int listIndex = index - 1;

        Cashflow toRemove = list.get(listIndex);
        list.remove(listIndex);
        toRemove.deleteCashflowValue();
        ui.printDeletedCashflow(toRemove);

        int newListSize = list.size();
        assert newListSize == existingListSize - 1;
        return toRemove.getAmount();
    }

    /**
     * Deletes all future recurrences of a cashflow that has an unspecified category.
     *
     * @param index The index of the cashflow as displayed to the user.
     */
    public void deleteRecurWithoutCategory(int index) {
        int listIndex = index - 1;

        Cashflow toRemoveRecur = list.get(listIndex);
        if (toRemoveRecur.getRecur() == 0 || toRemoveRecur.hasRecurred) {
            ui.showMessage("Cashflow is already not recurring or has already recurred");
        } else {
            toRemoveRecur.setDate(null);
            toRemoveRecur.setRecur(0);
            list.set(listIndex, toRemoveRecur);
            ui.printDeletedRecur(toRemoveRecur);
        }
    }

    private int cashflowIndexFinder(CashflowCategory category, int cashflowIndex) throws FinancialPlannerException {
        assert category.equals(CashflowCategory.INCOME) || category.equals(CashflowCategory.EXPENSE)
                || category.equals(CashflowCategory.RECURRING);

        switch (category) {
        case INCOME:
            return findCashflowIndexFromIncomeIndex(cashflowIndex);
        case EXPENSE:
            return findCashflowIndexFromExpenseIndex(cashflowIndex);
        case RECURRING:
            return findCashflowIndexFromRecurIndex(cashflowIndex);
        default:
            throw new FinancialPlannerException("Error in finding cashflow in the list.");
        }
    }

    private int findCashflowIndexFromIncomeIndex(int cashflowIndex) {
        int cashflowCounter = 0;
        int overallCashflowIndex = 0;

        for (Cashflow entry : list) {
            if (entry instanceof Income) {
                cashflowCounter += 1;
            }
            if (cashflowCounter == cashflowIndex) {
                break;
            }
            overallCashflowIndex += 1;
        }
        return overallCashflowIndex;
    }

    private int findCashflowIndexFromExpenseIndex(int cashflowIndex) {
        int cashflowCounter = 0;
        int overallCashflowIndex = 0;

        for (Cashflow entry : list) {
            if (entry instanceof Expense) {
                cashflowCounter += 1;
            }
            if (cashflowCounter == cashflowIndex) {
                break;
            }
            overallCashflowIndex += 1;
        }
        return overallCashflowIndex;
    }
    private int findCashflowIndexFromRecurIndex(int cashflowIndex) {
        int cashflowCounter = 0;
        int overallCashflowIndex = 0;

        for (Cashflow entry : list) {
            if (entry.getRecur() > 0 && !entry.getHasRecurred()) {
                cashflowCounter += 1;
            }
            if (cashflowCounter == cashflowIndex) {
                break;
            }
            overallCashflowIndex += 1;
        }
        return overallCashflowIndex;
    }

    /**
     * Deletes all future recurrences of a cashflow that has a specified category.
     *
     * @param category The type of cashflow: income, expense or recurring.
     * @param index The index of the cashflow as displayed to the user.
     */
    public void deleteRecurWithCategory(CashflowCategory category, int index) {
        try {
            int listIndex = cashflowIndexFinder(category, index);
            Cashflow toRemoveRecur = list.get(listIndex);
            if (toRemoveRecur.getRecur() == 0 || toRemoveRecur.hasRecurred) {
                ui.showMessage("Cashflow is already not recurring or has already recurred.");
            } else {
                toRemoveRecur.setDate(null);
                toRemoveRecur.setRecur(0);
                list.set(listIndex, toRemoveRecur);
                ui.printDeletedRecur(toRemoveRecur);
            }
        } catch (FinancialPlannerException e) {
            ui.showMessage(e.getMessage());
        }
    }

    /**
     * Deletes a cashflow that has a specified category.
     *
     * @param category The type of cashflow: income, expense or recurring.
     * @param index The index of the cashflow as displayed to the user.
     * @return The value of the cashflow to be deleted.
     */
    public double deleteCashflowWithCategory(CashflowCategory category, int index) {
        try {
            int existingListSize = list.size();
            int listIndex = cashflowIndexFinder(category, index);

            Cashflow toRemove = list.get(listIndex);
            list.remove(listIndex);
            toRemove.deleteCashflowValue();
            ui.printDeletedCashflow(toRemove);

            int newListSize = list.size();
            assert newListSize == existingListSize - 1;
            return toRemove.getAmount();
        } catch (FinancialPlannerException e) {
            ui.showMessage(e.getMessage());
        }
        return 0;
    }

    /**
     * Adds a saved cashflow from the storage to the list.
     *
     * @param entry The cashflow object to be laoded.
     */
    public void load(Cashflow entry) {
        addToList(entry);
    }

    /**
     * Formats the list to string with each entry seperated by a newline.
     *
     * @return The formatted list.
     */
    public String getList() {
        StringBuilder output = new StringBuilder();
        for (Cashflow entry : list) {
            output.append(entry).append("\n");
        }
        return output.toString();
    }
}
