package quizhub.command;

import quizhub.storage.Storage;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;
/**
 * Represents a command to delete a task.
 */
public class CommandDelete extends Command {
    private int qnIndex;
    /**
     * Creates a new delete command for a task of specified index.
     *
     * @param qnIndex Index of the question to be deleted.
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
        if(!taskName.equals("Task Not Found")) {
            questions.deleteQuestionByIndex(qnIndex);
            dataStorage.updateData(questions);
        }
    }
}
