package quizhub.command;

import quizhub.storage.Storage;
import quizhub.questionlist.QuizList;
import quizhub.ui.Ui;
/**
 * Represents a command to list all tasks.
 */
public class CommandList extends Command {
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
     * @param tasks Current task list in the program.
     * @param dataStorage Hard disk storage for storing task data.
     */
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuizList tasks){
        tasks.printTaskList();
    }
}
