package quizhub.command;

import quizhub.storage.Storage;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;
/**
 * Represents a command to edit a task.
 */
public class CommandEdit extends Command {
    private int qnIndex;
    private String newDescription;
    private String newAnswer;

    public CommandEdit(String userInput) {
        super(CommandType.EDIT);
        String[] editDetails;
        try {
            editDetails = userInput.split(" ");
            qnIndex = Integer.parseInt(editDetails[1].strip());
        } catch (NumberFormatException incompleteCommand) {
            System.out.println("    Ono! You did not indicate the index of the question you wish to edit :<");
            System.out.println("    Please format your input as edit [question number] /description [description] " +
                    "or edit /answer [answer]!");
            return;
        } catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            System.out.println("    Ono! You did not indicate if you are editing question description or answer :<");
            System.out.println("    Please format your input as edit [question number] /description [description] " +
                    "or edit /answer [answer]!");
            return;
        }
        try {
            String editCriteria = editDetails[2].strip();
            switch (editCriteria){
                case "/description":
                    newDescription = getContentFromUserInput(userInput, "/description");
                    break;
                case "/answer":
                    newAnswer = getContentFromUserInput(userInput, "/answer");
                    break;
                default:
                    throw new ArrayIndexOutOfBoundsException();
            }
        } catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            System.out.println("    Ono! You did not indicate the content you are editing to :<");
            System.out.println("    Please format your input as edit [question number] /description [description] " +
                    "or edit /answer [answer]!");
        }
    }

    private String getContentFromUserInput(String userInput, String keyWord) throws ArrayIndexOutOfBoundsException {
        String content;
        content = userInput.split(keyWord)[1].strip();
        if (content.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return content;
    }

    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuestionList questions) {
        if (newDescription == null && newAnswer == null) {
            return;
        }
        questions.editQuestionByIndex(qnIndex, newDescription, newAnswer);
    }
}
