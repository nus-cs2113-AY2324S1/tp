package seedu.financialplanner.visualisations;

import com.sun.source.tree.Tree;
import seedu.financialplanner.list.Cashflow;
import seedu.financialplanner.list.Expense;
import seedu.financialplanner.list.FinancialList;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Categorizer {
    public static Map<String, Double> SortExpenses(FinancialList financialList) {
        Map<String, Double> expensesByCat = new HashMap<>();
        for (Cashflow e: financialList.list) {
            if (e instanceof Expense) {
                String key = e.getType();
                double value = expensesByCat.getOrDefault(key, 0.0) + e.getValue();
                expensesByCat.put(key, value);
            }
        }
        return expensesByCat;
    }
}
