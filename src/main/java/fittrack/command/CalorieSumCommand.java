package fittrack.command;

import fittrack.data.Meal;
import fittrack.parser.CommandParser;

public class CalorieSumCommand extends Command{
    public static final String COMMAND_WORD = "caloriesum";
    private static final String DESCRIPTION =
            String.format("`%s` calculates your total calories burned.", COMMAND_WORD);
    private static final String USAGE =
            String.format("Type `%s` to calculate your total calories burned.", COMMAND_WORD);
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    private double calorieSum = 0;

    public CalorieSumCommand(String commandLine) {
        super(commandLine);
    }

    @Override
    public CommandResult execute() {
        for (Meal m: mealList.getMealList()){
            calorieSum += m.getCalories().value;
        }
        return new CommandResult("Total Calories: " + calorieSum + "kcals");
    }

    @Override
    public void setArguments(String args, CommandParser parser) {
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
