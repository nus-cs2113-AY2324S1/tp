package seedu.stocker.commands;


import seedu.stocker.drugs.StockEntry;

/**
 * Represents an incorrect command. Upon execution, produces some feedback to the user.
 */
public class IncorrectCommand extends Command {

    public final String feedbackToUser;

    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public CommandResult<StockEntry> execute() {
        return new CommandResult<>(feedbackToUser);
    }

}
