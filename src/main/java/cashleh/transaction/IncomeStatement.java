package cashleh.transaction;

import cashleh.exceptions.CashLehMissingTransactionException;
import cashleh.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
/**
 * Represents an Income Statement in the CashLeh application.
 * This class is used to manage and store a list of incomes.
 * It provides methods for adding, deleting, retrieving, and displaying incomes within the statement.
 */
public class IncomeStatement {

    private final ArrayList<Income> incomeStatement = new ArrayList<>();

    public IncomeStatement() {}

    public IncomeStatement(Income... incomes) {
        incomeStatement.addAll(List.of(incomes));
    }

    public int getSize() {
        return incomeStatement.size();
    }

    /**
     * Adds an income to the statement.
     * @param incomeToAdd The income to be added.
     */
    public void addIncome(Income incomeToAdd) {
        incomeStatement.add(incomeToAdd);
    }

    /**
     * Deletes an income from the statement.
     * @param incomeIndex The index of the income to be deleted.
     * @throws CashLehMissingTransactionException If the specified income is not found.
     */
    public void deleteIncome(int incomeIndex) throws CashLehMissingTransactionException {
        try {
            incomeStatement.remove(incomeIndex);
        } catch(IndexOutOfBoundsException e) {
            throw new CashLehMissingTransactionException();
        }
    }

    /**
     * Retrieve income from the statement.
     * @param incomeIndex The index of the income to be retrieved.
     * @return The income at the specified index.
     * @throws CashLehMissingTransactionException If the specified income is not found.
     */
    public Income getIncome(int incomeIndex) throws CashLehMissingTransactionException {
        try {
            return incomeStatement.get(incomeIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new CashLehMissingTransactionException();
        }
    }

    public ArrayList<Income> getIncomeStatement() {
        return this.incomeStatement;
    }

    public int getNumberOfIncomes() {
        return incomeStatement.size();
    }

    public double getTotalIncomeAmount() {
        return incomeStatement.stream().
                mapToDouble(Income::getAmount).sum();
    }

    /**
     * Prints the income statement, displaying details of all income transactions.
     * This method generates a formatted income statement based on the transactions in the incomeStatement list.
     * It creates a textual representation of each income transaction, including its type (Income), date, description,
     * amount, and category (if available), and then uses the Ui.printStatement method to display the statement.
     */
    public void printIncomes() {
        int listSize = incomeStatement.size();
        String[] texts = new String[listSize];
        for (int i = 0; i < listSize; i++) {
            Income currentIncome = incomeStatement.get(i);
            String type = "Income, ";
            String date = currentIncome.getDate().toString();
            String amt = String.valueOf(currentIncome.getAmount());
            String cat = currentIncome.getCategory() == null ? "-" : currentIncome.getCategory().toString();
            texts[i] = type + date + ", " + currentIncome.getDescription() + ", " + amt + ", " + cat;
        }
        Ui.printStatement("Income Statement", texts);
    }

    /**
     * Finds and displays incomes that match the specified criteria, including description, amount, date, and category.
     * @param description The description to filter transactions by. Can be left null or empty.
     * @param amount The amount to filter transactions by. Set to -1 if no amount is provided by user.
     * @param date The date to filter transactions by. Set to null if no date is provided by user.
     * @param category The category to filter transactions by. Set to null if no category is provided by user
     * @throws CashLehMissingTransactionException if no matching transactions are found.
     */
    public void findIncome(String description, OptionalDouble amount, LocalDate date, Categories category)
            throws CashLehMissingTransactionException {
        ArrayList<String> matchingIncomes = new ArrayList<>();
        boolean isMatch = false;
        StringBuilder message = new StringBuilder("Here are your corresponding incomes with ");
        if (description != null && !description.isEmpty()) {
            message.append("<description>: ").append(description).append(" ||");
        }
        if (amount.isPresent()) {
            message.append("<amount>: ").append(amount.getAsDouble()).append(" ||");
        }
        if (date != null) {
            message.append("<date>: ").append(date).append(" ||");
        }
        if (category != null) {
            message.append("<category>: ").append(category).append(" ||");
        }
        matchingIncomes.add(message.toString());
        for (Income income : incomeStatement) {
            boolean descriptionMatch = (description == null) || (description.isEmpty())
                    || income.getDescription().equals(description);
            boolean amountMatch = (amount.isEmpty()) || (income.getAmount() == amount.getAsDouble());
            boolean dateMatch = (date == null) || (income.getDate().equals(date));
            boolean categoryMatch = (category == null) || (income.getCategory().equals(category));
            if (descriptionMatch && amountMatch && dateMatch && categoryMatch) {
                matchingIncomes.add(income.toString());
                isMatch = true;
            }
        }
        if (isMatch) {
            Ui.printMultipleText(matchingIncomes);
        } else {
            throw new CashLehMissingTransactionException();
        }
    }

    @Override
    public String toString() {
        return incomeStatement.stream().map(Income::toString).collect(Collectors.joining("\n"));
    }
}
