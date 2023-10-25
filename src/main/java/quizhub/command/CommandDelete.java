package quizhub.command;

import quizhub.parser.Parser;
import quizhub.storage.Storage;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;
/**
 * Represents a command to delete a question.
 */
public class CommandDelete extends Command {
    private int qnIndex;
    public static final String INVALID_FORMAT_MSG = "    Please format your input as delete [question number]";
    public static final String MISSING_INDEX_MSG = "    Ono! You did not indicate question index :<";

    /**
     * Creates a new delete command for a question.
     *
     * @param qnIndex 0-based index of question to be deleted.
     */
    public CommandDelete(int qnIndex){
        super(CommandType.DELETE);
        this.qnIndex = qnIndex;
    }
    /**
     * Checks if specified task exists.
     * Deletes the specified task and updates storage data.
     *
     * @param ui User interface for interactions with user through CLI.
     * @param questions Current question list in the program.
     * @param dataStorage Hard disk storage for storing task data.
     */
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuestionList questions){
        String taskName = questions.viewQuestionByIndex(qnIndex);
        if (taskName.equals("Question Not Found")) {
            ui.displayMessage("    Ono! The question you are deleting is not found!");
            return;
        }
        questions.deleteQuestionByIndex(qnIndex);
        dataStorage.updateData(questions);
    }
}
