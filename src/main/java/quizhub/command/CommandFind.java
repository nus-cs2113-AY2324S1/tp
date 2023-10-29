package quizhub.command;

import quizhub.storage.Storage;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;
/**
 * Represents a command to search for a task.
 */
public class CommandFind extends Command{
    public static final String COMMAND_WORD = "find";
    public static final String MISSING_CRITERIA_MSG = "    Ono! You did not indicate " +
            "if you are searching by description or module :<";
    public static final String MISSING_KEYWORD_MSG = "    Ono! You did not indicate " +
            "the keywords you are searching by :<";
    public static final String INVALID_FORMAT_MSG = "    Please format your input as find " +
            "/description [description] or find /module [module]!";
    private String searchCriteria;
    private String searchDetails;

    /**
     * Creates a new find command to search for a task.
     *
     * @param searchDetails User input containing details of what to search.
     */
    public CommandFind(String searchCriteria, String searchDetails){
        super((CommandType.FIND));
        this.searchCriteria = searchCriteria;
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
        assert questions != null && dataStorage != null && ui != null : "Invalid null parameter";
        switch (searchCriteria) {
        case "description":
            questions.searchListByDescription(searchDetails);
            break;
        case "module":
            questions.searchListByModule(searchDetails);
            break;
        default:
            ui.displayMessage(INVALID_FORMAT_MSG);
        }
    }
}
