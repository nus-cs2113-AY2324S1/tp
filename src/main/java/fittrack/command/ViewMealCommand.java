package fittrack.command;

import fittrack.parser.CommandParser;

public class ViewMealCommand extends Command {
    public static final String COMMAND_WORD = "viewmeal";
    private static final String DESCRIPTION =
            String.format("`%s` shows the list of all meals.", COMMAND_WORD);
    private static final String USAGE =
            String.format("Type `%s` to view the list of meals.", COMMAND_WORD);
    private static final String HELP = DESCRIPTION + "\n" + USAGE;

    @Override
    public CommandResult execute() {
        // TODO: get a list of meals and make them to lines of strings.
        return null;
    }

    @Override
    public void setArguments(String args, CommandParser parser) {
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
