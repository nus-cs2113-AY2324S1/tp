package cashleh.commands;

import cashleh.CashLehException;

public class ViewIncomes extends Command {
    public static final String COMMAND = "viewIncomes";
    public ViewIncomes() throws CashLehException {
        if (incomeStatement.getNumberOfEntries() == 0) {
            throw new CashLehException("You did not add any entries yet. "
                    + "Please procede by adding some incomes to view your income statement.");
        }
    }
    @Override
    public void execute() {
        assert getIncomeStatement() != null;
        System.out.println("The current sum of all your incomes amounts to: "
                + getIncomeSum() + System.lineSeparator() + getIncomeStatement());
    }
}
