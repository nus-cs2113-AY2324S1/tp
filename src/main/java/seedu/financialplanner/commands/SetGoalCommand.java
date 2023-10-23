package seedu.financialplanner.commands;
import seedu.financialplanner.goal.Goal;
import seedu.financialplanner.goal.GoalList;
import seedu.financialplanner.utils.Ui;

public class SetGoalCommand extends AbstractCommand{
    private final String goal;
    private int amount;
    public SetGoalCommand(RawCommand rawCommand) throws IllegalArgumentException {
        String labelString = String.join(" ", rawCommand.args);
        if(!rawCommand.extraArgs.containsKey("g")){
            throw new IllegalArgumentException("Goal must have an amount");
        }
        try{
            amount = Integer.parseInt(rawCommand.extraArgs.get("g"));
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Amount must be a number");
        }
        rawCommand.extraArgs.remove("g");
        if(!rawCommand.extraArgs.containsKey("l")){
            throw new IllegalArgumentException("Please specify the content of the goal");
        }
        goal = rawCommand.extraArgs.get("l");
        rawCommand.extraArgs.remove("l");
        if(!rawCommand.extraArgs.isEmpty()){
            String unknownExtraArgument = new java.util.ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }

    @Override
    public void execute() {
        GoalList.INSTANCE.list.add(new Goal(goal, amount));
        Ui.getInstance().showMessage("Set Goal Successfully!");
    }
}
