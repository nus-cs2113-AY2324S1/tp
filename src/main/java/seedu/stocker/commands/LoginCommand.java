package seedu.stocker.commands;

import seedu.stocker.authentication.LoginSystem;
import seedu.stocker.exceptions.StockerException;

import java.io.IOException;

/**
 * Login existing user into system.
 */
public class LoginCommand extends Command {



    public static final String COMMAND_WORD = "login";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Login new user into system."
            + System.lineSeparator()
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Welcome back!" ;

    public CommandResult execute() throws IOException, StockerException {
        LoginSystem system = new LoginSystem();
        system.loadExistingUsers();
        system.loginExistingUser();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
