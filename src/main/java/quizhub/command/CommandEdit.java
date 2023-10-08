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
        String[] editInfo;
        try {
            editDetails = userInput.split("edit")[1].strip().split("/");
            qnIndex = Integer.parseInt(editDetails[0].strip());
            editInfo = editDetails[1].strip().split(" ");
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
        try{
            String editCriteria = editInfo[0].strip();
            String editContent = editInfo[1].strip();
            switch (editCriteria){
                case "description":
                    newDescription = editContent;
                    newAnswer = "";
                    break;
                case "answer":
                    newDescription = "";
                    newAnswer = editContent;
                    break;
                default:
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            System.out.println("    Ono! You did not indicate the content you are editing to :<");
            System.out.println("    Please format your input as edit [question number] /description [description] " +
                    "or edit /answer [answer]!");
        }
    }

    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuestionList questions) {
        questions.editQuestionByIndex(qnIndex, newDescription, newAnswer);
    }
}