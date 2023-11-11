package seedu.financialplanner.visualisations;

import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.cashflow.Cashflow;
import seedu.financialplanner.cashflow.CashflowList;
import seedu.financialplanner.cashflow.Expense;
import seedu.financialplanner.cashflow.Income;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Class that is used to sort the cash flow list into different categories according to the type they are
 * (Income, Expense)
 */
public class Categorizer {
    private static final Logger logger = Logger.getLogger("Financial Planner Logger");

    /**
     * Method that calls the required methods to sort the cash flow based on the type is specified by the user
     * (Income, Expense)
     *
     * @param cashflowList
     * @param type
     * @return hashmap of sorted income/expense according to category
     * @throws FinancialPlannerException
     */
    public static HashMap<String, Double> sortType(CashflowList cashflowList, String type)
            throws FinancialPlannerException {
        switch (type.toLowerCase()) {
        case "expense":
            logger.log(Level.INFO, "categorizing expenses");
            return sortExpenses(cashflowList);
        case "income":
            logger.log(Level.INFO, "categorizing income");
            return sortIncome(cashflowList);
        default:
            throw new FinancialPlannerException(type + " Type not found");
        }
    }

    /**
     * Method to sort the expenses of the cash flow list into different categories and return the sorted hashmap
     *
     * @param cashflowList
     * @return Hashmap of sorted expenses according to category
     */
    public static HashMap<String, Double> sortExpenses(CashflowList cashflowList) {
        HashMap<String, Double> expensesByCat = new HashMap<>();
        for (Cashflow e: cashflowList.list) {
            if (e instanceof Expense) {
                String key = e.getExpenseType().toString().toLowerCase();
                double value = expensesByCat.getOrDefault(key, 0.0) + e.getAmount();
                assert value >= 0;
                expensesByCat.put(key, value);
            }
        }
        return expensesByCat;
    }

    /**
     * Method to sort the incomes of the cash flow list into different categories and return the sorted hashmap
     *
     * @param cashflowList
     * @return Hashmap containing income sorted according to category
     */
    public static HashMap<String, Double> sortIncome(CashflowList cashflowList) {
        HashMap<String, Double> incomeByCat = new HashMap<>();
        for (Cashflow e: cashflowList.list) {
            if (e instanceof Income) {
                String key = e.getIncomeType().toString().toLowerCase();
                double value = incomeByCat.getOrDefault(key, 0.0) + e.getAmount();
                assert value >= 0;
                incomeByCat.put(key, value);
            }
        }
        return incomeByCat;
    }
}
