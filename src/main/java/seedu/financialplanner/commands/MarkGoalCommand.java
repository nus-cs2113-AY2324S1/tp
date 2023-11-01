package seedu.financialplanner.commands;

import seedu.financialplanner.goal.WishList;
import seedu.financialplanner.utils.Ui;
import seedu.financialplanner.cashflow.CashflowList;
import seedu.financialplanner.goal.Goal;
import seedu.financialplanner.enumerations.ExpenseType;
public class MarkGoalCommand extends Command {
    private final int index;
    public MarkGoalCommand(RawCommand rawCommand) throws IllegalArgumentException {
        String stringIndex;
        if (rawCommand.args.size() == 1) {
            stringIndex = rawCommand.args.get(0);
        } else {
            throw new IllegalArgumentException("Incorrect arguments.");
        }

        try {
            index = Integer.parseInt(stringIndex);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Index must be an integer");
        }
        if (index == 0) {
            throw new IllegalArgumentException("Index must be within the list");
        }
        if (index > WishList.getInstance().list.size()+1){
            throw new IllegalArgumentException("Index exceed the list size");
        }
        rawCommand.extraArgs.remove("i");
        if(!rawCommand.extraArgs.isEmpty()){
            String unknownExtraArgument = new java.util.ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }

    @Override
    public void execute() {
        Goal goal = WishList.getInstance().list.get(index-1);
        goal.markAsDone();
        Ui.getInstance().showMessage("You have achieved " + goal + System.lineSeparator() + "Congratulations!");
        CashflowList.getInstance().addExpense(goal.getAmount(), ExpenseType.OTHERS, 0, goal.getLabel());
    }
}
