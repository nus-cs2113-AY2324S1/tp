package quizhub.command;

import quizhub.storage.Storage;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;
/**
 * Represents a command to list all tasks.
 */
public class CommandList extends Command {
    public static final String COMMAND_WORD = "list";
    /**
     * Creates a new list command to list all tasks.
     */
    public CommandList(){
        super(CommandType.LIST);
    }
    /**
     * Prints all tasks in current task list.
     *
     * @param ui User interface for interactions with user through CLI.
     * @param questions Current question list in the program.
     * @param dataStorage Hard disk storage for storing task data.
     */
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuestionList questions){
        questions.printQuestionList();
    }
}
