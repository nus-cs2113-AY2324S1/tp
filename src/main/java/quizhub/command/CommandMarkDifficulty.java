package quizhub.command;

import quizhub.exception.QuizHubExceptions;
import quizhub.parser.Parser;
import quizhub.question.Question;
import quizhub.questionlist.QuestionList;
import quizhub.storage.Storage;
import quizhub.ui.Ui;
/**
 * Represents a command to mark the difficulty of a question.
 */
public class CommandMarkDifficulty extends Command{

    private int qnIndex;
    private Question.QnDifficulty qnDifficulty;
    public static final String MISSING_INDEX_MSG = "    Ono! You did not indicate index of question to be marked :<";
    public static final String MISSING_DIFFICULTY_MSG = "    Ono! You did not indicate difficulty " +
            "to be assigned the question :<";
    public static final String INVALID_FORMAT_MSG = "    Please format your input as markdiff " +
            "[question number] [question difficulty]!";
    /**
     * Creates a new command to mark the difficulty of a question of specified index
     *
     * @param userInput User input from CLI.
     */
    public CommandMarkDifficulty(String userInput){
        super(CommandType.MARKDIFFICULTY);
        String[] commandDetails = userInput.split(" ");
        try {
            qnIndex = Integer.parseInt(commandDetails[1].strip());
            if(qnIndex < 0){
                System.out.println(Parser.INVALID_INTEGER_INDEX_MSG);
            }
        } catch (NumberFormatException incompleteCommand) {
            System.out.println(Parser.INVALID_INTEGER_INDEX_MSG);
            return;
        } catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            System.out.println(MISSING_INDEX_MSG);
            System.out.println(INVALID_FORMAT_MSG);
            return;
        }
        try {
            qnDifficulty = Parser.extractQuestionDifficulty(commandDetails[2].strip());
        } catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            System.out.println(MISSING_DIFFICULTY_MSG);
        }
    }

    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuestionList questions) {
        assert qnDifficulty != null;
        if(qnDifficulty != Question.QnDifficulty.NORMAL) {
            questions.markQuestionDifficulty(qnIndex, qnDifficulty, true);
            dataStorage.updateData(questions);
        }
    }
}
