package seedu.duke.commands;

/**
 * Represents an executable command.
 */
public class Command {
    public Command() {
    }

    /**
     * Executes the command and returns the result.
     */
    public CommandResult execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }
}
