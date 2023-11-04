package cashleh.transaction;

import cashleh.Ui;
import cashleh.exceptions.CashLehMissingTransactionException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
/**
 * Represents an Expense Statement in the CashLeh application.
 * This class is used to manage and store a list of expenses.
 * It provides methods for adding, deleting, retrieving, and displaying expenses within the statement.
 */
public class ExpenseStatement {
    private static final int MESSAGE_START_INDEX = 0;
    private static final int MESSAGE_END_INDEX = 41;
    private static final String SEPARATOR = " ||";
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
        List<String> expensesDetails = new ArrayList<>();
        for (Expense currentExpense : expenseStatement) {
            String type = "Expense, ";
            String date = currentExpense.getDate().toString();
            String amt = String.valueOf(currentExpense.getAmount());
            String cat = currentExpense.getCategory() == null ? "-" : currentExpense.getCategory().toString();
            expensesDetails.add(type + date + ", " + currentExpense.getDescription() + ", " + amt + ", " + cat);
        }

        // Sort expenses based on the date of expense
        Collections.sort(expensesDetails, (expense1, expense2) -> {
            String[] expenseParts1 = expense1.split(", ");
            String [] expenseParts2 = expense2.split(", ");
            String date1 = expenseParts1[1];
            String date2 = expenseParts2[1];
            return date1.compareTo(date2);
        });

        String[] texts = expensesDetails.toArray(new String[expensesDetails.size()]);

        Ui.printStatement("Expense Statement", texts);
    }

    /**
     * Finds and displays expenses that match the specified criteria, including description, amount, date, and category.
     * @param description The description to filter transactions by. Can be left null or empty.
     * @param amount The amount to filter transactions by. Set to -1 if no amount is provided by user.
     * @param date The date to filter transactions by. Set to null if no date is provided by user.
     * @param category The category to filter transactions by. Set to null if no category is provided by user
     * @throws CashLehMissingTransactionException if no matching transactions are found.
     */
    public void findExpense(String description, OptionalDouble amount, LocalDate date, Categories category)
            throws CashLehMissingTransactionException {
        ArrayList<String> matchingExpenses = new ArrayList<>();
        boolean isMatch = false;

        // Customize the message based on input
        StringBuilder filterMessage = new StringBuilder("Here are your corresponding expenses with ");
        if (description != null && !description.isEmpty()) {
            filterMessage.append("<description>: ").append(description).append(SEPARATOR);
        }
        if (amount.isPresent()) {
            filterMessage.append("<amount>: ").append(amount.getAsDouble()).append(SEPARATOR);
        }
        if (date != null) {
            filterMessage.append("<date>: ").append(date).append(SEPARATOR);
        }
        if (category != null) {
            filterMessage.append("<category>: ").append(category).append(SEPARATOR);
        }

        StringBuilder matchingExpensesMessage = new StringBuilder();
        for (Expense expense : expenseStatement) {
            boolean descriptionMatch = (description == null) || (description.isEmpty())
                    || expense.getDescription().equals(description);
            boolean amountMatch = (amount.isEmpty()) || (expense.getAmount() == amount.getAsDouble());
            boolean dateMatch = (date == null) || (expense.getDate().equals(date));
            boolean categoryMatch = (category == null) || (expense.getCategory() != null &&
                    expense.getCategory().equals(category));
            if (descriptionMatch && amountMatch && dateMatch && categoryMatch) {
                matchingExpensesMessage.append(expense.toString()).append("\n").append("\t");
                isMatch = true;
            }
        }
        if (isMatch) {
            //Add initial filter message
            matchingExpenses.add(filterMessage.toString());
            //Add matching expenses
            matchingExpenses.add(matchingExpensesMessage.toString().trim());
            Ui.printMultipleText(matchingExpenses);
        } else {
            filterMessage.replace(MESSAGE_START_INDEX, MESSAGE_END_INDEX, "Your input is");
            matchingExpenses.add(filterMessage.toString());
            Ui.printMultipleText(matchingExpenses);
            throw new CashLehMissingTransactionException();
        }
    }

    @Override
    public String toString() {
        return expenseStatement.stream().map(Expense::toString).collect(Collectors.joining("\n"));
    }
}
