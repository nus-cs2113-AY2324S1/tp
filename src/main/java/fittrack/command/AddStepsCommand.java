package fittrack.command;

import fittrack.data.Step;
import fittrack.parser.CommandParser;
import fittrack.parser.ParseException;

public class AddStepsCommand extends Command{
    public static final String COMMAND_WORD = "addsteps";
    private static final String DESCRIPTION =
            String.format("`%s` shows your total number of steps on a specific date.", COMMAND_WORD);
    private static final String USAGE = String.format(
            "Type `%s <DATE>` to see the total steps walked on that date.\n" +
                    "You should type <DATE> in format of `yyyy-MM-dd`.",
            COMMAND_WORD
    );
    public static final String HELP = DESCRIPTION + "\n" + USAGE;
    private final CommandParser parser = new CommandParser();
    private Step newStep;

    public AddStepsCommand(String commandLine) {
        super(commandLine);
    }

    /**
     * Execute the command.
     *
     * @return result of the execution
     */
    @Override
    public CommandResult execute() {
        stepList.addToList(newStep);
        return new CommandResult("I've added the following steps:" + "\n" + newStep.toString());
    }

    /**
     * Apply arguments to its field using parser.
     *
     * @param args   arguments as a string
     * @throws ParseException if parse fails
     */
    @Override
    public void setArguments(String args) throws ParseException {
        newStep = parser.parseStep(args);
    }

    public Step getStep(){
        return newStep;
    }
    /**
     * Returns help of the command.
     *
     * @return help
     */
    @Override
    protected String getHelp() {
        return null;
    }
}
