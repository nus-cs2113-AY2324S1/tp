package cashleh.commands;

import cashleh.exceptions.CashLehMissingTransactionException;
import cashleh.parser.FindParser;
import cashleh.transaction.IncomeStatement;

import java.util.logging.Level;

/**
 * The FilterIncome class represents a command to filter incomes in the CashLeh application.
 * It allows users to filter income transactions based on various criteria such as description,
 * amount, date, and category.
 */
public class FilterIncome extends Command{
    private final FindParser incomeToFind;
    private final IncomeStatement incomeStatement;

    /**
     * Constructs a new FilterIncome command with the specified filtering criteria and the incomeStatement.
     * @param incomeToFind The criteria used for filtering incomes.
     * @param incomeStatement The income statement to filter incomes from.
     */
    public FilterIncome(FindParser incomeToFind, IncomeStatement incomeStatement) {
        this.incomeToFind = incomeToFind;
        this.incomeStatement = incomeStatement;
    }

    /**
     * Executes the filtering operation to find and display matching income transactions.
     * @throws CashLehMissingTransactionException if no matching transactions are found.
     */
    @Override
    public void execute() throws CashLehMissingTransactionException {
        try {
            assert incomeStatement != null;
            incomeStatement.findIncome(incomeToFind.getDescription(), incomeToFind.getAmount(),
                    incomeToFind.getDate(), incomeToFind.getCategory());
            logger.log(Level.INFO, "income entry was successfully filtered");
        } catch (CashLehMissingTransactionException e) {
            throw new CashLehMissingTransactionException();
        }
    }
}
