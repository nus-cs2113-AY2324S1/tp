package seedu.duke.commands;

/**
 * Represents the abstract class for all commands.
 * This class serves as a blueprint for all specific command classes in the application
 *
 */
public abstract class Command {

    /**
     * This method is used to execute the command.
     *
     * @throws KaChinnnngException if there is an error in the command
     */
    public void execute() throws KaChinnnngException {
    }

}
