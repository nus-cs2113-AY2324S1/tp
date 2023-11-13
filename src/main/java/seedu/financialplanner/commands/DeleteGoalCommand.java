package seedu.financialplanner.commands;

import seedu.financialplanner.commands.utils.Command;
import seedu.financialplanner.commands.utils.RawCommand;
import seedu.financialplanner.goal.Goal;
import seedu.financialplanner.goal.WishList;
import seedu.financialplanner.utils.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("unused")
public class DeleteGoalCommand extends Command {
    public static final String NAME = "deletegoal";

    public static final String USAGE = "deletegoal <INDEX>";
    public static final String EXAMPLE = "deletegoal 1";
    private static final Logger logger = Logger.getLogger("Financial Planner Logger");
    private final int index;

    /**
     * Constructor of the command to delete a goal.
     *
     * @param rawCommand The input from the user.
     * @throws IllegalArgumentException if erroneous inputs are detected.
     */
    public DeleteGoalCommand(RawCommand rawCommand) throws IllegalArgumentException {
        String stringIndex;
        if (rawCommand.args.size() == 1) {
            stringIndex = rawCommand.args.get(0);
        } else {
            throw new IllegalArgumentException("Please specify a valid index of goal");
        }

        try {
            logger.log(Level.INFO, "Parsing index as integer");
            index = Integer.parseInt(stringIndex);
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, "Invalid argument for index");
            throw new IllegalArgumentException("Index must be a valid integer");
        }

        if (index <= 0) {
            logger.log(Level.WARNING, "Invalid value for index");
            throw new IllegalArgumentException("Index must be within the list");
        }

        if (index > WishList.getInstance().list.size()) {
            logger.log(Level.WARNING, "Invalid value for index");
            throw new IllegalArgumentException("Index exceed the list size");
        }
        rawCommand.extraArgs.remove("i");
        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new java.util.ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            logger.log(Level.WARNING, "Invalid extra arguments found");
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }

    /**
     * Executes the command to delete a goal.
     */
    @Override
    public void execute() {
        assert index > 0 && index <= WishList.getInstance().list.size();
        Goal goalToDelete = WishList.getInstance().list.get(index - 1);
        WishList.getInstance().deleteGoal(index - 1);
        Ui.getInstance().showMessage("You have deleted " + goalToDelete);
    }
}
