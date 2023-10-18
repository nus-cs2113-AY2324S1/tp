package seedu.stocker.commands;

import java.util.List;
import seedu.stocker.drugs.Drug;

import java.util.Optional;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    /** The feedback message to be shown to the user. Contains a description of the execution result */
    public final String feedbackToUser;

    /** The list of drugs that was produced by the command */
    private final List<Drug> relevantDrugs;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        relevantDrugs = null;
    }

    public CommandResult(String feedbackToUser, List<Drug> relevantDrugs) {
        this.feedbackToUser = feedbackToUser;
        this.relevantDrugs = relevantDrugs;
    }

    /**
     * Returns a list of drugs that was produced by the command, if any.
     */
    public Optional<List<Drug>> getRelevantDrugs() {
        return Optional.ofNullable(relevantDrugs);
    }



}
