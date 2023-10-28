package quizhub.command;

import quizhub.storage.Storage;
import quizhub.question.Question;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;
/**
 * Represents a command to add a quiz question.
 */
public class CommandShortAnswer extends Command {
    public static final String INVALID_FORMAT_MSG = "    Please format your input as " +
            "short [question]/[answer]/[module]/[difficulty]!";
    public static final String MISSING_FIELDS_MSG = "    You have one or more field missing!";
    public static final String TOO_MANY_ARGUMENTS_MSG = "    Ono! There should not be arguments after /difficulty";
    public static final String DUPLICATED_INPUT = "    You have a duplicated input, please fill add a different input!";
    private final String description;
    private final String answer;
    private final String module;
    private final Question.QnDifficulty qnDifficulty;
    /**
     * Creates a new question command to add a SHORTANSWER question
     *
     * @param description Question description
     * @param answer answer to question
     * @param module module of question
     * @param qnDifficulty difficulty of question
     */
    public CommandShortAnswer(String description, String answer, String module,
                              Question.QnDifficulty qnDifficulty){
        super(CommandType.ADD);
        this.description = description;
        this.answer = answer;
        this.module = module;
        this.qnDifficulty = qnDifficulty;
    }

    /**
     * Adds the SHORTANSWER question and updates storage data.
     *
     * @param ui User interface for interactions with user through CLI.
     * @param questions Current question list in the program.
     * @param dataStorage Hard disk storage for storing task data.
     */
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuestionList questions){
        questions.addShortAnswerQn(description, answer, module, qnDifficulty, true);
        dataStorage.updateData(questions);
    }

}
