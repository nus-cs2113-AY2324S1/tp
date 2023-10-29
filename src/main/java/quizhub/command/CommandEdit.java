package quizhub.command;

import quizhub.storage.Storage;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;
/**
 * Represents a command to edit a task.
 */
public class CommandEdit extends Command {
    public static final String INVALID_FORMAT_MSG = "    Please format your input as edit [question number] " +
            "/description [description] or /answer [answer]!";
    public static final String MISSING_INDEX_MSG = "    Ono! You did not indicate question index :<";
    public static final String TOO_MANY_INDEX_MSG = "    Ono! You tried to edit more than 1 question :<";
    public static final String MISSING_CRITERIA_MSG = "    Ono! You did not indicate if " +
            "you are editing question description or answer :<";
    public static final String INVALID_CRITERIA_MSG = "    Ono! You tried to edit by a criteria other than " +
            "description or answer :<";
    public static final String TOO_MANY_CRITERIA_MSG = "    Ono! You tried to edit using more than 1 question " +
            "component :<";
    public static final String MISSING_KEYWORD_MSG = "    Ono! You did not enter a new description / answer :<";
    private int qnIndex;
    private String newDescription;
    private String newAnswer;

    /**
     * Creates a new edit command
     *
     * @param qnIndex Question index in current question list.
     * @param newDescription New description to replace the current question description with.
     * @param newAnswer New answer to replace the current question answer with.
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
        dataStorage.updateData(questions);
    }
}
