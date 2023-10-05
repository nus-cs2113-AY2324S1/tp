package quizhub.command;

import quizhub.storage.Storage;
import quizhub.quiz.Quiz;
import quizhub.quizlist.QuizList;
import quizhub.ui.Ui;
/**
 * Represents a command to add a quiz question.
 */
public class CommandAdd extends Command {

    private String userInput;
    /**
     * Creates a new todo command to add a todo task.
     *
     * @param userInput User input containing details of the todo task.
     */
    public CommandAdd(String userInput){
        super(CommandType.ADD);
        this.userInput = userInput;
    }
    /**
     * Adds the todo task and updates storage data.
     *
     * @param ui User interface for interactions with user through CLI.
     * @param tasks Current task list in the program.
     * @param dataStorage Hard disk storage for storing task data.
     */
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuizList tasks){
        tasks.addToTaskList(userInput, Quiz.TaskType.TODO, true);
        dataStorage.updateData(tasks);
    }

}
