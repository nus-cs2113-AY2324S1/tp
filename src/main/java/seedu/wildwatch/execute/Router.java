//@@woodenclock
package seedu.wildwatch.execute;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.wildwatch.command.Command;
import seedu.wildwatch.command.EditCommand;
import seedu.wildwatch.command.SummaryCommand;
import seedu.wildwatch.command.ListCommand;
import seedu.wildwatch.command.AddCommand;
import seedu.wildwatch.command.ByeCommand;
import seedu.wildwatch.command.DeleteCommand;
import seedu.wildwatch.command.ExportCommand;
import seedu.wildwatch.command.FindCommand;
import seedu.wildwatch.command.HelpCommand;

import seedu.wildwatch.error.InvalidInputErrorType;
import seedu.wildwatch.exception.InvalidInputException;
import seedu.wildwatch.parser.AddCommandParser;
import seedu.wildwatch.parser.DeleteCommandParser;
import seedu.wildwatch.parser.EditCommandParser;
import seedu.wildwatch.parser.FindCommandParser;
import seedu.wildwatch.parser.ListCommandParser;
import seedu.wildwatch.parser.SummaryCommandParser;
import seedu.wildwatch.parser.ExportCommandParser;


/**
 * Identifies the input command and sends it to the corresponding parser.
 */
public class Router {
    private static final Logger LOGGER = Logger.getLogger(Router.class.getName());

    public static Command route(String input) throws InvalidInputException {

        LOGGER.log(Level.INFO, "Routing entry for input: {0}", input);
        Scanner bufferScanner = new Scanner(input);     //Scanner for the buffer
        if (!bufferScanner.hasNext()) {
            throw new InvalidInputException(InvalidInputErrorType.EMPTY_INPUT);
        }
        String firstWord = bufferScanner.next();              //Stores first word in the input

        //Functionalities
        switch (firstWord) {
        case AddCommand.COMMAND_WORD:
            LOGGER.log(Level.INFO, "Add command was input: {0}", input);
            return new AddCommandParser().parse(input);
        case DeleteCommand.COMMAND_WORD:
            LOGGER.log(Level.INFO, "Delete command was input: {0}", input);
            return new DeleteCommandParser().parse(input);
        case EditCommand.COMMAND_WORD:
            LOGGER.log(Level.INFO, "Edit command was input: {0}", input);
            return new EditCommandParser().parse(input);
        case FindCommand.COMMAND_WORD:
            LOGGER.log(Level.INFO, "Find command was input: {0}", input);
            return new FindCommandParser().parse(input);
        case ListCommand.COMMAND_WORD:
            LOGGER.log(Level.INFO, "List command was input: {0}", input);
            return new ListCommandParser().parse(input);
        case SummaryCommand.COMMAND_WORD:
            LOGGER.log(Level.INFO, "Summary command was input: {0}", input);
            return new SummaryCommandParser().parse(input);
        case ExportCommand.COMMAND_WORD:
            LOGGER.log(Level.INFO, "Export command was input: {0}", input);
            return new ExportCommandParser().parse(input);
        case HelpCommand.COMMAND_WORD:
            LOGGER.log(Level.INFO, "Help command was input: {0}", input);
            return new HelpCommand(input);
        case ByeCommand.COMMAND_WORD:
            LOGGER.log(Level.INFO, "Exit command was input: {0}", input);
            return new ByeCommand();
        default:
            LOGGER.log(Level.WARNING, "Invalid input received: {0}. Throwing exception.", input);
            throw new InvalidInputException(InvalidInputErrorType.INVALID_INPUT);
        }
    }
}
