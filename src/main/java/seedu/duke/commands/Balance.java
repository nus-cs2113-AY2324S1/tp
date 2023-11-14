package seedu.duke.commands;

import seedu.duke.financialrecords.Expense;
import seedu.duke.financialrecords.Income;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * The `Balance` class represents a financial balance calculator
 * that calculates the balance between income and expenses.
 * It takes a list of income and expense records as input and provides methods to calculate and display the balance.
 */
public class Balance {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private final ArrayList<Income> incomes;
    private final ArrayList<Expense> expenses;

    public Balance(ArrayList<Income> incomes, ArrayList<Expense> expenses) {
        assert incomes != null : "incomes should not be null";
        assert expenses != null : "expenses should not be null";
        this.incomes = incomes;
        this.expenses = expenses;
    }

    /**
     * Calculates the financial balance by subtracting the total expenses from the total income.
     *
     * @return The calculated financial balance.
     */
    public double getBalance() {
        double totalIncome = 0;
        double totalExpense = 0;
        for (Income income : incomes) {
            totalIncome += income.getAmount();
        }
        for (Expense expense : expenses) {
            totalExpense += expense.getAmount();
        }
        return totalIncome - totalExpense;
    }

    /**
     * Prints a message to the console displaying the calculated financial balance.
     */
    public void getBalanceMessage() {
        System.out.printf("Balance: " + df.format(getBalance()));
        System.out.println();
    }
}
