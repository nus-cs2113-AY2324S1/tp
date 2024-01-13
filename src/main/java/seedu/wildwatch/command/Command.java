package seedu.wildwatch.command;

import seedu.wildwatch.exception.InvalidInputException;

/**
 * Command class for manipulating EntryList
 */
public abstract class Command {
    public Command() {}

    public abstract void execute() throws InvalidInputException;
}
