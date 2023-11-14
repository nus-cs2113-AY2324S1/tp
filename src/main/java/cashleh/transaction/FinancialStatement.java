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
 * Represents a Financial Statement in the CashLeh application.
 * This class is used to manage and store a list of incomes and expenses which it fetches
 * from the income and expense statements.
 * It provides methods for retrieving and displaying transactions within the statement
 * as well as a method to calculate net cash on hand.
 */
public class FinancialStatement {
    private static final int MESSAGE_START_INDEX = 0;
    private static final int MESSAGE_END_INDEX = 45;
    private static final String SEPARATOR = " ||";
    private ArrayList<Transaction> financialStatement = new ArrayList<>();
    private IncomeStatement incomeStatement;
    private ExpenseStatement expenseStatement;

    public FinancialStatement(IncomeStatement incomeStatement, ExpenseStatement expenseStatement) {
        this.incomeStatement = incomeStatement;
        this.expenseStatement = expenseStatement;
        this.financialStatement.addAll(incomeStatement.getIncomeStatement());
        this.financialStatement.addAll(expenseStatement.getExpenseStatement());
    }

    public int getSize() {
        return financialStatement.size();
    }

    /**
     * Calculates the net cash on hand which can be interpreted as the sum of
     * all incomes minus the sum of all expenses.
     * @return cash on hand as double.
     */
    public double getNetCash() {
        return incomeStatement.getTotalIncomeAmount() - expenseStatement.getTotalExpenseAmount();
    }

    /**
     * Prints the financial statement, including both income and expense transactions.
     * This method generates a formatted financial statement based on the transactions
     * in the financialStatement list. It creates a textual representation of each
     * transaction, including its type (Income or Expense), date, description, amount,
     * and category (if available), and then uses the Ui.printStatement method to display the statement.
     */
    public void printTransactions() {
        int listSize = financialStatement.size();
        List<String []> transactionDetails = new ArrayList<>();

        for (Transaction currentTransaction : financialStatement) {
            String type = (currentTransaction instanceof Income) ? "Income" : "Expense";
            String date = currentTransaction.getDate().toString();
            String amt = String.valueOf(currentTransaction.getAmount());
            String cat = currentTransaction.getCategory() == null ? "-" : currentTransaction.getCategory().toString();
            transactionDetails.add(new String[]{type, date, currentTransaction.getDescription(), amt, cat});
        }

        // Sort transactions based on the date of transaction
        Collections.sort(transactionDetails, (transaction1, transaction2) -> {
            String date1 = transaction1[1];
            String date2 = transaction2[1];
            return date1.compareTo(date2);
        });

        String[][] texts = transactionDetails.toArray(new String[0][]);

        Ui.printStatement("Financial Statement", texts);
    }

    /**
     * Finds and displays transactions that match the specified criteria,
     * including description, amount, date, and category.
     * @param description The description to filter transactions by. Can be left null or empty.
     * @param amount The amount to filter transactions by. Set to -1 if no amount is provided by user.
     * @param date The date to filter transactions by. Set to null if no date is provided by user.
     * @param category The category to filter transactions by. Set to null if no category is provided by user
     * @throws CashLehMissingTransactionException if no matching transactions are found.
     */
    public void findTransaction(String description, OptionalDouble amount, LocalDate date, Categories category)
            throws CashLehMissingTransactionException {
        ArrayList<String> matchingTransactions = new ArrayList<>();
        boolean isMatch = false;

        StringBuilder filterMessage = new StringBuilder("Here are your corresponding transactions with");
        int hasFilterCriteria = 0;
        String[] filterCriteria = { (description != null && !description.isEmpty()) ?
                " <description>: " + description : null, amount.isPresent() ?
                " <amount>: " + amount.getAsDouble() : null, (date != null) ?
                " <date>: " + date : null, (category != null) ?
                " <category>: " + category : null
        };


        for (String criterion : filterCriteria) {
            if (criterion != null) {
                if (hasFilterCriteria > 0) {
                    filterMessage.append(SEPARATOR);
                }
                filterMessage.append(criterion);
                hasFilterCriteria++;
            }
        }

        StringBuilder matchingTransactionsMessage = new StringBuilder();
        for (Transaction transaction : financialStatement) {
            boolean descriptionMatch = (description == null) || (description.isEmpty())
                    || transaction.getDescription().equals(description);
            boolean amountMatch = (amount.isEmpty()) || (transaction.getAmount() == amount.getAsDouble());
            boolean dateMatch = (date == null) || (transaction.getDate().equals(date));
            boolean categoryMatch = (category == null) ||
                    (String.valueOf(transaction.getCategory()).equals(String.valueOf(category)));
            // Determine the sign based on the type of transaction
            String sign = (transaction instanceof Income) ? "[+] " : "[-] ";
            if (descriptionMatch && amountMatch && dateMatch && categoryMatch) {
                matchingTransactionsMessage.append(sign).append(transaction.toString()).append("\n").append("\t");
                isMatch = true;
            }
        }
        if (isMatch) {
            //Add initial filter message
            matchingTransactions.add(filterMessage.toString());
            //Add matching expenses
            matchingTransactions.add(matchingTransactionsMessage.toString().trim());
            Ui.printMultipleText(matchingTransactions);
        } else {
            filterMessage.replace(MESSAGE_START_INDEX, MESSAGE_END_INDEX, "Your input is");
            matchingTransactions.add(filterMessage.toString());
            Ui.printMultipleText(matchingTransactions);
            throw new CashLehMissingTransactionException();
        }
    }

    @Override
    public String toString() {
        return financialStatement.stream().
                map(Transaction::toString).collect(Collectors.joining("\n"));
    }
}
