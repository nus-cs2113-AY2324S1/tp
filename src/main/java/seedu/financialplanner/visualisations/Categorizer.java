package seedu.financialplanner.visualisations;

import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.list.Cashflow;
import seedu.financialplanner.list.CashflowList;
import seedu.financialplanner.list.Expense;
import seedu.financialplanner.list.Income;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Categorizer {
    private static final Logger logger = Logger.getLogger("Financial Planner Logger");
    public static Map<String, Double> sortType(CashflowList cashflowList, String type)
            throws FinancialPlannerException {
        switch (type) {
        case "expense":
            logger.log(Level.INFO, "categorizing expenses");
            return sortExpenses(cashflowList);
        case "income":
            logger.log(Level.INFO, "categorizing income");
            return sortIncome(cashflowList);
        default:
            throw new FinancialPlannerException("Type not found");
        }
    }
    public static Map<String, Double> sortExpenses(CashflowList cashflowList) {
        Map<String, Double> expensesByCat = new HashMap<>();
        for (Cashflow e: cashflowList.list) {
            if (e instanceof Expense) {
                String key = e.getType();
                double value = expensesByCat.getOrDefault(key, 0.0) + e.getAmount();
                expensesByCat.put(key, value);
            }
        }
        return expensesByCat;
    }

    public static Map<String, Double> sortIncome(CashflowList cashflowList) {
        Map<String, Double> incomeByCat = new HashMap<>();
        for (Cashflow e: cashflowList.list) {
            if (e instanceof Income) {
                String key = e.getType();
                double value = incomeByCat.getOrDefault(key, 0.0) + e.getAmount();
                incomeByCat.put(key, value);
            }
        }
        return incomeByCat;
    }
}
