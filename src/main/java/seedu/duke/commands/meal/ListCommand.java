package seedu.duke.commands.meal;

import seedu.duke.commands.CommandResult;
import seedu.duke.data.meal.Meal;
import seedu.duke.data.DateTime;

import java.util.List;

public class ListCommand extends MealCommand {
    public static final String COMMAND_WORD = "meal_list";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List all the meals that have been recorded.\n"
            + "Example: " + COMMAND_WORD;
    private static final int[] validArgumentAmounts = new int[] { 0 };

    public ListCommand(List<String> arguments) throws Exception {
        checkArgument(arguments, validArgumentAmounts);
    }

    @Override
    public CommandResult execute() throws Exception {
        if (meals.size() == 0) {
            return new CommandResult("The meal list is empty!");
        }
        String listString = "";
        for (int i = 0; i < meals.size(); i++) {
            listString += "\n" + (i + 1) + "." + meals.get(i).toString();
        }
        return new CommandResult(
                "Here's the meal list:" + listString);
    }
}
