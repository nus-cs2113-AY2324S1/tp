package cashleh.commands;

import Exceptions.CashLehMissingTransactionException;
import cashleh.ExpenseStatement;
import cashleh.IncomeStatement;
import cashleh.Ui;

public class DeleteIncome extends Command {
    private final int incomeIndex;
    private final Ui ui = new Ui();

    public DeleteIncome(int incomeIndex) {
        this.incomeIndex = incomeIndex;
    }
    @Override
    public void execute(ExpenseStatement expenseStatement, IncomeStatement incomeStatement) throws CashLehMissingTransactionException {
        try {
            String incomeBeingDeleted = incomeStatement.getIncome(incomeIndex - 1).toString();
            incomeStatement.deleteIncome(incomeIndex - 1);
            ui.printMultipleText(new String[] {
                    "Noted! CashLeh has removed the following income:",
                    incomeBeingDeleted
            });
        } catch (CashLehMissingTransactionException e) {
            throw new CashLehMissingTransactionException();
        }
    }
}
