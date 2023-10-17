package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.EditPriceCommand;

/**
 * Parse everything received from the users on terminal
 * into a format that can be interpreted by other core classes
 */
public class Parser {
    public Command parseCommand(String userInput) {
        String[] command = userInput.trim().split(" ");

        switch (command[0].toLowerCase()) {
        case EditPriceCommand.COMMAND_WORD:
                return prepareEditPriceCommand();
        }
    }

    private Command prepareEditPriceCommand() {
        try {
            final int dishIndex = parseArgsAsDisplayedIndex(userInput);
            return new EditPriceCommand(dishIndex, price);
        }
    }


}
