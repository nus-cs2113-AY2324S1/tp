package seedu.stocker.commands;

import seedu.stocker.authentication.LoginSystem;
import seedu.stocker.exceptions.StockerException;

import java.io.IOException;

/**
 * Registers new user into login system.
 */
public class RegisterCommand extends Command {

    public static final String COMMAND_WORD = "register";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Register new user into system."
            + System.lineSeparator()
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "New User Created.";

    public CommandResult execute() throws IOException, StockerException {
        LoginSystem system = new LoginSystem();
        system.loadExistingUsers();
        system.newUserCreator();
        return new CommandResult<>(MESSAGE_SUCCESS);
    }
}
