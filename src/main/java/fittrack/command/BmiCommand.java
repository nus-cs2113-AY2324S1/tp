package fittrack.command;

import fittrack.parser.CommandParser;
import java.text.DecimalFormat;

public class BmiCommand extends Command {
    public static final String COMMAND_WORD = "bmi";
    private static final String DESCRIPTION =
            String.format("`%s` calculates your current BMI.", COMMAND_WORD);
    private static final String USAGE =
            String.format("Type `%s to view your BMI.", COMMAND_WORD);
    public static final String HELP = DESCRIPTION + "\n" + USAGE;
    public double bmi;
    public final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public CommandResult execute() {
        double heightInMetres = userProfile.getHeight() / 100;
        bmi = userProfile.getWeight() / Math.pow(heightInMetres, 2);
        return new CommandResult("Your current BMI is " + df.format(bmi));
    }

    @Override
    public void setArguments(String args, CommandParser parser) {
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
