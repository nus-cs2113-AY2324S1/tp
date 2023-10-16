package seedu.financialplanner.list;

public abstract class Budget {
    private static final int MONTH = 30;
    private static double initialBudget = 0;
    private static double currentBudget = 0;

    public static void setBudget(double amount) {
        initialBudget = amount;
        currentBudget = amount;
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
    }

    public static double getCurrentBudget() {
        return currentBudget;
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
