package fittrack.command;

import fittrack.UserProfile;
import fittrack.parser.CommandParser;
import fittrack.parser.NumberFormatException;
import fittrack.parser.PatternMatchFailException;

public class EditProfileCommand extends Command {
    public static final String COMMAND_WORD = "editprofile";
    private static final String DESCRIPTION = "`" + COMMAND_WORD + "` allows you to edit your profile.";
    private static final String USAGE = "Type `" + COMMAND_WORD + "` h/<height> w/<weight>";
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    UserProfile newProfile;

    @Override
    public CommandResult execute() {
        userProfile.setHeight(newProfile.getHeight());
        userProfile.setWeight(newProfile.getWeight());
        userProfile.setDailyCalorieLimit(newProfile.getDailyCalorieLimit());
        return new CommandResult("I've edited the following:" + "\n" + userProfile.toString());
    }

    @Override
    public void setArguments(String args, CommandParser parser)
            throws PatternMatchFailException, NumberFormatException {
        newProfile = parser.parseProfile(args);
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
