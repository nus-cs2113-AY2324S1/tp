package cashleh.budget;

/**
 * This class creates an object of type Budget which the user can set to manage its
 * finances. The budget is to be interpreted as the minimum amount of liquid cash
 * (cash on hand) the user wants to have at all times.
 * The user can delete the budget by deactivating it.
 */
public class Budget {
    private final double budget;
    private boolean isActive;
    public Budget(double budget) {
        this.budget = budget;
        this.isActive = false;
    }
    public double getBudget() {
        return Math.abs(this.budget);
    }
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    public boolean isActive() {
        return this.isActive;
    }
    @Override
    public String toString() {
        return "You have set a budget of: " + String.valueOf(this.budget);
    }
}
