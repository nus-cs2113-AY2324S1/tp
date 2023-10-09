package seedu.cashleh.commands;

public class ViewIncome extends Command {
    public static final String COMMAND = "viewIncome";
    public ViewIncome(int incomeIndexToView) {
        super(incomeIndexToView);
    }
    @Override
    public void execute() {
        System.out.println("Here's the requested information:\n\t" + getIncome());
    }
}
