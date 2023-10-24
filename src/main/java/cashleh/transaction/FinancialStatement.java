package cashleh.transaction;

import cashleh.Ui;
import cashleh.exceptions.CashLehMissingTransactionException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;
/**
 * Represents a Financial Statement in the CashLeh application.
 * This class is used to manage and store a list of incomes and expenses which it fetches
 * from the income and expense statements.
 * It provides methods for retrieving and displaying transactions within the statement
 * as well as a method to calculate net cash on hand.
 */
public class FinancialStatement {
    private ArrayList<Transaction> financialStatement = new ArrayList<>();
    private IncomeStatement incomeStatement;
    private ExpenseStatement expenseStatement;

    public FinancialStatement(IncomeStatement incomeStatement, ExpenseStatement expenseStatement) {
        this.incomeStatement = incomeStatement;
        this.expenseStatement = expenseStatement;
        this.financialStatement.addAll(incomeStatement.getIncomeStatement());
        this.financialStatement.addAll(expenseStatement.getExpenseStatement());
    }

    /**
     * Retrieves the transaction from the statement.
     * @param transactionIndex The index of the transaction to be retrieved.
     * @return The transaction at the specified index.
     * @throws CashLehMissingTransactionException If the specified transaction is not found.
     */
    public Transaction getTransaction(int transactionIndex) throws CashLehMissingTransactionException {
        try {
            return financialStatement.get(transactionIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new CashLehMissingTransactionException();
        }
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
     * Displays the net cash on hand and prints each entry of
     * the financial statement on a new line, differentiating between
     * incomes and expenses by adding a '+' sign to the former and a '-'
     * sign to the latter.
     */
    public void printTransactions() {
        int listSize = financialStatement.size();
        String[] texts = new String[listSize + 1];
        texts[0] = "Your current cash on hand amounts to: " + getNetCash();
        for (int i = 1; i <= listSize; i++) {
            String sign = "";
            Transaction currentTransaction = financialStatement.get(i - 1);
            if (currentTransaction instanceof Income) {
                sign = "+";
            } else if (currentTransaction instanceof Expense) {
                sign = "-";
            }
            texts[i] = "\t" + i + ". " + sign + " " + currentTransaction.toString();
        }
        Ui.printMultipleText(texts);
    }

    public void findTransaction(String description, double amount, LocalDate date) throws CashLehMissingTransactionException {
        ArrayList<String> matchingTransactions = new ArrayList<>();
        boolean isMatch = false;
        StringBuilder message = new StringBuilder("Here are your corresponding transactions with ");
        if (description != null && !description.isEmpty()) {
            message.append("description: ").append(description);
        }
        if (amount != -1) {
            if (description != null && !description.isEmpty()) {
                message.append(" , ");
            }
            message.append("amount: ").append(amount);
        }
        if (date != null) {
            if (description != null && !description.isEmpty() || amount != -1) {
                message.append(" , ");
            }
            message.append("date: ").append(date);
        }
        matchingTransactions.add(message.toString());

        for (Transaction transaction : financialStatement) {
            boolean descriptionMatch = (description == null) || (description.isEmpty())
                    || transaction.getDescription().equals(description);
            boolean amountMatch = (amount == -1) || (transaction.getAmount() == amount);
            boolean dateMatch = (date == null) || (transaction.getDate().equals(date));
            // Determine the sign based on the type of transaction
            String sign = (transaction instanceof Income) ? "[+] " : "[-] ";
            if (descriptionMatch && amountMatch && dateMatch) {
                matchingTransactions.add(sign + transaction.toString());
                isMatch = true;
            }
        }
        if (isMatch) {
            Ui.printMultipleText(matchingTransactions);
        } else {
            throw new CashLehMissingTransactionException();
        }
    }

    @Override
    public String toString() {
        return financialStatement.stream().
                map(Transaction::toString).collect(Collectors.joining("\n"));
    }
}
