package quizhub.command;

import quizhub.question.Question;
import quizhub.storage.Storage;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Command to Start the Quiz
 */
public class CommandStart extends Command{
    public static final String COMMAND_WORD = "start";
    public static final String MISSING_QUIZ_MODE_MSG = "    Ono! You did not indicate mode of the quiz :< " +
            System.lineSeparator() + "    Quiz mode must be either 'all' or 'module'";
    public static final String MISSING_START_DETAILS = "    Ono! You did not indicate start details for the quiz " +
            "mode that you have chosen :<";
    public static final String MISSING_QN_MODE_MSG = "    Ono! You did not indicate mode of arranging " +
            "quiz questions :<";
    public static final String INVALID_MODE_MSG = "    Question mode must be either 'random' or 'normal'";
    public static final String INVALID_FORMAT_MSG = "    Please format your input as start " +
            "/[quiz mode] [start details] /[qn mode]!";
    public static final String TOO_MANY_ARGUMENTS_MSG = "    Ono! There should not be arguments after /[qn mode]";
    private final String startMode;
    private final String startDetails;
    private final String startQnMode;
    /**
     * Creates a new start command
     *
     * @param startMode Mode to start the quiz with, indicates how questions for the quiz are selected.
     * @param startDetails Details to complement quiz mode for choosing questions for the quiz.
     * @param startQnMode Mode for arranging the questions within the quiz.
     */
    public CommandStart(String startMode, String startDetails, String startQnMode) {
        super(CommandType.START);
        this.startMode = startMode;
        this.startDetails = startDetails;
        this.startQnMode = startQnMode;
    }

    /**
     * Loop through the array list of questions & allow the user to answer them.
     * If the input given matches EXACTLY (v1.0), then the answer is correct
     * returns "Correct" or "Wrong"
     *
     * @param ui User interface for interactions with user through CLI.
     * @param questions Current question list in the program.
     * @param dataStorage Hard disk storage for storing question data.
     */
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, QuestionList questions) {
        assert questions != null && ui != null && dataStorage != null;

        ArrayList<Question> matchedQuestions;

        switch (startMode.toLowerCase()) {
        case "module":
            assert startDetails != null;
            String[] modules  = startDetails.split(" ");
            matchedQuestions = questions.categoriseListByModule(modules);
            break;
        case "all":
            matchedQuestions = questions.getAllQns();
            break;
        default:
            ui.displayMessage("    Please enter a valid quiz mode :<");
            return;
        }

        switch(startQnMode.toLowerCase()){
        case "random":
            Collections.shuffle(matchedQuestions); // shuffles matched Questions
            questions.startQuiz(ui, matchedQuestions);
            break;
        case "normal":
            questions.startQuiz(ui, matchedQuestions);
            break;
        default:
            ui.displayMessage("    Please enter a valid quiz mode :<");
            return;
        }
    }
}
