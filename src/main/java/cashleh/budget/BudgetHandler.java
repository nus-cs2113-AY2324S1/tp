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
        double cashOnHand = this.financialStatement.getCashOnHand();
        this.budgetProgress = new Progress(cashOnHand, budgetAmount);
    }
    public void deleteBudget() {
        budget.setActive(false);
    }

    /**
     * Manages the situation in which the user spent more than 75% of its budget.
     * @throws CashLehBudgetException warning the user about his bad financial situation.
     */
    public void printBasicWarning() throws CashLehBudgetException {
        boolean budgetIsNotOnTrack = (budgetProgress.getProgress() < 0.25 && budgetProgress.getProgress() < 1);
        if (budget.isActive() && budgetIsNotOnTrack) {
            String text = "Hey watch out you do not have that much cash left over according to your budget."
                    + "\n\tTry earning some money before making any crazy expenses!";
            throw new CashLehBudgetException(text);
        }
    }

    /**
     * Manages the situation in which the user spent all of his budget.
     * @throws CashLehBudgetException when the user has a budget deficit.
     */
    public void printSeriousWarning() throws CashLehBudgetException {
        boolean budgetHasBeenMaxedOut = budgetProgress.getProgress() == 0;
        double budgetDeficit = (budget.getBudget() - this.financialStatement.getCashOnHand());
        if (budget.isActive() && budgetHasBeenMaxedOut) {
            String text = "Hey watch out you are currently below your budget by: "
                    + budgetDeficit + "\n\tNeed some financial advise? Stop spending so much!";
            throw new CashLehBudgetException(text);
        }
    }

    /**
     * Prints an overview of the budget situation containing some text, the net
     * cash on hand and a progress bar indicating how much money is available in terms
     * of budgeting.
     * An empty bar chart means that the user is doing well and has not used up his budget yet
     * as he/she has more cash on hand than the set budget (meaning that income is larger than the expenses)
     * A full bar chart implies that the user has used up all his budget and may now find himself in a deficit.
     * @throws CashLehBudgetException when the user's finances reach a certain threshold.
     */
    public void printBudget() throws CashLehBudgetException {
        setBudgetPercentage();
        assert budgetProgress != null;
        String[] texts = {budget.toString(), "Here's a quick view of how you're doing so far:", "You have a net "
                + "cash on hand of: " + financialStatement.getCashOnHand(), "You still have the following"
                + " percent of your budget left:\n", budgetProgress.displayProgressBar()};
        if (budget.isActive()) {
            printSeriousWarning();
            printBasicWarning();
            Ui.printMultipleText(texts);
        } else {
            Ui.printText("Please create a new budget as you haven't"
                    + " set one yet or deleted the previous one.");
        }
    }
}
