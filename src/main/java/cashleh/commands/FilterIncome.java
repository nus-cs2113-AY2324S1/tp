package cashleh.commands;

import cashleh.exceptions.CashLehMissingTransactionException;
import cashleh.parser.FindParser;
import cashleh.transaction.IncomeStatement;

import java.util.logging.Level;

public class FilterIncome extends Command{
    private final FindParser incomeToFind;
    private final IncomeStatement incomeStatement;

    public FilterIncome(FindParser incomeToFind, IncomeStatement incomeStatement) {
        this.incomeToFind = incomeToFind;
        this.incomeStatement = incomeStatement;
    }
    @Override
    public void execute() throws CashLehMissingTransactionException {
        try {
            incomeStatement.findIncome(incomeToFind.getDescription(), incomeToFind.getAmount(), incomeToFind.getDate());
            logger.log(Level.INFO, "income entry was successfully filtered");
        } catch (CashLehMissingTransactionException e) {
            throw new CashLehMissingTransactionException();
        }
    }
}
