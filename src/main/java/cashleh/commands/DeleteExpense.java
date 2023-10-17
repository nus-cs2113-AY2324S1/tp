package cashleh.commands;
import exceptions.CashLehMissingTransactionException;
import cashleh.transaction.ExpenseStatement;
import cashleh.Ui;

public class DeleteExpense extends Command {
    private final int expenseIndex;
    private final ExpenseStatement expenseStatement;
    public DeleteExpense(int expenseIndex, ExpenseStatement expenseStatement) {
        this.expenseIndex = expenseIndex;
        this.expenseStatement = expenseStatement;
    }

    @Override
    public void execute() throws CashLehMissingTransactionException {
        try {
            String expenseBeingDeleted = expenseStatement.getExpense(expenseIndex - 1).toString();
            expenseStatement.deleteExpense(expenseIndex - 1);
            Ui.printMultipleText(new String[] {
                "Noted! CashLeh has removed the following expense:",
                expenseBeingDeleted
            });
        } catch (CashLehMissingTransactionException e) {
            throw new CashLehMissingTransactionException();
        }
    }
}
