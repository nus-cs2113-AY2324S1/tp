package seedu.duke.commands.meal;

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
}
