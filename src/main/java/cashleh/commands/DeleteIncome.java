package cashleh.commands;

import exceptions.CashLehMissingTransactionException;
import cashleh.transaction.IncomeStatement;
import cashleh.Ui;

public class DeleteIncome extends Command {
    private final int incomeIndex;
    private final IncomeStatement incomeStatement;
    public DeleteIncome(int incomeIndex, IncomeStatement incomeStatement) {
        this.incomeIndex = incomeIndex;
        this.incomeStatement = incomeStatement;
    }
    @Override
    public void execute() throws CashLehMissingTransactionException {
        try {
            String incomeBeingDeleted = incomeStatement.getIncome(incomeIndex - 1).toString();
            incomeStatement.deleteIncome(incomeIndex - 1);
            Ui.printMultipleText(new String[] {
                "Noted! CashLeh has removed the following income:",
                incomeBeingDeleted
            });
        } catch (CashLehMissingTransactionException e) {
            throw new CashLehMissingTransactionException();
        }
    }
}
