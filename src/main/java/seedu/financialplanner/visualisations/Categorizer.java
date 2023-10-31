package seedu.financialplanner.visualisations;

import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.cashflow.Cashflow;
import seedu.financialplanner.cashflow.CashflowList;
import seedu.financialplanner.cashflow.Expense;
import seedu.financialplanner.cashflow.Income;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Categorizer {
    private static final Logger logger = Logger.getLogger("Financial Planner Logger");

    public static HashMap<String, Double> sortType(CashflowList cashflowList, String type)
            throws FinancialPlannerException {
        switch (type) {
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
