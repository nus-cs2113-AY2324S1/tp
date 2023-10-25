package quizhub.command;

import quizhub.parser.Parser;
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
    public static final String INVALID_FORMAT_MSG = "    Please format your input as edit [question number] " +
            "/description [description] or edit /answer [answer]!";
    public static final String MISSING_INDEX_MSG = "    Ono! You did not indicate question index :<";
    public static final String MISSING_CRITERIA_MSG = "    Ono! You did not indicate if " +
            "you are editing question description or answer :<";

    /**
     * Creates a new edit command
     *
     * @param userInput User input from CLI.
     */
    public CommandEdit(String userInput) {
        super(CommandType.EDIT);
        String[] editDetails;
        String editCriteria;
        try {
            editDetails = userInput.split(" ");
            qnIndex = Integer.parseInt(editDetails[1].strip());
        } catch (NumberFormatException incompleteCommand) {
            System.out.println(Parser.INVALID_INTEGER_INDEX_MSG);
            System.out.println(INVALID_FORMAT_MSG);
            return;
        } catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            System.out.println(MISSING_INDEX_MSG);
            System.out.println(INVALID_FORMAT_MSG);
            return;
        }
        try {
            editCriteria = editDetails[2].strip();
        } catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            System.out.println(MISSING_CRITERIA_MSG);
            System.out.println(INVALID_FORMAT_MSG);
            return;
        }
        try{
            switch (editCriteria){
            case "/description":
                newDescription = Parser.getContentAfterKeyword(userInput, "/description");
                break;
            case "/answer":
                newAnswer = Parser.getContentAfterKeyword(userInput, "/answer");
                break;
            default:
                throw new ArrayIndexOutOfBoundsException();
            }
        } catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            System.out.println(MISSING_CRITERIA_MSG);
            System.out.println(INVALID_FORMAT_MSG);
        }
    }

    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuestionList questions) {
        if (newDescription == null && newAnswer == null) {
            return;
        }
        questions.editQuestionByIndex(qnIndex, newDescription, newAnswer);
        dataStorage.updateData(questions);
    }
}
