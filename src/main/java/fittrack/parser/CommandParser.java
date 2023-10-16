package fittrack.parser;

import fittrack.UserProfile;
import fittrack.command.AddMealCommand;
import fittrack.command.AddWorkoutCommand;
import fittrack.command.Command;
import fittrack.command.DeleteMealCommand;
import fittrack.command.DeleteWorkoutCommand;
import fittrack.command.EditProfileCommand;
import fittrack.command.ExitCommand;
import fittrack.command.HelpCommand;
import fittrack.command.InvalidCommand;
import fittrack.command.ViewDailyCalorieSurplusLimitCommand;
import fittrack.command.ViewHeightCommand;
import fittrack.command.ViewMealsCommand;
import fittrack.command.ViewWeightCommand;
import fittrack.command.ViewWorkoutsCommand;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {
    // This constant has to be changed whenever any command is added.
    public static final String ALL_COMMAND_WORDS =
            "help, exit, editprofile, addmeal, deletemeal, addwork, deletework, setlimit, listall, listmeals";

    private static final Pattern COMMAND_PATTERN = Pattern.compile(
            "(?<word>\\S+)(?<args>.*)"
    );
    private static final Pattern PROFILE_PATTERN = Pattern.compile(
            "h/(?<height>\\S+)\\s+w/(?<weight>\\S+)\\s+l/(?<calLimit>\\S+)"
    );

    public Command parseCommand(String userCommandLine) {
        final Matcher matcher = COMMAND_PATTERN.matcher(userCommandLine.strip());
        if (!matcher.matches()) {
            Command command = new InvalidCommand(userCommandLine);
            command.setArguments(null, this);
            return command;
        }

        final String word = matcher.group("word").strip();
        final String args = matcher.group("args").strip();

        Command command = getBlankCommand(word);
        command.setArguments(args, this);
        return command;
    }

    public Command getBlankCommand(String word) {
        switch (word) {

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case EditProfileCommand.COMMAND_WORD:
            return new EditProfileCommand();
        case ViewHeightCommand.COMMAND_WORD:
            return new ViewHeightCommand();
        case ViewWeightCommand.COMMAND_WORD:
            return new ViewWeightCommand();
        case ViewDailyCalorieSurplusLimitCommand.COMMAND_WORD:
            return new ViewDailyCalorieSurplusLimitCommand();
        case AddMealCommand.COMMAND_WORD:
            return new AddMealCommand();
        case DeleteMealCommand.COMMAND_WORD:
            return new DeleteMealCommand();
        case ViewMealsCommand.COMMAND_WORD:
            return new ViewMealsCommand();
        case AddWorkoutCommand.COMMAND_WORD:
            return new AddWorkoutCommand();
        case DeleteWorkoutCommand.COMMAND_WORD:
            return new DeleteWorkoutCommand();
        case ViewWorkoutsCommand.COMMAND_WORD:
            return new ViewWorkoutsCommand();
        default:
            return new InvalidCommand(word);
        }
    }

    /**
     * Parses user profile, format of `h/(HEIGHT) w/(WEIGHT)`.
     *
     * @param profile profile as a string
     * @return height and weight as a double array
     * @throws PatternMatchFailException if regex match fails
     * @throws NumberFormatException if one of arguments is not double
     */
    public UserProfile parseProfile(String profile) throws PatternMatchFailException, NumberFormatException {
        final Matcher matcher = PROFILE_PATTERN.matcher(profile);
        if (!matcher.matches()) {
            throw new PatternMatchFailException();
        }

        final String height = matcher.group("height");
        final String weight = matcher.group("weight");
        final String dailyCalorieSurplusLimit = matcher.group("calLimit");

        return new UserProfile(
                null,
                Double.parseDouble(height),
                Double.parseDouble(weight),
                Double.parseDouble(dailyCalorieSurplusLimit)
        );
    }

    // TODO: Make a parse method for a meal.

    // TODO: Make a parse method for a work.

    public String getFirstWord(String str) {
        assert str != null && !str.isEmpty();
        return str.split("\\s")[0];
    }
}
