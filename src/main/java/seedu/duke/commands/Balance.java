package seedu.duke.commands;

import seedu.duke.financialrecords.Expense;
import seedu.duke.financialrecords.Income;

import java.util.ArrayList;

public class Balance {
    private final ArrayList<Income> incomes;
    private final ArrayList<Expense> expenses;

    public Balance(ArrayList<Income> incomes, ArrayList<Expense> expenses) {
        this.incomes = incomes;
        this.expenses = expenses;
    }
    public double getBalance() {
        double total_incomes = 0;
        double total_expenses = 0;
        for (Income income : incomes) {
            total_incomes += income.getAmount();
        }
        for (Expense expense : expenses) {
            total_expenses += expense.getAmount();
        }
        return total_incomes + total_expenses;
    }

    public void getBalanceMessage() {
        System.out.println("Balance: " + getBalance());
    }
}
