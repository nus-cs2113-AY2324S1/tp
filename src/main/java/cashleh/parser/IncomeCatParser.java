package cashleh.parser;

import cashleh.transaction.IncomeCategories;
import cashleh.transaction.IncomeCategories.IncomeCategory;

public class IncomeCatParser {
    
    private static String[] incomeCatList = {
        "SALARY",
        "ALLOWANCE",
        "INVESTMENT",
        "LOTTERY_GAMBLING",
        "OTHERS"};

    /**
     * Parses the category string into the corresponding IncomeCategory.
     * @param catString the category string to be parsed.
     * @return the corresponding IncomeCategory.
     */
    public static IncomeCategory parse(String catString) {
        System.out.println(catString);
        for (String cat : incomeCatList) {
            if (cat.startsWith(catString.toUpperCase())) {
                catString = cat;
            }
        }
        return IncomeCategories.getIncomeCategory(catString);
    }
}
