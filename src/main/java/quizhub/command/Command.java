package quizhub.command;

import quizhub.storage.Storage;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;
/**
 * Represents a user command to execute an action.
 */
public class Command {
    public enum CommandType {HELP, ADD, LIST, EDIT, DELETE, START, END, RESULT, FIND, EXIT};
    private CommandType commandType;
    /**
     * Creates a new blank command.
     */
    public Command(){}
    /**
     * Creates a new command of given type.
     *
     * @param commandType Type of command.
     */
    public Command(CommandType commandType){
        this.commandType = commandType;
    }
    /**
     * Performs an action according to command.
     *
     * @param ui User interface for interactions with user through CLI.
     * @param questions Current question list in the program.
     * @param dataStorage Hard disk storage for storing task data.
     */
    public void executeCommand(Ui ui, Storage dataStorage, QuestionList questions){}
    /**
     * Checks if the latest command calls for program termination.
     */
    public boolean toExit(){
        return commandType == CommandType.EXIT;
    }
}
