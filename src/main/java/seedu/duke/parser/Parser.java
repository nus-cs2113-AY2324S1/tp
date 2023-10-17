package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.ListIngredientCommand;
import seedu.duke.command.ListMenuCommand;
import seedu.duke.command.AddDishCommand;
import seedu.duke.command.DeleteDishCommand;
import seedu.duke.command.IncorrectCommand;

import javax.swing.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse everything received from the users on terminal
 * into a format that can be interpreted by other core classes
 */
public class Parser {
    public static final Pattern COMMAND_ARGUMENT_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    // Command Argument Patterns
    private static final String ADD_ARGUMENT_STRING = "name/([A-Za-z]+) price/(\\d+(\\.\\d+)?) (ingredient/[A-Za-z]+ qty/(\\d+(\\.\\d+)?)(?:, )?)+";
    private static final String LIST_INGREDIENTS_ARGUMENT_STRING = "(\\d+)";
    private static final String DELETE_ARGUMENT_STRING = "(\\d+)";
    private static final String EDIT_PRICE_ARGUMENT_STRING = "index/(\\d+) price/(\\d+(\\.\\d+)?)";

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
