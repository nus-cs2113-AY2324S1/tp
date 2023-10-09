package seedu.cashleh.commands;

public class DeleteIncome extends Command {
    public static final String COMMAND = "deleteIncome";
    public DeleteIncome(int incomeIndexToDelete) {
        super(incomeIndexToDelete);
    }
    @Override
    public void execute() {
        String incomeBeingDeleted = getIncome().toString();
        incomeStatement.delete(getIndex());
        System.out.println("The following income was deleted:\n\t" + incomeBeingDeleted);
    }
}
