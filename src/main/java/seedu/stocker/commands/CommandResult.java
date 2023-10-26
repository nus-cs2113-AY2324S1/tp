package seedu.stocker.commands;

import java.util.List;
// import seedu.stocker.drugs.Drug;

import java.util.Optional;

/**
 * Represents the result of a command execution.
 */
public class CommandResult<T> {

    /**
     * The feedback message to be shown to the user. Contains a description of the execution result
     */
    public final String feedbackToUser;

    /**
     * The list of drugs that was produced by the command
     */
    private final List<T> relevantElements;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        relevantElements = null;
    }

    public CommandResult(String feedbackToUser, List<T> relevantElements) {
        this.feedbackToUser = feedbackToUser;
        this.relevantElements = relevantElements;
    }

    /**
     * Returns a list of drugs that was produced by the command, if any.
     */
    public Optional<List<T>> getRelevantElements() {
        return Optional.ofNullable(relevantElements);
    }

    public String getFeedbackToUserFindTest() {
        if (relevantElements != null) {
            StringBuilder feedback = new StringBuilder();
            for (int i = 0; i < relevantElements.size(); i++) {
                feedback.append(i + 1).append(". ").append(relevantElements.get(i));
                feedback.append(System.lineSeparator());
            }
            feedback.append(System.lineSeparator());
            feedback.append(feedbackToUser);
            return feedback.toString();
        } else {
            return feedbackToUser;
        }
    }

}
