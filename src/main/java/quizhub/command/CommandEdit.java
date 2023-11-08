package quizhub.command;

import quizhub.storage.Storage;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;
/**
 * Represents a command to edit a question.
 */
public class CommandEdit extends Command {
    public static final String COMMAND_WORD = "edit";
    public static final String INVALID_FORMAT_MSG = "    Please format your input as:" + System.lineSeparator() +
            "    edit [question number] " +
            "/description [new description] or /answer [new answer]! for short answer questions and" +
            System.lineSeparator() +
            "    edit [question number] /description [new description] or /answer [new answer] or " + 
            "/option[number] [new value] for multiple choice questions";
    public static final String MISSING_INDEX_MSG = "    Ono! You did not indicate question index :<";
    public static final String TOO_MANY_INDEX_MSG = "    Ono! You cannot enter more than 1 valid integer index :<";
    public static final String MISSING_CRITERIA_MSG = "    Ono! You did not indicate which " +
            "question field to edit :<";
    public static final String INVALID_CRITERIA_MSG = "    Ono! You tried to edit by an unknown criteria :<";
    public static final String TOO_MANY_CRITERIA_MSG = "    Ono! You tried to edit using more than 1 question " +
            "fields :<";
    public static final String MISSING_KEYWORD_MSG = "    Ono! You did not enter the new value :<";
    public static final String INVALID_SHORT_ANSWER_CRITERIA_MSG = "    Ono! Short answer questions can only be " +
            "edited by description or answer :<";
    public static final String INDEX_NOT_IN_RANGE_MSG = "    Ono! The question index you entered is not in the " +
            "range of the question list :<";
    public static final String NO_CHANGES_MADE_MSG = "   The value you entered is the same as the current value! " +
            "No changes made to question :>";
    public static final String SUCCESSFUL_EDIT_MSG = "    Roger that! I have edited the following question >w< !";
    private final int qnIndex;
    private final String newDescription;
    private final String newAnswer;

    /**
     * Creates a new edit command
     *
     * @param qnIndex        Question index in current question list.
     * @param newDescription New description to replace the current question
     *                       description with.
     * @param newAnswer      New answer to replace the current question answer with.
     */
    public CommandEdit(int qnIndex, String newDescription, String newAnswer) {
        super(CommandType.EDIT);
        this.qnIndex = qnIndex;
        this.newDescription = newDescription;
        this.newAnswer = newAnswer;
    }

    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuestionList questions) {
        if (newDescription == null && newAnswer == null) {
            return;
        }
        questions.editQuestionByIndex(qnIndex, newDescription, newAnswer);
        ui.displayMessage(SUCCESSFUL_EDIT_MSG);
        dataStorage.updateData(questions);
    }
}
