package cashleh.transaction;

public class IncomeCategories {
    public enum IncomeCategory implements Categories {
        SALARY,
        ALLOWANCE,
        INVESTMENT,
        LOTTERY_GAMBLING,
        OTHERS
    }

    public static Categories getCategory(String categoryString) {
        try {
            return ExpenseCategories.ExpenseCategory.valueOf(categoryString.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ExpenseCategories.ExpenseCategory.OTHERS;
        }
    }
    public static IncomeCategory getIncomeCategory(String input) {
        try {
            return IncomeCategory.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return IncomeCategory.OTHERS;
        }
    }
    
}
