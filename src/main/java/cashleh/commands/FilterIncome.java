package cashleh.commands;

import cashleh.exceptions.CashLehMissingTransactionException;
import cashleh.parser.FindParser;
import cashleh.transaction.IncomeStatement;

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
            incomeStatement.findIncome(incomeToFind.getDescription(), incomeToFind.getAmount());
        } catch (CashLehMissingTransactionException e) {
            throw new CashLehMissingTransactionException();
        }
    }
}
