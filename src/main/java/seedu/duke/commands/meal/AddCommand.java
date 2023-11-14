package seedu.duke.commands.meal;

import seedu.duke.data.Date;

import java.util.List;

public class AddCommand extends MealCommand {
    public static final String COMMAND_WORD = "meal_add";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a meal and record the amount of calories.\n"
            + "Example: " + COMMAND_WORD + " potato 15";
    private static final int[] validArgumentAmounts = new int[] { 3, 4 };
    private final String name;
    private final int calories;
    private final String category;
    private final Date time;

    public AddCommand(List<String> arguments) throws Exception {
        checkArgument(arguments, validArgumentAmounts);
        name = arguments.get(0);
        calories = Integer.parseInt(arguments.get(1));
        category = arguments.get(2);
        if (arguments.size() >= 4) {
            time = new Date(arguments.get(3), false);
        } else {
            time = Date.now();
        }
    }
}
