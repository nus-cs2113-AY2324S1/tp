package seedu.financialplanner.cashflow;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the monthly budget.
 */
public abstract class Budget {
    private static double initialBudget = 0;
    private static double currentBudget = 0;

    /**
     * Sets the monthly budget equal to amount.
     *
     * @param amount The monthly budget to be set
     */
    public static void setBudget(double amount) {
        initialBudget = amount;
        currentBudget = amount;
        assert initialBudget == currentBudget : "Initial and current budget should be the same";
        assert initialBudget != 0 && currentBudget != 0 : "Initial and current budget should not be 0";
    }

    /**
     * Returns the initial budget set.
     *
     * @return The initial budget.
     */
    public static double getInitialBudget() {
        return initialBudget;
    }

    /**
     * Updates initial budget to the new budget and updates the current budget
     * with the difference between the old initial budget and new initial budget.
     *
     * @param budget The budget to be updated to.
     */
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

    /**
     * Returns the current budget.
     *
     * @return The current budget.
     */
    public static double getCurrentBudget() {
        return currentBudget;
    }

    /**
     * Returns the current budget in 2 decimal places after converting
     * it into a string.
     *
     * @return The current budget in string format.
     */
    public static String getCurrentBudgetString() {
        DecimalFormat decimalFormat = new DecimalFormat("####0.00");
        return decimalFormat.format(Cashflow.round(currentBudget, 2));
    }

    /**
     * Returns the initial budget in 2 decimal places after converting
     * it into a string.
     *
     * @return The initial budget in string format.
     */
    public static String getInitialBudgetString() {
        DecimalFormat decimalFormat = new DecimalFormat("####0.00");
        return decimalFormat.format(Cashflow.round(initialBudget, 2));
    }

    /**
     * Deducts the current budget by the amount given.
     *
     * @param amount The amount to be deducted.
     */
    public static void deduct(double amount) {
        currentBudget -= amount;
    }

    /**
     * Loads the budget from the save file.
     *
     * @param initial The saved initial budget.
     * @param current The saved current budget.
     * @param savedDate The date that was saved to storage.
     */
    public static void load(double initial, double current, LocalDate savedDate) {
        initialBudget = initial;
        currentBudget = current;
        LocalDate currentDate = LocalDate.now();
        // Resets budget if it is a new month or new year.
        if (!currentDate.getMonth().equals(savedDate.getMonth()) || currentDate.getYear() != savedDate.getYear()) {
            resetBudget();
        }
    }

    /**
     * Checks if there is a budget.
     *
     * @return True if there is a budget set, false otherwise.
     */
    public static boolean hasBudget() {
        return initialBudget != 0;
    }

    /**
     * Returns a string representation of the budget to be saved to the save file.
     *
     * @return A string representation of the budget.
     */
    public static String formatString() {
        return "B | " + initialBudget + " | " + currentBudget + " | " +
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Deletes an existing budget.
     */
    public static void deleteBudget() {
        initialBudget = 0;
        currentBudget = 0;
    }

    /**
     * Resets the current budget to initial budget.
     */
    public static void resetBudget() {
        currentBudget = initialBudget;
    }

    /**
     * Sets initial budget to the amount given.
     *
     * @param amount The amount to be set.
     */
    public static void setInitialBudget(double amount) {
        initialBudget = amount;
    }

    /**
     * Updates current budget by adding amount to current budget.
     *
     * @param amount The amount to be updated.
     */
    public static void updateCurrentBudget(double amount) {
        currentBudget += amount;
    }
}
