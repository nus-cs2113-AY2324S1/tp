package fittrack.command;

import fittrack.parser.CommandParser;

public class ViewProfileCommand extends Command {
    public static final String COMMAND_WORD = "viewprofile";
    private static final String DESCRIPTION =
            String.format("`%s` shows all profile details.", COMMAND_WORD);
    private static final String USAGE =
            String.format("Type `%s` to view your profile.", COMMAND_WORD);
    private static final String HELP = DESCRIPTION + "\n" + USAGE;

    @Override
    public CommandResult execute() {
        // TODO: get profile details and make them to lines of strings.
        return new CommandResult("Your Profile:\n" + userProfile.toString());
    }

    @Override
    public void setArguments(String args, CommandParser parser) {
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
