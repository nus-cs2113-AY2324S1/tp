package seedu.financialplanner.list;

import java.text.DecimalFormat;

public abstract class Budget {
    private static final int MONTH = 30;
    private static double initialBudget = 0;
    private static double currentBudget = 0;

    public static void setBudget(double amount) {
        initialBudget = amount;
        currentBudget = amount;
        assert initialBudget == currentBudget : "Initial and current budget should be the same";
        assert initialBudget != 0 && currentBudget != 0 : "Initial and current budget should not be 0";
    }

    public static double getInitialBudget() {
        return initialBudget;
    }

    public static void updateBudget(double budget) {
        double diff;
        if (budget > initialBudget) {
            diff = budget - initialBudget;
            initialBudget = budget;
            currentBudget += diff;
        } else if (budget < initialBudget) {
            diff = initialBudget - budget;
            initialBudget = budget;
            currentBudget -= diff;
        }
        assert initialBudget == budget : "Initial budget should be equal to updated budget";
    }

    public static double getCurrentBudget() {
        return currentBudget;
    }

    public static String getCurrentBudgetString() {
        DecimalFormat decimalFormat = new DecimalFormat("####0.00");
        return decimalFormat.format(Cashflow.round(currentBudget, 2));
    }

    public static String getInitialBudgetString() {
        DecimalFormat decimalFormat = new DecimalFormat("####0.00");
        return decimalFormat.format(Cashflow.round(initialBudget, 2));
    }

    public static void deduct(double amount) {
        Budget.currentBudget -= amount;
    }

    public static void load(double initial, double current) {
        initialBudget = initial;
        currentBudget = current;
    }

    public static boolean hasBudget() {
        return initialBudget != 0;
    }

    public static String formatString() {
        return "B | " + initialBudget + " | " + currentBudget;
    }
}
