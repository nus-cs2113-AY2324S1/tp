package seedu.duke.commands;

/**
 * Represents an executable command.
 */
public class Command {
    String userCommand;

    public Command() {
    }

    public Command(String cmd){
        this.userCommand = cmd;
    }

    /**
     * Executes the command and returns the result.
     */
    public CommandResult execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }


}
