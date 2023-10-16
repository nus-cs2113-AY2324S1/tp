package fittrack.parser;

import fittrack.command.AddMealCommand;
import fittrack.command.AddWorkCommand;
import fittrack.command.CheckDailyCalorieSurplusLimitCommand;
import fittrack.command.CheckHeightCommand;
import fittrack.command.CheckWeightCommand;
import fittrack.command.Command;
import fittrack.command.DeleteMealCommand;
import fittrack.command.DeleteWorkCommand;
import fittrack.command.EditProfileCommand;
import fittrack.command.ExitCommand;
import fittrack.command.HelpCommand;
import fittrack.command.InvalidCommand;
import fittrack.command.ListMealsCommand;
import fittrack.command.ListWorkoutCommand;
import fittrack.command.SetDailyCalorieSurplusLimitCommand;


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
            "h/(?<height>\\S+)\\s+w/(?<weight>\\S+)"
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
        case AddMealCommand.COMMAND_WORD:
            return new AddMealCommand();
        case DeleteMealCommand.COMMAND_WORD:
            return new DeleteMealCommand();
        case AddWorkCommand.COMMAND_WORD:
            return new AddWorkCommand();
        case DeleteWorkCommand.COMMAND_WORD:
            return new DeleteWorkCommand();
        case CheckHeightCommand.COMMAND_WORD:
            return new CheckHeightCommand();
        case CheckWeightCommand.COMMAND_WORD:
            return new CheckWeightCommand();
        case CheckDailyCalorieSurplusLimitCommand.COMMAND_WORD:
            return new CheckDailyCalorieSurplusLimitCommand();
        case ListWorkoutCommand.COMMAND_WORD:
            return new ListWorkoutCommand();
        case SetDailyCalorieSurplusLimitCommand.COMMAND_WORD:
            return new SetDailyCalorieSurplusLimitCommand();
        case ListMealsCommand.COMMAND_WORD:
            return new ListMealsCommand();
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

    public double[] parseProfile(String profile) throws PatternMatchFailException, NumberFormatException {
        final Matcher matcher = PROFILE_PATTERN.matcher(profile);
        if (!matcher.matches()) {
            throw new PatternMatchFailException();
        }

        final String height = matcher.group("height");
        final String weight = matcher.group("weight");

        return new double[]{ Double.parseDouble(height), Double.parseDouble(weight) };
    }


    public String getFirstWord(String str) {
        assert str != null && !str.isEmpty();
        return str.split("\\s")[0];
    }
}
