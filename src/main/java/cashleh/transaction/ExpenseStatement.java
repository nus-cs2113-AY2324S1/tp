package cashleh.transaction;

import cashleh.Ui;
import cashleh.exceptions.CashLehMissingTransactionException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Represents an Expense Statement in the CashLeh application.
 * This class is used to manage and store a list of expenses.
 * It provides methods for adding, deleting, retrieving, and displaying expenses within the statement.
 */
public class ExpenseStatement {

    private final ArrayList<Expense> expenseStatement = new ArrayList<>();

    public ExpenseStatement() {
    }

    public ExpenseStatement(Expense... expenses) {
        expenseStatement.addAll(List.of(expenses));
    }

    public int getSize () {
        return expenseStatement.size();
    }

    /**
     * Adds an expense to the statement.
     * @param expenseToAdd The expense to be added.
     */
    public void addExpense(Expense expenseToAdd) {
        expenseStatement.add(expenseToAdd);
    }

    /**
     * Deletes an expense from the statement.
     * @param expenseIndex The index of the expense to be deleted.
     * @throws CashLehMissingTransactionException If the specified expense is not found.
     */
    public void deleteExpense(int expenseIndex) throws CashLehMissingTransactionException {
        try {
            expenseStatement.remove(expenseIndex);
        } catch(IndexOutOfBoundsException e) {
            throw new CashLehMissingTransactionException();
        }
    }

    /**
     * Retrieve expense from the statement.
     * @param expenseIndex The index of the expense to be retrieved.
     * @return The expense at the specified index.
     * @throws CashLehMissingTransactionException If the specified expense is not found.
     */
    public Expense getExpense(int expenseIndex) throws CashLehMissingTransactionException {
        try {
            return expenseStatement.get(expenseIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new CashLehMissingTransactionException();
        }
    }

    public ArrayList<Expense> getExpenseStatement() {
        return this.expenseStatement;
    }
    
    public int getNumberOfExpenses() {
        return expenseStatement.size();
    }

    public double getTotalExpenseAmount() {
        return expenseStatement.stream().
                mapToDouble(Expense::getAmount).sum();
    }

    /**
     * Prints the expense statement, displaying details of all expense transactions.
     * This method generates a formatted expense statement based on the transactions in the expenseStatement list.
     * It creates a textual representation of each expense transaction, including its type (Expense), date, description,
     * amount, and category (if available), and then uses the Ui.printStatement method to display the statement.
     */
    public void printExpenses() {
        int listSize = expenseStatement.size();
        String[] texts = new String[listSize];
        for (int i = 0; i < listSize; i++) {
            Expense currentExpense = expenseStatement.get(i);
            String type = "Expense, ";
            String date = currentExpense.getDate().toString();
            String amt = String.valueOf(currentExpense.getAmount());
            String cat = currentExpense.getCategory() == null ? "-" : currentExpense.getCategory().toString();
            texts[i] = type + date + ", " + currentExpense.getDescription() + ", " + amt + ", " + cat;
        }
        Ui.printStatement("Expense Statement", texts);
    }

    @Override
    public String toString() {
        return expenseStatement.stream().map(Expense::toString).collect(Collectors.joining("\n"));
    }
}
