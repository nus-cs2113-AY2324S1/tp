package cashleh.commands;

import cashleh.transaction.Income;
import cashleh.transaction.IncomeStatement;
import cashleh.Ui;

import java.util.logging.Level;

public class AddIncome extends Command {
    private final Income incomeToAdd;
    private final IncomeStatement incomeStatement;

    /**
     * Constructs an AddIncome object with the specified parameters.
     * @param incomeToAdd     The income to be added to the income statement.
     * @param incomeStatement Income statement to which the income will be added.
     */
    public AddIncome(Income incomeToAdd, IncomeStatement incomeStatement) {
        this.incomeToAdd = incomeToAdd;
        this.incomeStatement = incomeStatement;
    }
    /**
     * Executes the command to add an income to the income statement.
     * This method adds the specified income to the income statement,
     * prints a message indicating the successful addition,
     * and logs the operation at the INFO level.
     */
    @Override
    public void execute() {
        incomeStatement.addIncome(incomeToAdd);
        Ui.printMultipleText(new String[] {"The following income was added:", incomeToAdd.toString()});
        logger.log(Level.INFO, "income entry was successfully added to the income statement");
    }
}
