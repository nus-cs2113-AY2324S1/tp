package quizhub.command;

import quizhub.question.Question;
import quizhub.questionlist.QuestionList;
import quizhub.storage.Storage;
import quizhub.ui.Ui;
/**
 * Represents a command to mark the difficulty of a question.
 */
public class CommandMarkDifficulty extends Command{
    public static final String MISSING_INDEX_MSG = "    Ono! You did not indicate index of question to be marked :<";
    public static final String TOO_MANY_INDEX_MSG = "    Ono! You tried to mark more than 1 question :<";
    public static final String MISSING_DIFFICULTY_MSG = "    Ono! You did not indicate difficulty " +
            "to be assigned the question :<";
    public static final String INVALID_FORMAT_MSG = "    Please format your input as markdiff " +
            "[qn number] /[qn difficulty]!";
    public static final String TOO_MANY_DIFFICULTY_MSG = "    Ono! You tried to assign more than 1 difficulty level :<";
    private final int qnIndex;
    private final Question.QnDifficulty qnDifficulty;
    /**
     * Creates a new command to mark the difficulty of a question of specified index.
     *
     * @param qnIndex Index of the question which difficulty is to be marked.
     * @param qnDifficulty Difficulty to be assigned to the question.
     */
    public CommandMarkDifficulty(int qnIndex, Question.QnDifficulty qnDifficulty){
        super(CommandType.MARKDIFFICULTY);
        this.qnIndex = qnIndex;
        this.qnDifficulty = qnDifficulty;
    }

    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuestionList questions) {
        assert qnDifficulty != null;
        if(qnDifficulty != Question.QnDifficulty.INVALID) {
            questions.markQuestionDifficulty(qnIndex, qnDifficulty, true);
            dataStorage.updateData(questions);
        }
    }
}
