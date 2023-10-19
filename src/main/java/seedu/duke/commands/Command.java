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

    public CommandResult execute() throws Exception {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }


}
