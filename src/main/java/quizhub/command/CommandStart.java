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

        ArrayList<Question> matchedQuestions;

        switch (startMode.toLowerCase()) {
        case "module":
            assert startDetails != null;
            String[] modules  = startDetails.split(" ");
            try {
                matchedQuestions = questions.assembleListByModules(modules);
            } catch (QuizHubExceptions emptyList){
                System.out.println(emptyList.getMessage());
                return;
            }
            break;
        case "all":
            matchedQuestions = questions.getAllQns();
            break;
        default:
            ui.displayMessage("    Please enter a valid quiz mode :<");
            return;
        }

        // Ensure 'startQnType' is without slashes (e.g., "short" or "mcq")
        if (!startQnType.equals("mix")) {
            matchedQuestions = matchedQuestions.stream()
                    .filter(q -> (startQnType.equals("short") && q instanceof ShortAnsQn) ||
                            (startQnType.equals("mcq") && q instanceof MultipleChoiceQn))
                    .collect(Collectors.toCollection(ArrayList::new)); // Use this to ensure an ArrayList is returned
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
