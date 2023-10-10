package seedu.cashleh.commands;

import seedu.cashleh.Income;

import java.time.LocalDate;

public class AddIncome extends Command {
    public static final String COMMAND = "addIncome";
    private final Income incomeToAdd;
    public AddIncome(int amount, String description, LocalDate date) {
        this.incomeToAdd = new Income(amount, description, date);
    }
    public AddIncome(Income incomeToAdd) {
        this.incomeToAdd = incomeToAdd;
    }
    @Override
    public void execute() {
        incomeStatement.add(incomeToAdd);
        System.out.println("The following income was added:\n" + getIncome());
    }
}
