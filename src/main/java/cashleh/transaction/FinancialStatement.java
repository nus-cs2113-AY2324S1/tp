package cashleh.transaction;

import cashleh.Ui;
import cashleh.exceptions.CashLehMissingTransactionException;

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
        String[] texts = new String[listSize];
        for (int i = 0; i < listSize; i++) {
            Transaction currentTransaction = financialStatement.get(i);
            String type = (currentTransaction instanceof Income) ? "Income, " : "Expense, ";
            String date = currentTransaction.getDate().toString();
            String amt = String.valueOf(currentTransaction.getAmount());
            String cat = currentTransaction.getCategory() == null ? "-" : currentTransaction.getCategory().toString();
            texts[i] = type + date + ", " + currentTransaction.getDescription() + ", " + amt + ", " + cat;
        }
        Ui.printStatement("Financial Statement", texts);
    }

    @Override
    public String toString() {
        return financialStatement.stream().
                map(Transaction::toString).collect(Collectors.joining("\n"));
    }
}
