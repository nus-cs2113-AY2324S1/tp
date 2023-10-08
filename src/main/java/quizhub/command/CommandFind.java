package quizhub.command;

import quizhub.storage.Storage;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;
/**
 * Represents a command to search for a task.
 */
public class CommandFind extends Command{
    private String searchDetails;
    /**
     * Creates a new find command to search for a task.
     *
     * @param searchDetails User input containing details of what to search.
     */
    public CommandFind(String searchDetails){
        super((CommandType.FIND));
        this.searchDetails = searchDetails;
    }

    /**
     * Checks if specified task exists.
     * Marks the specified task as not done and updates storage data.
     *
     * @param ui User interface for interactions with user through CLI.
     * @param questions Current question list in the program.
     * @param dataStorage Hard disk storage for storing task data.
     */
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuestionList questions) {
        questions.searchList(searchDetails);
    }
}
