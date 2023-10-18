package cashleh.transaction;

import cashleh.Ui;
import cashleh.exceptions.CashLehMissingTransactionException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseStatement {
    private final ArrayList<Expense> expenseStatement = new ArrayList<>();
    public ExpenseStatement() {
    }
    public ExpenseStatement(Expense... expenses) {
        expenseStatement.addAll(List.of(expenses));
    }
    public void addExpense(Expense expenseToAdd) {
        expenseStatement.add(expenseToAdd);
    }
    public void deleteExpense(int expenseIndex) throws CashLehMissingTransactionException {
        try {
            expenseStatement.remove(expenseIndex);
        } catch(IndexOutOfBoundsException e) {
            throw new CashLehMissingTransactionException();
        }
    }
    public Expense getExpense(int expenseIndex) throws CashLehMissingTransactionException {
        try {
            return expenseStatement.get(expenseIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new CashLehMissingTransactionException();
        }
    }
    public int getNumberOfExpenses() {
        return expenseStatement.size();
    }
    public double getSumOfExpenses() {
        return expenseStatement.stream().mapToDouble(Expense::getAmount).sum();
    }


    public void printExpenses() {
        int listSize = expenseStatement.size();
        String[] texts = new String[listSize + 1];
        texts[0] = "Here's a list of your current expenses:";
        for (int i = 1; i <= listSize; i++) {
            Expense currentExpense = expenseStatement.get(i - 1);
            texts[i] = "\t" + i + "." + currentExpense.toString();
        }
        Ui.printMultipleText(texts);
    }

    @Override
    public String toString() {
        return expenseStatement.stream().map(Expense::toString).collect(Collectors.joining("\n"));
    }
}
