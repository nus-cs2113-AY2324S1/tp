package seedu.duke.commands.meal;

import seedu.duke.commands.CommandResult;

import java.util.List;

public class DeleteCommand extends MealCommand {
    public static final String COMMAND_WORD = "meal_delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete a meal from the existing list.\n"
            + "Example: " + COMMAND_WORD + " 2";
    private static final int[] validArgumentAmounts = new int[] { 1 };
    private final int index;

    public DeleteCommand(List<String> arguments) throws Exception {
        checkArgument(arguments, validArgumentAmounts);
        index = Integer.parseInt(arguments.get(0)) - 1;
        if (index <= 0) {
            throw new Exception("Invalid index!");
        }
    }

    @Override
    public CommandResult execute() throws Exception {
        if (meals.size() <= index) {
            return new CommandResult("Exceeded index!");
        }
        CommandResult result = new CommandResult(
                "Successfully delete meal at index " + index + "!\n" + meals.get(index));
        meals.remove(index);
        return result;
    }
}
