package cashleh.transaction;

public class IncomeCategories {
    public enum IncomeCategory implements Categories {
        SALARY,
        ALLOWANCE,
        INVESTMENT,
        LOTTERY_GAMBLING,
        OTHERS
    }

    public static IncomeCategory getIncomeCategory(String input) {
        try {
            return IncomeCategory.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return IncomeCategory.OTHERS;
        }
    }
    
}
