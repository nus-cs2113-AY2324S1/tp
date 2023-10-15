package seedu.duke.commands;

import java.util.List;
import java.util.Optional;

import seedu.duke.data.Printable;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    /**
     * The feedback message to be shown to the user. Contains a description of the execution result
     */
    public final String feedbackToUser;

    /**
     * The list of objects that was produced by the command
     */
    private final List<? extends Printable> relevantItems;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        relevantItems = null;
    }

    public CommandResult(String feedbackToUser, List<? extends Printable> relevantItems) {
        this.feedbackToUser = feedbackToUser;
        this.relevantItems = relevantItems;
    }

    /**
     * Returns a list of objects relevant to the command result, if any.
     */
    public Optional<List<? extends Printable>> getRelevantItems() {
        return Optional.ofNullable(relevantItems);
    }

}
