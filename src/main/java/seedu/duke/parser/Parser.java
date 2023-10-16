package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.ListIngredientCommand;
import seedu.duke.command.ListMenuCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse everything received from the users on terminal
 * into a format that can be interpreted by other core classes
 */
public class Parser {
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
    if (!matcher.matches()) {
        return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
    }

    final String commandWord = matcher.group("commandWord");
    final String arguments = matcher.group("arguments");

    public Command parseCommand(String userInput) {
        switch (commandWord) {

            case AddDishCommand.COMMAND_WORD:
                return prepareAdd(arguments);

            case DeleteDishCommand.COMMAND_WORD:
                return prepareDelete(arguments);

            case EditPriceCommand.COMMAND_WORD:
                return new ClearCommand();

            case ListIngredientCommand.COMMAND_WORD:
                return prepareFind(arguments);

            case ListMenuCommand.COMMAND_WORD:
                return new ListCommand();

            default:
                return new HelpCommand();
        }
    }
}
