package cashleh.commands;
import cashleh.transaction.Expense;
import cashleh.transaction.ExpenseStatement;
import cashleh.Ui;

public class AddExpense extends Command {
    private final Expense expenseToAdd;
    private final ExpenseStatement expenseStatement;

    public AddExpense(Expense expenseToAdd, ExpenseStatement expenseStatement) {
        this.expenseToAdd = expenseToAdd;
        this.expenseStatement = expenseStatement;
    }

    @Override
    public void execute() {
        expenseStatement.addExpense(expenseToAdd);
        Ui.printMultipleText(new String[] {"The following expense was added:", expenseToAdd.toString()});
    }
}