package seedu.duke.commands.meal;

import seedu.duke.commands.CommandResult;
import seedu.duke.data.meal.Meal;
import seedu.duke.data.Date;

import java.util.List;

public class AddCommand extends MealCommand {
    public static final String COMMAND_WORD = "meal_add";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a meal and record the amount of calories.\n"
            + "Example: " + COMMAND_WORD + " potato 15";
    private static final int[] validArgumentAmounts = new int[] { 2, 3 };
    private final String name;
    private final int calories;
    private final Date time;

    public AddCommand(List<String> arguments) throws Exception {
        checkArgument(arguments, validArgumentAmounts);
        name = arguments.get(0);
        calories = Integer.parseInt(arguments.get(1));
        if (arguments.size() >= 3) {
            time = new Date(arguments.get(2));
        } else {
            time = Date.Now();
        }
    }

    @Override
    public CommandResult execute() throws Exception {
        meals.add(new Meal(name, calories, time));
        return new CommandResult("Successfully add meal " + meals.get(meals.size() - 1) + "!");
    }
}
