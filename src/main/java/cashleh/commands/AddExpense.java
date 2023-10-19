package cashleh.commands;
import cashleh.transaction.Expense;
import cashleh.transaction.ExpenseStatement;
import cashleh.Ui;

import java.util.logging.Level;
/**
 * This class extends the Command class and is used to
 * encapsulate the action of adding an expense to the application's expense statement.
 * When executed, it adds the specified expense to the expense statement and logs the operation.
 */
public class AddExpense extends Command {
    private final Expense expenseToAdd;
    private final ExpenseStatement expenseStatement;

    /**
     * Constructs an AddExpense object with the specified parameters.
     * @param expenseToAdd     The expense to be added to the expense statement.
     * @param expenseStatement Expense statement to which the expense will be added.
     */
    public AddExpense(Expense expenseToAdd, ExpenseStatement expenseStatement) {
        this.expenseToAdd = expenseToAdd;
        this.expenseStatement = expenseStatement;
    }

    /**
     * Executes the command to add an expense to the expense statement.
     * This method adds the specified expense to the expense statement,
     * prints a message indicating the successful addition,
     * and logs the operation at the INFO level.
     */
    @Override
    public void execute() {
        expenseStatement.addExpense(expenseToAdd);
        Ui.printMultipleText(new String[] {"The following expense was added:", expenseToAdd.toString()});
        logger.log(Level.INFO, "expense entry was successfully added to the expense statement");
    }

}
