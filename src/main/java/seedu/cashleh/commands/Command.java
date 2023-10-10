package seedu.cashleh.commands;

import seedu.cashleh.Income;
import seedu.cashleh.IncomeStatement;

public class Command {
    protected IncomeStatement incomeStatement;
    private int index;
    public Command(int index) {
        this.index = index;
    }
    public Command() {
        //throw new UnsupportedOperationException("A generic command is not executable. Please define a subclass.");
    }
    public int getIndex() {
        return this.index;
    }
    public Income getIncome() {
        return incomeStatement.get(getIndex());
    }
    public IncomeStatement getIncomeStatement() {
        return incomeStatement;
    }
    public double getIncomeSum() {
        return incomeStatement.getSumOfEntries();
    }
    public void execute() {}
    public void setIncomeStatement(IncomeStatement incomeStatement) {
        this.incomeStatement = incomeStatement;
    }
}
