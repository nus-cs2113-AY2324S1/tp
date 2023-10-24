package cashleh.transaction;

public class ExpenseCategories {
    public enum ExpenseCategory implements Categories{
        FOOD_DRINK,
        SHOPPING,
        HOUSING,
        TRANSPORTATION,
        ENTERTAINMENT,
        UTILITIES,
        OTHERS;
    }
    public static ExpenseCategory getExpenseCategory(String input) {
        try {
            return ExpenseCategory.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ExpenseCategory.OTHERS;
        }
    }
}
