package cashleh.commands;

import cashleh.exceptions.CashLehMissingTransactionException;
import cashleh.parser.FindParser;
import cashleh.transaction.ExpenseStatement;
import cashleh.transaction.IncomeStatement;

public class FilterTransaction extends Command{
    private final FindParser transactionToFind;
    private final ExpenseStatement expenseStatement;
    private final IncomeStatement incomeStatement;


    public FilterTransaction(FindParser transactionToFind, ExpenseStatement expenseStatement, IncomeStatement incomeStatement) {
        this.transactionToFind = transactionToFind;
        this.expenseStatement = expenseStatement;
        this.incomeStatement = incomeStatement;
    }
    @Override
    public void execute() throws CashLehMissingTransactionException {
        try {
            expenseStatement.findTransaction(transactionToFind.getDescription(), transactionToFind.getAmount());
            incomeStatement.findTransaction(transactionToFind.getDescription(), transactionToFind.getAmount());
        } catch (CashLehMissingTransactionException e) {
            throw new CashLehMissingTransactionException();
        }
    }
}
