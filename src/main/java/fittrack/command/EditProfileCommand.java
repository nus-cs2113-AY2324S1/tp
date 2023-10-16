package fittrack.command;

import fittrack.UserProfile;
import fittrack.parser.CommandParser;
import fittrack.parser.PatternMatchFailException;

public class EditProfileCommand extends Command {
    public static final String COMMAND_WORD = "editprofile";
    private static final String DESCRIPTION = "`" + COMMAND_WORD + "` allows you to edit your profile.";
    private static final String USAGE = "Type editprofile h/<height> w/<weight>";
    private static final String HELP = DESCRIPTION + "\n" + USAGE;
    UserProfile userProfile;

    @Override
    public CommandResult execute() {
        return new CommandResult("I've edited the following:" + "\n" + userProfile.toString());
    }

    @Override
    public void setArguments(String args, CommandParser parser) throws PatternMatchFailException {
        double[] profile;
        profile = new CommandParser().parseProfile(args);
        userProfile = new UserProfile(profile[0], profile[1]);
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
