package cashleh.budget;

import cashleh.Ui;
import cashleh.exceptions.CashLehBudgetException;
import cashleh.transaction.FinancialStatement;

/**
 * This class creates an object of type BudgetHandler which
 * manages the budget itself. It has methods to set, get, delete and print the
 * budget as well as to handle some cases in which the budget may turn into a deficit.
 */
public class BudgetHandler {
    private static final double MINIMUM_BUDGET_PERCENTAGE_THRESHOLD = 0.25;
    private static final double MAXIMUM_BUDGET_PERCENTAGE = 1;
    private Progress budgetProgress; // net cash on hand divided by the budget
    private Budget budget;
    private final FinancialStatement financialStatement;

    public BudgetHandler(FinancialStatement financialStatement, Budget budget) {
        this.financialStatement = financialStatement;
        this.budget = budget;
    }
    public void setBudget(Budget budget) throws CashLehBudgetException {
        this.budget = budget;
        setBudgetPercentage();
    }
    public Budget getBudget() {
        return this.budget;
    }

    /**
     * Initialises and updates the budget percentage for correct view.
     */
    public void setBudgetPercentage() throws CashLehBudgetException {
        if (budget.getBudget() == 0) {
            throw new CashLehBudgetException("Budget cannot be zero!");
        }
        double budgetAmount = budget.getBudget();
        assert budgetAmount > 0;
        double cashOnHand = this.financialStatement.getNetCash();
        this.budgetProgress = new Progress(cashOnHand, budgetAmount);
    }
    public void deleteBudget() {
        budget.setActive(false);
    }

    public Progress getBudgetProgress() {
        return this.budgetProgress;
    }

    public double getMinimumBudgetPercentageThreshold() {
        return MINIMUM_BUDGET_PERCENTAGE_THRESHOLD;
    }

    /**
     * Manages the situation in which the user has less than 25% of his budget left (still surplus).
     */
    public void printBasicWarning() {
        assert budget.isActive();
        String[] texts = {"Hey watch out you do not have that much cash left over according to your budget."
                , "Try earning some money before making any crazy expenses!"};
        Ui.printMultipleText(texts);
    }

    /**
     * Manages the situation in which the user spent all of his budget (or even more and is now running a deficit).
     */
    public void printSeriousWarning() {
        double budgetDeficit = (budget.getBudget() - this.financialStatement.getNetCash());
        assert budget.isActive();
        String[] texts = new String[]{"Hey watch out you are currently below your budget by: "
                + budgetDeficit, "Need some financial advise? Stop spending so much!"};
        Ui.printMultipleText(texts);
    }

    /**
     * Prints an overview of the budget situation containing some text, the net
     * cash on hand and a progress bar indicating how much money is available in terms
     * of budgeting.
     * A full bar chart means that the user is doing well and has not used up his budget yet
     * as he/she has more cash on hand than the set budget (meaning that income is larger than the expenses)
     * An empty bar chart implies that the user has used up all his budget and may now find himself in a deficit.
     * @throws CashLehBudgetException when user has not set a budget yet.
     */
    public void printBudget() throws CashLehBudgetException {
        setBudgetPercentage();
        assert budgetProgress != null;
        String[] texts = {budget.toString(), "Here's a quick view of how you're doing so far:", "You have a net "
                + "cash on hand of: " + financialStatement.getNetCash(), "You still have the following"
                + " percent of your budget left:\n", budgetProgress.displayProgressBar()};
        if (budget.isActive()) {
            boolean budgetHasBeenMaxedOut = budgetProgress.getProgress() == 0;
            boolean budgetIsNotOnTrack = (budgetProgress.getProgress() < MINIMUM_BUDGET_PERCENTAGE_THRESHOLD
                    && budgetProgress.getProgress() < MAXIMUM_BUDGET_PERCENTAGE);
            if (budgetHasBeenMaxedOut) {
                printSeriousWarning();
            } else if (budgetIsNotOnTrack) {
                printBasicWarning();
            }
            Ui.printMultipleText(texts);
        } else {
            String text = "Please create a new budget as you haven't"
                    + " set one yet or deleted the previous one.";
            throw new CashLehBudgetException(text);
        }
    }
}
