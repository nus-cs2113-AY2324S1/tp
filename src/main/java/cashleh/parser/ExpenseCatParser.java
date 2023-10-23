package cashleh.parser;

import cashleh.transaction.ExpenseCategories;
import cashleh.transaction.ExpenseCategories.ExpenseCategory;

public class ExpenseCatParser {
    
    private static String[] expenseCatList = 
    {
        "FOOD_DRINK",
        "SHOPPING",
        "HOUSING",
        "TRANSPORTATION",
        "ENTERTAINMENT",
        "UTILITIES",
        "OTHERS"};
    
    public static ExpenseCategory parse(String catString) {
        for (String cat : expenseCatList) {
            if (cat.startsWith(catString.toUpperCase())) {
                catString = cat;
            }
        }
        return ExpenseCategories.getExpenseCategory(catString);
    }
}
