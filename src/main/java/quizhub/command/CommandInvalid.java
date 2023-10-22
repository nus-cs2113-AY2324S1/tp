package quizhub.command;

import quizhub.questionlist.QuestionList;
import quizhub.storage.Storage;
import quizhub.ui.Ui;
/**
 * Represents an invalid command to be handled.
 */
public class CommandInvalid extends Command {

    public String feedback;
    /**
     * Creates a new invalid command with error feedback for user.
     */
    public CommandInvalid(String feedback) {
        super(CommandType.INVALID);
        this.feedback = feedback;
    }
    /**
     * Displays the error feedback on CLI for user.
     */
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuestionList questions) {
        ui.showInvalidCommandHelp(feedback);
    }
}
