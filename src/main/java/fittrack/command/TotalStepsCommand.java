package fittrack.command;

import fittrack.data.Step;
import fittrack.parser.CommandParser;
import fittrack.parser.ParseException;

public class TotalStepsCommand extends Command{
    public static final String COMMAND_WORD = "totalsteps";
    private static final String DESCRIPTION = "`" + COMMAND_WORD + "` shows the total number of steps taken.";
    private static final String USAGE = "Type `totalsteps` to show the total number of steps taken.";
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    private int totalSteps = 0;
    private final CommandParser parser = new CommandParser();

    public TotalStepsCommand(String commandLine) {
        super(commandLine);
    }

    @Override
    public CommandResult execute() {
        for (Step step: stepList.getStepList()) {
            totalSteps += step.getSteps();
        }
        return new CommandResult("Total steps taken: " + totalSteps + " steps");
    }

    /**
     * Apply arguments to its field using parser.
     *
     * @param args   arguments as a string
     * @throws ParseException if parse fails
     */
    @Override
    public void setArguments(String args) throws ParseException {
    }


    @Override
    protected String getHelp() {
        return HELP;
    }
}
