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
    private String startMode;
    private String startDetails = "";
    private String startQnMode = "";
    /**
     * Creates a new start command
     *
     * @param userInput User input from CLI.
     */
    public CommandStart(String userInput) {
        super(CommandType.START);
        String[] commandDetails = userInput.split("/");
        try {
            startMode = commandDetails[1].split(" ")[0].strip();
        } catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            System.out.println("    Ono! You did not indicate mode of the quiz :<");
            System.out.println("    Please format your input as start /[quiz mode] [start details]!");
            return;
        }
        try {
            if(!startMode.equalsIgnoreCase("all")){
                startDetails = commandDetails[1].split(" ")[1].strip();
            }
        }  catch (ArrayIndexOutOfBoundsException incompleteCommand) {
            System.out.println("");
            System.out.println("    Please format your input as start /[quiz mode] [start details] /[qn mode]!");
        }
        try {
            // Reads in /random or /normal
            startQnMode = commandDetails[2].split(" ")[0].strip();
            if (!startQnMode.equals("random") && !startQnMode.equals("normal")) {
                throw new IllegalArgumentException("    Question mode must be either 'random' or 'normal'");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("    Please format your input as start /[quiz mode] [start details] /[qn mode]!");
        }finally {
            if (startQnMode.isEmpty()) {
                System.out.println("    Please format your input as start /[quiz mode] [start details] /[qn mode]!");
            }
        }
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
            matchedQuestions = questions.categoriseListByModule(startDetails);
            break;
        case "all":
            matchedQuestions = questions.getAllQns();
            break;
        default:
            System.out.println("    Please enter a valid quiz mode :<");
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
            System.out.println("    Please enter a valid quiz mode :<");
            return;
        }
    }
}
