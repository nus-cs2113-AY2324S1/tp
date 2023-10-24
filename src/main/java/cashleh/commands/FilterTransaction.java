package cashleh.commands;

import cashleh.exceptions.CashLehMissingTransactionException;
import cashleh.parser.FindParser;
import cashleh.transaction.ExpenseStatement;
import cashleh.transaction.FinancialStatement;
import cashleh.transaction.IncomeStatement;

public class FilterTransaction extends Command{
    private final FindParser transactionToFind;
    private final FinancialStatement financialStatement;


    public FilterTransaction(FindParser transactionToFind, ExpenseStatement expenseStatement, IncomeStatement incomeStatement) {
        this.transactionToFind = transactionToFind;
        this.financialStatement = new FinancialStatement(incomeStatement, expenseStatement);
    }
    @Override
    public void execute() throws CashLehMissingTransactionException {
        try {
            financialStatement.findTransaction(transactionToFind.getDescription(), transactionToFind.getAmount());
        } catch (CashLehMissingTransactionException e) {
            throw new CashLehMissingTransactionException();
        }
    }
}
