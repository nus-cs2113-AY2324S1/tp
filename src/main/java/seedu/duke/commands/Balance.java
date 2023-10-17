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
        double totalIncome = 0;
        double totalExpense = 0;
        for (Income income : incomes) {
            totalIncome += income.getAmount();
        }
        for (Expense expense : expenses) {
            totalExpense += expense.getAmount();
        }
        return totalIncome + totalExpense;
    }

    public void getBalanceMessage() {
        System.out.println("Balance: " + getBalance());
    }
}
