package quizhub.command;

import quizhub.exception.QuizHubExceptions;
import quizhub.question.Question;
import quizhub.storage.Storage;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;
import quizhub.question.ShortAnsQn;
import quizhub.question.MultipleChoiceQn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Command to Start the Quiz
 */
public class CommandStart extends Command{
    public static final String COMMAND_WORD = "start";
    public static final String MISSING_QUIZ_MODE_MSG = "    Ono! You did not indicate mode of the quiz :< " +
            System.lineSeparator() + "    Quiz mode must be either 'all' or 'module'";
    public static final String INVALID_QUIZ_MODE_MSG = "    Quiz mode must be either 'all' or 'module'";
    public static final String MISSING_START_DETAILS = "    Ono! You did not indicate start details for the quiz " +
            "mode that you have chosen :<";
    public static final String MISSING_QN_MODE_MSG = "    Ono! You did not indicate mode of arranging " +
            "quiz questions :<";
    public static final String INVALID_QN_MODE_MSG = "    Question mode must be either 'random' or 'normal'";
    public static final String INVALID_FORMAT_MSG = "    Please format your input as start " +
            "/[quiz mode] [start details] /[qn mode]!";
    public static final String TOO_MANY_ARGUMENTS_MSG = "    Ono! You gave too many arguments :<";
    public static final int NUM_ARGUMENTS = 4;
    private final String startMode;
    private final String startDetails;
    private final String startQnMode;
    private final String startQnType;
    /**
     * Creates a new start command
     *
     * @param startMode Mode to start the quiz with, indicates how questions for the quiz are selected.
     * @param startDetails Details to complement quiz mode for choosing questions for the quiz.
     * @param startQnMode Mode for arranging the questions within the quiz.
     */
    public CommandStart(String startMode, String startDetails, String startQnMode, String startQnType) {
        super(CommandType.START);
        this.startMode = startMode;
        this.startDetails = startDetails;
        this.startQnMode = startQnMode;
        this.startQnType = startQnType;
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

        ArrayList<Question> matchedQuestions = getMatchedQuestions(questions);
        if (matchedQuestions == null) {
            ui.displayMessage("    Please enter a valid quiz mode :<");
            return;
        }

        filterQuestionsByType(matchedQuestions);
        arrangeQuestions(matchedQuestions);

        questions.startQuiz(ui, matchedQuestions);
    }

    private ArrayList<Question> getMatchedQuestions(QuestionList questions) {
        switch (startMode.toLowerCase()) {
        case "module":
            assert startDetails != null;
            String[] modules  = startDetails.split(" ");
            try {
                return questions.assembleListByModules(modules);
            } catch (QuizHubExceptions emptyList) {
                System.out.println(emptyList.getMessage());
                return null;
            }
        case "all":
            return questions.getAllQns();
        default:
            return null;
        }
    }

    private void filterQuestionsByType(ArrayList<Question> matchedQuestions) {
        if (!startQnType.equals("mix")) {
            matchedQuestions.retainAll(matchedQuestions.stream()
                    .filter(q -> (startQnType.equals("short") && q instanceof ShortAnsQn) ||
                            (startQnType.equals("mcq") && q instanceof MultipleChoiceQn))
                    .collect(Collectors.toList()));
        }
    }

    private void arrangeQuestions(ArrayList<Question> matchedQuestions) {
        switch(startQnMode.toLowerCase()) {
        case "random":
            Collections.shuffle(matchedQuestions); // shuffles matched Questions
            break;
        case "normal":
            // For 'normal', no action is needed as the list is already in order.
            break;
        default:
            // In case of an invalid mode, it will be handled before this method is called.
            break;
        }
    }

}
