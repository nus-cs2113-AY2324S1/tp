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

    public static void setInitialBudget(double initialBudget) {
        Budget.initialBudget = initialBudget;
    }

    public static double getCurrentBudget() {
        return currentBudget;
    }

    public static void setCurrentBudget(double currentBudget) {
        Budget.currentBudget = currentBudget;
    }

    public static void load(double initial, double current) {
        initialBudget = initial;
        currentBudget = current;
    }

    public static String formatString() {
        return "B | " + initialBudget + " | " + currentBudget;
    }
}
