package seedu.financialplanner.commands.utils;

/**
 * Represents a generic command that can be inherited.
 */
public abstract class Command {
    public abstract void execute() throws Exception;
}
