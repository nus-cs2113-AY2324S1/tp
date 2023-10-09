package fittrack.parser;

import fittrack.command.AddMealCommand;
import fittrack.command.AddWorkCommand;
import fittrack.command.Command;
import fittrack.command.DeleteMealCommand;
import fittrack.command.DeleteWorkCommand;
import fittrack.command.EditProfileCommand;
import fittrack.command.ExitCommand;
import fittrack.command.HelpCommand;
import fittrack.command.SetCalorieSurplusLimitCommand;

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
        case SetCalorieSurplusLimitCommand.COMMAND_WORD:
            return new SetCalorieSurplusLimitCommand();
        default:
            return new HelpCommand();
        }
    }
}
