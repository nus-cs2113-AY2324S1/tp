package seedu.wildwatch.parser;

import seedu.wildwatch.command.Command;
import seedu.wildwatch.exception.InvalidInputException;

/**
 * Returns a specific command object as defined in the command package.
 *
 * @param <T>
 */
public interface Parser<T extends Command> {
    T parse(String input) throws InvalidInputException;
}
