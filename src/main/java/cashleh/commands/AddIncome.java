package cashleh.commands;

import cashleh.CashLehException;
import cashleh.Income;

import java.time.LocalDate;
import java.util.logging.*;

public class AddIncome extends Command {
    public static final String COMMAND = "addIncome";
    private final Income incomeToAdd;
    public AddIncome(double amount, String description, LocalDate date) throws CashLehException {
        if (description.isEmpty() || amount < 0) {
            logger.log(Level.WARNING, "error while adding an income");
            throw new CashLehException("Invalid income. Please include description and "
                    + "amount of expense in following format:"
                    + "\"addExpense DESCRIPTION /amt AMOUNT\"");
        }
        this.incomeToAdd = new Income(amount, description, date);
    }
    public AddIncome(Income incomeToAdd) {
        this.incomeToAdd = incomeToAdd;
    }
    @Override
    public void execute() {
        assert incomeToAdd != null;
        incomeStatement.add(incomeToAdd);
        assert incomeStatement.getNumberOfEntries() > 0;
        System.out.println("The following income was added:\n" + getIncome());
        logger.log(Level.INFO, "a new income was created and added to the incomeStatement");
    }
}
