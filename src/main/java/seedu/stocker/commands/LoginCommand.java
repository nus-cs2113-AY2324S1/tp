package seedu.stocker.commands;

import seedu.stocker.authentication.LoginSystem;

import java.io.IOException;

public class LoginCommand extends Command {



    public static final String COMMAND_WORD = "login";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Login new user into system. "
            + System.lineSeparator()
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Welcome back!" ;

    public CommandResult execute() throws IOException {
        LoginSystem system = new LoginSystem();
        system.loadExistingUsers();
        system.loginExistingUser();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
