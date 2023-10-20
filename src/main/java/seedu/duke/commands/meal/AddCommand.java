package seedu.duke.commands.meal;

import seedu.duke.commands.CommandResult;
import seedu.duke.data.meal.Meal;

public class AddCommand extends MealCommand {
    public static final String COMMAND_WORD = "meal add";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a meal and record the amount of calories.\n"
            + "Example: " + COMMAND_WORD + " potato 15";
    private static final int validArgumentAmount = 2;
    private final String name;
    private final int calories;

    public AddCommand(String[] arguments) throws Exception {
        if (arguments == null || arguments.length != 2) {
            throw new Exception("Incorrect amount of the arguments.");
        }
        name = arguments[0];
        calories = Integer.parseInt(arguments[1]);
    }

    @Override
    public CommandResult execute() throws Exception {
        meals.add(new Meal(name, calories));
        return new CommandResult("Successfully add meal '" + name + "'(" + calories + "' calories)!");
    }
}
