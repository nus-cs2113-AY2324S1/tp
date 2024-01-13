package seedu.wildwatch.error;

import java.util.logging.Logger;

import seedu.wildwatch.command.EditCommand;
import seedu.wildwatch.command.FindCommand;
import seedu.wildwatch.command.HelpCommand;
import seedu.wildwatch.exception.InvalidInputException;
import seedu.wildwatch.ui.ErrorPrinter;
import seedu.wildwatch.ui.ListCommandPrinter;
import seedu.wildwatch.ui.DeleteCommandPrinter;
import seedu.wildwatch.ui.EmptyDescriptionPrinter;

public class ErrorHandler {
    private static final Logger LOGGER = Logger.getLogger(ErrorHandler.class.getName());

    public static void handleInputError(InvalidInputException exception) {
        boolean validInput = false;

        switch (exception.getError()) {
        case CUSTOM:
            LOGGER.warning("Command is invalid.");
            ErrorPrinter.customInvalidInputMessagePrinter(exception.getCustomMessage());
            break;
        case EMPTY_INPUT:
            LOGGER.warning("Received an empty input.");
            EmptyDescriptionPrinter.emptyDescriptionMessagePrinter(null);
            break;
        case EMPTY_EDIT_INPUT:
            LOGGER.warning("Received an empty edit input.");
            EmptyDescriptionPrinter.emptyDescriptionMessagePrinter(EditCommand.COMMAND_WORD);
            break;
        case EMPTY_FIND_INPUT:
            LOGGER.warning("Received an empty find input.");
            EmptyDescriptionPrinter.emptyDescriptionMessagePrinter(FindCommand.COMMAND_WORD);
            break;
        case EMPTY_LIST:
            LOGGER.info("List is empty.");
            ListCommandPrinter.emptyListMessagePrinter();
            validInput = true;
            break;
        case ENTRY_NOT_FOUND:
            LOGGER.warning("Queried entry not found.");
            DeleteCommandPrinter.entryNotFoundMessagePrinter();
            validInput = true;
            break;
        case INVALID_DATE:
            LOGGER.warning("Date is invalid.");
            ErrorPrinter.invalidDateMessagePrinter();
            break;
        case INVALID_INDEX:
            LOGGER.warning("Index is invalid.");
            ErrorPrinter.invalidIndexMessagePrinter();
            break;
        case INVALID_INPUT:
            LOGGER.warning("Command is invalid.");
            ErrorPrinter.invalidInputMessagePrinter();
            break;
        default:
            ErrorPrinter.invalidInputMessagePrinter();
        }
        if (!validInput) {
            HelpCommand.printNeedHelpMessage();
        }
    }
}
