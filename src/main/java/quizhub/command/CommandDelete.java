package quizhub.command;

import quizhub.storage.Storage;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;
/**
 * Represents a command to delete a question.
 */
public class CommandDelete extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String INVALID_FORMAT_MSG = "    Please format your input as delete [question number]";
    public static final String MISSING_INDEX_MSG = "    Ono! You did not indicate question index :<";
    public static final String EXCESSIVE_INDEX_MSG = "    Please enter only 1 question index!";
    private int qnIndex;

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
     * Checks if specified question exists.
     * Deletes the specified question and updates storage data.
     *
     * @param ui User interface for interactions with user through CLI.
     * @param questions Current question list in the program.
     * @param dataStorage Hard disk storage for storing question data.
     */
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuestionList questions){
        String questionName = questions.viewQuestionByIndex(qnIndex);
        if (questionName.equals("Question Not Found")) {
            ui.showInvalidCommandHelp(Ui.INVALID_INTEGER_INDEX_MSG);
            ui.displayNumberOfQuestions();
            return;
        }
        questions.deleteQuestionByIndex(qnIndex);
        dataStorage.updateData(questions);
    }
}
