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
    /**
     * Creates a new delete command for a question.
     *
     * @param userInput User input from CLI.
     */
    public CommandDelete(String userInput){
        super(CommandType.DELETE);
        String[] editDetails;
        try {
            editDetails = userInput.split(" ");
            qnIndex = Integer.parseInt(editDetails[1].strip());
        } catch (NumberFormatException incompleteCommand) {
            System.out.println(Parser.INVALID_INTEGER_INDEX);
            System.out.println("    Please format your input as delete [question number]");
        } catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            System.out.println("    Ono! You did not indicate question index :<");
            System.out.println("    Please format your input as delete [question number]!");
        }
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
        if(!taskName.equals("Question Not Found")) {
            questions.deleteQuestionByIndex(qnIndex);
            dataStorage.updateData(questions);
        }
    }
}
