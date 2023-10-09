package fittrack.parser;

import fittrack.command.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {
    private static final Pattern COMMAND_FORMAT = Pattern.compile(
            "(?<word>\\S+)(?<args>.*)"
    );

    public Command parseCommand(String userCommandLine) {
        final Matcher matcher = COMMAND_FORMAT.matcher(userCommandLine.strip());
        if (!matcher.matches()) {
            //
        }
        final String word = matcher.group("word");
        final String args = matcher.group("args");

        switch (word) {
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
        case SetCalorieSurplusLimitCommand.COMMAND_WORD:
            return new SetCalorieSurplusLimitCommand();
        case CheckHeightCommand.COMMAND_WORD:
            return new CheckHeightCommand();
        case CheckWeightCommand.COMMAND_WORD:
            return new CheckWeightCommand();
        case CheckCalorieSurplusLimitCommand.COMMAND_WORD:
            return new CheckCalorieSurplusLimitCommand();
        case ListWorkoutCommand.COMMAND_WORD:
            return new ListWorkoutCommand();
        case SetDailyCalorieLimitCommand.COMMAND_WORD:
            return new SetDailyCalorieLimitCommand();
        default:
            return new HelpCommand();
        }
    }
}
