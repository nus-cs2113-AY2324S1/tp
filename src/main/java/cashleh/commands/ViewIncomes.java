package cashleh.commands;

public class ViewIncomes extends Command {
    public static final String COMMAND = "viewIncomes";
    public ViewIncomes(int incomeIndexToView) {
        super(incomeIndexToView);
    }
    @Override
    public void execute() {
        System.out.println("The current sum of all your incomes amounts to: "
                + getIncomeSum() + System.lineSeparator() + getIncomeStatement());
    }
}
