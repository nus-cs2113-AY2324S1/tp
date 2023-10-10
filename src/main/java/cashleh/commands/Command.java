package cashleh.commands;

import cashleh.Income;
import cashleh.IncomeStatement;

public class Command {
    protected IncomeStatement incomeStatement;
    private int index;
    public Command(int index) {
        this.index = index;
    }
    public Command() {
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
