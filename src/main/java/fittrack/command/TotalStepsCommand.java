package fittrack.command;

import fittrack.data.Date;
import fittrack.data.Step;
import fittrack.parser.CommandParser;
import fittrack.parser.ParseException;

public class TotalStepsCommand extends Command{
    public static final String COMMAND_WORD = "totalsteps";
    private static final String DESCRIPTION = "`" + COMMAND_WORD + "` shows the total number of steps taken.";
    private static final String USAGE = "Type `%s` <DATE> to show the total number of steps walked on that date.\n."+
            "You should type <DATE> in format of `yyyy-MM-dd`.";
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    private Date date;
    private Step totalSteps;
//    private int totalSteps = 0;


    public TotalStepsCommand(String commandLine) {
        super(commandLine);
    }

    @Override
    public CommandResult execute() {
        StringBuilder feedbackBuilder = new StringBuilder();
        totalSteps = new Step(0, null);

        for (Step step: stepList.getStepList()) {
            if (date.equals(step.getDate())) {
                totalSteps = totalSteps.sum(step);
                feedbackBuilder.append(step).append("\n");
            }
        }
        return new CommandResult("Total steps taken: " + totalSteps.getSteps() + " steps");
    }

    /**
     * Apply arguments to its field using parser.
     *
     * @param args   arguments as a string
     * @throws ParseException if parse fails
     */
    @Override
    public void setArguments(String args) throws ParseException {
        date = Date.parseDate(args);
    }


    @Override
    protected String getHelp() {
        return HELP;
    }
}
