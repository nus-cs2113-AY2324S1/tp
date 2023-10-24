package cashleh.commands;

import cashleh.exceptions.CashLehMissingTransactionException;
import cashleh.parser.FindParser;
import cashleh.transaction.ExpenseStatement;
import cashleh.transaction.FinancialStatement;
import cashleh.transaction.IncomeStatement;

import java.util.logging.Level;

/**
 * The FilterTransaction class represents a command to filter expenses and incomes in the CashLeh application.
 * It allows users to filter expense and income transactions based on various criteria such as description,
 * amount, date, and category.
 */
public class FilterTransaction extends Command{
    private final FindParser transactionToFind;
    private final FinancialStatement financialStatement;

    /**
     * Constructs a new FilterTransaction command with the specified filtering criteria and the financialStatement.
     * @param transactionToFind The criteria used for filtering expenses and incomes.
     * @param expenseStatement The expense statement to filter expenses from.
     * @param incomeStatement The income statement to filter incomes from.
     */
    public FilterTransaction(FindParser transactionToFind, ExpenseStatement expenseStatement,
                             IncomeStatement incomeStatement) {
        this.transactionToFind = transactionToFind;
        this.financialStatement = new FinancialStatement(incomeStatement, expenseStatement);
    }

    /**
     * Executes the filtering operation to find and display matching expense and income transactions.
     * @throws CashLehMissingTransactionException if no matching transactions are found.
     */
    @Override
    public void execute() throws CashLehMissingTransactionException {
        try {
            assert expenseStatement != null;
            assert incomeStatement != null;
            financialStatement.findTransaction(transactionToFind.getDescription(), transactionToFind.getAmount(),
                    transactionToFind.getDate(), transactionToFind.getCategory());
            logger.log(Level.INFO, "transaction entry was successfully filtered");
        } catch (CashLehMissingTransactionException e) {
            throw new CashLehMissingTransactionException();
        }
    }
}
